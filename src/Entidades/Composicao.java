package Entidades;

import java.util.ArrayList;

/** Representacao de uma Composicao
 * @author GGTRangers
 */
public class Composicao {
	
	/** Identificador unico da Composicao 
	 */
	protected String codigo;
	
	/** Lista com todas as locomotivas que pertencem a Composicao 
	 */
	protected ArrayList<Locomotiva> locomotivas;
	
	/** Lista com todos os vagoes que pertencem a Composicao
	 */
	protected ArrayList<Vagao> vagoes;
	
	/** A A soma dos comprimentos dos elementos da composicao
	 */
	protected double comprimento;
	
	/** Obtem o codigo da Composicao
	 * @return String com o codigo da Composicao
	 */
	public String getCodigo() {
		return codigo;
	}
	
	/** A bitola da primeira locomotiva inserida na composicao
	 */
	private char bitola;
	
	/** Insere o codigo da composicao
	 * @param codigo 
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	/** Obtem a lista de Locomotivas da Composicao
	 * @return ArrayList com as Locomotivas da Composicao
	 */
	public ArrayList<Locomotiva> getLocomotivas() {
		return locomotivas;
	}
	
	/** Insere as Locomotivas na Composicao
	 * @param locomotivas 
	 */
	public void setLocomotivas(ArrayList<Locomotiva> locomotivas) {
		this.locomotivas = locomotivas;
	}
	
	/** Otbtem a lista de Vagoes da Composicao
	 * @return ArrayList com os Vagoes da composicao
	 */
	public ArrayList<Vagao> getVagoes() {
		return vagoes;
	}
	
	/** Insere os vagoes na composicao
	 * @param vagoes
	 */
	public void setVagoes(ArrayList<Vagao> vagoes) {
		this.vagoes = vagoes;
	}
	
	/** Responsavel por informar a quantidade de vagoes na composicao
	 * @return int
	 */
	public int getQtdVagao(){
		return this.vagoes.size();
	}
	
	/** Responsavel por informar a quantidade de locomotivas na composicao
	 * @return int
	 */
	public int getQtdLocomotiva(){
		return this.locomotivas.size();
	}

	/** Reponsavel por obter o comprimento da composicao
	 * @return double
	 */
	public double getComprimento() {
		return comprimento;
	}
	
	/** Responsavel por inserir o comprimento na composicao
	 * @param comprimento
	 */
	public void setComprimento(double comprimento) {
		this.comprimento = comprimento;
	}

	/** Responsavel por inserir um Vagao possa ser inserido na composicao
	 * @param v
	 */
	public void add(Vagao v){
		if(this.locomotivas.isEmpty()){
			throw new RuntimeException("Nenhuma Locomotiva na composição!");
		}
		this.vagoes.add(v);		
	}
	
	/** Responsavel por inserir uma Locomotiva dentro da composicao
	 * @param l
	 */
	public void add(Locomotiva l){
		if(this.locomotivas.isEmpty()){
			this.bitola = l.getBitola();
		}
		this.locomotivas.add(l);
	}
}
 