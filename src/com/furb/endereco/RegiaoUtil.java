package com.furb.endereco;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import net.sf.javaml.clustering.Clusterer;
import net.sf.javaml.clustering.KMeans;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;
import net.sf.javaml.core.DenseInstance;
import net.sf.javaml.tools.data.FileHandler;
import net.sf.javaml.tools.weka.WekaClusterer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import weka.clusterers.XMeans;

import com.furb.pedido.Pedido;

public class RegiaoUtil {
	public static Dataset[] CriarRegiaoKMeans(Pedido[] pedidos,
			int numClusters) {

		InputStream in = InputStreamFromArrayPedidos(pedidos);

		Reader reader = new InputStreamReader(in);

		Dataset data = FileHandler.load(reader, 0, ";");

		Clusterer km = new KMeans(numClusters);
		Dataset[] clustersKM = km.cluster(data);

		return clustersKM;

	}

	public static Dataset[] CriarRegiaoWekaClusterer(
			Pedido[] pedidos, int numClusters) {

		InputStream in = InputStreamFromArrayPedidos(pedidos);

		Reader reader = new InputStreamReader(in);

		Dataset data = FileHandler.load(reader, 0, ";");

		XMeans xm = new XMeans();

		xm.setMinNumClusters(numClusters);
		xm.setMaxNumClusters(numClusters);

		Clusterer jmlxm = new WekaClusterer(xm);

		Dataset[] clustersXM = jmlxm.cluster(data);
		
		return clustersXM;
	}

	private static InputStream InputStreamFromArrayPedidos(Pedido[] pedidos) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		for (Pedido pedido : pedidos) {
			try {
				baos.write(MessageFormat.format("{0};{1};{2}\n",
						pedido.getCodigo(), pedido.getCoordenadaX(),
						pedido.getCoordenadaY()).getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		byte[] bytes = baos.toByteArray();

		return new ByteArrayInputStream(bytes);
	}

	public static XYSeriesCollection DataSetToXYSeriesCollection(
			Dataset[] dataset) {
		XYSeriesCollection result = new XYSeriesCollection();
		for (int i = 0; i < dataset.length; i++) {
			XYSeries series = new XYSeries("Região " + (i + 1));

			DefaultDataset defaultDataset = ((DefaultDataset) dataset[i]);
			for (int j = 0; j < defaultDataset.size(); j++) {
				DenseInstance denseInst = (DenseInstance) defaultDataset
						.elementAt(j);
				series.add(denseInst.value(0), denseInst.value(1));
			}

			result.addSeries(series);
		}

		return result;
	}	
}
