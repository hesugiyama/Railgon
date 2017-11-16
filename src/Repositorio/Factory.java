package Repositorio;

import java.util.ArrayList;

import Entidades.*;

/** Representacao de uma Fabrica
 * @author GGTRangers
 */
public class Factory {
	
	/** Responsavel por criar um novo Vagao
	 * @return Retorna a instância de um Vagao
	 */
	public Vagao getVagao(VeiculoFerroviario.Bitola bitola, Vagao.Tipo tipo, Vagao.SubTipo subtipo, char[] proprietario, double comprimento) {
		return new Vagao(bitola, tipo, subtipo, proprietario, comprimento);
	}
	
	/** Responsavel por criar um novo Vagao
	 * @return Retorna a instância de um Vagao
	 */
	public Vagao getVagao(String identificacao, double comprimento) {
		return new Vagao(identificacao, comprimento);
	}
	
	/** Responsavel por criar uma nova Locomotiva
	 * @return Retorna a instância de uma Locomotiva
	 */
	public Locomotiva getLocomotiva(VeiculoFerroviario.Bitola bitola, int classe, String descricao, double comprimento, double pesoMax) {
		return new Locomotiva(bitola, classe, descricao, comprimento, pesoMax);
	}
	
	/** Responsavel por criar uma nova Locomotiva
	 * @return Retorna a instância de uma Locomotiva
	 */
	public Locomotiva getLocomotiva(String bitola, int classe, String descricao, double comprimento, double pesoMax) {
		return new Locomotiva(bitola, classe, descricao, comprimento, pesoMax);
	}
	
	/** Responsavel por criar uma nova Composicao
	 * @return Retorna a instancia de uma Composicao
	 */
	public Composicao getComposicao(Locomotiva l, String descricao) {
		return new Composicao(l,descricao);
	}
	
	/** Responsavel por criar uma nova Composicao
	 * @return Retorna a instancia de uma Composicao
	 */
	public Composicao getComposicao(ArrayList<Locomotiva> l, ArrayList<Vagao> v, String descricao) {
		return new Composicao(l,v,descricao);
	}
	
	/** Responsavel por criar a instancia da Controller 
	 * @return Retorna a instancia de um Controller
	 */
	public Controller getController(){
		return new Controller();
	}
	
	

}
