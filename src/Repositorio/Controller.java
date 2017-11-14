package Repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
			String sql = "INSERT INTO VAGAO (ID,TIPO,SUBTIPO,IDENTIFICACAO, PROPRIETARIO, BITOLA, COMPRIMENTO) VALUES ('?','?','?','?','?','?','?','?')";
			
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, v.getId());
		pstmt.setString(2, String.valueOf(v.getTipo()));
		pstmt.setString(3, String.valueOf(v.getSubTipo()));
		pstmt.setString(4, v.getIdentificacao().toString());
		pstmt.setString(5, String.valueOf(v.getProprietario()));
		pstmt.setString(6, String.valueOf(v.getBitola()));
		pstmt.setDouble(7, v.getComprimento());
		pstmt.executeUpdate();
		
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/** Responsavel por inserir uma Locomotica no banco de dados
	 * @param l A locomotiva que deve ser inserida no banco
	 */
	public void create(Locomotiva l) {
		try {
			String sql= "INSERT INTO LOCOMOTIVA (ID, BITOLA, COMPRIMENTO,CLASSE, DESCRICAO) VALUES ('?','?','?','?','?')";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, l.getId());
			pstmt.setString(2, String.valueOf(l.getBitola()));
			pstmt.setDouble(3, l.getComprimento());
			pstmt.setInt(4, l.getClasse());
			pstmt.setString(5, l.getDescricao());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	/** Responsavel por inserir uma Composicao no banco de dados
	 * @param c A composicao que deve ser inserida no banco
	 */
	public void create(Composicao c) {
		try {
			String sqlC  = "INSERT INTO COMPOSICAO (ID, QTDVAGAO, QTDLOCOMOTIVA, COMPRIMENTO) VALUES ('?','?','?','?')";
			String sqlVF = "INSERT INTO COMPOSICAO_VEICULOFERROVIARIO(CODCOMPOSICAO,CODLOCOMOTIVA, CODVAGAO) VALUES ('?','?','?')";
			
			pstmt = conn.prepareStatement(sqlC);
			pstmt.setString(1,c.getCodigo());
			pstmt.setInt(2,c.getQtdVagao());
			pstmt.setInt(3,c.getQtdLocomotiva());
			pstmt.setDouble(4,c.getComprimento());
			pstmt.executeUpdate();
			
			for(int i=0; i< c.getVagoes().size()-1;i++){
				pstmt = conn.prepareStatement(sqlVF);
				pstmt.setString(1, c.getCodigo());
				pstmt.setString(2, String.valueOf(c.getVagoes().get(i).getNumero()));
				pstmt.executeUpdate();
			}
			
			for(int i=0; i< c.getLocomotivas().size()-1;i++){
				pstmt = conn.prepareStatement(sqlC);
				pstmt.setString(1, c.getCodigo());
				pstmt.setInt(2, c.getLocomotivas().get(i).getClasse());
				pstmt.executeUpdate();
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
