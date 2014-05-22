package com.furb.utils;

import java.util.List;
import java.util.Random;
import java.util.Vector;

import com.furb.pedido.Pedido;

import net.sf.jdmf.algorithms.classification.BayesAlgorithm;
import net.sf.jdmf.algorithms.classification.OneRuleAlgorithm;
import net.sf.jdmf.algorithms.clustering.KMeansAlgorithm;
import net.sf.jdmf.algorithms.clustering.centroid.InitialCentroidChoiceStrategy;
import net.sf.jdmf.data.input.InputData;
import net.sf.jdmf.data.input.attribute.Instance;
import net.sf.jdmf.data.input.clustering.ClusteringInputData;
import net.sf.jdmf.data.output.DataMiningModel;
import net.sf.jdmf.data.output.Rule;
import net.sf.jdmf.data.output.clustering.Cluster;
import net.sf.jdmf.data.output.clustering.ClusteringDataMiningModel;
import net.sf.jdmf.visualization.clustering.ChartGenerator;

import org.apache.commons.logging.*;
import org.apache.commons.math.*;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;

public class DataMiningClass {
	private static List<Vector<Double>> pontos;

	public static void main( String[] args ) throws Exception {
		KMeansAlgorithm algorithm = new KMeansAlgorithm();
		BayesAlgorithm bayes = new BayesAlgorithm();
		
		Pedido[] pedidos = new Pedido[80];
		
		Pedido temp;
		Random random = new Random();
		for (int i = 0; i < pedidos.length; i++) {
			temp = new Pedido();
			temp.setCodigo(i);
			temp.setCoordenadaX(random.nextInt(25));
			temp.setCoordenadaY(i);
			pedidos[i] = temp;
		}
		
		ClusteringInputData dadosEntrada = new ClusterizarRegioes(pedidos);
		
		Instance instancia = new Instance();
		
		instancia.addAttributeValue("first", 0);
						
		dadosEntrada.setInstanceToBeClassified(instancia);
		
		dadosEntrada.setNumberOfClusters(4);
		
		ClusteringDataMiningModel dataMiningModel 
	    = (ClusteringDataMiningModel) algorithm.analyze( dadosEntrada );
				
		DataMiningModel mingingBayes = bayes.analyze(dadosEntrada);
		
		List<Rule> rules = mingingBayes.getRules();
		
		ChartGenerator chartGenerator = new ChartGenerator();
		
		// visualize the clusters formed (2D only)
        JFreeChart xyChart = chartGenerator.generateXYChart( 
            dataMiningModel.getClusters(), 0, "first", 1, "second" );
        
        
        ChartFrame chartFrame = new ChartFrame( "Clustering example", xyChart );
        chartFrame.pack();
        chartFrame.setVisible( true );
		//pontos = clusters.get(0).getPoints();
        
        List<Vector<Double>> pontos = dataMiningModel.getClusters().get(0).getPoints();
	}
}
