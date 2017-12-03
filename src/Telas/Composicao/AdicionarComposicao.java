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
	
	private FactoryLayout fLayout = new FactoryLayout();
	
	
	public AdicionarComposicao( ComposicaoTableModel md, int linha, Composicao c){
		this(md);
		this.linha = linha;
		setComposicao(c);
		
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
	
	protected static void setComposicao(Composicao c){
		compos = c;
	}
	
	
	protected void iniciaValores(){
		JLdisponiveis = new JList<>();
		JLAuxiliar    = new JList<>();
		
		DLMdisponiveis = new DefaultListModel<>();
		DLMauxiliar    = new DefaultListModel<>();
		
		ArrayList<Vagao> ALVagoes = null;
		ArrayList<Locomotiva> ALLocomotivas = null;
		
		//nÃ£o foi passada uma instancia
		if(compos == null){
			Factory fact = new Factory();
			Controller control = fact.getController();
			control.connect();
			
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

		JLbComposicao  = new JLabel("ComposiÃ§Ã£o:");
		JLbDisponiveis = new JLabel("DisponÃ­veis:");
		JLbNome		   = new JLabel("Nome da ComposiÃ§Ã£o:");
		
		JTFnome = new JTextField();
		
		JBadd = new JButton(">");
		JBremove  = new JButton("<");
		
		JBexcluir = new JButton("EXCLUIR");
		JBsalvar = new JButton("SALVAR");
		JBnovo = new JButton("NOVO");
		JBcancelar = new JButton("CANCELAR");
		JBBuscar = new JButton("PESQUISAR");
		
		//chama os mÃ©todos de cada painel para construir seus componentes 
		iniciaHead();
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
		JPhead.setBorder( new TitledBorder("ComposiÃ§Ã£o"));
		JPhead.add(JLbNome,cc.xy(2,2));
		JPhead.add(JTFnome,cc.xy(4, 2));
		JPhead.add(JBBuscar,cc.xy(6, 2));
	}
	
	protected void iniciaBody(){
		
		FormLayout layout = new FormLayout(
				"30dlu,60dlu,40dlu,30dlu,pref,30dlu,60dlu,40dlu", //colunas
				"10dlu,5dlu,40dlu,10dlu,20dlu,10dlu,20dlu,10dlu" //linhas
				);
		
		CellConstraints cc = new CellConstraints();		
		
		JPbody = new JPanel(layout);
		JPbody.setBorder( new TitledBorder("Elementos"));
		JPbody.add(JLbDisponiveis, cc.xy(2,1));
		JPbody.add(JLdisponiveis, cc.xywh(2,3,2,5));
		JPbody.add(JLbComposicao, cc.xy(7,1));
		JPbody.add(JBadd, cc.xy(5,4));
		JPbody.add(JBremove,cc.xy(5,6));
		JPbody.add(JLAuxiliar, cc.xywh(7, 3, 2, 5));		
	}
	
	protected void iniciaFooter(){
		
		FormLayout layout = new FormLayout(
				"60dlu,60dlu,60dlu,60dlu,60dlu,60dlu,60dlu", //colunas
				"10dlu,40dlu,10dlu" //linhas
				);
		
		CellConstraints cc = new CellConstraints();
		
		JPfooter = new JPanel(layout);
		//JPfooter.setBackground(new Color(220,20,170));
		JPfooter.setBorder( new TitledBorder("Gravar"));
		JPfooter.add(JBcancelar, cc.xy(1,2));
		JPfooter.add(JBexcluir, cc.xy(3,2));
		JPfooter.add(JBsalvar, cc.xy(5,2));
		JPfooter.add(JBnovo, cc.xy(7,2));
	}
	
	/** Valida se os campos da tela estÃ£o certos
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
	
	//dispara a criaÃ§Ã£o de eventos na aplicaÃ§Ã£o
	protected void criaEventos(){
		
		JLdisponiveis.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt){
				if(evt.getClickCount() == 2){
					try{
						Locomotiva aux = (Locomotiva) JLdisponiveis.getSelectedValue();
						fLayout.openAtualizarLocomotiva(null,0,aux);
					}
					catch(Exception e){
						Vagao aux = (Vagao) JLdisponiveis.getSelectedValue();
						fLayout.openAlertInfo("tela de vagÃ£o", "Aguardando implementaÃ§Ã£o");
						//fLayout.openAtualizarVagao(null,0,aux);
					}
				}
			}
		});
		 
		JLAuxiliar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt){
				if(evt.getClickCount() == 2){
					try{
						Locomotiva aux = (Locomotiva) JLAuxiliar.getSelectedValue();
						fLayout.openAtualizarLocomotiva(null,0, aux);
					}catch(Exception e){
						Vagao aux = (Vagao) JLAuxiliar.getSelectedValue();
						fLayout.openAlertInfo("tela de vagÃ£o", "Aguardando implementaÃ§Ã£o");
						//fLayout.openAtualizarVagao(null,0,aux);
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
		//chama o mÃ©todo EXCLUIR
		JBexcluir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				btnExcluir(); // mÃ©todo que realiza (re)aÃ§Ã£o
			}
		});
		//chama o mÃ©todo SALVAR 
		JBsalvar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				btnSalvar(); // mÃ©todo que realiza (re)aÃ§Ã£o
			}
		});
		//chama o mÃ©todo NOVO
		JBnovo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				btnNovo(); // mÃ©todo que realiza (re)aÃ§Ã£o
			}
		});
		//chama o mÃ©todo CANCELAR
		JBcancelar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				btnCancelar(); // mÃ©todo que realiza (re)aÃ§Ã£o
			}
		});
	}
	
	//implementaÃ§Ã£o do mÃ©todo voltar
	private void btnAdd(){
		try{
			//se tentar inserir um vagÃ£o sem locomotiva
			if(JLdisponiveis.isSelectionEmpty()){				
				fLayout.openAlertInfo("SELEÃ‡ÃƒO VAZIA","Ã‰ necessÃ¡rio selecionar um elemento disponÃ­vel!");
				return;
			}
			
			//posicao do vagao na lista e o prÃ³prio vagÃ£o
			int pos = JLdisponiveis.getSelectedIndex();
			VeiculoFerroviario vf = JLdisponiveis.getSelectedValue();
			
			//descobre qual o tipo do elemento escolhido
			if(vf instanceof Locomotiva){
				Locomotiva l = (Locomotiva) vf;
				if(compos==null){
					Factory f = new Factory();
					String descricao = JTFnome.getText();
					compos = f.getComposicao(l, descricao);
				//se ja existe uma composiÃ§Ã£o, adiciona a locomotiva
				}else{
					compos.add(l);
				}
				
			}else{
				Vagao v = (Vagao) vf;
				compos.add(v);
			}			
			
			//tira o elemento da lista
			DLMdisponiveis.remove(pos);
			
			//adiciona o elemnto na lista de elementos da composiÃ§Ã£o
			DLMauxiliar.addElement(vf);
			JLAuxiliar.setModel(DLMauxiliar);
		}
		catch(Exception e){
			fLayout.openAlertError("ERRO INESPERADO", e.getMessage());
		}
	}
	
	//implementaÃ§Ã£o do mÃ©todo de remover da composicao
	protected void btnRemove(){
		if(!JLAuxiliar.isSelectionEmpty()){
			
			//seleciona a posiÃ§Ã£o do elemento selecionado e o prÃ³prio elemento
			int posElement = JLAuxiliar.getSelectedIndex();
			VeiculoFerroviario element = JLAuxiliar.getSelectedValue();
			Factory f = new Factory();
			Controller control = f.getController();
			control.connect();
			
			if(element instanceof Locomotiva){
				Locomotiva l = (Locomotiva) element;
				
				//remove de fato a locomotiva da composiÃ§Ã£o
				try{
					compos.remove(l);
				}catch(Exception e){
					fLayout.openAlertInfo("ERRO AO REMOVER",e.getMessage());
					return;
				}				
				DLMauxiliar.remove(posElement);
			}else{
				Vagao v = (Vagao) element;
				
				//remove de fato o vagÃ£o da composiÃ§Ã£o
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
			fLayout.openAlertInfo("SELEÃ‡ÃƒO VAZIA","Selecione um componente para retirar da composiÃ§Ã£o");
		}
	}
	
	//implementaÃ§Ã£o do mÃ©todo Excluir
	private void btnExcluir(){
		Factory f = new Factory();
		Controller control = f.getController();
		control.connect();
		
		int resp = fLayout.openConfirm("Deseja remover a composiÃ§Ã£o?\nOs elementos ficaram dispobÃ­veis para serem usados por outras composiÃ§Ãµes.");
		
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
	
	//implementaÃ§Ã£o do mÃ©todo Salvar
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
			
			fLayout.openAlertInfo("SUCESSO", "A COMPOSIÃ‡ÃƒO FOI SALVA COM SUCESSO!");
			dispose();
		}catch(Exception e){
			fLayout.openAlertError("ERRO AO SALVAR", e.getMessage());
		}
	}
	
	//implementaÃ§Ã£o do mÃ©todo Novo
	private void btnNovo(){
		compos = null;
		JTFnome.setText("");
		
		for(int i=0; i< DLMauxiliar.size() ; i++){
			DLMdisponiveis.addElement(DLMauxiliar.getElementAt(i));
		}
		DLMauxiliar.removeAllElements();
	}
	
	//implementaÃ§Ã£o do mÃ©todo Novo
	private void btnCancelar(){
		dispose();
	}

	public JPanel GetPanel() {
		return this.JPrincipal;
	}
}