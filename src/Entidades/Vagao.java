
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
	
	/** Concatenacao de Tipo,SubTipo e o proprietario
	 */
	protected String identificacao;
	
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
		setIdentificacao(tipo, subtipo, bitola, proprietario);
	}
	
	/** Responsavel por costruir um vagao com a bitola, a identificacao e o comprimento
	 * @param bitola Bitola que o vagao esta usando
	 * @param identificacao String com o Tipo,Subtipo e o proprietario concatenados 
	 * @param comprimento Comprimento do vagao
	 */
	public Vagao(String identificacao, double comprimento){
		setIdentificacao(identificacao);
		setComprimento(comprimento); 
	}	
	
	/** Responsavel por obter a identificacao
	 * @return char[]
	 */
	public String getIdentificacao() {
		return identificacao;
	}

	/** Responsavel por inserir a identificacao
	 * @param identificacao
	 */
	protected void setIdentificacao(String identificacao) {
		this.tipo    = Tipo.valueOf(identificacao.substring(0,1));
		this.subTipo = SubTipo.valueOf(identificacao.substring(1,2));
		this.bitola  = Bitola.valueOf(identificacao.substring(2,3));
		this.proprietario = identificacao.substring(3).toCharArray();
		this.identificacao = identificacao;
	}
	
	/** Responsavel por inserir a identificadao a partir do tipo, subtipo, bitola e proprietario
	 * @param identificacao
	 */
	protected void setIdentificacao(Tipo tipo, SubTipo subTipo, Bitola bitola, char[] proprietario) {
		StringBuilder sb = new StringBuilder();
		sb.append(tipo.name());
		sb.append(subTipo.name());
		sb.append(bitola.name());
		sb.append(proprietario);
		this.identificacao = sb.toString();
	}
	
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
	
	public void teste(String teste){
		/*Bitola t;
		t = Bitola.valueOf(teste);*/
		System.out.println(teste);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("IDENTIFICACAO...: ");
		sb.append(getIdentificacao());
		sb.append("\n");
		sb.append("PROPRIETARIO....: ");
		sb.append(getProprietario());
		sb.append("\n");
		sb.append("BITOLA..........: ");
		sb.append(getBitola());
		sb.append("\n");
		sb.append("TIPO............: ");
		sb.append(getTipo());
		sb.append("\n");
		sb.append("SUBTIPO.........: ");
		sb.append(getSubTipo());
		sb.append("\n");
		sb.append("COMPRIMENTO.....: ");
		sb.append(getComprimento());
		sb.append("\n");
		sb.append("DISTANCIA BITOLA: ");
		sb.append(getDistanciaMaxBitola());
		sb.append("\n");
		sb.append("PESO BITOLA.....: ");
		sb.append(getPesoMaxBitola());		
		return sb.toString();
	}
	
	@Override
	public void save() {
		
	}
}
