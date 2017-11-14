package Repositorio;

import java.sql.Connection;
import java.sql.DriverManager;

/** Realiza a conexao com o banco de dados
 * 	@author GGTRangers
 */
public class Conexao{	
	
	/** Guarda a instancia da propria classe
	 * 
	 */
	private static Conexao instance = null;
	
	/** Guarda a conexão com o banco
	 */
	private static Connection conn = null;
	
	/** Informa o local do banco de dados, nome e o parâmetro para criar caso nao exista
	 */
	private static final String PATH = "jdbc:derby:ferrovia;create=true";
	
	/** Usuario do banco
	 */
	private static final String USER = "app";
	
	/** Senha do usuario do banco
	 */
	private static final String PASS = "app";
	
	/** Realiza a conexao com o banco de dados
	 * @param PATH Caminho onde esta o banco de dados
	 * @param USER Usuário do banco de dados
	 * @param PASS Senha do usuario
	 * @see DriverManager
	 */
	private Conexao(){
		try{
			conn = DriverManager.getConnection(PATH, USER, PASS);
		}catch(Exception e){
			throw new RuntimeException("Erro ao carregar o Driver " + e.getMessage());
		}
	}
	
	/** Responsavel por realizar a instancia da classe ou devolver a instancia caso ja exista
	 * @return Conexao Retorna a conexao com o banco de dados
	 */
	public static Conexao getInstance(){
		try{
			if(instance == null){
				instance = new Conexao();
			}
			return instance;
		}catch(Exception e){
			throw new RuntimeException("Erro ao pegar a instancia " + e.getMessage());
		}		
		
	}
	
	/** Responsavel por garantir somente uma conexao com o banco
	 * @return Connection Contem a conexao com o banco
	 */
	public Connection On(){
		if(conn == null){
			throw new RuntimeException("Conexão não realizada");
		}
		return conn;
	} 
	
	/** Responsavel por desligar a conexao com o banco e encerrar a instancia da classe 
	 */
	public void Off(){
		try{
			//encerra a conexão com o banco
			conn.close();
			conn = null;
			instance = null;
		}catch(Exception e){
			throw new RuntimeException("Erro ao encerrar conexão" + e.getMessage());
		}
	}	
}
