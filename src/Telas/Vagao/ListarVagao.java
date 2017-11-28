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
import Telas.Interface.ITelas;

public class ListarVagao extends JFrame implements ITelas{
	
	private JPanel painelFundo;
	//private JPanel painelBotoes;
	private JTable tabela;
	private JScrollPane barraRolagem;
	private VagaoTableModel modelo;
	private String codigoLinha;
	private Vagao vagaoEditar;
	
	List<Vagao> lista;
	String[] colunasVagao = new String[]{"Id", "Bitola", "Comprimento", "Tipo", "Subtipo", "Proprietário", "Identificação"};
	
	public ListarVagao() {
		criaJTable();
		criaJanela();
	}
	
	// Método responsável por criar a tela
	public void criaJanela(){
		//painelBotoes = new JPanel();
		barraRolagem = new JScrollPane(tabela);
		painelFundo = new JPanel();
		painelFundo.setLayout(new BorderLayout());
		painelFundo.add(BorderLayout.CENTER, barraRolagem);
		//painelFundo.add(BorderLayout.SOUTH, painelBotoes);
		
		getContentPane().add(painelFundo);
	}
	
	
	// Método responsável por criar a tabela e chamar o método que lista os dados
	private void criaJTable() {
        tabela = new JTable(modelo);
 
        tabela.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                	vagaoEditar = new Vagao((String) tabela.getValueAt(tabela.getSelectedRow(), 6), (Double) tabela.getValueAt(tabela.getSelectedRow(), 2));
                	System.out.println(vagaoEditar.toString());
                }
            }
            public void mousePressed(MouseEvent e) {
            	System.out.println("Mouse pressed");
            }
            public void mouseReleased(MouseEvent e) {
            	System.out.println("Mouse Released");
            }
            public void mouseEntered(MouseEvent e) {
            	System.out.println("Mouse Entered");
            }
            public void mouseExited(MouseEvent e) {
            	System.out.println("Mouse exited");
            }
        });
        
        tabela.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					System.out.println("Enter");
				}
				if(e.getKeyCode() == KeyEvent.VK_DELETE){
					System.out.println("Delete");
				}
				
			}

	
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub		
			}
        	
        });
        
        pesquisar();
    }
	
	// Método responsável por listar os dados do vagão e jogar na tabela
	private void pesquisar() {
        Factory f = new Factory();
        Controller c = f.getController();
        c.connect();
        lista = c.selectVagoes();
        modelo = new VagaoTableModel(lista, colunasVagao);
        tabela.setModel(modelo);
        //c.disconnect();
    }
	
	public JPanel GetPanel(){
		return this.painelFundo;
	}
	
}
