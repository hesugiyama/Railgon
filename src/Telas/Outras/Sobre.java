package Telas.Outras;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import Telas.Interface.ITelas;

public class Sobre extends JFrame{

	private JLabel JLtitulo;
	private JLabel JLlogo;
	
	private JTextArea JTPdescricao;
	
	private JPanel JPprincipal;
	private JPanel JPhead;
	private JPanel JPlogo;
	private JPanel JPbody;
	private JPanel JPfooter;
	
	
	
	public Sobre() {
		
		JPprincipal = new JPanel();
		
		iniciaValores();		
		
		JPprincipal.setLayout(new BorderLayout());
		JPprincipal.add(BorderLayout.NORTH, JPhead);
		JPprincipal.add(BorderLayout.WEST, JPlogo);
		JPprincipal.add(BorderLayout.CENTER, JPbody);
		JPprincipal.add(BorderLayout.SOUTH, JPfooter);
		
		getContentPane().add(JPprincipal);		
	}
	
	public void iniciaValores(){

		JPhead   = new JPanel();
		JPlogo   = new JPanel();
		JPbody   = new JPanel();
		JPfooter = new JPanel();
		
		JPbody.setLayout(null);
		
		JTPdescricao = new JTextArea();
		JLlogo = new JLabel();
		
		JLlogo.setBounds(0,0,700,500);
		ImageIcon imgIcon = new ImageIcon("res/LogoRailgon.png");
		Image img = imgIcon.getImage().getScaledInstance(JLlogo.getWidth(),JLlogo.getHeight(), Image.SCALE_DEFAULT);
		JLlogo.setIcon(new ImageIcon(img));
		
		JLtitulo = new JLabel("RAILGON©");
		JLtitulo.setFont(new Font("Arial", Font.BOLD, 50));
		
		JTPdescricao.setBounds(0, 0, 500, 300);
		JTPdescricao.setEditable(false);
		JTPdescricao.setLineWrap(true);
		JTPdescricao.setText("RAILGON©, é a simulação de um sistema simples de uma ferrovia, onde a funcionalidade principal é gerenciar uma composição (formada por vagões e locomotivas)\nNeste simulador pode-se incluir novas Composições, alterar e atualizar as mesmas, o mesmo procedimento é válido para Locomotivas e Vagões.\nBoa Sorte!");
		
		JPhead.add(JLtitulo);		
		JPlogo.add(JLlogo);		
		JPbody.add(JTPdescricao);
	}

	public JPanel GetPanel() {
		// TODO Auto-generated method stub
		return JPprincipal;
	}
	
}