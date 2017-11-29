package Telas.Locomotiva;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.*;
//import javax.swing.text.MaskFormatter;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import Entidades.Locomotiva;
import Repositorio.Controller;
import Repositorio.Factory;

public class AdicionarLocomotiva extends JFrame{
	//declarando botões
	private JButton Excluir;
	private JButton Salvar;
	private JButton Novo;
	
	//declarando campos de texto
	private JTextField campoClasse;
	private JTextField campoDescricao;
	private JTextField campoPesomaxreb;
	private JTextField campoBitola;
	private JTextField campoComploc;
	
	//declarando nome do campo
	private JLabel Classe;
	private JLabel Descricao;
	private JLabel Pesomaxreb;
	private JLabel Bitola;
	private JLabel Comploc;
	
	//declarando seções da pagina
	JPanel Jhead;
	JPanel Jbody;
	JPanel Jfooter;
	
	//em caso tenha locomotiva adicionada, o botão Excluir irá aparecer na tela
	//Setando campos da locomotiva para caso haja adicionado, irá poder excluir.
	public AdicionarLocomotiva(Locomotiva l) {
		this();
		this.campoClasse.setText(String.valueOf(l.getClasse()));
		this.campoDescricao.setText(String.valueOf(l.getDescricao()));
		this.campoPesomaxreb.setText(String.valueOf(l.getPesoMax()));
		this.campoBitola.setText(String.valueOf(l.getBitola()));
		this.campoComploc.setText(String.valueOf(l.getComprimento()));
		Excluir.setVisible(true);
	}
	//metodo que instancia todos campos, botões e chama as seções da pagina
	public  AdicionarLocomotiva() {
		super("Adicionar Locomotiva"); // ajusta título
		//setSize(400,100); 
		setResizable(false);
		
		campoClasse = new JTextField();
		campoDescricao = new JTextField();
		campoPesomaxreb = new JTextField();
		campoBitola = new JTextField();
		campoComploc = new JTextField();
		Classe = new JLabel("Classe:");
		Descricao = new JLabel("Descrição:");
		Pesomaxreb = new JLabel("Peso Máximo Rebocável(toneladas):");
		Bitola = new JLabel("Bitola:");
		Comploc = new JLabel ("Comprimento da locomotiva(Metros):");
		Excluir = new JButton("Excluir");
		Salvar = new JButton("Salvar");
		Novo = new JButton("Novo");
		Jhead();
		Jbody();
		Jfooter();	
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(Jhead, BorderLayout.NORTH);
		cp.add(Jbody, BorderLayout.CENTER);
		cp.add(Jfooter, BorderLayout.SOUTH);
		pack();
	}
	//metodo da seção head da pagina onde contém classe e descrição da locomotiva
	private void Jhead(){
		FormLayout layouthead = new FormLayout(
				"pref, 5dlu, 50dlu, 20dlu, min,pref, 5dlu, 50dlu, 5dlu, min", // colunas
				"pref, 2dlu, pref, 2dlu, pref"); // linha
		//layouthead.setRowGroups(new int[][] { { 7, 1, 5 } });
		Jhead = new JPanel(layouthead);
		CellConstraints cc = new CellConstraints();
		Jhead.add(Classe, cc.xy(1, 1));
		campoClasse = new JTextField();;
		Jhead.add(campoClasse, cc.xy(3, 1));
		Jhead.add(Bitola, cc.xy(5, 1));
		campoBitola = new JTextField();
		Jhead.add(campoBitola, cc.xy(8,1));
		Jhead.add(Descricao, cc.xy(1, 3));
		campoDescricao = new JTextField();
		Jhead.add(campoDescricao, cc.xyw(3,3,5));
	}
	//seção body da pagina onde contem peso maximo rebocavel, bitola e comprimento da locomotiva
	private void Jbody() {
		FormLayout layoutbody = new FormLayout(
				"pref, 5dlu, 50dlu, 5dlu, min,", // colunas
				"pref, 3dlu, pref, 3dlu, pref"); // linha
		//layoutbody.setRowGroups(new int[][] { { 1, 3, 5 } });
		Jbody = new JPanel(layoutbody);
		CellConstraints cc = new CellConstraints();
		Jbody.add(Pesomaxreb, cc.xy(1, 1));
		Jbody.add(campoPesomaxreb, cc.xy(3, 1));
		Jbody.add(Comploc, cc.xy(1,5));
		campoComploc = new JTextField();
		Jbody.add(campoComploc, cc.xy(3,5));
	}
	//seção footer, onde contém os botões da pagina
	private void Jfooter() {
		FormLayout layoutfooter = new FormLayout(
				"pref, 5dlu, 42dlu, 5dlu, min,", // colunas
				"pref, 5dlu, pref, 5dlu, pref"); // linha
		//layoutbody.setRowGroups(new int[][] { { 1, 3, 5 } });
		Jfooter = new JPanel(layoutfooter);
		CellConstraints cc = new CellConstraints();
		Salvar = new JButton("Salvar");
		Jfooter.add(Salvar, cc.xy(1,3));
		Novo = new JButton("Novo");
		Jfooter.add(Novo,cc.xy(3, 3 ));
		Excluir = new JButton("Excluir");
		Excluir.setVisible(false);
		Jfooter.add(Excluir,cc.xy(5,3));
		Salvar.addActionListener(Salvar1);
		Novo.addActionListener(Novo1);
		Excluir.addActionListener(Excluir1);
	}
	//ação para o botão excluir 
	ActionListener Excluir1 = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				Factory f = new Factory();
				Controller c = f.getController();
				c.connect();
				//c.remove(l);
				//JOptionPane.showMessageDialog(null,"A locomotiva foi removida com sucesso!");
				//Locomotiva l = c.selectLocomotiva("11111");
			}catch(Exception err){
				JOptionPane.showMessageDialog(null, err.getMessage());
				return;
			}
			
		}
	};
	//açao para o botão salvar
	ActionListener Salvar1 = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int classe = 0;
			double pesomaxreb = 0;
			double comploc = 0;
			//tratamento de erro de campo vazio e  preenchimento do usuario no campo classe
			try {
				classe  = Integer.parseInt(campoClasse.getText().trim());
			}catch(NumberFormatException err ){
				JOptionPane.showMessageDialog(null,"O campo classe necessita ser preenchido corretamentes!");
					return;
			}
			//tratamento de erro de campo vazio e preenchimento do usuario no campo peso maximo rebocavel
			try {
				pesomaxreb = Double.parseDouble(campoPesomaxreb.getText());
			}catch(NumberFormatException err ){
				JOptionPane.showMessageDialog(null,"O campo peso necessita ser preenchido corretamente!");
					return;
			}
			//tratamento de erro de campo vazio e preenchimento do usuario no campo comprimento da locomotiva
			try {
				 comploc = Double.parseDouble(campoComploc.getText());
			}catch(NumberFormatException err ){
				JOptionPane.showMessageDialog(null,"O campo Comprimento da locomotiva necessita ser preenchido corretamente!");
					return;
			}
			String bitola = campoBitola.getText();
			String descricao = campoDescricao.getText();
			//exceção para caso o campo esteja vazio
			if(descricao.isEmpty()) {
				JOptionPane.showMessageDialog(null,"Preencha a Descrição, a locomotiva não pode ser salva sem preencher!");
					return;
			//exceção para caso o campo esteja vazio
			}else if(bitola.isEmpty()) {
				JOptionPane.showMessageDialog(null,"Preencha o campo Bitola, a locomotiva não pode ser salva sem preencher!");
					return;
			}
			//ação para caso clique no botão salvar e todos os campos estejam certos
			//conexão com o banco de dados, salvar no banco de dados e desconectar do banco
			try {
				Factory f = new Factory();
				Controller c = f.getController();
				Locomotiva l = f.getLocomotiva(bitola, classe, descricao, comploc, pesomaxreb);
				c.connect();
				c.create(l);//salvar no banco
				JOptionPane.showMessageDialog(null,"A locomotiva foi salva com sucesso!");	
			}catch(Exception err){
				JOptionPane.showMessageDialog(null,err.getMessage());	
			}finally{
				//c.disconnect();//desconectar do banco
			}
		}	
	};
	//ação para o botão novo, limpa os campos
	ActionListener Novo1 = new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
				campoClasse.setText(" ");
				campoDescricao.setText(" ");
				campoPesomaxreb.setText(" ");
				campoBitola.setText(" ");
				campoComploc.setText(" ");
		}
	};
}

/*private void excecao() {
	try {
		campoClasse = new JFormattedTextField(new MaskFormatter("000000000"));
		campoPesomaxreb = new JFormattedTextField(new MaskFormatter("000,00 Tonelada(s)"));
		campoBitola = new JFormattedTextField(new MaskFormatter("0,00 Metros"));
		campoComploc = new JFormattedTextField(new MaskFormatter("000,00 Metros"));
	} catch(ParseException e) {
		System.out.println("verifique os formatos dos campos!");
	}*/