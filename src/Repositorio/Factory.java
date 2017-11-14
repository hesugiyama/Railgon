package Repositorio;

import Entidades.Vagao;
import Entidades.Locomotiva;
import Entidades.Composicao;

/** Representacao de uma Fabrica
 * @author GGTRangers
 */
public class Factory {
	
	/** Responsavel por criar um novo Vagao
	 * @return Retorna a instância de um Vagao
	 */
	public Vagao getVagao() {
		return new Vagao();
	}
	
	/** Responsavel priar uma nova Locomotiva
	 * @return Retorna a instância de uma Locomotiva
	 */
	public Locomotiva getLocomotiva() {
		return new Locomotiva();
	}
	
	/** Responsavel por criar uma nova Composicao
	 * @return Retorna a instancia de uma Composicao
	 */
	public Composicao geComposicao() {
		return new Composicao();
	}
	
	/** Responsavel por criar a instancia da Controller 
	 * @return Retorna a instancia de um Controller
	 */
	public Controller getController(){
		return new Controller();
	}
	
	

}
