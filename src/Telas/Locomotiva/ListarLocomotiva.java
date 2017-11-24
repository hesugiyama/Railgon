package Telas.Locomotiva;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import Entidades.Locomotiva;
import Repositorio.Controller;
import Repositorio.Factory;

public class ListarLocomotiva extends JFrame{
	private JPanel painelFundo;
    //private JPanel painelBotoes;
    private JTable tabela;
    private JScrollPane barraRolagem;
    private LocomotivaTableModel modelo;
    List<Locomotiva> lista;
    
    public ListarLocomotiva() {
        super("Listar Locomotiva"); // ajusta título
        criaJTable();
        criaJanela();
    }
    
    public void criaJanela(){
        //painelBotoes = new JPanel();
        barraRolagem = new JScrollPane(tabela);
        painelFundo = new JPanel();
        painelFundo.setLayout(new BorderLayout());
        painelFundo.add(BorderLayout.CENTER, barraRolagem);
        //painelFundo.add(BorderLayout.SOUTH, painelBotoes);
        
        getContentPane().add(painelFundo);
        setSize(500, 320);
       //setVisible(true);
    }
    
    private void criaJTable() {
       tabela = new JTable(modelo);
       pesquisar();
    }
    
    private void pesquisar() {
       Factory f = new Factory();
       Controller c = f.getController();
       c.connect();
       lista = c.selectLocomotivas();
       modelo = new LocomotivaTableModel(lista);
       tabela.setModel(modelo);
       //c.disconnect();
    }	    
}