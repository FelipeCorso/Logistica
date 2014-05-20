package Atividade_3_1;

import jomp.runtime.OMP;

public class soma_matriz_jomp {


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

// OMP PARALLEL BLOCK BEGINS
{
  __omp_Class0 __omp_Object0 = new __omp_Class0();
  // shared variables
  __omp_Object0.matriz = matriz;
  __omp_Object0.n = n;
  __omp_Object0.numThreads = numThreads;
  __omp_Object0.args = args;
  // firstprivate variables
  try {
    jomp.runtime.OMP.doParallel(__omp_Object0);
  } catch(Throwable __omp_exception) {
    System.err.println("OMP Warning: Illegal thread exception ignored!");
    System.err.println(__omp_exception);
  }
  // reduction variables
  cont  += __omp_Object0._rd_cont;
  // shared variables
  matriz = __omp_Object0.matriz;
  n = __omp_Object0.n;
  numThreads = __omp_Object0.numThreads;
  args = __omp_Object0.args;
}
// OMP PARALLEL BLOCK ENDS

		
		System.out.println("Valor final de cont = " + cont);
		
	}

// OMP PARALLEL REGION INNER CLASS DEFINITION BEGINS
private static class __omp_Class0 extends jomp.runtime.BusyTask {
  // shared variables
  int [ ] [ ] matriz;
  int n;
  int numThreads;
  String [ ] args;
  // firstprivate variables
  // variables to hold results of reduction
  int _rd_cont;

  public void go(int __omp_me) throws Throwable {
  // firstprivate variables + init
  // private variables
  int myId;
  // reduction variables, init to default
  int cont = 0;
    // OMP USER CODE BEGINS

		{
			myId = OMP.getThreadNum();
			
			for (int i = 0; i < n; i++) {
				cont += matriz[myId][i];
			}
			
			System.out.println("Thread " + myId + " total= " + cont);
		}
    // OMP USER CODE ENDS
  // call reducer
  cont = (int) jomp.runtime.OMP.doPlusReduce(__omp_me, cont);
  // output to _rd_ copy
  if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
    _rd_cont = cont;
  }
  }
}
// OMP PARALLEL REGION INNER CLASS DEFINITION ENDS

}

