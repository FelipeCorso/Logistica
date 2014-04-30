import java.util.ArrayList;

import com.furb.endereco.Regiao;
import com.furb.transportadora.Transportadora;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		TestarCalculoFrota();
	}

	private static void TestarCalculoFrota() throws InterruptedException {
		ArrayList<Regiao> regioes = new ArrayList<Regiao>();
		Regiao tempReg;

		for (int i = 1; i < 10; i++) {
			tempReg = new Regiao("Região " + i);
			tempReg.setNumeroParadas(i);
			tempReg.setNumeroPontosASeremVisitados(i * 30);
			tempReg.setNumRoteirosDiarioVeiculo(2);
			regioes.add(tempReg);
		}

		Transportadora transp = new Transportadora(regioes);

		transp.CalcularFrotaNecessaria();
	}
}
