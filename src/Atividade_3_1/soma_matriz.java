package Atividade_3_1;

import jomp.runtime.OMP;

public class soma_matriz {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int numThreads = 15;
		int myId;
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
			myId = OMP.getThreadNum();
			
			for (int i = 0; i < n; i++) {
				cont += matriz[myId][i];
			}
			
			System.out.println("Thread " + myId + " total= " + cont);
		}
		
		System.out.println("Valor final de cont = " + cont);
		
	}

}
