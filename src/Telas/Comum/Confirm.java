package Telas.Comum;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 * @author GGTRangers
 * 
 * Classe responsável pela criação de um modal de confirmação.
 * A tela é apenas de sim ou não e é obrigatório passar uma mensagem.
 *
 */
public class Confirm extends JFrame {
	
	private String message;
	
	public Confirm(String message){
		
		Object[] options = { "Sim", "Não" };
		JOptionPane.showOptionDialog(null, message,
				"Confirmação", JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE, null, options,
				options[0]);
		
	}
}
