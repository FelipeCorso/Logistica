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

public class Main_jomp {

	private static final String CALCULAR_FROTA_NECESSARIA = "1 - Calcular Frota Necess\u00e1ria";
	private static final String CRIAR_REGIOES = "2 - Criar Regi\u00f5es";
	private static final String CALCULAR_FRETE = "3 - Calcular o Frete do Pedido: ";
	private static final String ESCOLHA_UMA_OPCAO = "Escolha uma op\u00e7\u00e3o: ";
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
					System.out.println("\nQuantas regi\u00f5es voc\u00ea deseja criar?");
					opcao = Integer.parseInt(scanner.next());
					CriarRegioes(opcao);
					break;
				case 3:
					System.out.println("\nQuantos pedidos voc\u00ea deseja criar?");
					opcao = Integer.parseInt(scanner.next());
					calcularFrete.execCalcularFrete(opcao);
					
					break;
				case 666:
					sair = true;
				default:
					break;
				}

				System.out.println("\n" + ESCOLHA_UMA_OPCAO);
				System.out.println(CALCULAR_FROTA_NECESSARIA);
				System.out.println(CRIAR_REGIOES);
				System.out.println("666 - Sair");

			} catch (NumberFormatException nfe) {
				System.out.println("\nOp\u00c3\u00a7\u00c3\u00a3o inv\u00c3\u00a1lida! Tente novamente.");
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

// OMP PARALLEL BLOCK BEGINS
{
  __omp_Class0 __omp_Object0 = new __omp_Class0();
  // shared variables
  __omp_Object0.numThreads = numThreads;
  __omp_Object0.i = i;
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
  i = __omp_Object0.i;
  periodoAtendimento = __omp_Object0.periodoAtendimento;
  numDiasUteis = __omp_Object0.numDiasUteis;
  regioes = __omp_Object0.regioes;
}
// OMP PARALLEL BLOCK ENDS


		System.out.println("Frota necess\u00ef\u00bf\u00bdria = " + numFrota);
	}

	private static void CriarRegioes(int numRegioes) {

		Pedido[] pedidos = mock.GetPedidos(70);

		JFreeChart chartKMeans;
		JFreeChart chartWeka;

		Dataset[] dsKMeans;
		Dataset[] dsWeka;

		OMP.setNumThreads(2);

// OMP PARALLEL BLOCK BEGINS
{
  __omp_Class4 __omp_Object4 = new __omp_Class4();
  // shared variables
  __omp_Object4.pedidos = pedidos;
  __omp_Object4.numRegioes = numRegioes;
  // firstprivate variables
  try {
    jomp.runtime.OMP.doParallel(__omp_Object4);
  } catch(Throwable __omp_exception) {
    System.err.println("OMP Warning: Illegal thread exception ignored!");
    System.err.println(__omp_exception);
  }
  // reduction variables
  // shared variables
  dsWeka = __omp_Object4.dsWeka;
  dsKMeans = __omp_Object4.dsKMeans;
  chartWeka = __omp_Object4.chartWeka;
  chartKMeans = __omp_Object4.chartKMeans;
  pedidos = __omp_Object4.pedidos;
  numRegioes = __omp_Object4.numRegioes;
}
// OMP PARALLEL BLOCK ENDS


		JFrame frame = new JFrame("Chart");
		frame.getContentPane().add(new ChartPanel(chartKMeans),
				BorderLayout.WEST);
		frame.getContentPane()
				.add(new ChartPanel(chartWeka), BorderLayout.EAST);
		frame.pack();
		frame.setVisible(true);

		System.out
				.println("\nQual regi\u00e3o voc\u00ea deseja salvar? 1 - KMeans Clustering / 2 - Weka Clustering");

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

		mock.SetRegioes(regioes);
	}

	private static Regiao[] CriarRegioes(Dataset[] ds, Pedido[] pedidos) {
		Regiao[] regioes = new Regiao[ds.length];
		Regiao tempReg;
		Pedido tempPed;

		OMP.setNumThreads(regioes.length);

		int j = 0;

		for (int i = 0; i < ds.length; i++) {
			tempReg = new Regiao("Regi\u00e3o " + (i + 1));

			DefaultDataset defaultDataset = ((DefaultDataset) ds[i]);

// OMP PARALLEL BLOCK BEGINS
{
  __omp_Class6 __omp_Object6 = new __omp_Class6();
  // shared variables
  __omp_Object6.defaultDataset = defaultDataset;
  __omp_Object6.i = i;
  __omp_Object6.j = j;
  __omp_Object6.tempReg = tempReg;
  __omp_Object6.regioes = regioes;
  __omp_Object6.pedidos = pedidos;
  __omp_Object6.ds = ds;
  // firstprivate variables
  try {
    jomp.runtime.OMP.doParallel(__omp_Object6);
  } catch(Throwable __omp_exception) {
    System.err.println("OMP Warning: Illegal thread exception ignored!");
    System.err.println(__omp_exception);
  }
  // reduction variables
  // shared variables
  defaultDataset = __omp_Object6.defaultDataset;
  i = __omp_Object6.i;
  j = __omp_Object6.j;
  tempReg = __omp_Object6.tempReg;
  regioes = __omp_Object6.regioes;
  pedidos = __omp_Object6.pedidos;
  ds = __omp_Object6.ds;
}
// OMP PARALLEL BLOCK ENDS


			regioes[i] = tempReg;
		}

		return regioes;
	}

// OMP PARALLEL REGION INNER CLASS DEFINITION BEGINS
private static class __omp_Class6 extends jomp.runtime.BusyTask {
  // shared variables
  DefaultDataset defaultDataset;
  int i;
  int j;
  Regiao tempReg;
  Regiao [ ] regioes;
  Pedido [ ] pedidos;
  Dataset [ ] ds;
  // firstprivate variables
  // variables to hold results of reduction

  public void go(int __omp_me) throws Throwable {
  // firstprivate variables + init
  // private variables
  Pedido tempPed = new Pedido();
  // reduction variables, init to default
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
                                   jomp.runtime.LoopData __omp_WholeData8 = new jomp.runtime.LoopData();
                                   jomp.runtime.LoopData __omp_ChunkData7 = new jomp.runtime.LoopData();
                                   __omp_WholeData8.start = (long)( 0);
                                   __omp_WholeData8.stop = (long)( defaultDataset.size());
                                   __omp_WholeData8.step = (long)(1);
                                   jomp.runtime.OMP.setChunkStatic(__omp_WholeData8);
                                   while(!__omp_ChunkData7.isLast && jomp.runtime.OMP.getLoopStatic(__omp_me, __omp_WholeData8, __omp_ChunkData7)) {
                                   for(;;) {
                                     if(__omp_WholeData8.step > 0) {
                                        if(__omp_ChunkData7.stop > __omp_WholeData8.stop) __omp_ChunkData7.stop = __omp_WholeData8.stop;
                                        if(__omp_ChunkData7.start >= __omp_WholeData8.stop) break;
                                     } else {
                                        if(__omp_ChunkData7.stop < __omp_WholeData8.stop) __omp_ChunkData7.stop = __omp_WholeData8.stop;
                                        if(__omp_ChunkData7.start > __omp_WholeData8.stop) break;
                                     }
                                     for(int j = (int)__omp_ChunkData7.start; j < __omp_ChunkData7.stop; j += __omp_ChunkData7.step) {
                                       // OMP USER CODE BEGINS
 {
					DenseInstance denseInst = (DenseInstance) defaultDataset
							.elementAt(j);
					tempPed = PedidoUtil
							.findPedido(Integer.parseInt(denseInst.classValue()
									.toString()), pedidos);

					if (tempPed != null) {
                                                 // OMP CRITICAL BLOCK BEGINS
                                                 synchronized (jomp.runtime.OMP.getLockByName("")) {
                                                 // OMP USER CODE BEGINS

						{
							tempReg.addPedido(tempPed);
						}
                                                 // OMP USER CODE ENDS
                                                 }
                                                 // OMP CRITICAL BLOCK ENDS

					}
				}
                                       // OMP USER CODE ENDS
                                       if (j == (__omp_WholeData8.stop-1)) amLast = true;
                                     } // of for 
                                     if(__omp_ChunkData7.startStep == 0)
                                       break;
                                     __omp_ChunkData7.start += __omp_ChunkData7.startStep;
                                     __omp_ChunkData7.stop += __omp_ChunkData7.startStep;
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
  // output to _rd_ copy
  if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
  }
  }
}
// OMP PARALLEL REGION INNER CLASS DEFINITION ENDS



// OMP PARALLEL REGION INNER CLASS DEFINITION BEGINS
private static class __omp_Class4 extends jomp.runtime.BusyTask {
  // shared variables
  Dataset [ ] dsWeka;
  Dataset [ ] dsKMeans;
  JFreeChart chartWeka;
  JFreeChart chartKMeans;
  Pedido [ ] pedidos;
  int numRegioes;
  // firstprivate variables
  // variables to hold results of reduction

  public void go(int __omp_me) throws Throwable {
  // firstprivate variables + init
  // private variables
  // reduction variables, init to default
    // OMP USER CODE BEGINS

		{
                         { // OMP SECTIONS BLOCK BEGINS
                         // copy of firstprivate variables, initialized
                         // copy of lastprivate variables
                         // variables to hold result of reduction
                         boolean amLast=false;
                         {
                           // firstprivate variables + init
                           // [last]private variables
                           // reduction variables + init to default
                           // -------------------------------------
                           __ompName_5: while(true) {
                           switch((int)jomp.runtime.OMP.getTicket(__omp_me)) {
                           // OMP SECTION BLOCK 0 BEGINS
                             case 0: {
                           // OMP USER CODE BEGINS

				{
					dsKMeans = RegiaoUtil
							.CriarRegiaoKMeans(pedidos, numRegioes);
					chartKMeans = ChartFactory.createScatterPlot(
							"KMeans Clustering", "X", "Y",
							RegiaoUtil.DataSetToXYSeriesCollection(dsKMeans));
				}
                           // OMP USER CODE ENDS
                             };  break;
                           // OMP SECTION BLOCK 0 ENDS
                           // OMP SECTION BLOCK 1 BEGINS
                             case 1: {
                           // OMP USER CODE BEGINS

				{
					dsWeka = RegiaoUtil.CriarRegiaoWekaClusterer(pedidos,
							numRegioes);
					chartWeka = ChartFactory.createScatterPlot(
							"Weka Clustering", "X", "Y",
							RegiaoUtil.DataSetToXYSeriesCollection(dsWeka));
				}
                           // OMP USER CODE ENDS
                           amLast = true;
                             };  break;
                           // OMP SECTION BLOCK 1 ENDS

                             default: break __ompName_5;
                           } // of switch
                           } // of while
                           // call reducer
                           jomp.runtime.OMP.resetTicket(__omp_me);
                           jomp.runtime.OMP.doBarrier(__omp_me);
                           // copy lastprivate variables out
                           if (amLast) {
                           }
                         }
                         // update lastprivate variables
                         if (amLast) {
                         }
                         // update reduction variables
                         if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
                         }
                         } // OMP SECTIONS BLOCK ENDS

		}
    // OMP USER CODE ENDS
  // call reducer
  // output to _rd_ copy
  if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
  }
  }
}
// OMP PARALLEL REGION INNER CLASS DEFINITION ENDS



// OMP PARALLEL REGION INNER CLASS DEFINITION BEGINS
private static class __omp_Class0 extends jomp.runtime.BusyTask {
  // shared variables
  int numThreads;
  int i;
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

