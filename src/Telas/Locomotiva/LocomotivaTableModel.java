package Telas.Locomotiva;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import Entidades.Locomotiva;
import Telas.Comum.AbstractTable;

public class LocomotivaTableModel extends AbstractTable<Locomotiva> {
    
	private static final int COL_Classe = 0;
	private static final int COL_Descricao = 1;
    private static final int COL_Bitola = 2;
    private static final int COL_Comprimento = 3;
    
    public LocomotivaTableModel(List<Locomotiva> locomotivas, String[] colunasLocomotiva ){
        super(new ArrayList<>(locomotivas), colunasLocomotiva);
    }
    
    
    public Class getColumnClass(int columnIndex) {
       if(columnIndex == COL_Bitola){
           return Character.class;
       }
       if(columnIndex == COL_Comprimento){
           return Double.class;
       }
       if(columnIndex == COL_Classe){
           return Integer.class;
       }
       return String.class;
   }
    
    
    public Object getValueAt(int row, int column) {
        Locomotiva l = linhas.get(row);
        
    	if(column == COL_Bitola) { return (Object) l.getBitola(); } else
        if(column == COL_Comprimento) { return l.getComprimento(); } else
        if(column == COL_Classe) { return l.getClasse(); } else
        if(column == COL_Descricao) { return l.getDescricao(); } return "";
    }
    
}