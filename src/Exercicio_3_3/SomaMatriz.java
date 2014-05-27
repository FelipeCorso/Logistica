package Exercicio_3_3;

import jomp.runtime.OMP;

public class SomaMatriz {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int numThreads = 400;
		int myId = 0;
		int n = 10;
		int cont = 0;
		int[][] matriz = new int[numThreads][n];

		for (int i = 0; i < numThreads; i++) {
			int indiceAux = i * matriz[i].length;
			for (int j = 0; j < n; j++) {
				matriz[i][j] = indiceAux++;
			}
		}

		OMP.setNumThreads(numThreads);

		//omp parallel private(myId) reduction(+:cont) 		
		{
			//omp for
			for (int j = 0; j < numThreads; j++) {
				for (int i = 0; i < n; i++) {					
					cont += matriz[j][i];
				}
				
				myId = OMP.getThreadNum();
				
				System.out.println("Thread " + myId + " total= " + cont);
			}
		}

		System.out.println("Valor final de cont = " + cont);
	}
}
