package Ferrovia;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import Entidades.Vagao;
import Repositorio.*;

import java.sql.Connection;
import java.sql.ResultSet;
		
public class Ferrovia {

	public static void main (String args[]){
		
		Factory f = new Factory();
		
		Vagao v = f.getVagao();

		
		/*
		 * BATATA DO GIRO TA ASSANDO!
		 */
		
		v.setBitola(Vagao.Bitola.A);
		
		v.setSubTipo(Vagao.SubTipo.D);
		
		System.out.println(v.getSubTipo());
		
		/*
		 ArrayList<Locomotiva> l = new ArrayList<Locomotiva>();
		 
		ArrayList<Vagao> v = new ArrayList<Vagao>();
		char[] a = {'t','i','a','g','o'};
		char[] b = {'t','h','i','a','g','o'};
		
		l.add(new Locomotiva(1234567));
		v.add(new Vagao(a));
		v.add(new Vagao(b));
		
		Composicao c = new Composicao();
		c.setCodigo("compThiago");
		c.setLocomotivas(l);
		c.setVagoes(v);
		
		Controller control = new Controller();
		control.connect();
		String sql = control.create(c);
		
		System.out.println(sql);
		
	}
}
	*/	
		/*
		//recebe a classe com o método de conexao
		Connection conn = null;
			
		//responsavel por receber os resutlados do sql
		ResultSet rs = null;
		
		//responsavel por receber os comandos SQL e executa-los no banco
		Statement stmt = null;
		
		Scanner scan = new Scanner(System.in);
		
		int resp= 0;
		int cont = 0;
		String sql= "";
		
		try {
			
			conn = Conexao.getInstance().On();
			
			stmt = conn.createStatement();
			
			do{
				sql = ""; cont = 0;
				
				System.out.println("Informe a operação (0=Finalizar | 1= Criar Tabela | 2= Criar Cliente | 3= EXIBIR CLIENTES | 4= APAGAR TABELA ):");
				resp = scan.nextInt();
				
				switch(resp){
					case 1:
						sql = "CREATE TABLE CLIENTE ( ID INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY, NOME VARCHAR(30))";
						cont = stmt.executeUpdate(sql);
						System.out.println("Tabela CLIENTE criada " + cont);
					break;	
					case 2:
						System.out.print("NOME CLIENTE: ");
						String nome = scan.next();
						sql = "INSERT INTO CLIENTE (NOME) VALUES ('" + nome + "')";
						cont = stmt.executeUpdate(sql);
						System.out.println("Registro inserido " + cont);
					break;
					case 3:
						sql = "SELECT * FROM CLIENTE";
						rs = stmt.executeQuery(sql);
						
						while(rs.next()){						
							System.out.println("ID: " + rs.getInt("ID") + " NOME: " + rs.getString("NOME"));
						}				
					break;
					case 4:
						sql = "DROP TABLE CLIENTE";
						cont = stmt.executeUpdate(sql);
						System.out.println("Tabela CLIENTE apagada " + cont);
					break;
					case 5:
						sql = "CREATE DATABASE TESTE; USE TESTE;";
						cont = stmt.executeUpdate(sql);
						System.out.println("database apagado " + cont);
					break;
				}
				
			}while(resp!=0);
			
			rs.close();
			stmt.close();
			conn.close();
			
		}catch(Exception e){
			System.err.print(e.getMessage());
		}	
		
		Conexao.getInstance().Off();
		System.exit(0);
		
		*/
	}
}	