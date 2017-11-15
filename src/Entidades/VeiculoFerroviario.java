package Entidades;

/** Representacao de caracteristicas comuns entre os Vagoes e as Locomotivas
 * @author GGTRangers
 */
public abstract class VeiculoFerroviario {
	
	/** Bitola usado por todos os Vagoes
	 */
	public static enum Bitola {
		A,B,C,D,E,F,G,P,Q,R,S,T,U;
		
		private String b = "ABCDEFG";		
		
		/** Retorna a distancia da Bitola
		 * @return Double com a distancia maxima permitida pela bitola
		 */
		protected double getDistancia(){
			//verifica se a letra escolhida contem dentro da String
			if(b.contains(this.name())){
				return 1.0;
			}else{
				return 1.6;
			}
		}
		
		/** Retorna o peso maximo da bitola
		 * @return Double
		 */				
		protected double getPeso(){
			if((this.name().contains("BP"))){
				return 47.0;
			}
			if((this.name().contains("CQ"))){
				return 64.0;
			}
			if((this.name().contains("DR"))){
				return 80.0;
			}
			if((this.name().contains("ES"))){
				return 100.0;
			}
			if((this.name().contains("FT"))){
				return 119.0;
			}
			if((this.name().contains("GU"))){
				return 143.0;
			}
			return 30.0;
		}
	}
	
	/** id do Objeto utilizado na base de dados
	 */
	protected int id;
	
	/** Comprimento
	 */
	protected double comprimento;
	
	/** Ordem do Objto na Composicao
	 */
	protected int ordemComposicao;
	
	/** Bitola
	 */
	protected Bitola bitola;
	
	/** Responsavel por obter o comprimento 
	 * @return double com o comprimento
	 */
	public double getComprimento() {
		return comprimento;
	}
	
	/** Responsavel por obter o id
	 * @return int
	 */
	public int getId() {
		return id;
	}
	
	/** Responsavel por inserir o id do objeto
	 */
	public void setId(int id) {
		this.id = id;
	}

	/** Responsavel por obter a ordem na composicao do Objeto
	 * @return int
	 */
			
	public int getOrdemComposicao() {
		return ordemComposicao;
	}

	/** Responsavel por inserir a ordem na composicao
	 * @param ordemComposicao
	 */
	public void setOrdemComposicao(int ordemComposicao) {
		this.ordemComposicao = ordemComposicao;
	}

	/** Responsavel por inserir o comprimento
	 * @param comprimento
	 */
	public void setComprimento(double comprimento) {
		this.comprimento = comprimento;
	}
	
	/** Responsavel por obter a bitola
	 * @return char com a bitola
	 */
	public char getBitola() {
		return bitola.name().charAt(0);
	}

	/** Responsavel por inserir a bitola
	 * @param bitola
	 */
	public void setBitola(Bitola bitola) {
		this.bitola = bitola;
	}
	
	/** Responsavel por obter o peso maximo admissivel pela bitola
	 * @return char com o peso maximo admissivel pela bitola
	 */
	public double getPesoMaxBitola() {
		//return Bitola.valueOf((String.valueOf(this.bitola))).getPeso();
		return bitola.getPeso();
	}
	
	/** Responsavel por obter a distancia maxima da bitola
	 * @return char com a distancia maxima da bitola
	 */
	public double getDistanciaMaxBitola() {
		return bitola.getDistancia();
	}
}
