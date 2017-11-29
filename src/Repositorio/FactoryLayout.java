package Repositorio;

import Telas.Vagao.*;
import Telas.Locomotiva.*;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import Telas.Composicao.*;
import Telas.Comum.Alert;
import Telas.Comum.Confirm;
import Telas.Outras.*;

/** Representação de uma Fabrica para chamada de telas
 * @author GGTRangers
 */
public class FactoryLayout {
	
	/** Responsavel por exibir a tela de adicionar vagão
	 * @return void
	 */
	public JPanel openAdicionarVagao(){
		return new AdicionarVagao().GetPanel();
	}
	
	/** Responsavel por exibir a tela de listar vagão
	 * @return void
	 */
	/*public void openListarVagao(){
		new ListarVagao().setVisible(true);
	}*/
	public JPanel openListarVagao(){
		return new ListarVagao().GetPanel();
	}
	
	/** Responsavel por exibir a tela de adicionar locomotiva
	 * @return void
	 */
	public JPanel openAdicionarLocomotiva(){
		return new AdicionarLocomotiva().GetPanel();
	}
	
	/** Responsavel por exibir a tela de listar locomotiva
	 * @return void
	 */
	public JPanel openListarLocomotiva(){
		return new ListarLocomotiva().GetPanel();
	}
	
	/** Responsavel por exibir a tela de adicionar composição
	 * @return void
	 */
	public JPanel openAdicionarComposicao(){
		return new AdicionarComposicao().GetPanel();
	}

	/** Responsavel por exibir a tela de listar composição
	 * @return void
	 */
	public JPanel openListarComposicao(){
		return new ListarComposicao().GetPanel();
	}

	/** Responsavel por exibir a tela de sobre
	 * @return void
	 */
	public void openSobre(){
		new Sobre().setVisible(true);
	}
	

	/** Responsavel por exibir o alerta de erro
	 * @return void
	 */
	public void openAlertError(String title, String message){
		new Alert(0, title, message);
	}
	
	/** Responsavel por exibir o alerta de informação
	 * @return void
	 */
	public void openAlertInfo(String title, String message){
		new Alert(1, title, message);
	}
	
	/** Responsavel por exibir o alerta de warning
	 * @return void
	 */
	public void openAlertWarning(String title, String message){
		new Alert(2, title, message);
	}
	
	/** Responsavel por exibir o alerta de questão
	 * @return void
	 */
	public void openAlertQuestion(String title, String message){
		new Alert(3, title, message);
	}
	
	/** Responsavel por exibir um "modal" de confirmação
	 * @return void
	 */
	public int openConfirm(String message){
		return new Confirm(message).getRetorno();
	}
	
}
