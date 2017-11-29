package Telas.Locomotiva;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.*;

import Entidades.Locomotiva;
import Entidades.Vagao;
import Repositorio.Controller;
import Repositorio.Factory;
import Repositorio.FactoryLayout;
import Telas.Comum.Confirm;
import Telas.Interface.ITelas;
import Telas.Vagao.VagaoTableModel;


public class ListarLocomotiva extends JFrame implements ITelas{
	
	private JPanel painelFundo;
	private JTable tabela;
	private JScrollPane barraRolagem;
	
	private LocomotivaTableModel modelo;
	private Locomotiva locomotivaEditar;
	
	private FactoryLayout tela = new FactoryLayout();
    private Factory f = new Factory();
    private Controller c = f.getController();
	
	List<Locomotiva> lista;
	private String[] colunas = new String[]{ "Classe", "Descricao", "Bitola", "Comprimento", "Peso Máximo"};
	
	public ListarLocomotiva() {
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
        tabela.addMouseListener(new MouseListener() {
        	public void mouseClicked(MouseEvent e) {
        		locomotivaEditar = f.getLocomotiva(tabela.getValueAt(tabela.getSelectedRow(),2).toString(),
						   (Integer) tabela.getValueAt(tabela.getSelectedRow(), 0),
						   (String) tabela.getValueAt(tabela.getSelectedRow(), 1),
						   (Double) tabela.getValueAt(tabela.getSelectedRow(), 3),  
						   (Double) tabela.getValueAt(tabela.getSelectedRow(), 4));
        		if(e.getClickCount() == 2) {
        			EditarLocomotiva(locomotivaEditar);
        		}
        	}
        	public void mousePressed(MouseEvent e) { /* System.out.println("Mouse pressed"); */ }
            public void mouseReleased(MouseEvent e) { /* System.out.println("Mouse Released"); */ }
            public void mouseEntered(MouseEvent e) { /* System.out.println("Mouse Entered"); */ }
            public void mouseExited(MouseEvent e) { /* System.out.println("Mouse exited"); */ }
        });
		tabela.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {
				locomotivaEditar = f.getLocomotiva(tabela.getValueAt(tabela.getSelectedRow(),2).toString(),
						   (Integer) tabela.getValueAt(tabela.getSelectedRow(), 0),
						   (String) tabela.getValueAt(tabela.getSelectedRow(), 1),
						   (Double) tabela.getValueAt(tabela.getSelectedRow(), 3),  
						   (Double) tabela.getValueAt(tabela.getSelectedRow(), 4));
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					EditarLocomotiva(locomotivaEditar);
				}
				if(e.getKeyCode() == KeyEvent.VK_DELETE){
					int confirm = tela.openConfirm("Tem certeza que deseja excluir o locomotiva?");
					if(confirm == 0){
						try {
							c.connect();
							//c.remove(locomotivaEditar);
							tela.openAlertInfo("", "Excluído com sucesso.");
							//c.disconnect();
						} catch (Exception e2) {
							tela.openAlertError("ERRO AO EXCLUIR LOCOMOTIVA", e2.getMessage());
						}
					}
				}
				
			}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
        	
        });
        
        pesquisar();
    }
	
	protected void EditarLocomotiva(Locomotiva locomotivaEditar) {
		System.out.println(locomotivaEditar);
	}
	
	// Método responsável por listar os dados do vagão e jogar na tabela
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