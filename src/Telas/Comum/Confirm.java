package Telas.Comum;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 * @author GGTRangers
 * 
 * Classe respons�vel pela cria��o de um modal de confirma��o.
 * A tela � apenas de sim ou n�o e � obrigat�rio passar uma mensagem.
 *
 */
public class Confirm extends JFrame {
	
	private int retorno;
	public Confirm(String message){
		
		Object[] options = { "Sim", "Não" };
		retorno = JOptionPane.showOptionDialog(null, message,
				"Confirmação", JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE, null, options,
				options[0]);
		
	}
	
	public int getRetorno(){
		return this.retorno;
	}
}
