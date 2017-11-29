package Telas.Vagao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.*;

import Entidades.Vagao;
import Repositorio.Controller;
import Repositorio.Factory;
import Repositorio.FactoryLayout;
import Telas.Comum.Confirm;
import Telas.Interface.ITelas;

public class ListarVagao extends JFrame implements ITelas{
	
	private JPanel painelFundo;
	private JTable tabela;
	private JScrollPane barraRolagem;
	
	private VagaoTableModel modelo;
	private Vagao vagaoEditar;
	
	private FactoryLayout tela = new FactoryLayout();
	private Factory f = new Factory();
	private Controller c = f.getController();
	
	List<Vagao> lista;
	String[] colunasVagao = new String[]{"Identifica��o", "Bitola", "Comprimento", "Tipo", "Subtipo", "Propriet�rio"};
	
	public ListarVagao() {
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
        tabela.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                	vagaoEditar = new Vagao((String) tabela.getValueAt(tabela.getSelectedRow(), 0), (Double) tabela.getValueAt(tabela.getSelectedRow(), 2));
                	editarVagao(vagaoEditar);
                }
            }
            public void mousePressed(MouseEvent e) { /* System.out.println("Mouse pressed"); */ }
            public void mouseReleased(MouseEvent e) { /* System.out.println("Mouse Released"); */ }
            public void mouseEntered(MouseEvent e) { /* System.out.println("Mouse Entered"); */ }
            public void mouseExited(MouseEvent e) { /* System.out.println("Mouse exited"); */ }
        });
        
        tabela.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					vagaoEditar = new Vagao((String) tabela.getValueAt(tabela.getSelectedRow(), 0), (Double) tabela.getValueAt(tabela.getSelectedRow(), 2));
                	editarVagao(vagaoEditar);
				}
				if(e.getKeyCode() == KeyEvent.VK_DELETE){
					int confirm = tela.openConfirm("Tem certeza que deseja excluir o vag�o?");
					if(confirm == 0){
						tela.openAlertInfo("", "Exclu�do com sucesso.");
						tela.openAlertWarning("", "SQN");
					    c.connect();
					    //c.
					}
				}
				
			}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
        	
        });
        
        pesquisar();
    }
	
	protected void editarVagao(Vagao vagaoEditar) {
		System.out.println(vagaoEditar);
	}

	// M�todo respons�vel por listar os dados do vag�o e jogar na tabela
	private void pesquisar() {
		try{
			c.connect();
	        lista = c.selectVagoes();
	        modelo = new VagaoTableModel(lista, colunasVagao);
	        tabela.setModel(modelo);
		}
		catch(Exception e){
			tela.openAlertError("ERRO LISTAR VAG�ES", "Ocorreu um erro ao listar os vag�es: " + e.getMessage());
		}	
		finally{			
			//c.disconnect();
		}
    }
	
	public JPanel GetPanel(){
		return this.painelFundo;
	}
	
}
