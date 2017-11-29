package Telas.Composicao;

import java.util.ArrayList;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import Entidades.Vagao;
import Entidades.VeiculoFerroviario;
import Entidades.Composicao;
import Entidades.Locomotiva;
import Repositorio.Controller;
import Repositorio.Factory;
import Telas.Interface.ITelas;

public class AdicionarComposicao extends JFrame implements ITelas{
	
	private static Composicao compos = null;

	private static DefaultListModel<VeiculoFerroviario> DLMauxiliar;
	private static DefaultListModel<VeiculoFerroviario> DLMdisponiveis;       
	
	private JList<VeiculoFerroviario> JLdisponiveis;
	private JList<VeiculoFerroviario> JLAuxiliar;
	
	private JPanel JPrincipal;
	
	private JPanel JPhead;
	private JPanel JPbody;
	private JPanel JPfooter;

	private JLabel  JLbNome;
	private JLabel  JLbDisponiveis;
	private JLabel  JLbComposicao;
	
	private JButton JBadd; 
	private JButton JBremove;
	private JButton JBBuscar; 

	private JTextField JTFnome;

	private JButton JBexcluir;
	private JButton JBsalvar;
	private JButton JBnovo;
	private JButton JBcancelar;
	
	public AdicionarComposicao(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		iniciaValores();
		
		JPrincipal = new JPanel();
		JPrincipal.setLayout(new BorderLayout());
		JPrincipal.add(JPhead, BorderLayout.NORTH);
		JPrincipal.add(JPbody,BorderLayout.CENTER);
		JPrincipal.add(JPfooter,BorderLayout.SOUTH);
		
		getContentPane().add(JPrincipal);
		
		pack();
		
		criaEventos();
	}
	
	protected void iniciaValores(){
		Factory fact = new Factory();
		
		Controller control = fact.getController();
		control.connect();
		
		ArrayList<Vagao> ALVagoes = control.selectVagoes();
		ArrayList<Locomotiva> ALLocomotivas = control.selectLocomotivas();
		
		//converte o ArrayList em um array comum
		//Vagao[] vetorVagao           = ALVagoes.toArray(new Vagao[ALVagoes.size()]);
		//Locomotiva[] vetorLocomotiva = ALLocomotivas.toArray(new Locomotiva[ALLocomotivas.size()]);
		
		control.disconnect();

		JLdisponiveis = new JList<>();
		JLAuxiliar    = new JList<>();
		
		DLMdisponiveis = new DefaultListModel<>();
		DLMauxiliar    = new DefaultListModel<>();
		
		//populando os componentes com os valores do banco

		for(int i=0; i < ALLocomotivas.size();i++){
			DLMdisponiveis.addElement(ALLocomotivas.get(i));
		}
		for(int i=0; i < ALVagoes.size();i++){
			DLMdisponiveis.addElement(ALVagoes.get(i));
		}		
		
		JLdisponiveis.setModel(DLMdisponiveis);
		JLAuxiliar.setModel(DLMauxiliar);

		JLbComposicao  = new JLabel("Composição:");
		JLbDisponiveis = new JLabel("Disponíveis:");
		JLbNome		   = new JLabel("Descição da Composição:");
		
		JTFnome = new JTextField();
		
		JBadd = new JButton(">");
		JBremove  = new JButton("<");
		
		JBexcluir = new JButton("EXCLUIR");
		JBsalvar = new JButton("SALVAR");
		JBnovo = new JButton("NOVO");
		JBcancelar = new JButton("CANCELAR");
		JBBuscar = new JButton("PESQUISAR");
		
		//chama os métodos de cada painel para construir seus componentes 
		iniciaHead();
		iniciaBody();
		iniciaFooter();
	}
	
	protected void iniciaHead(){
		
		FormLayout layout = new FormLayout(
				"100dlu,50dlu,100dlu,50dlu,80dlu", //colunas
				"15dlu,pref,15dlu" //linhas
				);
		//layout.setRowGroups(new int[][]{{1,3,5}});
		
		CellConstraints cc = new CellConstraints();
		
		JPhead = new JPanel(layout);
		JPhead.setBackground(new Color(90,230,70));
		JPhead.add(JLbNome,cc.xy(1,2));
		JPhead.add(JTFnome,cc.xy(3, 2));
		JPhead.add(JBBuscar,cc.xy(5, 2));
	}
	
	protected void iniciaBody(){
		
		FormLayout layout = new FormLayout(
				"60dlu,40dlu,30dlu,15dlu,30dlu,60dlu,40dlu", //colunas
				"10dlu,5dlu,40dlu,10dlu,20dlu,10dlu,20dlu,10dlu" //linhas
				);
		
		CellConstraints cc = new CellConstraints();		
		
		JPbody = new JPanel(layout);
		JPbody.add(JLbDisponiveis, cc.xy(1,1));
		JPbody.add(JLdisponiveis, cc.xywh(1,3,2,5));
		//JPbody.add(JLVagoes, cc.xywh(1, 3, 2, 5)); // col, row, colspan, rowspan
		JPbody.add(JLbComposicao, cc.xy(6,1));
		//JPbody.add(JLLocomotivas, cc.xywh(1, 9, 2, 2)); 
		JPbody.add(JBadd, cc.xy(4,4));
		JPbody.add(JBremove,cc.xy(4,6));
		JPbody.add(JLAuxiliar, cc.xywh(6, 3, 2, 5));		
	}
	
	protected void iniciaFooter(){
		
		FormLayout layout = new FormLayout(
				"60dlu,60dlu,60dlu,60dlu,60dlu,60dlu,60dlu", //colunas
				"40dlu,40dlu,40dlu" //linhas
				);
		
		CellConstraints cc = new CellConstraints();
		
		JPfooter = new JPanel(layout);
		JPfooter.setBackground(new Color(220,20,170));
		

		JPfooter.add(JBcancelar, cc.xy(1,2));
		JPfooter.add(JBexcluir, cc.xy(3,2));
		JPfooter.add(JBsalvar, cc.xy(5,2));
		JPfooter.add(JBnovo, cc.xy(7,2));
	}
	
	protected void Alert(Object texto){
		JOptionPane.showMessageDialog(null, texto);
	}
	
	protected boolean validacao(){
		boolean aux = true;
		
		if(JTFnome.getText().isEmpty()){
			Alert("Preencha o nome da composição!");
			aux = false;
		}
		
		if(JTFnome.getText().length() > 200){
			Alert("O nome da composição deve ter menos de 200 caracteres!");
			aux = false;
		}	
		return aux;
	}
	
	//dispara a criação de eventos na aplicação
	protected void criaEventos(){
		
		JBadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnAdd();
			}
		});
		
		JBremove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnRemove();
			}
		});
		//chama o método EXCLUIR
		JBexcluir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				btnExcluir(); // método que realiza (re)ação
			}
		});
		//chama o método SALVAR 
		JBsalvar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				btnSalvar(); // método que realiza (re)ação
			}
		});
		//chama o método NOVO
		JBnovo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				btnNovo(); // método que realiza (re)ação
			}
		});
		//chama o método CANCELAR
		JBcancelar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				btnCancelar(); // método que realiza (re)ação
			}
		});
	}
	
	//implementação do método voltar
	private void btnAdd(){
		try{
			//se tentar inserir um vagão sem locomotiva
			if(JLdisponiveis.isSelectionEmpty()){				
				Alert("É necessário selecionar um elemento disponível!");
				return;
			}
			
			//posicao do vagao na lista e o próprio vagão
			int pos = JLdisponiveis.getSelectedIndex();
			VeiculoFerroviario vf = JLdisponiveis.getSelectedValue();
			
			//descobre qual o tipo do elemento escolhido
			if(vf instanceof Locomotiva){
				Locomotiva l = (Locomotiva) vf;
				if(compos==null){
					Factory f = new Factory();
					String descricao = JTFnome.getText();
					compos = f.getComposicao(l, descricao);
				//se ja existe uma composição, adiciona a locomotiva
				}else{
					compos.add(l);
				}
				
			}else{
				Vagao v = (Vagao) vf;
				compos.add(v);
			}			
			
			//tira o elemento da lista
			DLMdisponiveis.remove(pos);
			
			//adiciona o elemnto na lista de elementos da composição
			DLMauxiliar.addElement(vf);
			JLAuxiliar.setModel(DLMauxiliar);
		}
		catch(Exception e){
			Alert("teste" + e.getMessage());
		}
	}
	
	//implementação do método de remover da composicao
	protected void btnRemove(){
		if(!JLAuxiliar.isSelectionEmpty()){
			
			//seleciona a posição do elemento selecionado e o próprio elemento
			int posElement = JLAuxiliar.getSelectedIndex();
			VeiculoFerroviario element = JLAuxiliar.getSelectedValue();
			
			Factory f = new Factory();
			Controller control = f.getController();
			control.connect();
			
			if(element instanceof Locomotiva){
				Locomotiva l = (Locomotiva) element;
				
				//remove de fato a locomotiva da composição
				try{
					compos.remove(l);
				}catch(Exception e){
					Alert(e.getMessage());
					return;
				}				
				DLMauxiliar.remove(posElement);
			}else{
				Vagao v = (Vagao) element;
				
				//remove de fato o vagão da composição
				try{
					compos.remove(v);
				}catch(Exception e){
					Alert(e.getMessage());
					return;
				}				
				DLMauxiliar.remove(posElement);
			}
			
			//devolve o objeto para a lista
			DLMdisponiveis.addElement(element);
			
			control.disconnect();
			
		}else{
			Alert("Selecione um componente para retirar da composição");
		}
	}
	
	//implementação do método Excluir
	private void btnExcluir(){
		Alert(compos.getQtdLocomotiva());
	}
	
	//implementação do método Salvar
	private void btnSalvar(){
		boolean validado = validacao();
		
		if(!validado){
			compos.setDescricao(JTFnome.getText());
		}else{
			compos.save();
		}
	}
	
	//implementação do método Novo
	private void btnNovo(){
		compos = null;
		JTFnome.setText("");
		DLMauxiliar.removeAllElements();
	}
	
	//implementação do método Novo
	private void btnCancelar(){
		dispose();
	}

	public JPanel GetPanel() {
		return this.JPrincipal;
	}
}