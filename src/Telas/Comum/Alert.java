package Telas.Comum;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/** Criação de um alerta genérico
* Retorna um alerta do tipo que for informado
* 0 - Erro, 1 - Info, 2 - Warning, 3 - Questão
**/
public class Alert extends JFrame{
	private String title;
	private int tipo;
	private String message;
	
	public Alert(int tipo, String title, String message){
		JOptionPane.showMessageDialog(Alert.this, message, title, tipo);
	}

}

