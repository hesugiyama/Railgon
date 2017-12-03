package Telas.Composicao;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import Entidades.Composicao;
import Telas.Interface.ITelas;

import Repositorio.Controller;
import Repositorio.Factory;
import Repositorio.FactoryLayout;

public class ListarComposicao extends JFrame implements ITelas<ComposicaoTableModel>{
	
	private JPanel painelFundo;
	private JTable tabela;
	private JScrollPane barraRolagem;
	
	private ComposicaoTableModel modelo;
	
	private FactoryLayout tela = new FactoryLayout();
    private Factory f = new Factory();
    private Controller c = f.getController();
	
	List<Composicao> lista;
	private String[] colunas = new String[]{ "C�digo", "Descri��o", "# Locomotiva", "# Vag�es", "Peso M�ximo", "Peso Atual"};
	
	public ListarComposicao() {
		criaJTable();
		criaJanela();
	}

	// Método responsável por criar a tela
		public void criaJanela(){
			barraRolagem = new JScrollPane(tabela);
			painelFundo = new JPanel();
			painelFundo.setLayout(new BorderLayout());
			painelFundo.add(BorderLayout.CENTER, barraRolagem);
			getContentPane().add(painelFundo);
		}
		
		// Método responsável por criar a tabela e chamar o método que lista os dados
		private void criaJTable() {
	        tabela = new JTable(modelo);
	        pesquisar();
	    }
		
		// Método responsável por listar os dados do vagão e jogar na tabela
		private void pesquisar() {
			try{
				c.connect();
		        lista = c.selectComposicoes();
		        modelo = new ComposicaoTableModel(lista, colunas);
		        tabela.setModel(modelo);
			}
	        catch(Exception e){
	        	tela.openAlertError("ERRO LISTAR COMPOSIÇÕES", "Ocorreu um erro ao listar as composições: " + e.getMessage());
	        }
			finally{
				//c.desconnect();
			}
	    }
		
		public JPanel GetPanel(){
			return this.painelFundo;
		}
		
		public ComposicaoTableModel GetModelo(){
			return this.modelo;
		}
	
}