package Telas.Locomotiva;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import Entidades.Locomotiva;
import Entidades.Vagao;
import Repositorio.Controller;
import Repositorio.Factory;
import Repositorio.FactoryLayout;
import Telas.Interface.ITelas;
import Telas.Vagao.VagaoTableModel;

public class ListarLocomotiva extends JFrame implements ITelas{
	
	private JPanel painelFundo;

	private JTable tabela;
	private JScrollPane barraRolagem;
	private LocomotivaTableModel modelo;
	
	private FactoryLayout tela = new FactoryLayout();
    private Factory f = new Factory();
    private Controller c = f.getController();
	
	List<Locomotiva> lista;
	private String[] colunas = new String[]{ "Classe", "Descricao", "Bitola", "Comprimento"};
	
	public ListarLocomotiva() {
		criaJTable();
		criaJanela();
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
	        lista = c.selectLocomotivas();
	        modelo = new LocomotivaTableModel(lista, colunas);
	        tabela.setModel(modelo);
		}
        catch(Exception e){
        	tela.openAlertError("ERRO LISTAR LOCOMOTIVAS", "Ocorreu um erro ao listar as locomotivas: " + e.getMessage());
        }
		finally{
			//c.desconnect();
		}
    }
	
	public JPanel GetPanel(){
		return this.painelFundo;
	}

}