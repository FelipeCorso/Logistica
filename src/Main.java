import java.util.Scanner;

import jomp.runtime.OMP;

import com.furb.endereco.Regiao;
import com.furb.utils.Mock;

public class Main {
	private static final String CALCULAR_FROTA_NECESSARIA = "1 - Calcular Frota Necessária";
	private static final String ESCOLHA_UMA_OPCAO = "Escolha uma Opção: ";

	public static void main(String[] args) {
		int opcao = 0;
		boolean sair = false;
		Scanner scanner = new Scanner(System.in);

		System.out.println(ESCOLHA_UMA_OPCAO);
		System.out.println(CALCULAR_FROTA_NECESSARIA);
		System.out.println("666 - Sair");

		while (!sair) {
			try {
				opcao = Integer.parseInt(scanner.next());
				switch (opcao) {
				case 1:
					CalcularFrota();
					break;
				case 666:
					sair = true;
				default:
					break;
				}

				System.out.println("\n" + ESCOLHA_UMA_OPCAO);
				System.out.println(CALCULAR_FROTA_NECESSARIA);
				System.out.println("666 - Sair");

			} catch (NumberFormatException nfe) {
				System.out.println("\nOpção inválida! Tente novamente.");
			}
		}
	}

	private static void CalcularFrota() {

		Regiao[] regioes = Mock.GetRegioes(10);

		int numDiasUteis = 6;
		int periodoAtendimento = 2;

		int numFrota = 0;

		int numThreads = regioes.length;
		OMP.setNumThreads(numThreads);

		// omp parallel reduction(+:numFrota)
		{
			// omp for
			for (int i = 0; i < numThreads; i++) {
				numFrota = regioes[i].CalcularNumFrotaNecessaria(numDiasUteis, periodoAtendimento);
			}
		}

		System.out.println("Frota necess�ria = " + numFrota);
	}
}
