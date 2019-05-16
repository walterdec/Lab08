package it.polito.tdp.dizionariograph.model;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {
	
	WordDAO dao;
	String verticeGradoMax="";

	private Graph<String, DefaultEdge> grafo;
	
	public Model () {
		dao = new WordDAO();
	}

	public Graph<String, DefaultEdge> getGrafo() {
		return grafo;
	}

	public void createGraph(int numeroLettere) {
		grafo = new SimpleGraph<>(DefaultEdge.class);
		List<String> allWords = dao.getAllWordsFixedLength(numeroLettere);
		Graphs.addAllVertices(grafo, allWords);
		
		for(String word1 : allWords) {
			for(String word2 : allWords) {
				if(isValid(word1, word2, numeroLettere) && !word1.equals(word2)) {
					grafo.addEdge(word1,word2);
				}
			}
		}
	}

	private boolean isValid(String word1, String word2, int numeroLettere) {
		int diff = 0;
		char wordA[]=word1.toCharArray();
		char wordB[]=word2.toCharArray();
		for(int i=0; i<numeroLettere; i++) {
			if(wordA[i]!=wordB[i]) {
				diff++;
				if(diff>1) {
					return false;
					
				}
			}
		}
		return true;
	}

	public List<String> displayNeighbours(String parolaInserita) {
		return Graphs.neighborListOf(grafo, parolaInserita);
	}

	public int findMaxDegree() {
		int max = 0;
		
		for(String w : grafo.vertexSet()) {
			if(grafo.degreeOf(w)>max) {
				max=grafo.degreeOf(w);
				verticeGradoMax = w;
			}
		}	
		return max;
	}
	
	
	public String getVerticeGradoMax() {
		return verticeGradoMax;
	}
	
}
