package Telas.Menu;

import java.awt.event.*;
import javax.swing.*;

import Repositorio.FactoryLayout;


public class Menu extends JFrame {
	
	// Ordem: Nome do item - Icone do Item - Letra sublinhada
	// Quando passamos nulo, na hora em que deveríamos passar as novas informações de item, ele coloca uma linha separadora
	private static String sVagao[] = {"Novo Vagão", "res/newVagao.gif", null, null, "Vagões", "res/listaVagao.gif", null};
	private static String sLocomotiva[] = {"Nova Locomotiva", "res/newLocomotiva.gif", null, null, "Locomotivas", "res/listaLocomotiva.gif", null};
	private static String sComposicao[] = {"Nova Composição", "res/newComposicao.gif", null, null, "Composições", "res/listaComposicao.gif", null};
	private static String sSistema[] = {"Sobre", "res/sobre.gif", null, null, "Sair", "res/listaComposicao.gif", null};
	
	FactoryLayout telas = new FactoryLayout();
	
	public Menu() {
		
		// Construtor passando título
		super("Railgon");
		
		// Instanciando o menu
		JMenuBar mb = new JMenuBar();
		
		// Ainda não sei
		MenuHandler mh = new MenuHandler();
		
		// Adicionando as opçoes no Menu, sendo Nome da opção, letra sublinhada, conjunto de itens que conterá nela e 
		mb.add(MenuBuilder.newMenu("Vagão", 'V', sVagao, mh));
		mb.add(MenuBuilder.newMenu("Locomotiva", 'L', sLocomotiva, mh));
		mb.add(MenuBuilder.newMenu("Composição", 'C', sComposicao, mh));
		mb.add(MenuBuilder.newMenu("Sistema", 'S', sSistema, mh));
		
		// Setando o menu
		setJMenuBar(mb);
		
		// Setando o tamanho máximo da tela
		setExtendedState(this.MAXIMIZED_BOTH);
	}
	
	private class MenuHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String acao = ((JMenuItem) e.getSource()).getText();
			
			switch(acao){
			case "Sair":
				System.exit(0);
			break;
			case "Novo Vagão":
				System.out.println("Novo Vagão");
				telas.openAdicionarVagao();
			break;
			case "Vagões":
				System.out.println("Vagões");
				telas.openListarVagao();
			break;
			case "Nova Locomotiva":
				System.out.println("Nova Locomotiva");
				telas.openAdicionarLocomotiva();
			break;
			case "Locomotivas":
				System.out.println("Locomotivas");
				telas.openListarLocomotiva();
			break;
			case "Nova Composição":
				System.out.println("Nova Composição");
				telas.openAdicionarComposicao();
			break;
			case "Composições":
				System.out.println("Composições");
				telas.openListarComposicao();
			break;
			case "Sobre":
				System.out.println("Sobre");
				telas.openSobre();
			break;
			default:
				System.err.print("Osh");
			break;
			}
				
		}
	}

	
}
