package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.traverse.DepthFirstIterator;

import it.polito.tdp.genes.db.GenesDao;
import it.polito.tdp.itunes.model.Track;
import javafx.util.Callback;

public class Model {
	
	private Graph<Classification, DefaultWeightedEdge> grafo;
	private GenesDao dao;
	private List<Arco> archi;
	List<Classification> vertici;
	Set<Localization> locConnesse;
	
	public Model()
	{
		dao = new GenesDao();
		creaGrafo();
	}
	
	public void creaGrafo()
	{
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
	
// Aggiungere i vertici
		Graphs.addAllVertices(this.grafo, dao.getVertices());
		vertici = new ArrayList<Classification>(dao.getVertices());
	
// Aggiungere gli archi
		archi = new ArrayList<Arco>(this.dao.getEdges());
		
		for(int i=0; i<archi.size(); i++)
		{
			for(int j=0; j<archi.size(); j++)
			{
				if(archi.get(i).getC1().equals(archi.get(j).getC2()) && archi.get(i).getC2().equals(archi.get(j).getC1()) && archi.get(i).getType().compareTo(archi.get(j).getType())==0)
				{
					archi.remove(i);
				}
			}
		}
		for(Arco a : archi)
		{
			DefaultWeightedEdge edge = this.grafo.getEdge(a.getC1(), a.getC2());
			
			if(edge==null)
			{
				Graphs.addEdgeWithVertices(this.grafo, a.getC1(), a.getC2(), 1);
				
			}
			else
			{
				double pesoVecchio = this.grafo.getEdgeWeight(edge);
				double pesoNuovo = pesoVecchio + 1;
				this.grafo.setEdgeWeight(edge, pesoNuovo);
			}
		}
	}
	
	public int numeroVertici()
	{
		return this.grafo.vertexSet().size();
	}

	public int numeroArchi()
	{
		return this.grafo.edgeSet().size();
	}

	public List<Localization> getLocalizations() 
	{
// Componente connessa
				//canzoniConnesse = new HashSet<>();
				//Set<Track> parziale = new HashSet<Track>();
				//DepthFirstIterator<Track, DefaultWeightedEdge> it = new DepthFirstIterator<Track, DefaultWeightedEdge>(this.grafo, canzone);
		locConnesse = new HashSet<>();
		DepthFirstIterator<Classification, DefaultWeightedEdge> it = new DepthFirstIterator<Classification, DefaultWeightedEdge(this.grafo, )>;
		
		return null;
	}
	
}