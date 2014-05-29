import java.awt.BorderLayout;
import java.util.Scanner;

import javax.swing.JFrame;

import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;
import net.sf.javaml.core.DenseInstance;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import jomp.runtime.OMP;

import com.furb.endereco.RegiaoUtil;
import com.furb.endereco.Regiao;
import com.furb.frete.CalcularFrete;
import com.furb.pedido.Pedido;
import com.furb.pedido.PedidoUtil;
import com.furb.utils.Mock;

public class Main {
	private static final String CALCULAR_FROTA_NECESSARIA = "1 - Calcular Frota Necessária";
	private static final String CRIAR_REGIOES = "2 - Criar Regiões";
	private static final String CALCULAR_FRETE = "3 - Calcular o Frete do Pedido: ";
	private static final String ESCOLHA_UMA_OPCAO = "Escolha uma opção: ";
	private static Mock mock;
	private static Scanner scanner;
	

	public static void main(String[] args) {
		CalcularFrete calcularFrete = new CalcularFrete();
		int opcao = 0;
		boolean sair = false;
		scanner = new Scanner(System.in);
		mock = new Mock();

		System.out.println(ESCOLHA_UMA_OPCAO);
		System.out.println(CALCULAR_FROTA_NECESSARIA);
		System.out.println(CRIAR_REGIOES);
		System.out.println(CALCULAR_FRETE);
		System.out.println("666 - Sair");

		while (!sair) {
			try {
				opcao = Integer.parseInt(scanner.next());
				switch (opcao) {
				case 1:
					CalcularFrota();
					break;
				case 2:
					System.out.println("\nQuantas regiões você deseja criar?");
					opcao = Integer.parseInt(scanner.next());
					CriarRegioes(opcao);
					break;
				case 3:
					System.out.println("\nQuantos pedidos você deseja criar?");
					int nPedido = Integer.parseInt(scanner.next());
					System.out.println("\nQuantos produtos tera cada pedido?");
					int nProduto = Integer.parseInt(scanner.next());
					calcularFrete.execCalcularFrete(nPedido, nProduto);
					
					break;
				case 666:
					sair = true;
				default:
					break;
				}

				System.out.println("\n" + ESCOLHA_UMA_OPCAO);
				System.out.println(CALCULAR_FROTA_NECESSARIA);
				System.out.println(CRIAR_REGIOES);
				System.out.println(CALCULAR_FRETE);
				System.out.println("666 - Sair");

			} catch (NumberFormatException nfe) {
				System.out.println("\nOpcao invalida! Tente novamente.");
			}
		}
	}

	private static void CalcularFrota() {

		Regiao[] regioes = mock.GetRegioes();

		int numDiasUteis = 6;
		int periodoAtendimento = 2;

		int numFrota = 0;
		int i = 0;
		int numThreads = regioes.length;
		OMP.setNumThreads(numThreads);

		//omp parallel reduction(+:numFrota)
		{
			//omp for
			for (i = 0; i < numThreads; i++) {
				numFrota = regioes[i].CalcularNumFrotaNecessaria(numDiasUteis,
						periodoAtendimento);
			}
		}

		System.out.println("Frota necessï¿½ria = " + numFrota);
	}

	private static void CriarRegioes(int numRegioes) {

		Pedido[] pedidos = mock.GetPedidos(70);

		JFreeChart chartKMeans;
		JFreeChart chartWeka;

		Dataset[] dsKMeans;
		Dataset[] dsWeka;

		OMP.setNumThreads(2);

		//omp parallel
		{
			//omp sections
			{
				//omp section
				{
					dsKMeans = RegiaoUtil
							.CriarRegiaoKMeans(pedidos, numRegioes);
					chartKMeans = ChartFactory.createScatterPlot(
							"KMeans Clustering", "X", "Y",
							RegiaoUtil.DataSetToXYSeriesCollection(dsKMeans));
				}
				//omp section
				{
					dsWeka = RegiaoUtil.CriarRegiaoWekaClusterer(pedidos,
							numRegioes);
					chartWeka = ChartFactory.createScatterPlot(
							"Weka Clustering", "X", "Y",
							RegiaoUtil.DataSetToXYSeriesCollection(dsWeka));
				}
			}
		}

		JFrame frame = new JFrame("Chart");
		frame.getContentPane().add(new ChartPanel(chartKMeans),
				BorderLayout.WEST);
		frame.getContentPane()
				.add(new ChartPanel(chartWeka), BorderLayout.EAST);
		frame.pack();
		frame.setVisible(true);

		System.out
				.println("\nQual região você deseja salvar? 1 - KMeans Clustering / 2 - Weka Clustering");

		Dataset[] dsFinal = null;

		int opcao = 0;
		try {
			opcao = Integer.parseInt(scanner.next());
		} catch (NumberFormatException e) {
			opcao = 1;
		}

		switch (opcao) {
		case 1:
			dsFinal = dsKMeans;
			break;
		case 2:
			dsFinal = dsWeka;
			break;
		default:
			dsFinal = dsKMeans;
			break;
		}

		Regiao[] regioes = CriarRegioes(dsFinal, pedidos);
		
		ImprimirRegioes(regioes);
		

		mock.SetRegioes(regioes);
	}
	
	private static void ImprimirRegioes(Regiao[] regioes){		
		for (int i = 0; i < regioes.length; i++) {
			System.out.println("");
			System.out.print(regioes[i].getId());
			for (int j = 0; j < regioes[i].getPedidos().size(); j++) {
				System.out
						.print(" -> ("
								+ regioes[i].getPedidos().get(j)
										.getCoordenadaX()
								+ ", "
								+ regioes[i].getPedidos().get(j)
										.getCoordenadaY() + ")");
			}
		}
	}

	private static Regiao[] CriarRegioes(Dataset[] ds, Pedido[] pedidos) {
		Regiao[] regioes = new Regiao[ds.length];
		Regiao tempReg;
		Pedido tempPed;

		OMP.setNumThreads(regioes.length);

		int j = 0;

		for (int i = 0; i < ds.length; i++) {
			tempReg = new Regiao("Região " + (i + 1));

			DefaultDataset defaultDataset = ((DefaultDataset) ds[i]);

			//omp parallel private(tempPed)
			{
				//omp for
				for (j = 0; j < defaultDataset.size(); j++) {
					DenseInstance denseInst = (DenseInstance) defaultDataset
							.elementAt(j);
					tempPed = PedidoUtil
							.findPedido(Integer.parseInt(denseInst.classValue()
									.toString()), pedidos);

					if (tempPed != null) {
						//omp critical
						{
							tempReg.addPedido(tempPed);
						}
					}
				}
			}

			regioes[i] = tempReg;
		}

		return regioes;
	}
}
