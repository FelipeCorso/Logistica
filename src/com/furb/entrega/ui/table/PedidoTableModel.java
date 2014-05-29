package com.furb.entrega.ui.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.furb.produto.Produto;

public class PedidoTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	/* Lista de Produtos que representam as linhas. */
	private List<Produto> linhas;

	/* Array de Strings com o nome das colunas. */
	private String[] colunas = new String[] { "Código", "Descrição" };

	public PedidoTableModel() {
		linhas = new ArrayList<Produto>();
	}

	/* Retorna a quantidade de colunas. */
	@Override
	public int getColumnCount() {
		// Está retornando o tamanho do array "colunas".
		// Mas como o array é fixo, vai sempre retornar 2.
		return colunas.length;
	}

	/* Retorna a quantidade de linhas. */
	@Override
	public int getRowCount() {
		// Retorna o tamanho da lista de produtos.
		return linhas.size();
	}

	/*
	 * Seta o valor da célula especificada pelos índices da linha e da coluna.
	 * Aqui ele está implementado para não fazer nada, até porque este table
	 * model não é editável.
	 */
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	};

	/*
	 * Retorna um valor booleano que define se a célula em questão pode ser
	 * editada ou não. Este método é utilizado pela JTable na hora de definir o
	 * editor da célula. Neste caso, estará sempre retornando false, não
	 * permitindo que nenhuma célula seja editada.
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// Pega o sócio da linha especificada.
		Produto produto = linhas.get(rowIndex);

		// Retorna o campo referente a coluna especificada.
		// Aqui é feito um switch para verificar qual é a coluna
		// e retornar o campo adequado. As colunas são as mesmas
		// que foram especificadas no array "colunas".
		switch (columnIndex) {
		case 0: // Primeira coluna é o nome.
			return produto.getCodigo();
		case 1: // Segunda coluna é o telefone.
			return produto.getDescricao();
		default:
			// Se o índice da coluna não for válido, lança um
			// IndexOutOfBoundsException (Exceção de índice fora dos limites).
			// Não foi necessário verificar se o índice da linha é inválido,
			// pois o próprio ArrayList lança a exceção caso seja inválido.
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}
	}

	/* Adiciona um registro. */
	public void addProduto(Produto produto) {
		// Adiciona o registro.
		linhas.add(produto);

		// Pega a quantidade de registros e subtrai um para achar
		// o último índice. É preciso subtrair um, pois os índices
		// começam pelo zero.
		int ultimoIndice = getRowCount() - 1;

		// Reporta a mudança. O JTable recebe a notificação
		// e se redesenha permitindo que visualizemos a atualização.
		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	} /*
	 * Retorna o nome da coluna no índice especificado. Este método é usado pela
	 * JTable para saber o texto do cabeçalho.
	 */

	@Override
	public String getColumnName(int columnIndex) {
		// Retorna o conteúdo do Array que possui o nome das colunas
		// no índice especificado.
		return colunas[columnIndex];
	};/* Remove todos os registros. */

	public void limpar() {
		// Remove todos os elementos da lista de produtos.
		linhas.clear();

		// Reporta a mudança. O JTable recebe a notificação
		// e se redesenha permitindo que visualizemos a atualização.
		fireTableDataChanged();
	}

	/* Verifica se este table model está vazio. */
	public boolean isEmpty() {
		return linhas.isEmpty();
	}

	public List<Produto> getLinhas() {
		return linhas;
	}
}
