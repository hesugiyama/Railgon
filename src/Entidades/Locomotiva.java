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
	
	/** Responsavel por guardar o peso maximo admissivel pela Locomotiva
	 */
	protected double pesoMax;	
	
	/** Responsavel por instancial uma Locomotiva
	 * @param bitola
	 * @param classe
	 * @param descricao
	 * @param comprimento
	 * @param pesoMax
	 */
	public Locomotiva(Bitola bitola, int classe, String descricao, double comprimento, double pesoMax){
		setBitola(bitola);
		setClasse(classe);
		setDescricao(descricao);
		setComprimento(comprimento);
		setPesoMax(pesoMax);
	}
	
	public Locomotiva(String bitola, int classe, String descricao, double comprimento, double pesoMax){
		setBitola(bitola);
		setClasse(classe);
		setDescricao(descricao);
		setComprimento(comprimento);
		setPesoMax(pesoMax);
	}
	
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
	
	/** Responsavel por obter o peso maximo admissivel pela Locomotiva
	 * @return char com o peso maximo admissivel pela Locomotiva
	 */
	public double getPesoMax() {
		return pesoMax;
	}
	
	/** Responsavel por inserir o peso maximo inserido pela Locomotiva
	 * @param pesoMax
	 */
	public void setPesoMax(double pesoMax){
		this.pesoMax = pesoMax;
	}

	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		/*
		sb.append("CLASSE..............: ");
		sb.append(getClasse());
		sb.append("\n");
		sb.append("DESCRICAO...........: ");
		sb.append(getDescricao());
		sb.append("\n");
		sb.append("COMPRIMENTO.........: ");
		sb.append(getComprimento());
		sb.append("\n");
		sb.append("PESO MAX:...........: ");
		sb.append(getPesoMax());
		sb.append("\n");
		sb.append("BITOLA..............: ");
		sb.append(getBitola());
		sb.append("\n");
		sb.append("PESO MAX BITOLA:....: ");
		sb.append(getPesoMaxBitola());
		sb.append("\n");
		sb.append("DISTANCIA MAX BITOLA: ");
		sb.append(getDistanciaMaxBitola());
		*/
		sb.append(getClasse());
		
		return sb.toString();
	}
	
	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}
}
