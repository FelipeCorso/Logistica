package com.furb.entrega.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.furb.entrega.Entrega_jomp;
import com.furb.entrega.ui.table.PedidoTableModel;
import com.furb.pedido.Pedido;
import com.furb.produto.Produto;
import com.furb.veiculo.Veiculo;
import com.furb.veiculo.combustivel.EnCombustivel;
import com.furb.veiculo.motorista.Motorista;
import com.furb.veiculo.tipo.EnTipoVeiculo;

public class CadastrarVeiculos extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane panelAbas = new JTabbedPane();
	private JPanel panelVeiculo = new JPanel();
	private JPanel panelMotorista = new JPanel();
	private JPanel panelPedido = new JPanel();
	private JTextField tfNome = new JTextField();
	private JTextField tfRg = new JTextField();
	private JComboBox<Motorista> cbMotorista = new JComboBox<Motorista>();
	private JComboBox<EnTipoVeiculo> cbTipo = new JComboBox<EnTipoVeiculo>();
	private JComboBox<EnCombustivel> cbCombustivel = new JComboBox<EnCombustivel>();
	private JFormattedTextField ftfPlaca = new JFormattedTextField();
	private List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
	private final JLabel lblCodPedidoDesc = new JLabel("Código do Pedido:");
	private JLabel lblCodPedido = null;
	private final JLabel lblAdicionarProduto = new JLabel("Adicionar Produto");
	private final JLabel lblCodProdutoDesc = new JLabel("Código do Produto:");
	private JLabel lblCodProduto = null;
	private final JLabel lblDescProduto = new JLabel("Descrição do Produto:");
	private final JButton btnCadPedido = new JButton("Cadastrar");
	private JTable tabelaProdutos;
	private final JTextField tfDescProduto = new JTextField();
	private final JButton btnAddProduto = new JButton("Adicionar Produto");
	private int contadorCodPedido = 1;
	private int contadorCodProduto = 1;
	private final JLabel lblPedido = new JLabel("Pedido:");
	private final JComboBox<Pedido> cbPedido = new JComboBox<Pedido>();
	private JCheckBox chckbxDemonstrao;

	public CadastrarVeiculos() {

		lblCodPedido = new JLabel(String.valueOf(contadorCodPedido));
		lblCodProduto = new JLabel(String.valueOf(contadorCodProduto));
		tfDescProduto.setColumns(10);
		lblAdicionarProduto.setFont(new Font("Tahoma", Font.BOLD, 12));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new FlowLayout());
		setBounds(100, 100, 533, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setVisible(true);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(
				gl_contentPane.createSequentialGroup().addComponent(panelAbas, GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE).addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(
				gl_contentPane.createSequentialGroup().addComponent(panelAbas, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE).addContainerGap()));

		panelVeiculo.setPreferredSize(new Dimension(200, 200));

		JLabel lblTipo = new JLabel("Tipo:");
		panelAbas.addTab("Veículo", null, panelVeiculo, "Cadastro de Veículo");
		panelAbas.addTab("Motorista", null, panelMotorista, "Cadastro de Motorista");
		panelAbas.addTab("Pedido", null, panelPedido, "Cadastro de Pedidos e Produtos");

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panelPedido = new GroupLayout(panelPedido);
		gl_panelPedido.setHorizontalGroup(gl_panelPedido.createParallelGroup(Alignment.LEADING).addGroup(
				gl_panelPedido
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_panelPedido
										.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
										.addGroup(gl_panelPedido.createSequentialGroup().addComponent(lblCodPedidoDesc).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblCodPedido))
										.addComponent(btnCadPedido)
										.addGroup(
												gl_panelPedido
														.createSequentialGroup()
														.addGroup(gl_panelPedido.createParallelGroup(Alignment.LEADING).addComponent(lblCodProdutoDesc).addComponent(lblDescProduto))
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(
																gl_panelPedido.createParallelGroup(Alignment.LEADING).addComponent(lblCodProduto)
																		.addComponent(tfDescProduto, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE))
										.addGroup(gl_panelPedido.createSequentialGroup().addComponent(lblAdicionarProduto).addPreferredGap(ComponentPlacement.RELATED, 360, Short.MAX_VALUE))
										.addComponent(btnAddProduto)).addContainerGap()));

		btnCadPedido.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (!((PedidoTableModel) tabelaProdutos.getModel()).isEmpty()) {
					Pedido pedido = new Pedido();
					pedido.setCodigo(contadorCodPedido);

					for (Produto produto : ((PedidoTableModel) tabelaProdutos.getModel()).getLinhas()) {
						pedido.addProduto(produto);
					}

					cbPedido.addItem(pedido);
					contadorCodPedido++;
					lblCodPedido.setText(String.valueOf(contadorCodPedido));
					contadorCodProduto = 1;
					lblCodProduto.setText(String.valueOf(contadorCodProduto));
					tfDescProduto.setText("");
					((PedidoTableModel) tabelaProdutos.getModel()).limpar();
				}

			}
		});
		gl_panelPedido.setVerticalGroup(gl_panelPedido.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panelPedido
								.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_panelPedido.createParallelGroup(Alignment.BASELINE).addComponent(lblCodPedidoDesc).addComponent(lblCodPedido))
								.addGap(27)
								.addComponent(lblAdicionarProduto)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panelPedido.createParallelGroup(Alignment.BASELINE).addComponent(lblCodProdutoDesc).addComponent(lblCodProduto))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panelPedido.createParallelGroup(Alignment.BASELINE).addComponent(lblDescProduto)
												.addComponent(tfDescProduto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnAddProduto).addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED).addComponent(btnCadPedido)
								.addContainerGap()));

		tabelaProdutos = new JTable(new PedidoTableModel());
		tabelaProdutos.setBorder(new LineBorder(null, 0));
		tabelaProdutos.setBackground(SystemColor.menu);
		tabelaProdutos.setShowVerticalLines(true);
		tabelaProdutos.setShowHorizontalLines(true);
		tabelaProdutos.setVisible(true);
		scrollPane.setViewportView(tabelaProdutos);
		panelPedido.setLayout(gl_panelPedido);

		JLabel lblNome = new JLabel("Nome:");

		JLabel lblRg = new JLabel("RG:");

		tfNome.setColumns(10);

		JButton btnCadMotorista = new JButton("Cadastrar");
		btnCadMotorista.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Motorista motorista = new Motorista();

				if (isNaoVazio(tfNome.getText())) {
					motorista.setNome(tfNome.getText());
				}
				if (isNaoVazio(tfRg.getText())) {
					motorista.setRg(tfRg.getText());
				}

				cbMotorista.addItem(motorista);
				limpaCamposMotorista();

			}

			private void limpaCamposMotorista() {
				tfNome.setText("");
				tfRg.setText("");
			}
		});

		tfRg.setColumns(10);
		GroupLayout gl_panelMotoristas = new GroupLayout(panelMotorista);
		gl_panelMotoristas.setHorizontalGroup(gl_panelMotoristas.createParallelGroup(Alignment.LEADING).addGroup(
				gl_panelMotoristas
						.createSequentialGroup()
						.addGroup(
								gl_panelMotoristas
										.createParallelGroup(Alignment.LEADING)
										.addGroup(
												gl_panelMotoristas
														.createSequentialGroup()
														.addContainerGap()
														.addGroup(gl_panelMotoristas.createParallelGroup(Alignment.LEADING).addComponent(lblNome).addComponent(lblRg))
														.addGap(16)
														.addGroup(
																gl_panelMotoristas.createParallelGroup(Alignment.LEADING)
																		.addComponent(tfNome, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE)
																		.addComponent(tfRg, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)))
										.addGroup(gl_panelMotoristas.createSequentialGroup().addGap(10).addComponent(btnCadMotorista))).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_panelMotoristas.setVerticalGroup(gl_panelMotoristas.createParallelGroup(Alignment.LEADING).addGroup(
				gl_panelMotoristas
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_panelMotoristas.createParallelGroup(Alignment.BASELINE).addComponent(lblNome)
										.addComponent(tfNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(9)
						.addGroup(
								gl_panelMotoristas.createParallelGroup(Alignment.BASELINE).addComponent(lblRg)
										.addComponent(tfRg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(58).addComponent(btnCadMotorista)
						.addContainerGap(90, Short.MAX_VALUE)));
		panelMotorista.setLayout(gl_panelMotoristas);

		cbTipo.setModel(new DefaultComboBoxModel<EnTipoVeiculo>(EnTipoVeiculo.values()));

		JLabel lblCombustvel = new JLabel("Combustível:");

		cbCombustivel.setModel(new DefaultComboBoxModel<EnCombustivel>(EnCombustivel.values()));

		JLabel lblPlaca = new JLabel("Placa:");

		ftfPlaca.setText("###-0000");
		ftfPlaca.setToolTipText("");

		JLabel lblMotorista = new JLabel("Motorista:");

		JButton btnCadVeiculo = new JButton("Cadastrar");
		btnCadVeiculo.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Veiculo veiculo = new Veiculo();
				veiculo.setEnTipoVeiculo(cbTipo.getItemAt(cbTipo.getSelectedIndex()));
				veiculo.setEnCombustivel(cbCombustivel.getItemAt(cbCombustivel.getSelectedIndex()));
				veiculo.setPlaca(ftfPlaca.getText());
				veiculo.setMotorista(cbMotorista.getItemAt(cbMotorista.getSelectedIndex()));
				veiculo.addPedido(cbPedido.getItemAt(cbPedido.getSelectedIndex()));

				zeraComboBox();

				getListaVeiculos().add(veiculo);

			}

		});

		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void mouseClicked(MouseEvent arg0) {

				try {
					if (chckbxDemonstrao.isSelected()) {
						File file = new File("Utils/Logistica.txt");

						FileInputStream fs = new FileInputStream(file);
						BufferedInputStream bs = new BufferedInputStream(fs);
						ObjectInputStream objInput = new ObjectInputStream(bs);

						getCadastrarVeiculos().setListaVeiculos((List<Veiculo>) objInput.readObject());
						objInput.close();

					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				Entrega_jomp.efetuarEntrega_jomp(getCadastrarVeiculos().getListaVeiculos());
				getCadastrarVeiculos().dispose();
			}
		});

		chckbxDemonstrao = new JCheckBox("Demonstração");
		GroupLayout gl_panelVeiculos = new GroupLayout(panelVeiculo);
		gl_panelVeiculos.setHorizontalGroup(gl_panelVeiculos.createParallelGroup(Alignment.TRAILING).addGroup(
				gl_panelVeiculos
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_panelVeiculos
										.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCombustvel)
										.addGroup(
												gl_panelVeiculos
														.createSequentialGroup()
														.addGroup(
																gl_panelVeiculos.createParallelGroup(Alignment.LEADING).addComponent(lblTipo).addComponent(lblPlaca).addComponent(lblMotorista)
																		.addComponent(lblPedido, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
														.addGap(87)
														.addGroup(
																gl_panelVeiculos
																		.createParallelGroup(Alignment.LEADING)
																		.addComponent(cbPedido, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
																		.addComponent(cbCombustivel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																		.addComponent(cbTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																		.addGroup(
																				gl_panelVeiculos.createParallelGroup(Alignment.TRAILING, false)
																						.addComponent(cbMotorista, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																						.addComponent(ftfPlaca, Alignment.LEADING))))
										.addGroup(
												gl_panelVeiculos.createSequentialGroup().addComponent(btnCadVeiculo).addPreferredGap(ComponentPlacement.RELATED, 229, Short.MAX_VALUE)
														.addComponent(chckbxDemonstrao).addGap(18).addComponent(btnOk))).addContainerGap()));
		gl_panelVeiculos.setVerticalGroup(gl_panelVeiculos.createParallelGroup(Alignment.LEADING).addGroup(
				gl_panelVeiculos
						.createSequentialGroup()
						.addGap(5)
						.addGroup(
								gl_panelVeiculos.createParallelGroup(Alignment.BASELINE).addComponent(cbTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTipo))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_panelVeiculos.createParallelGroup(Alignment.BASELINE).addComponent(lblCombustvel)
										.addComponent(cbCombustivel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_panelVeiculos.createParallelGroup(Alignment.BASELINE).addComponent(lblPlaca)
										.addComponent(ftfPlaca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_panelVeiculos.createParallelGroup(Alignment.BASELINE).addComponent(lblMotorista)
										.addComponent(cbMotorista, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_panelVeiculos.createParallelGroup(Alignment.LEADING).addGroup(gl_panelVeiculos.createSequentialGroup().addGap(3).addComponent(lblPedido))
										.addComponent(cbPedido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 185, Short.MAX_VALUE)
						.addGroup(gl_panelVeiculos.createParallelGroup(Alignment.BASELINE).addComponent(btnOk).addComponent(btnCadVeiculo).addComponent(chckbxDemonstrao)).addContainerGap()));
		gl_panelVeiculos.linkSize(SwingConstants.HORIZONTAL, new Component[] { cbTipo, ftfPlaca });
		panelVeiculo.setLayout(gl_panelVeiculos);
		contentPane.setLayout(gl_contentPane);

		btnAddProduto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (!tfDescProduto.getText().equalsIgnoreCase("")) {
					Produto produto = new Produto();
					produto.setCodigo(contadorCodProduto);
					produto.setDescricao(tfDescProduto.getText());

					((PedidoTableModel) tabelaProdutos.getModel()).addProduto(produto);
					contadorCodProduto++;
					lblCodProduto.setText(String.valueOf(contadorCodProduto));
					tfDescProduto.setText("");
				}

			}
		});

		zeraComboBox();

	}

	public static void main(String[] args) {
		new CadastrarVeiculos();
	}

	private boolean isNaoVazio(String str) {
		return !str.equalsIgnoreCase("");
	}

	public List<Veiculo> getListaVeiculos() {
		return this.listaVeiculos;
	}

	public void setListaVeiculos(List<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}

	private CadastrarVeiculos getCadastrarVeiculos() {
		return this;
	}

	private void zeraComboBox() {
		cbTipo.setSelectedIndex(-1);
		cbCombustivel.setSelectedIndex(-1);
		ftfPlaca.setText("###-0000");
		cbMotorista.setSelectedIndex(-1);
		cbPedido.setSelectedIndex(-1);
	}
}
