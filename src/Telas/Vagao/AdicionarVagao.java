package Telas.Vagao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import Entidades.Vagao;
import Entidades.VeiculoFerroviario;
import Repositorio.Factory;
import Repositorio.Controller;

import Telas.Interface.ITelas;

public class AdicionarVagao extends JFrame{
	
	private VagaoTableModel modelo;
	private Vagao vagao = null;
	private int linha;
	
	//declarando botoes
	private JButton JBExcluir;
	private JButton JBSalvar;
	private JButton JBNovo;
	
	//declarando campos de texto
	private JTextField JTBitola;
	private JTextField JTComprimento;
	private JTextField JTTipo;
	private JTextField JTSubtipo;
	private JTextField JTProprietario;
	
	//declarando as labels 
	private JLabel JLTitulo;
	private JLabel JLBitola;
	private JLabel JLComprimento;
	private JLabel JLTipo;
	private JLabel JLSubtipo;
	private JLabel JLProprietario;

	//declarando as combobox
	//bitola
	private DefaultComboBoxModel<VeiculoFerroviario.Bitola> DCBMbitola;
	private JComboBox<VeiculoFerroviario.Bitola> JCBBitola;
	
	//subtipo
	private DefaultComboBoxModel<Vagao.SubTipo> DCBMSubTipo;
	private JComboBox<Vagao.SubTipo> JCBSubTipo;
	
	//tipo
	private DefaultComboBoxModel<Vagao.Tipo> DCBMTipo; 
	private JComboBox<Vagao.Tipo> JCBTipo;
	
	//declarando seções da janela
	JPanel Jhead;
	JPanel Jbody;
	JPanel Jfooter;
	
	
	//em caso tenha vagao adicionado, o botão excluir ira aparecer na tela
	//Setando campos do vagao para caso haja adicionado, ira poder excluir.
	public AdicionarVagao(VagaoTableModel md, int linhaSelecionada, Vagao v) {
		this(md);
		vagao = v;
		linha = linhaSelecionada;
		
		//textfield
		this.JTComprimento.setText(String.valueOf(vagao.getComprimento()));
		this.JTProprietario.setText(String.valueOf(vagao.getProprietario()));
		
		//comboBox
		this.JCBBitola.setSelectedItem(VeiculoFerroviario.Bitola.valueOf(String.valueOf(vagao.getBitola())));
		this.JCBTipo.setSelectedItem(Vagao.Tipo.valueOf(String.valueOf(vagao.getTipo())));
		this.JCBSubTipo.setSelectedItem(Vagao.SubTipo.valueOf(String.valueOf(vagao.getSubTipo())));
		
		//botão
		JBExcluir.setVisible(true); 
	}
	
	//metodo para intanciar meus componentes e seta as secoes na janela
	public AdicionarVagao(VagaoTableModel md) {
		modelo = md;
		
		//campos de texto
		JTComprimento = new JTextField();
		JTProprietario = new JTextField();
		
		//comboBox
		//bitola
		DCBMbitola = new DefaultComboBoxModel<>(VeiculoFerroviario.Bitola.values());
		JCBBitola = new JComboBox<>();
		JCBBitola.setModel(DCBMbitola);
		
		//subtipo
		DCBMSubTipo = new DefaultComboBoxModel<>(Vagao.SubTipo.values());
		JCBSubTipo = new JComboBox<>();
		JCBSubTipo.setModel(DCBMSubTipo);
		
		//tipo
		DCBMTipo = new DefaultComboBoxModel<>(Vagao.Tipo.values());
		JCBTipo = new JComboBox<Vagao.Tipo>();
		JCBTipo.setModel(DCBMTipo);
		
		//labels
		JLTitulo = new JLabel("Cadastrar Vagão");
		JLBitola = new JLabel("Bitola:");
		JLComprimento = new JLabel("Comprimento:");
		JLProprietario = new JLabel("Proprietário:");
		JLSubtipo = new JLabel("Subtipo:");
		JLTipo = new JLabel("Tipo:");
		
		//botões
		JBExcluir = new JButton("Excluir");
		JBNovo = new JButton("Novo");
		JBSalvar = new JButton("Salvar");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(450, 300);
		setResizable(false);
		
		//Jhead();
		Jbody();
		Jfooter();	
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		//cp.add(Jhead, BorderLayout.NORTH);
		cp.add(Jbody, BorderLayout.CENTER);
		cp.add(Jfooter, BorderLayout.SOUTH);
		pack();
	}
	
	public AdicionarVagao() {
		// TODO Auto-generated constructor stub
	}

	//titulo da janela
	/*private void Jhead() { 
		FormLayout layouthead = new FormLayout(
				"pref, 5dlu, 50dlu, 20dlu, min,pref, 5dlu, 50dlu, 5dlu, min", // colunas
				"pref, 2dlu, pref, 2dlu, pref"); // linha
		Jhead = new JPanel(layouthead);
		CellConstraints cc = new CellConstraints();
		//titulo
		Jhead.add(JLTitulo, cc.xy(1, 1));
	}*/
	
	//conteudo do formulário
	private void Jbody() { 
		FormLayout layouthead = new FormLayout(
				"pref, 5dlu, 50dlu, 20dlu, min,pref, 5dlu, 50dlu, 5dlu, min", // colunas
				"pref, 2dlu, pref, 2dlu, pref"); // linha
		Jbody = new JPanel(layouthead);
		CellConstraints cc = new CellConstraints();
		//bitola
		Jbody.add(JCBBitola, cc.xy(1, 1));
		JCBBitola = new JComboBox<VeiculoFerroviario.Bitola>();
		Jbody.add(JCBBitola, cc.xy(3, 1));
		
		//comprimento
		Jbody.add(JLComprimento, cc.xy(5, 1));
		JTComprimento = new JTextField();
		Jbody.add(JTComprimento, cc.xy(8,1));
		
		//proprietario
		Jbody.add(JLProprietario, cc.xy(1, 3));
		JTProprietario= new JTextField();
		Jbody.add(JTProprietario, cc.xyw(3,3,5));
		
		//Subtipo
		Jbody.add(JCBSubTipo, cc.xy(1, 3));
		JCBSubTipo= new JComboBox<Vagao.SubTipo>();
		Jbody.add(JCBSubTipo, cc.xyw(3,3,5));
		
		//Tipo
		Jbody.add(JCBTipo, cc.xy(1, 3));
		JCBTipo= new JComboBox<Vagao.Tipo>();
		Jbody.add(JCBTipo, cc.xyw(3,3,5));
	}
	
	//botoes do formulario
	private void Jfooter() { 
		FormLayout layoutfooter = new FormLayout(
				"pref, 5dlu, 42dlu, 5dlu, min,", // colunas
				"pref, 5dlu, pref, 5dlu, pref"); // linha
		Jfooter = new JPanel(layoutfooter);
		CellConstraints cc = new CellConstraints();
		
		//botão salvar
		JBSalvar = new JButton("Salvar");
		Jfooter.add(JBSalvar, cc.xy(1,3));
		
		//botão novo
		JBNovo = new JButton("Novo");
		Jfooter.add(JBNovo,cc.xy(3, 3 ));
		
		//botão excluir
		JBExcluir = new JButton("Excluir");
		JBExcluir.setVisible(false); //apenas para o alterar
		Jfooter.add(JBExcluir,cc.xy(5,3));
		
		//ações dos botões
		JBSalvar.addActionListener(jbSalvarActLt);
		JBNovo.addActionListener(jbNovoActLt);
		JBExcluir.addActionListener(jbExCluirActLt);
	}
	
	//ação botão Salvar
	ActionListener jbSalvarActLt = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//textField
			//comprimento
			double valorComprimento = 0;
			try {
				valorComprimento = Double.parseDouble(JTComprimento.getText());
			}catch(NumberFormatException err ){
				JOptionPane.showMessageDialog(null,"O campo comprimento necessita ser preenchido corretamente!");
				return;
			}
			String valorProprietario = "";
			//proprietario
			try {
				valorProprietario = JTProprietario.getText();
				if(valorProprietario.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Por favor, preecha o valor do proprietário.");
				} else if (valorProprietario.length() != 6) {
					JOptionPane.showMessageDialog(null, "Por favor, preecha o valor do proprietário corretamente.");
					return;
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null,"O campo proprietário necessita ser preenchido corretamente!");
				return;
			}
			
			//comboBox
			String valorBitola = JCBBitola.getSelectedItem().toString();
			String valorSubTipo = JCBSubTipo.getSelectedItem().toString();
			String valorTipo = JCBTipo.getSelectedItem().toString(); 
			
			
			//acao para caso clique no botao salvar e todos os campos estejam certos
			//conexao com o banco de dados, salvar no banco de dados e desconectar do banco
			Factory f = new Factory();
			Controller c = f.getController();
			try {
				Vagao v = f.getVagao(VeiculoFerroviario.Bitola.A, Vagao.Tipo.A, Vagao.SubTipo.A, valorProprietario.toCharArray(), valorComprimento);
				//Vagao v = f.getVagao(valorBitola.charAt(0), valorTipo.charAt(0), valorSubTipo.charAt(0), valorProprietario.toCharArray(), valorComprimento);
				c.connect();
				if(vagao == null){
					//c.create(v);//salvar no banco
					JOptionPane.showMessageDialog(null,"Vagão salvo com sucesso!");
					//modelo.addVagao(v);
				}
				else {
					//c.update(v);
					JOptionPane.showMessageDialog(null,"Vagãos alterado com sucesso!");
					//modelo.updateVagao(linha, v);
				}
				
			}catch(Exception err){
				JOptionPane.showMessageDialog(null,err.getMessage());	
			}finally{
				c.disconnect();//desconectar do banco
			}
			dispose();
		}	
		
	};
	
	//ação botao Novo
	ActionListener jbNovoActLt = new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
				JTBitola.setText(" ");
				JTComprimento.setText(" ");
				JTProprietario.setText(" ");
				JTSubtipo.setText(" ");
				JTTipo.setText(" ");
		}
	};
	
	//ação botão excluir
	ActionListener jbExCluirActLt = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Factory f = new Factory();
			Controller c = f.getController();
			try {
				c.connect();
				c.remove(vagao);
				JOptionPane.showMessageDialog(null,"O vagão foi removido com sucesso!");
				modelo.removeVagao(linha);
			}catch(Exception err){
				JOptionPane.showMessageDialog(null, err.getMessage());
				return;
			} finally{
				c.disconnect();
			}
			dispose();
		}
	};
	
	public JPanel GetPanel() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
