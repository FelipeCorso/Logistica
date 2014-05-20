import java.net.URL;
import java.text.MessageFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jomp.runtime.OMP;

import com.furb.endereco.Regiao;
import com.furb.utils.Mock;

public class Main_jomp {

	public static void main(String[] args) throws InterruptedException {
		int opcao = 0;
		boolean sair = false;
		Scanner scanner =  new Scanner(System.in);
		
		System.out.println("Escolha uma Op\u00e7\u00e3o: ");
		System.out.println("1 - Calcular Frota Necess\u00e1ria");
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
				
				System.out.println("Escolha uma Op\u00e7\u00e3o: ");
				System.out.println("1 - Calcular Frota Necess\u00e1ria");
				System.out.println("666 - Sair");
				
			} catch (NumberFormatException nfe) {
				System.out.println("Op\u00e7\u00e3o inv\u00e1lida! Tente novamente.");
			}
		}
	}
	
	private static void CalcularFrota() throws InterruptedException {
		
		Regiao[] regioes = Mock.GetRegioes(10);
		
		int numDiasUteis = 6;
		int periodoAtendimento = 2;
		
		int numFrota = 0;

		int numThreads = regioes.length;
		OMP.setNumThreads(numThreads);

// OMP PARALLEL BLOCK BEGINS
{
  __omp_Class0 __omp_Object0 = new __omp_Class0();
  // shared variables
  __omp_Object0.numThreads = numThreads;
  __omp_Object0.periodoAtendimento = periodoAtendimento;
  __omp_Object0.numDiasUteis = numDiasUteis;
  __omp_Object0.regioes = regioes;
  // firstprivate variables
  try {
    jomp.runtime.OMP.doParallel(__omp_Object0);
  } catch(Throwable __omp_exception) {
    System.err.println("OMP Warning: Illegal thread exception ignored!");
    System.err.println(__omp_exception);
  }
  // reduction variables
  numFrota  += __omp_Object0._rd_numFrota;
  // shared variables
  numThreads = __omp_Object0.numThreads;
  periodoAtendimento = __omp_Object0.periodoAtendimento;
  numDiasUteis = __omp_Object0.numDiasUteis;
  regioes = __omp_Object0.regioes;
}
// OMP PARALLEL BLOCK ENDS


		System.out.println("Frota necess\u00e1ria = " + numFrota);
	}

// OMP PARALLEL REGION INNER CLASS DEFINITION BEGINS
private static class __omp_Class0 extends jomp.runtime.BusyTask {
  // shared variables
  int numThreads;
  int periodoAtendimento;
  int numDiasUteis;
  Regiao [ ] regioes;
  // firstprivate variables
  // variables to hold results of reduction
  int _rd_numFrota;

  public void go(int __omp_me) throws Throwable {
  // firstprivate variables + init
  // private variables
  // reduction variables, init to default
  int numFrota = 0;
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
                             for(int i = (int)__omp_ChunkData1.start; i < __omp_ChunkData1.stop; i += __omp_ChunkData1.step) {
                               // OMP USER CODE BEGINS
 {
				numFrota = regioes[i].CalcularNumFrotaNecessaria(numDiasUteis,
						periodoAtendimento);
			}
                               // OMP USER CODE ENDS
                               if (i == (__omp_WholeData2.stop-1)) amLast = true;
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
  numFrota = (int) jomp.runtime.OMP.doPlusReduce(__omp_me, numFrota);
  // output to _rd_ copy
  if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
    _rd_numFrota = numFrota;
  }
  }
}
// OMP PARALLEL REGION INNER CLASS DEFINITION ENDS

}

