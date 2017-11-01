package Ferrovia;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao{
	
	//instancia da minha propria classe
	private static Conexao instance = null;
	
	//conexão com o banco
	private Connection conn = null;
	
	//Informar o local do banco de dados, e o cria
	private static final String PATH = "jdbc:derby:ferrovia;create=true";
	//Usuário do banco
	private static final String USER = "app";
	//Senha do uśuario do banco
	private static final String PASS = "app";
	
	//responsavel por instanciar a classe
	private Conexao(){
		try{
			conn = DriverManager.getConnection(PATH, USER, PASS);
		}catch(Exception e){
			throw new RuntimeException("Erro ao carregar o Driver " + e.getMessage());
		}
	}
	
	//responsavel por verificar se existe a instancia da classe, e caos não tenha, chama  o constructor
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
	
	//responsavel por devolver a conexão com o banco
	public Connection On(){
		if(conn == null){
			throw new RuntimeException("Conexão não realizada");
		}
		return conn;
	} 
	
	//responsavel por desligar a conexão e encerrar a instancia
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
