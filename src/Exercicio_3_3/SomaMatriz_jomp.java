package Exercicio_3_3;

import jomp.runtime.OMP;

public class SomaMatriz_jomp {


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
                         { // OMP FOR BLOCK BEGINS
                         // copy of firstprivate variables, initialized
                         // copy of lastprivate variables
                         // variables to hold result of reduction
                         boolean amLast=false;
                         {
                           // firstprivate variables + init
                           // [last]private variables
                           // reduction variables + init to default
                           // -------------------------------------
                           jomp.runtime.LoopData __omp_WholeData2 = new jomp.runtime.LoopData();
                           jomp.runtime.LoopData __omp_ChunkData1 = new jomp.runtime.LoopData();
                           __omp_WholeData2.start = (long)( 0);
                           __omp_WholeData2.stop = (long)( numThreads);
                           __omp_WholeData2.step = (long)(1);
                           jomp.runtime.OMP.setChunkStatic(__omp_WholeData2);
                           while(!__omp_ChunkData1.isLast && jomp.runtime.OMP.getLoopStatic(__omp_me, __omp_WholeData2, __omp_ChunkData1)) {
                           for(;;) {
                             if(__omp_WholeData2.step > 0) {
                                if(__omp_ChunkData1.stop > __omp_WholeData2.stop) __omp_ChunkData1.stop = __omp_WholeData2.stop;
                                if(__omp_ChunkData1.start >= __omp_WholeData2.stop) break;
                             } else {
                                if(__omp_ChunkData1.stop < __omp_WholeData2.stop) __omp_ChunkData1.stop = __omp_WholeData2.stop;
                                if(__omp_ChunkData1.start > __omp_WholeData2.stop) break;
                             }
                             for(int j = (int)__omp_ChunkData1.start; j < __omp_ChunkData1.stop; j += __omp_ChunkData1.step) {
                               // OMP USER CODE BEGINS
 {
				for (int i = 0; i < n; i++) {					
					cont += matriz[j][i];
				}
				
				myId = OMP.getThreadNum();
				
				System.out.println("Thread " + myId + " total= " + cont);
			}
                               // OMP USER CODE ENDS
                               if (j == (__omp_WholeData2.stop-1)) amLast = true;
                             } // of for 
                             if(__omp_ChunkData1.startStep == 0)
                               break;
                             __omp_ChunkData1.start += __omp_ChunkData1.startStep;
                             __omp_ChunkData1.stop += __omp_ChunkData1.startStep;
                           } // of for(;;)
                           } // of while
                           // call reducer
                           jomp.runtime.OMP.doBarrier(__omp_me);
                           // copy lastprivate variables out
                           if (amLast) {
                           }
                         }
                         // set global from lastprivate variables
                         if (amLast) {
                         }
                         // set global from reduction variables
                         if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
                         }
                         } // OMP FOR BLOCK ENDS

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

