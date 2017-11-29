package Telas.Comum;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * 
 * @author GGTRangers
 *
 * @param <T>
 * 
 * Classe genérica para criação de tabelas
 */

public abstract class AbstractTable<T> extends AbstractTableModel {
	
	// Lista de uma entidade que será as linhas
	protected List<T> linhas;
	
	// Cabeçalho da tabela
	protected String[] colunas;
	
	// Construtor
	public AbstractTable(List<T> entidade, String[] colunas){
		// Define as linhas
		this.linhas = new ArrayList<>(entidade);
		// Define o cabeçalho
		this.colunas = colunas;
	}
	
	// Método implementado para AbstractTableModel
	/**
	 * @return número de linhas da tabela
	 */
	public int getRowCount(){
		return linhas.size();
	}
	
	// Método implementado para AbstractTableModel
	/**
	 * @return número de colunas da tabela
	 */
	public int getColumnCount(){
		return colunas.length;
	}
	
	// Método implementado para AbstractTableModel
	/**
	 * @return nome da coluna referente ao inde especificado 
	 */
	public String getColumnName(int indexColuna){
		return colunas[indexColuna];
	}

}
