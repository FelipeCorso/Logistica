package com.furb.entrega;

import java.util.List;

import jomp.runtime.OMP;

import com.furb.pedido.Pedido;
import com.furb.produto.Produto;
import com.furb.veiculo.Veiculo;

public class Entrega_jomp {


	public static void efetuarEntrega_jomp(List listaVeiculos) {
		System.out.println(listaVeiculos.size());
		OMP.setNumThreads(listaVeiculos.size());
		int inx;
		
		int qtdEntrega_jompsEfetuadas = 0;
		int inx;

// OMP PARALLEL BLOCK BEGINS
{
  __omp_Class0 __omp_Object0 = new __omp_Class0();
  // shared variables
  __omp_Object0.listaVeiculos = listaVeiculos;
  // firstprivate variables
  try {
    jomp.runtime.OMP.doParallel(__omp_Object0);
  } catch(Throwable __omp_exception) {
    System.err.println("OMP Warning: Illegal thread exception ignored!");
    System.err.println(__omp_exception);
  }
  // reduction variables
  qtdEntrega_jompsEfetuadas  += __omp_Object0._rd_qtdEntrega_jompsEfetuadas;
  // shared variables
  inx = __omp_Object0.inx;
  listaVeiculos = __omp_Object0.listaVeiculos;
}
// OMP PARALLEL BLOCK ENDS

	}

// OMP PARALLEL REGION INNER CLASS DEFINITION BEGINS
private static class __omp_Class0 extends jomp.runtime.BusyTask {
  // shared variables
  int inx;
  List listaVeiculos;
  // firstprivate variables
  // variables to hold results of reduction
  int _rd_qtdEntrega_jompsEfetuadas;

public void go(int __omp_me) throws Throwable {
  // firstprivate variables + init
  // private variables
  // reduction variables, init to default
  int qtdEntrega_jompsEfetuadas = 0;
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
                           __omp_WholeData2.stop = (long)( listaVeiculos.size());
                           __omp_WholeData2.step = (long)(1);
                           jomp.runtime.OMP.setChunkStatic(__omp_WholeData2);
                           jomp.runtime.OMP.resetOrderer(__omp_me, __omp_WholeData2.start);
                           while(!__omp_ChunkData1.isLast && jomp.runtime.OMP.getLoopStatic(__omp_me, __omp_WholeData2, __omp_ChunkData1)) {
                           for(;;) {
                             if(__omp_WholeData2.step > 0) {
                                if(__omp_ChunkData1.stop > __omp_WholeData2.stop) __omp_ChunkData1.stop = __omp_WholeData2.stop;
                                if(__omp_ChunkData1.start >= __omp_WholeData2.stop) break;
                             } else {
                                if(__omp_ChunkData1.stop < __omp_WholeData2.stop) __omp_ChunkData1.stop = __omp_WholeData2.stop;
                                if(__omp_ChunkData1.start > __omp_WholeData2.stop) break;
                             }
                             for(int inx = (int)__omp_ChunkData1.start; inx < __omp_ChunkData1.stop; inx += __omp_ChunkData1.step) {
                               // OMP USER CODE BEGINS
 {

				int idThread = OMP.getThreadNum();
				Veiculo veiculo = (Veiculo) listaVeiculos.get(idThread);
				System.out.println("ID THREAD: " + idThread);
				System.out.println("REALIZANDO ENTREGA DOS PEDIDO DO VE\u00c3?CULO: " + veiculo.getPlaca());
				for (int index = 0; index < veiculo.getListaPedidos().size(); index++) {

					Pedido pedido = veiculo.getListaPedidos().get(index);

					System.out.println("PEDIDO: " + pedido.getCodigo());
					for (int i = 0; i < pedido.getListaProdutos().size(); i++) {
						Produto produto = pedido.getListaProdutos().get(i);
						System.out.println("EFETUANDO ENTREGA");
						System.out.println("PRODUTO: " + produto.getCodigo() + " - " + produto.getDescricao());
						System.out.println("ENTREGA EFETUADA");
						qtdEntrega_jompsEfetuadas++;
					}
				}
				System.out.println("QUANTIDADE DE ENTREGAS EFETUADAS: " + qtdEntrega_jompsEfetuadas);
			}
                               // OMP USER CODE ENDS
                               if (inx == (__omp_WholeData2.stop-1)) amLast = true;
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
  qtdEntrega_jompsEfetuadas = (int) jomp.runtime.OMP.doPlusReduce(__omp_me, qtdEntrega_jompsEfetuadas);
  // output to _rd_ copy
  if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
    _rd_qtdEntrega_jompsEfetuadas = qtdEntrega_jompsEfetuadas;
  }
  }
}
// OMP PARALLEL REGION INNER CLASS DEFINITION ENDS

}

