package com.furb.entrega.ui;

import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class OpcoesEntrada extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					OpcoesEntrada frame = new OpcoesEntrada();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OpcoesEntrada() {
		setTitle("Métodos de Entrada");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 203);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		final JRadioButton rdbtnArquivo = new JRadioButton("Arquivo");
		final JRadioButton rdbtnCadastrarVeiculos = new JRadioButton("Cadastrar Veículos");
		rdbtnCadastrarVeiculos.setSelected(true);
		JRadioButton rdbtnSql = new JRadioButton("SQL");

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnArquivo);
		buttonGroup.add(rdbtnCadastrarVeiculos);
		buttonGroup.add(rdbtnSql);

		JButton btnOk = new JButton("Ok");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rdbtnArquivo.isSelected()) {
					FileDialog fileDialog = new FileDialog(getOpcoesEntrada(), "Abrir", FileDialog.LOAD);
					fileDialog.setDirectory("C:\\");
					fileDialog.setVisible(true);
					String filePath = fileDialog.getDirectory() + fileDialog.getFile();
					try {
						readFile(filePath);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					getOpcoesEntrada().dispose();
				} else {
					if (rdbtnCadastrarVeiculos.isSelected()) {
						new CadastrarVeiculos();
						getOpcoesEntrada().dispose();
					}
				}
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(rdbtnArquivo).addComponent(rdbtnCadastrarVeiculos))
														.addComponent(rdbtnSql)).addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup().addContainerGap(269, Short.MAX_VALUE).addComponent(btnOk).addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(
				gl_contentPane.createSequentialGroup().addGap(5).addComponent(rdbtnArquivo).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(rdbtnCadastrarVeiculos)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(rdbtnSql).addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE).addComponent(btnOk).addContainerGap()));
		contentPane.setLayout(gl_contentPane);
	}

	private JFrame getOpcoesEntrada() {
		return this;
	}

	private static void readFile(String filePath) throws IOException {
		// Nada faz
	}
}
