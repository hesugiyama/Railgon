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

	/* Realiza a conexao com o banco
	 */
	public void connect() {
		try {
			conn = Conexao.getInstance().On();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/** Responsavel por disconectar do banco e matar todas as propriedades
	 * 
	 */
	public void disconnect() {
		Conexao.getInstance().Off();
		conn = null;
		System.gc();
	}

	/** Responsavel por inserir um Vagao no banco de dados
	 * @param v O vagao que deve ser inserido no banco
	 */
	public void create(Vagao v) {
		
		try {
			String sql = "INSERT INTO VAGAO (TIPO,SUBTIPO,IDENTIFICACAO, PROPRIETARIO, BITOLA, COMPRIMENTO) VALUES (?,?,?,?,?,?)";
			
			PreparedStatement pstmt;
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
			PreparedStatement pstmt;
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
			PreparedStatement pstmt;
			Statement stmt;
			ResultSet rs;
			
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
	 * @return Retorna um Arraylist com todos os Vagoes
	 */
	public ArrayList<Vagao> selectVagoes(){
		try{
			Statement stmt;
			ResultSet rs;
			
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
	 * @return Retorna um Arraylist com todas as locomotivas
	 */
	public ArrayList<Locomotiva> selectLocomotivas(){
		try{
			Statement stmt;
			ResultSet rs;
			
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
	
	/** Responsavel por exibir todas as composições
	 * @return Retorna um Arraylist com todas as composições
	 */
	public ArrayList<Composicao> selectComposicoes(){
		try{
			Statement stmt;
			ResultSet rs;
			
			String sqlC = "SELECT ID FROM COMPOSICAO";
			ArrayList<Composicao> composicao = new ArrayList<>();
			
			//tentando recuperar as composicoes do banco
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sqlC);
			while(rs.next()){
				composicao.add(selectComposicao(rs.getInt(1)));
			}
			
			return composicao;
		}catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/** Responsavel por obter um Vagao do banco a partir da sua identificacao 
	 * @param identificacao
	 * @return Retorna um Vagao preenchido com dados do banco 
	 */
	public Vagao selectVagao(String identificacao){
		try{
			PreparedStatement pstmt;
			ResultSet rs;
			
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
	public Locomotiva selectLocomotiva(int classe){
		try{
			PreparedStatement pstmt;
			ResultSet rs;
			
			String sql = "SELECT BITOLA, CLASSE, DESCRICAO, COMPRIMENTO, PESOMAXIMO FROM LOCOMOTIVA WHERE CLASSE = ?";
			Factory f = new Factory();
			Locomotiva l = null;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classe);
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

	/** Responsavel por obter uma Composicao do banco de dados
	 * @param codigo
	 * @return Retorna uma Composicao preenchida com os valores da base de dados
	 */
	public Composicao selectComposicao(int codigo){
		try{
			
			PreparedStatement pstmt;
			ResultSet rs;
			
			Composicao c = null;
			ArrayList<Locomotiva> locomotiva = new ArrayList<>();
			ArrayList<Vagao> vagao 			 = new ArrayList<>();
			
			Factory f = new Factory();
			
			String sqlL = "SELECT L.ID, L.BITOLA, L.CLASSE, L.DESCRICAO, L.COMPRIMENTO, L.PESOMAXIMO, CL.ORDEM FROM LOCOMOTIVA L INNER JOIN COMPOSICAO_LOCOMOTIVA CL ON CL.CODLOCOMOTIVA = L.CLASSE WHERE CL.CODCOMPOSICAO = ?";
			String sqlV = "SELECT V.ID, V.IDENTIFICACAO, V.COMPRIMENTO, CV.ORDEM FROM VAGAO V INNER JOIN COMPOSICAO_VAGAO CV ON CV.CODVAGAO = V.IDENTIFICACAO WHERE CV.CODCOMPOSICAO = ?";
			String sqlC = "SELECT ID, DESCRICAO FROM COMPOSICAO WHERE ID = ?";
			
			//tentando recuperar as locomotivas da composicao do banco
			try{
				pstmt = conn.prepareStatement(sqlL);
				pstmt.setInt(1, codigo);
				rs = pstmt.executeQuery();
				while(rs.next()){
					Locomotiva l = f.getLocomotiva(rs.getString(2), rs.getInt(3), rs.getString(4), rs.getDouble(5), rs.getDouble(6));
					l.setId(rs.getInt(1));
					l.setOrdemComposicao(rs.getInt(7));
					locomotiva.add(l);
				}
			}catch(Exception e){
				throw new RuntimeException(e.getMessage());
			}
			
			//tentando recuperar os vagoes da composicao do banco
			try{
				pstmt = conn.prepareStatement(sqlV);
				pstmt.setInt(1, codigo);
				rs = pstmt.executeQuery();
				while(rs.next()){
					Vagao v = f.getVagao(rs.getString(2), rs.getDouble(3));
					v.setId(rs.getInt(1));
					v.setOrdemComposicao(rs.getInt(4));
					vagao.add(v);
				}
			}catch(Exception e){
				throw new RuntimeException(e.getMessage());
			}
			
			//buscando valores da composicao do banco
			try{
				pstmt = conn.prepareStatement(sqlC);
				pstmt.setInt(1, codigo);
				rs = pstmt.executeQuery();
				while(rs.next()){
					c = f.getComposicao(locomotiva, vagao, rs.getString(2));
					c.setCodigo(rs.getInt(1));
				}
			}catch(Exception e){
				throw new RuntimeException(e.getMessage());
			}
			
			if(c == null){
				throw new RuntimeException("A Composição não existe!");
			}
			
			return c;
			
		}catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/** Responsavel por atualizar os dados do Vagao
	 * @param v
	 * @return 1 SE EXECUTOU COM SUCESSO E 0 SE NÃO EXECUTOU
	 */
	public int update(Vagao v){
		try {
			
			PreparedStatement pstmt;
			
			String sql = "UPDATE VAGAO SET COMPRIMENTO = ? WHERE IDENTIFICACAO = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, v.getComprimento());
			pstmt.setString(2, v.getIdentificacao());
			return pstmt.executeUpdate();
		
		} catch (Exception e) {
			throw new RuntimeException("NÃO FOI POSSIVEL ATUALIZAR O VAGÃO\n" + e.getMessage());
		}
	}

	/** Responsavel por atualizar os dados do vagao
	 * @param l
	 * @return 1 SE EXECUTOU COM SUCESSO E 0 SE NÃO EXECUTOU
	 */
	public int update(Locomotiva l){
		try {
			
			PreparedStatement pstmt;
			
			String sql = "UPDATE LOCOMOTIVA SET BITOLA = ?, COMPRIMENTO = ?, DESCRICAO = ?, PESOMAXIMO = ? WHERE CLASSE = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, String.valueOf(l.getBitola()));
			pstmt.setDouble(2, l.getComprimento());
			pstmt.setString(3, l.getDescricao());
			pstmt.setDouble(4, l.getPesoMax());
			pstmt.setInt(5, l.getClasse());
			return pstmt.executeUpdate();
		
		} catch (Exception e) {
			throw new RuntimeException("NÃO FOI POSSIVEL ATUALIZAR A LOCOMOTIVA!\n" + e.getMessage());
		}
	}
	
	/* UPDATE COMPOSICAO - TAMANHO DA TRETA
	 * 
	 * ATUALIZAR DADOS
	 * SE TIVER MAIS ELEMENTOS QUE NO BANCO, INSERIR
	 * SE TIVER MENOS ELEMENTOS QUE NO BANCO , APAGAR
	 */
	/*
	public int updateComposicao(Composicao c){
		
		try {
			
			String sqlC  = "UPDATE COMPOSICAO SET DESCRICAO = ?, BITOLA = ?, QTDVAGAO = ?, QTDLOCOMOTIVA = ?, COMPRIMENTO = ? WHERE ID = ? ";
			String sqlV  = "UPDATE COMPOSICAO_VAGAO SET ORDEM = ? WHERE CODCOMPOSICAO = ? AND CODVAGAO = ?";
			String sqlL  = "UPDATE COMPOSICAO_LOCOMOTIVA SET ORDEM = ? WHERE CODCOMPOSICAO = ? AND CODLOCOMOTIVA = ?";

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
		}catch (Exception e) {
			throw new RuntimeException("NÃO FOI POSSIVEL ATUALIZAR O VAGÃO: " + v.getIdentificacao() + " " + e.getMessage());
		}
	}
	*/
	
	/** Responsavel por remover o Vagão da base de dados
	 * @param v Vagão a ser removido
	 * @return 1 se removeu e 0 se não removeu
	 */
	public int remove(Vagao v){
		try {		
			PreparedStatement pstmt;
			String sql = "DELETE FROM VAGAO WHERE IDENTIFICACAO = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, v.getIdentificacao());
			return pstmt.executeUpdate();
		
		} catch (Exception e) {
			throw new RuntimeException("NÃO FOI POSSIVEL REMOVER O VAGÃO: ELE ESTÁ EM UMA COMPOSIÇÃO!");
		}
	}
	
	/** Responsavel por remover a Locomotiva da base de dados
	 * @param l Locomotiva a ser removida
	 * @return 1 se removeu e 0 se não removeu
	 */
	public int remove(Locomotiva l){
		try {		
			PreparedStatement pstmt;
			String sql = "DELETE FROM LOCOMOTIVA WHERE CLASSE = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, l.getClasse());
			return pstmt.executeUpdate();
		
		} catch (Exception e) {
			throw new RuntimeException("NÃO FOI POSSIVEL REMOVER A LOCOMOTIVA: ELA ESTÁ EM UMA COMPOSIÇÃO!");
		}
	}

	/** Responsavel por remover a Composicao, mas não por apagar os seus componentes
	 * @param c Composicao
	 * @return 1 se removeu e 0 se não removeu
	 */
	public int remove(Composicao c){
		try {		
			PreparedStatement pstmt;
			String sqlC = "DELETE FROM COMPOSICAO WHERE ID = ?";
			String sqlV = "DELETE FROM COMPOSICAO_VAGAO WHERE CODCOMPOSICAO = ? AND CODVAGAO = ?";
			String sqlL = "DELETE FROM COMPOSICAO_LOCOMOTIVA WHERE CODCOMPOSICAO = ? AND CODLOCOMOTIVA = ?";
			
			int auxV = 1,
			    auxL = 1,
				auxC = 1;
			
			for(int i=0; i < c.getQtdVagao();i++){
				pstmt = conn.prepareStatement(sqlV);
				pstmt.setInt(1, c.getCodigo());
				pstmt.setString(2, c.getVagoes().get(i).getIdentificacao());
				auxV = pstmt.executeUpdate();
			}
			
			for(int i=0; i < c.getQtdLocomotiva();i++){
				pstmt = conn.prepareStatement(sqlL);
				pstmt.setInt(1, c.getCodigo());
				pstmt.setInt(2, c.getLocomotivas().get(i).getClasse());
				auxL = pstmt.executeUpdate();
			}	
			
			pstmt = conn.prepareStatement(sqlC);
			pstmt.setInt(1,c.getCodigo());
			auxC = pstmt.executeUpdate();			
			
			if(auxV+auxL+auxC == 3){
				return 1; 
			}

			throw new RuntimeException("ERRO AO APAGAR COMPOSICAO");
		
		} catch (Exception e) {
			throw new RuntimeException("NÃO FOI POSSIVEL REMOVER A LOCOMOTIVA: ELA ESTÁ EM UMA COMPOSIÇÃO!");
		}
	}
	
	public void teste(Composicao c){
		try{			
			PreparedStatement pstmt;
			ResultSet rs;
			
			String sqlC = "SELECT COUNT(*) FROM COMPOSICAO WHERE ID = ?";
			String sqlV = "SELECT COUNT(*) FROM COMPOSICAO_VAGAO WHERE CODCOMPOSICAO = ?";
			String sqlL = "SELECT COUNT(*) FROM COMPOSICAO_LOCOMOTIVA WHERE CODCOMPOSICAO = ?";
			
			pstmt = conn.prepareStatement(sqlV);
			pstmt.setInt(1, c.getCodigo());
			rs = pstmt.executeQuery();
			
			System.out.print("VAGAO: ");
			while(rs.next()){
				System.out.println(rs.getInt(1));
			}
			
			System.out.print("LOCOMOTIVA: ");
			pstmt = conn.prepareStatement(sqlL);
			pstmt.setInt(1, c.getCodigo());
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				System.out.println(rs.getInt(1));
			}
			
			System.out.print("COMPOSICAO: ");
			pstmt = conn.prepareStatement(sqlC);
			pstmt.setInt(1,c.getCodigo());
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				System.out.println(rs.getInt(1));
			}
			
		}catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}
	}
}
