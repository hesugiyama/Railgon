package Entidades;

/** Repesentacao de um Vagao
 * @author GGTRangers
 * @see VeiculoFerroviario
 */
public class Vagao extends VeiculoFerroviario {
	
	public static enum Tipo {G,P,T,F,I,H,A,C};
	public static enum SubTipo {D,P,F,M,T,S,H,C,B,N,Q,E,R,G,A,L,V};
	
	/** Tipo	
	 */
	protected Tipo tipo;
	
	/** SubTipo
	 */
	protected SubTipo subTipo;
	
	/** Proprietario 
	 */
	protected char[] proprietario;
	
	/** Construtor do Vagao
	 * @param bitola
	 * @param tipo
	 * @param subtipo
	 * @param proprietario
	 * @param comprimento
	 */
	public Vagao(Bitola bitola, Tipo tipo, SubTipo subtipo, char[] proprietario, double comprimento){
		setBitola(bitola);
		setTipo(tipo);
		setSubTipo(subtipo);
		setProprietario(proprietario);
		setComprimento(comprimento); 
	}
	
	/** Responsavel por obter a identificacao
	 * @return char[]
	 */
	public char[] getIdentificacao() {
		return identificacao;
	}

	/** Responsavel por inserir a identificacao
	 * @param identificacao
	 */
	public void setIdentificacao(char[] identificacao) {
		this.identificacao = identificacao;
	}

	/** Concatenacao de Tipo,SubTipo e o proprietario
	 */
	protected char[] identificacao;
	
	/** Responsavel por obter o tipo do Vagao
	 * @return char com o tipo do vagaor
	 */
	public char getTipo() {
		return tipo.name().charAt(0);
	}
	
	/** Responsavel por inserir o tipo do Vagao
	 * @param tipo
	 */
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	/** Responsavel por obter o subTipo do Vagao
	 * @return char com o subTipo do vagao
	 */
	public char getSubTipo() {
		return subTipo.name().charAt(0);
	}
	
	/** Responsavel por inserir o subTipo do Vagao
	 * @param subTipo
	 */
	public void setSubTipo(SubTipo subTipo) {
		this.subTipo = subTipo;
	}
	
	/** Responsavel por obter o proprietario do Vagao 
	 * @return char com o proprietario do vagao
	 */
	public char[] getProprietario() {
		return proprietario;
	}
	
	/** Responsavel por inserie o proprietario do Vagao
	 * @param proprietario
	 */
	public void setProprietario(char[] proprietario) {
		this.proprietario = proprietario;
	}
}
