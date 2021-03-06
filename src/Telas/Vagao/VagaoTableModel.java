package Telas.Vagao;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import Entidades.Locomotiva;
import Entidades.Vagao;
import Entidades.Vagao.Tipo;
import Telas.Comum.AbstractTable;

public class VagaoTableModel extends AbstractTable<Vagao> {
	
	
	// Vari�veis para facilitar o entendimento na hora da compara��o
	private static final int COL_Identificacao = 0;
	private static final int COL_Bitola = 1;
	private static final int COL_Comprimento = 2;
	private static final int COL_Tipo = 3;
	private static final int COL_Subtipo = 4;
	private static final int COL_Proprietario = 5;
	
	
	// Construtor que define o tipo de linhas da tabela e passa as colunas
	public VagaoTableModel(List<Vagao> vagoes, String[] colunasVagao){
		super(vagoes, colunasVagao);
	}
	
	/** 
	 * @return tipo de dado referente a coluna especificada no par�metro
	 */
	public Class getColumnClass(int columnIndex) {
        if(columnIndex == COL_Bitola || columnIndex == COL_Tipo || columnIndex == COL_Subtipo){
        	return Character.class;
        }
        if(columnIndex == COL_Comprimento){
        	return Double.class;
        }
        return String.class;
    }
	
	
	// M�todo implementado para AbstractTableModel
	/**
	 * @return Retorna o conte�do da c�lula especificada como par�metro
	 */
	public Object getValueAt(int row, int column) {
		Vagao v = linhas.get(row);
		
		if(column == COL_Bitola) { return v.getBitola(); } else
		if(column == COL_Comprimento) { return v.getComprimento(); } else
		if(column == COL_Tipo) { return v.getTipo(); } else
		if(column == COL_Subtipo) { return v.getSubTipo(); } else
		if(column == COL_Proprietario) { return String.valueOf(v.getProprietario()); } else
		if(column == COL_Identificacao) { return v.getIdentificacao(); } return "";
	}
	
	public void addVagao(Vagao vagao) {
        linhas.add(vagao);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
    
    public void updateVagao(int indiceLinha, Vagao vagao) {
        linhas.set(indiceLinha, vagao);
        fireTableRowsUpdated(indiceLinha, indiceLinha);
    }

    public void removeVagao(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }
}
