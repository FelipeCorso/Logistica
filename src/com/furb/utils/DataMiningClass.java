package com.furb.utils;

import java.util.List;
import java.util.Vector;

import com.furb.pedido.Pedido;

import net.sf.jdmf.algorithms.classification.OneRuleAlgorithm;
import net.sf.jdmf.data.input.clustering.ClusteringInputData;
import net.sf.jdmf.data.output.clustering.Cluster;
import net.sf.jdmf.data.output.clustering.ClusteringDataMiningModel;

public class DataMiningClass {
	private static List<Vector<Double>> pontos;

	public static void main( String[] args ) throws Exception {
		OneRuleAlgorithm algorithm = new OneRuleAlgorithm();
		
		Pedido[] pedidos = new Pedido[10];
		
		Pedido temp;
		for (int i = 0; i < pedidos.length; i++) {
			temp = new Pedido();
			temp.setCoordenadaX(2);
			temp.setCoordenadaY(i);
			pedidos[i] = temp;
		}
		
		ClusteringInputData dadosEntrada = new ClusterizarRegioes(pedidos);
		
		dadosEntrada.setNumberOfClusters(10);
			
		ClusteringDataMiningModel dataMiningModel 
	    = (ClusteringDataMiningModel) algorithm.analyze( dadosEntrada );
		
		List<Cluster> clusters = dataMiningModel.getClusters();
		
		pontos = clusters.get(0).getPoints();
	}
}
