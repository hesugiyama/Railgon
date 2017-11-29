package Telas.Menu;

import java.awt.event.*;
import java.util.List;

import javax.swing.*;

import Entidades.Vagao;
import Repositorio.Controller;
import Repositorio.Factory;
import Repositorio.FactoryLayout;
import Telas.Vagao.ListarVagao;
import Telas.Vagao.VagaoTableModel;


public class Menu extends JFrame {
	
	// Ordem: Nome do item - Icone do Item - Letra sublinhada
	// Quando passamos nulo, na hora em que dever�amos passar as novas informa��es de item, ele coloca uma linha separadora
	private static String sVagao[] = {"Novo Vag�o", "res/newVagao.gif", null, null, "Vag�es", "res/listaVagao.gif", null};
	private static String sLocomotiva[] = {"Nova Locomotiva", "res/newLocomotiva.gif", null, null, "Locomotivas", "res/listaLocomotiva.gif", null};
	private static String sComposicao[] = {"Nova Composi��o", "res/newComposicao.gif", null, null, "Composi��es", "res/listaComposicao.gif", null};
	private static String sSistema[] = {"Sobre", "res/sobre.gif", null, null, "Sair", "res/listaComposicao.gif", null};
	
	FactoryLayout telas = new FactoryLayout();
	
	private JPanel panelAdicionarVagao;
	private JPanel panelListarVagao = telas.openListarVagao();
	private JPanel panelAdicionarLocomotiva;
	private JPanel panelListarLocomotiva = telas.openListarLocomotiva();
	private JPanel panelAdicionarComposicao = telas.openAdicionarComposicao();
	private JPanel panelListarComposicao = telas.openListarComposicao();
	
	public Menu() {
		
		// Construtor passando t�tulo
		super("Railgon");
		
		// Instanciando o menu
		JMenuBar mb = new JMenuBar();
		
		// Instanciando respons�vel para olhar o objeto quando esse for clicado.
		MenuHandler mh = new MenuHandler();
		
		// Adicionando as op�oes no Menu, sendo Nome da op��o, letra sublinhada, conjunto de itens que conter� nela e 
		mb.add(MenuBuilder.newMenu("Vag�o", 'V', sVagao, mh));
		mb.add(MenuBuilder.newMenu("Locomotiva", 'L', sLocomotiva, mh));
		mb.add(MenuBuilder.newMenu("Composi��o", 'C', sComposicao, mh));
		mb.add(MenuBuilder.newMenu("Sistema", 'S', sSistema, mh));
		
		// Setando o menu
		setJMenuBar(mb);

		//panelListarVagao = telas.openListarVagao();
		
		// Definindo que ao clicar no X para fechar a aplica��o, o programa se encerra de fato.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Setando o tamanho m�ximo da tela
		setExtendedState(this.MAXIMIZED_BOTH);
		
		//panelListarVagao.add(telas.openListarVagao());
	}
	
	private class MenuHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JPanel panelBody = null;
			String acao = ((JMenuItem) e.getSource()).getText();
			
			switch(acao){
			case "Sair":
				System.exit(0);
			break;
			case "Novo Vag�o":
				panelBody = panelAdicionarVagao;
			break;
			case "Vag�es":
				getContentPane().removeAll();
				panelBody = panelListarVagao;	
			break;
			case "Nova Locomotiva":
				telas.openAdicionarLocomotiva();
			break;
			case "Locomotivas":
				getContentPane().removeAll();
				panelBody = panelListarLocomotiva;	
			break;
			case "Nova Composi��o":
				getContentPane().removeAll();
				panelBody = panelAdicionarComposicao;	
			break;
			case "Composi��es":
				getContentPane().removeAll();
				panelBody = panelListarComposicao;	
			break;
			case "Sobre":
				telas.openSobre();
			break;
			default:
				telas.openAlertError("Osh", "ERRO");
			break;
			}
			
			try{
				if(panelBody != null){
					getContentPane().add(panelBody);
				}
				revalidate();
				repaint();
				
			}
			catch(Exception ex){
				telas.openAlertError("ERRO - Corpo do painel", "Erro: " + ex.getMessage());
			}
			
		}
	}

	
}
