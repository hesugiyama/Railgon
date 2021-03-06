package Telas.Composicao;


import Entidades.*;
import Repositorio.*;
import Telas.Interface.ITelas;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class AdicionarComposicao extends JFrame{
	
	private ComposicaoTableModel modelo;
	private int linha;
	
	private Composicao compos = null;
	private DefaultListModel<VeiculoFerroviario> DLMauxiliar;
	private DefaultListModel<VeiculoFerroviario> DLMdisponiveis;       

	private JScrollPane JSPdisponiveis;
	private JScrollPane JSPauxiliar;
	
	private JList<VeiculoFerroviario> JLdisponiveis;
	private JList<VeiculoFerroviario> JLAuxiliar;
	
	private JPanel JPrincipal;
	private JPanel JPhead;
	private JPanel JPbody;
	private JPanel JPinfo;
	private JPanel JPfooter;

	private JLabel JLbNome;
	private JLabel JLbBitola;
	private JLabel JLbComprimento;
	private JLabel JLbPesoAtual;
	private JLabel JLbPesoMax;
	private JLabel JLbQtdVagao;
	private JLabel JLbQtdLocomotiva;
	
	private JLabel  JLbDisponiveis;
	private JLabel  JLbComposicao;
	
	private JButton JBadd; 
	private JButton JBremove;
	private JButton JBBuscar; 

	private JTextField JTFnome;
	private JTextField JTFbitola;
	private JTextField JTFcomprimento;
	private JTextField JTFpesoAtual;
	private JTextField JTFpesoMax;
	private JTextField JTFqtdVagao;
	private JTextField JTFqtdLocomotiva;

	private JButton JBexcluir;
	private JButton JBsalvar;
	private JButton JBnovo;
	private JButton JBcancelar;
	
	private FactoryLayout fLayout = new FactoryLayout();
	
	
	public AdicionarComposicao( ComposicaoTableModel md, int linha, Composicao c){
		this(md);
		this.linha = linha;
		compos = c;
		
		JBBuscar.setEnabled(false);
		JTFnome.setText(c.getDescricao());
	}
	
	public AdicionarComposicao(ComposicaoTableModel md){
		
		modelo = md;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(300, 100);
		setResizable(false);
		
		iniciaValores();
		
		JPrincipal = new JPanel();
		JPrincipal.setLayout(new BorderLayout());
		JPrincipal.add(JPhead, BorderLayout.NORTH);
		JPrincipal.add(JPbody,BorderLayout.CENTER);
		JPrincipal.add(JPfooter,BorderLayout.SOUTH);
		
		JBexcluir.setEnabled(false);

		getContentPane().add(JPrincipal);
		pack();
		
		criaEventos();
	}
	
	protected void iniciaValores(){

		JSPdisponiveis = new JScrollPane();
		JSPauxiliar    = new JScrollPane();		
		
		JLdisponiveis = new JList<>();
		JLAuxiliar    = new JList<>();
		
		DLMdisponiveis = new DefaultListModel<>();
		DLMauxiliar    = new DefaultListModel<>();
		
		ArrayList<Vagao> ALVagoes = null;
		ArrayList<Locomotiva> ALLocomotivas = null;
		
		//não foi passada uma instancia
		if(compos == null){
			
			Factory fact = new Factory();
			Controller control = fact.getController();
			control.connect();
			
			compos = fact.getComposicao();
			
			try{
				ALVagoes = control.selectVagoes();
				ALLocomotivas = control.selectLocomotivas();
			}catch(Exception e){
				fLayout.openAlertError("ERRO INESPERADO", e.getMessage());
				return;
			}finally {
				control.disconnect();
			}		
					
			//populando os componentes com os valores do banco

			for(int i=0; i < ALLocomotivas.size();i++){
				DLMdisponiveis.addElement(ALLocomotivas.get(i));
			}
			for(int i=0; i < ALVagoes.size();i++){
				DLMdisponiveis.addElement(ALVagoes.get(i));
			}
		//existe uma composicao
		}else{
			ALVagoes 	  = compos.getVagoes();
			ALLocomotivas = compos.getLocomotivas();
			
			for(int i=0; i < ALLocomotivas.size();i++){
				DLMdisponiveis.addElement(ALLocomotivas.get(i));
			}
			for(int i=0; i < ALVagoes.size();i++){
				DLMdisponiveis.addElement(ALVagoes.get(i));
			}
		}				
		
		JLdisponiveis.setModel(DLMdisponiveis);
		JLAuxiliar.setModel(DLMauxiliar);
		//JLAuxiliar.setAutoscrolls(true);
		JLdisponiveis.setAutoscrolls(true);

		JSPdisponiveis.setViewportView(JLdisponiveis);
		JSPauxiliar.setViewportView(JLAuxiliar);
		
		
		JLbComposicao    = new JLabel("Composição:");
		JLbDisponiveis   = new JLabel("Disponíveis:");
		JLbNome		     = new JLabel("Nome da Composição:");
		JLbBitola	     = new JLabel("Bitola:");
		JLbComprimento   = new JLabel("Comprimento:");
		JLbPesoAtual     = new JLabel("Peso Atual:");
		JLbPesoMax       = new JLabel("Peso Máximo:");
		JLbQtdVagao      = new JLabel("Qtd Vagões:");
		JLbQtdLocomotiva = new JLabel("Qtd Locomotivas:");
		
		JTFnome 		 = new JTextField();
		JTFbitola		 = new JTextField();
		JTFcomprimento	 = new JTextField();
		JTFpesoAtual	 = new JTextField();
		JTFpesoMax		 = new JTextField();
		JTFqtdVagao		 = new JTextField();
		JTFqtdLocomotiva = new JTextField();		
		
		JBadd = new JButton(">");
		JBremove  = new JButton("<");
		
		JBexcluir = new JButton("EXCLUIR");
		JBsalvar = new JButton("SALVAR");
		JBnovo = new JButton("NOVO");
		JBcancelar = new JButton("CANCELAR");
		JBBuscar = new JButton("PESQUISAR");
		
		//chama os métodos de cada painel para construir seus componentes 
		AtualizaValores();
		iniciaHead();
		iniciaInfo();
		iniciaBody();
		iniciaFooter();
	}
	
	protected void iniciaHead(){
		
		FormLayout layout = new FormLayout(
				"20dlu,100dlu,50dlu,100dlu,50dlu,80dlu", //colunas
				"15dlu,pref,15dlu" //linhas
				);
		
		CellConstraints cc = new CellConstraints();
		
		JPhead = new JPanel(layout);
		JPhead.setBorder( new TitledBorder("Composição"));
		JPhead.add(JLbNome,cc.xy(2,2));
		JPhead.add(JTFnome,cc.xy(4, 2));
		JPhead.add(JBBuscar,cc.xy(6, 2));
	}
	
	protected void iniciaBody(){
		
		FormLayout layout = new FormLayout(
				"30dlu,60dlu,40dlu,30dlu,pref,30dlu,60dlu,40dlu", //colunas
				"10dlu,5dlu,40dlu,10dlu,20dlu,10dlu,20dlu,10dlu,40dlu,20dlu,10dlu" //linhas
				);
		
		CellConstraints cc = new CellConstraints();		
		
		JPbody = new JPanel(layout);
		JPbody.setBorder( new TitledBorder("Elementos"));
		JPbody.add(JLbDisponiveis, cc.xy(2,1));
		JPbody.add(JSPdisponiveis, cc.xywh(2,3,2,5));
		JPbody.add(JLbComposicao, cc.xy(7,1));
		JPbody.add(JBadd, cc.xy(5,4));
		JPbody.add(JBremove,cc.xy(5,6));
		JPbody.add(JSPauxiliar, cc.xywh(7, 3, 2, 5));
		JPbody.add(JPinfo, cc.xywh(2, 9, 7,2));
		
	}
	
	protected void iniciaInfo(){
		JPinfo = new JPanel();
		
		FormLayout layout = new FormLayout(
				"5dlu,pref,5dlu,pref,5dlu,pref,5dlu,pref,5dlu,pref,5dlu,pref,5dlu,pref,5dlu,pref,5dlu,pref,5dlu,pref,5dlu,pref,5dlu,pref,5dlu", //colunas
				"20dlu,2dlu,20dlu" //linhas
				);
		
		CellConstraints cc = new CellConstraints();
		
		JPinfo.setLayout(layout);
		JPinfo.setBorder(new TitledBorder("Resumo"));

		JTFbitola.setEnabled(false);
		JTFcomprimento.setEnabled(false);
		JTFpesoAtual.setEnabled(false);
		JTFpesoMax.setEnabled(false);
		JTFqtdVagao.setEnabled(false);
		JTFqtdLocomotiva.setEnabled(false);
		
		JPinfo.add(JLbBitola, cc.xy(2, 1));
		JPinfo.add(JTFbitola, cc.xy(4, 1));
		JPinfo.add(JLbQtdVagao, cc.xy(6, 1));
		JPinfo.add(JTFqtdVagao, cc.xy(8, 1));
		JPinfo.add(JLbQtdLocomotiva, cc.xy(10, 1));
		JPinfo.add(JTFqtdLocomotiva, cc.xy(12, 1));
		
		JPinfo.add(JLbComprimento, cc.xy(2, 3));
		JPinfo.add(JTFcomprimento, cc.xy(4, 3));
		JPinfo.add(JLbPesoAtual, cc.xy(6, 3));
		JPinfo.add(JTFpesoAtual, cc.xy(8, 3));
		JPinfo.add(JLbPesoMax, cc.xy(10, 3));
		JPinfo.add(JTFpesoMax, cc.xy(12, 3));		
	}
	
	protected void iniciaFooter(){
		
		FormLayout layout = new FormLayout(
				"30dlu,60dlu,30dlu,60dlu,30dlu,60dlu,30dlu,60dlu,30dlu", //colunas
				"10dlu,40dlu,10dlu" //linhas
				);
		
		CellConstraints cc = new CellConstraints();
		
		JPfooter = new JPanel(layout);
		JPfooter.setBorder( new TitledBorder("Gravar"));
		JPfooter.add(JBcancelar, cc.xy(2,2));
		JPfooter.add(JBexcluir, cc.xy(4,2));
		JPfooter.add(JBsalvar, cc.xy(6,2));
		JPfooter.add(JBnovo, cc.xy(8,2));
	}
	
	/** Valida se os campos da tela estão certos
	 * @return true se estiver certo e false se estiver errado
	 */
	protected boolean validacao(){
		boolean aux = true;
		
		if(JTFnome.getText().isEmpty()){
			fLayout.openAlertInfo("NOME INVÁLIDO","Preencha o nome da composição!");
			aux = false;
		}
		
		if(JTFnome.getText().length() > 200){
			fLayout.openAlertInfo("NOME INVÁLIDO","O nome da composição deve ter menos de 200 caracteres!");
			aux = false;
		}	
		return aux;
	}
	
	//dispara a criação de eventos na aplicação
	protected void criaEventos(){
		
		JLdisponiveis.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt){
				if(evt.getClickCount() == 2){
					try{
						Locomotiva aux = (Locomotiva) JLdisponiveis.getSelectedValue();
						fLayout.openVisualizarLocomotiva(aux);
					}
					catch(Exception e){
						Vagao aux = (Vagao) JLdisponiveis.getSelectedValue();
						fLayout.openVisualizarVagao(aux);
					}
				}
			}
		});
		 
		JLAuxiliar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt){
				if(evt.getClickCount() == 2){
					try{
						Locomotiva aux = (Locomotiva) JLAuxiliar.getSelectedValue();
						fLayout.openVisualizarLocomotiva(aux);
					}catch(Exception e){
						Vagao aux = (Vagao) JLAuxiliar.getSelectedValue();
						fLayout.openVisualizarVagao(aux);
					}
				}					
			}
		});
		
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
	
	private void AtualizaValores(){
		Dimension dimensao = new Dimension(50, 20);
		JTFbitola.setText(String.valueOf(compos.getBitola()));
		JTFcomprimento.setText(String.valueOf(compos.getComprimento()));
		JTFpesoAtual.setText(String.valueOf(compos.getPesoAtual()));
		JTFpesoMax.setText(String.valueOf(compos.getPesoMax()));
		JTFqtdVagao.setText(String.valueOf(compos.getQtdVagao()));
		JTFqtdLocomotiva.setText(String.valueOf(compos.getQtdLocomotiva()));
		
		JTFbitola.setPreferredSize(new Dimension(15, 20));
		JTFcomprimento.setPreferredSize(dimensao);
		JTFpesoAtual.setPreferredSize(dimensao);
		JTFpesoMax.setPreferredSize(dimensao);
		JTFqtdVagao.setPreferredSize(dimensao);
		JTFqtdLocomotiva.setPreferredSize(dimensao);
	}
	//implementação do método voltar
	private void btnAdd(){
		try{
			//se tentar inserir um vagão sem locomotiva
			if(JLdisponiveis.isSelectionEmpty()){				
				fLayout.openAlertInfo("SELEÇÃO VAZIA","É necessário selecionar um elemento disponível!");
				return;
			}
			
			//posicao do vagao na lista e o próprio vagão
			int pos = JLdisponiveis.getSelectedIndex();
			VeiculoFerroviario vf = JLdisponiveis.getSelectedValue();
			
			//descobre qual o tipo do elemento escolhido
			if(vf instanceof Locomotiva){
				Locomotiva l = (Locomotiva) vf;
				compos.add(l);				
			}else{
				Vagao v = (Vagao) vf;
				compos.add(v);
			}
			
			//tira o elemento da lista
			DLMdisponiveis.remove(pos);
			
			//adiciona o elemnto na lista de elementos da composição
			DLMauxiliar.addElement(vf);
			 //JLAuxiliar.setModel(DLMauxiliar);
		}
		catch(Exception e){
			fLayout.openAlertError("ERRO INESPERADO", e.getMessage());
		}
		AtualizaValores();
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
					fLayout.openAlertInfo("ERRO AO REMOVER",e.getMessage());
					return;
				}				
				DLMauxiliar.remove(posElement);
			}else{
				Vagao v = (Vagao) element;
				
				//remove de fato o vagão da composição
				try{
					compos.remove(v);
				}catch(Exception e){
					fLayout.openAlertError("ERRO AO REMOVER",e.getMessage());
					return;
				}				
				DLMauxiliar.remove(posElement);
			}
			
			//devolve o objeto para a lista
			DLMdisponiveis.addElement(element);
			
			control.disconnect();
			
		}else{
			fLayout.openAlertInfo("SELEÇÃO VAZIA","Selecione um componente para retirar da composição");
		}
		AtualizaValores();
	}
	
	//implementação do método Excluir
	private void btnExcluir(){
		Factory f = new Factory();
		Controller control = f.getController();
		control.connect();
		
		int resp = fLayout.openConfirm("Deseja remover a composição?\nOs elementos ficaram disponíveis para serem usados por outras composições.");
		
		//clicou em SIM na tela
		if(resp==0){
			try{
				control.remove(compos);	
				modelo.removeComposicao(linha);		
			}
			catch(Exception e){
				fLayout.openAlertError("ERRO INESPERADO",e.getMessage());
			}
		}
	}
	
	//implementação do método Salvar
	private void btnSalvar(){
		
		compos.setDescricao(JTFnome.getText());
		
		if(!validacao()){
			return;
		}
		
		try{
			
			Factory f = new Factory();
			Controller control = f.getController();
			
			control.update(compos);
			
			modelo.addComposicao(compos);
			
			fLayout.openAlertInfo("SUCESSO", "A COMPOSIÇÃO FOI SALVA COM SUCESSO!");
			dispose();
		}catch(Exception e){
			fLayout.openAlertError("ERRO AO SALVAR", e.getMessage());
		}
	}
	
	//implementação do método Novo
	private void btnNovo(){
		Factory f = new Factory();
		compos = f.getComposicao();
		
		for(int i=0; i< DLMauxiliar.size() ; i++){
			DLMdisponiveis.addElement(DLMauxiliar.getElementAt(i));
		}
		DLMauxiliar.removeAllElements();
	}
	
	//implementação do método Novo
	private void btnCancelar(){
		dispose();
	}

}