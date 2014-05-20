package com.furb.utils;

import com.furb.pedido.Pedido;

import net.sf.jdmf.data.input.attribute.Attribute;
import net.sf.jdmf.data.input.clustering.ClusteringInputData;

public class ClusterizarRegioes  extends ClusteringInputData {
	private Pedido[] pedidos;
	
    public ClusterizarRegioes(Pedido[] pedidos) {
        super();
        
        this.pedidos = pedidos;
        prepareAttributes();
    }

    private void prepareAttributes() {
        Attribute firstAttribute = prepareFirstAttribute();
        Attribute secondAttribute = prepareSecondAttribute();
        
        addAttribute( firstAttribute );
        addAttribute( secondAttribute );
    }

    private Attribute prepareFirstAttribute() {
        Attribute atributoX = new Attribute();
        atributoX.setName( "first" );
        
        for ( int i = 0; i < this.pedidos.length; i++ ) {
        	atributoX.addValue(pedidos[i].getCoordenadaX());
        }
        
        return atributoX;
    }
    
    private Attribute prepareSecondAttribute() {
    	Attribute atributoY = new Attribute();
    	atributoY.setName( "second" );
        
        for ( int i = 0; i < this.pedidos.length; i++ ) {
        	atributoY.addValue(pedidos[i].getCoordenadaY());
        }
        
        return atributoY;
    }
}