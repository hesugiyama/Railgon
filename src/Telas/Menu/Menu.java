package Telas.Menu;

import java.awt.event.*;
import java.util.List;

import javax.swing.*;

import Entidades.Vagao;
import Repositorio.Controller;
import Repositorio.Factory;
import Repositorio.FactoryLayout;
import Telas.Composicao.ListarComposicao;
import Telas.Locomotiva.ListarLocomotiva;
import Telas.Vagao.ListarVagao;
import Telas.Vagao.VagaoTableModel;


public class Menu extends JFrame {
	
	// Ordem: Nome do item - Icone do Item - Letra sublinhada
	// Quando passamos nulo, na hora em que deveríamos passar as novas informações de item, ele coloca uma linha separadora
	private static String sVagao[] = {"Novo Vagão", "res/newVagao.gif", null, null, "Vagões", "res/listaVagao.gif", null};
	private static String sLocomotiva[] = {"Nova Locomotiva", "res/newLocomotiva.gif", null, null, "Locomotivas", "res/listaLocomotiva.gif", null};
	private static String sComposicao[] = {"Nova Composição", "res/newComposicao.gif", null, null, "Composições", "res/listaComposicao.gif", null};
	private static String sSistema[] = {"Sobre", "res/sobre.gif", null, null, "Sair", "res/listaComposicao.gif", null};
	
	FactoryLayout telas = new FactoryLayout();
	
	private JPanel panelAdicionarVagao;
	private JPanel panelListarVagao = telas.openListarVagao();
	private ListarLocomotiva panelListarLocomotiva = telas.openListarLocomotiva();
	private JPanel panelAdicionarComposicao = telas.openAdicionarComposicao();
	private ListarComposicao panelListarComposicao = telas.openListarComposicao();
	
	public Menu() {
		
		// Construtor passando título
		super("Railgon");
		
		// Instanciando o menu
		JMenuBar mb = new JMenuBar();
		
		// Instanciando responsável para olhar o objeto quando esse for clicado.
		MenuHandler mh = new MenuHandler();
		
		// Adicionando as opçoes no Menu, sendo Nome da opção, letra sublinhada, conjunto de itens que conterá nela e 
		mb.add(MenuBuilder.newMenu("Vagão", 'V', sVagao, mh));
		mb.add(MenuBuilder.newMenu("Locomotiva", 'L', sLocomotiva, mh));
		mb.add(MenuBuilder.newMenu("Composição", 'C', sComposicao, mh));
		mb.add(MenuBuilder.newMenu("Sistema", 'S', sSistema, mh));
		
		// Setando o menu
		setJMenuBar(mb);

		//panelListarVagao = telas.openListarVagao();
		
		// Definindo que ao clicar no X para fechar a aplicação, o programa se encerra de fato.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Setando o tamanho máximo da tela
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
			case "Novo Vagão":
				panelBody = panelAdicionarVagao;
			break;
			case "Vagões":
				getContentPane().removeAll();
				panelBody = panelListarVagao;	
			break;
			case "Nova Locomotiva":
				telas.openAdicionarLocomotiva(panelListarLocomotiva.GetModelo());
			break;
			case "Locomotivas":
				getContentPane().removeAll();
				panelBody = panelListarLocomotiva.GetPanel();	
			break;
			case "Nova Composição":
				getContentPane().removeAll();
				panelBody = panelAdicionarComposicao;	
			break;
			case "Composições":
				getContentPane().removeAll();
				panelBody = panelListarComposicao.GetPanel();	
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
