package Entidades;

/** Representacao de uma Locomotiva
 * @author GGTRangers
 * @see VeiculoFerroviario
 */
public class Locomotiva extends VeiculoFerroviario{
	
	/** Responsavel por guardar a classe da Locomotiva
	 */
	protected int classe;
	
	/** Responsavel por guardar a descricao da Locomotiva
	 */
	protected String descricao;
	
	/** Responsavel por obter a classe da Locomotiva
	 * @return int classe da Locomotivaa
	 */
	public int getClasse() {
		return classe;
	}
	
	/** Responsavel por inserir o valores da classe na Locomotiva
	 * @param classe Inteiro com a classe da Locomotiva
	 */
	public void setClasse(int classe) {
		this.classe = classe;
	}
	
	/** Responsavel por obter a descricao da Locomotiva
	 * @return String com a descricao da Locomotiva
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/** Responsavel por inserir o valors da descricao na Locomotiva
	 * @param descricao String com a descricao da Locomotiva
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

}
