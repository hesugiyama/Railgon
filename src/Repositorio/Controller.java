package Repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;

import Entidades.Vagao;
import Entidades.Locomotiva;
import Entidades.Composicao;

/** Responsavel por realizar as operacoes com o banco de dados
 * @author GGTRangers
 */
public class Controller {

	/* Responsavel por guardar a conexao com o banco
	 */
	private Connection conn = null;

	/** Responsavel por receber os resutlados das consultas
	 */
	private ResultSet rs = null;

	/** Responsavel por receber os comandos SQL sem parameros e executa-los no banco
	 */
	private Statement stmt = null;
	
	/** Responsavel por receber os comandos SQL com parametros e executa-los no banco 
	 * utilizada para preparar o sql 
	 */
	private PreparedStatement pstmt = null;

	/* Realiza a conexao com o banco
	 */
	public void connect() {
		try {
			conn = Conexao.getInstance().On();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	/** Responsavel por inserir um Vagao no banco de dados
	 * @param v O vagao que deve ser inserido no banco
	 */
	public void create(Vagao v) {
		try {
			String sql = "INSERT INTO VAGAO (TIPO,SUBTIPO,IDENTIFICACAO, PROPRIETARIO, BITOLA, COMPRIMENTO) VALUES (?,?,?,?,?,?)";
			
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, String.valueOf(v.getTipo()));
		pstmt.setString(2, String.valueOf(v.getSubTipo()));
		pstmt.setString(3, v.getIdentificacao().toString());
		pstmt.setString(4, String.valueOf(v.getProprietario()));
		pstmt.setString(5, String.valueOf(v.getBitola()));
		pstmt.setDouble(6, v.getComprimento());
		pstmt.executeUpdate();
		
		} catch (Exception e) {
			throw new RuntimeException("NÃO FOI POSSIVEL CRIAR O VAGÃO: " + v.getIdentificacao().toString() + e.getMessage());
		}
	}

	/** Responsavel por inserir uma Locomotiva no banco de dados
	 * @param l A locomotiva que deve ser inserida no banco
	 */
	public void create(Locomotiva l) {
		try {
			String sql= "INSERT INTO LOCOMOTIVA (BITOLA, COMPRIMENTO,CLASSE, DESCRICAO, PESOMAXIMO) VALUES (?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, String.valueOf(l.getBitola()));
			pstmt.setDouble(2, l.getComprimento());
			pstmt.setInt(3, l.getClasse());
			pstmt.setString(4, l.getDescricao());
			pstmt.setDouble(5, l.getPesoMax());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException("NÃO FOI POSSIVEL CRIAR A LOCOMOTIVA " + l.getClasse() + e.getMessage());
		}

	}

	/** Responsavel por inserir uma Composicao no banco de dados
	 * @param c A composicao que deve ser inserida no banco
	 */
	public void create(Composicao c) {
		try {
			String SQLid = "SELECT IDENTITY_VAL_LOCAL() FROM COMPOSICAO";
			String sqlC  = "INSERT INTO COMPOSICAO (DESCRICAO, BITOLA, QTDVAGAO, QTDLOCOMOTIVA, COMPRIMENTO) VALUES (?,?,?,?,?)";
			String sqlV  = "INSERT INTO COMPOSICAO_VAGAO(CODCOMPOSICAO, CODVAGAO,ORDEM) VALUES (?,?,?)";
			String sqlL  = "INSERT INTO COMPOSICAO_LOCOMOTIVA(CODCOMPOSICAO,CODLOCOMOTIVA,ORDEM) VALUES (?,?,?)";

			double cod = 0.0;
			
			pstmt = conn.prepareStatement(sqlC);
			pstmt.setString(1,c.getDescricao());
			pstmt.setString(2,String.valueOf(c.getBitola()));
			pstmt.setInt(3,c.getQtdVagao());
			pstmt.setInt(4,c.getQtdLocomotiva());
			pstmt.setDouble(5,c.getComprimento());
			pstmt.executeUpdate();			
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQLid);
			while(rs.next()){
				cod = rs.getDouble(1);
			}
			
			for(int i=0; i < c.getVagoes().size();i++){
				pstmt = conn.prepareStatement(sqlV);
				pstmt.setInt(1, (int) cod);
				pstmt.setString(2, String.valueOf(c.getVagoes().get(i).getIdentificacao()));
				pstmt.setInt(3,i);
				pstmt.executeUpdate();
			}
			
			for(int i=0; i < c.getLocomotivas().size();i++){
				pstmt = conn.prepareStatement(sqlL);
				pstmt.setInt(1, (int) cod);
				pstmt.setInt(2, c.getLocomotivas().get(i).getClasse());
				pstmt.setInt(3, i);
				pstmt.executeUpdate();
			}			
		} catch (Exception e) {
			throw new RuntimeException("NÃO FOI POSSIVEL CRIAR A COMPOSICAO: " + c.getCodigo() + e.getMessage());
		}
	}
	
	/** Responsavel por exibir todos os vagoes
	 * @return
	 */
	public ArrayList<Vagao> selectVagoes(){
		try{
			ArrayList<Vagao> vagoes = new ArrayList<>();
			String sql = "SELECT IDENTIFICACAO,COMPRIMENTO FROM VAGAO";
			Factory f = new Factory();		
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				vagoes.add(f.getVagao(rs.getString(1), rs.getDouble(2)));
			}
			return vagoes;
		}
		catch(Exception e){
			throw new RuntimeException("Não foi possivel resgatar os vagoes do banco! " + e.getMessage());
		}
	}
	
	/** Responsavel por exibir todos os vagoes
	 * @return
	 */
	public ArrayList<Locomotiva> selectLocomotivas(){
		try{
			ArrayList<Locomotiva> locomotivas = new ArrayList<>();
			String sql = "SELECT BITOLA, CLASSE, DESCRICAO, COMPRIMENTO, PESOMAXIMO FROM LOCOMOTIVA";
			Factory f = new Factory();		
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				locomotivas.add(f.getLocomotiva(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getDouble(4),rs.getDouble(5)));
			}
			return locomotivas;
		}
		catch(Exception e){
			throw new RuntimeException("Não foi possivel resgatar os vagoes do banco! " + e.getMessage());
		}
	}	
	
	/** Responsavel por obter um Vagao do banco a partir da sua identificacao 
	 * @param identificacao
	 * @return Retorna um Vagao preenchido com dados do banco 
	 */
	public Vagao selectVagao(String identificacao){
		try{
			Vagao v = null;
			String sql = "SELECT IDENTIFICACAO,COMPRIMENTO FROM VAGAO WHERE IDENTIFICACAO = ? ";
			Factory f = new Factory();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, identificacao);
			rs = pstmt.executeQuery();
			while(rs.next()){
				v = f.getVagao(rs.getString(1), rs.getDouble(2));
			}
			return 	v;
		}
		catch(Exception e){
			throw new RuntimeException("Não foi possivel resgatar os vagoes do banco! " + e.getMessage());
		}		
	}

	/** Responsavel por obter uma Locomotiva do banco de dados
	 * @param classe
	 * @return Retorna uma Locomotiva preenchida com os valores da base de dados
	 */
	public Locomotiva selectLocomotiva(String classe){
		try{
			String sql = "SELECT BITOLA, CLASSE, DESCRICAO, COMPRIMENTO, PESOMAXIMO FROM LOCOMOTIVA WHERE CLASSE = ?";
			Factory f = new Factory();
			Locomotiva l = null;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, classe);
			rs = pstmt.executeQuery();
			while(rs.next()){
				l = f.getLocomotiva(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getDouble(4),rs.getDouble(5));
			}
			return l;
		}
		catch(Exception e){
			throw new RuntimeException("Não foi possivel resgatar os vagoes do banco! " + e.getMessage());
		}		
	}
	
	/**
	 * 
	 * @return
	 */
	/*
	
	public Composicao selectComposicao() {
		

	}
	*/
	
	public void teste(){
		try{
			String SQL = "DELETE FROM COMPOSICAO";
			
			stmt = conn.createStatement();
			stmt.executeUpdate(SQL);
			
			/*
			 String teste1 = "DROP TABLE COMPOSICAO_LOCOMOTIVA";
			String teste2 = "DROP TABLE COMPOSICAO_VAGAO";
			String teste3 = "DROP TABLE COMPOSICAO";
			
			String DDLComposicao = "CREATE TABLE COMPOSICAO (ID INT GENERATED ALWAYS AS IDENTITY, DESCRICAO VARCHAR(200) NOT NULL, BITOLA CHAR NOT NULL, QTDLOCOMOTIVA INT NOT NULL, QTDVAGAO INT DEFAULT 0, COMPRIMENTO DOUBLE NOT NULL, PRIMARY KEY(ID), FOREIGN KEY (BITOLA) REFERENCES BITOLA(NOME))";
			String DDLComposicaoVagao = "CREATE TABLE COMPOSICAO_VAGAO (CODCOMPOSICAO INT NOT NULL, CODVAGAO VARCHAR(9) NOT NULL, ORDEM INT NOT NULL, PRIMARY KEY(CODCOMPOSICAO, CODVAGAO), FOREIGN KEY (CODVAGAO) REFERENCES VAGAO(IDENTIFICACAO), FOREIGN KEY (CODCOMPOSICAO) REFERENCES COMPOSICAO(ID))";
			String DDLComposicaoLocomotiva = "CREATE TABLE COMPOSICAO_LOCOMOTIVA (CODCOMPOSICAO INT NOT NULL, CODLOCOMOTIVA INT NOT NULL, ORDEM INT NOT NULL, PRIMARY KEY(CODCOMPOSICAO, CODLOCOMOTIVA), FOREIGN KEY (CODLOCOMOTIVA) REFERENCES LOCOMOTIVA(CLASSE), FOREIGN KEY (CODCOMPOSICAO) REFERENCES COMPOSICAO(ID))";
			
			//prepara a query para executar um comando fixo
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(teste1);
			stmt.executeUpdate(teste2);
			stmt.executeUpdate(teste3);
			stmt.executeUpdate(DDLComposicao);
			stmt.executeUpdate(DDLComposicaoVagao);
			stmt.executeUpdate(DDLComposicaoLocomotiva);
			*/			
		}
		catch(Exception e){
			throw new RuntimeException("Não foi possivel resgatar os vagoes do banco! " + e.getMessage());
		}
	}
}
