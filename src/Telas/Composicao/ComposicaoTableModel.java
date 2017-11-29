package Telas.Composicao;

import java.util.ArrayList;
import java.util.List;

import Entidades.Composicao;
import Entidades.Locomotiva;

import Telas.Comum.AbstractTable;

public class ComposicaoTableModel extends AbstractTable<Composicao>{
	
	private static final int COL_Codigo = 0;
	private static final int COL_Descricao = 1;
    private static final int COL_Locomotiva = 2;
    private static final int COL_Vagoes = 3;
    private static final int COL_Comprimento = 4;
    private static final int COL_PesoMax = 5;
    private static final int COL_PesoAtual = 6;
    
    public ComposicaoTableModel(List<Composicao> composicao, String[] colunasComposicao ){
        super(new ArrayList<>(composicao), colunasComposicao);
    }
    
    
    public Class getColumnClass(int columnIndex) {
       if(columnIndex == COL_Codigo || columnIndex == COL_Descricao){
           return String.class;
       }
       if(columnIndex == COL_Locomotiva || columnIndex == COL_Vagoes){
           return Integer.class;
       }
       return Double.class;
   }
    
    
    public Object getValueAt(int row, int column) {
        Composicao composicao = linhas.get(row);
        		
    	if(column == COL_Codigo) { return composicao.getCodigo(); } else
    	if(column == COL_Descricao) { return composicao.getDescricao(); } else
    	if(column == COL_Locomotiva) { return (Object) composicao.getLocomotivas().size(); } else
    	if(column == COL_Vagoes) { return (Object) composicao.getVagoes().size(); } else
    	if(column == COL_Comprimento) { return (Object) composicao.getComprimento(); } else
    	if(column == COL_PesoMax) { return (Object) composicao.getPesoMax(); } else
    	if(column == COL_PesoAtual) { return (Object) composicao.getPesoAtual(); } else return "";
    		
    }
    
}