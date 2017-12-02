package Telas.Composicao;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import Entidades.Composicao;
import Entidades.Locomotiva;
import Repositorio.Controller;
import Repositorio.Factory;
import Repositorio.FactoryLayout;
import Telas.Interface.ITelas;
import Telas.Locomotiva.LocomotivaTableModel;

public class ListarComposicao extends JFrame implements ITelas{
	
	private JPanel painelFundo;
	private JTable tabela;
	private JScrollPane barraRolagem;
	private ComposicaoTableModel modelo;
	
	private FactoryLayout tela = new FactoryLayout();
    private Factory f = new Factory();
    private Controller c = f.getController();
	
	List<Composicao> lista;
	private String[] colunas = new String[]{ "Código", "Descricao", "# Locomotiva", "# Vagões", "Peso Máximo", "Peso Atual"};
	
	public ListarComposicao() {
		criaJanela();
		criaJTable();
	}

	// M�todo respons�vel por criar a tela
		public void criaJanela(){
			barraRolagem = new JScrollPane(tabela);
			painelFundo = new JPanel();
			painelFundo.setLayout(new BorderLayout());
			painelFundo.add(BorderLayout.CENTER, barraRolagem);
			getContentPane().add(painelFundo);
		}
		
		// M�todo respons�vel por criar a tabela e chamar o m�todo que lista os dados
		private void criaJTable() {
	        tabela = new JTable(modelo);
	        pesquisar();
	    }
		
		// M�todo respons�vel por listar os dados do vag�o e jogar na tabela
		private void pesquisar() {
			try{
				c.connect();
		        lista = c.selectComposicoes();
		        modelo = new ComposicaoTableModel(lista, colunas);
		        tabela.setModel(modelo);
			}
	        catch(Exception e){
	        	tela.openAlertError("ERRO LISTAR COMPOSI��ES", "Ocorreu um erro ao listar as composi��es: " + e.getMessage());
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