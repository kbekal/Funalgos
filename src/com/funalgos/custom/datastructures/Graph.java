package com.funalgos.custom.datastructures;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Graph<T extends Comparable<? super T>> {
	int V;
	int E;
	int defaultWeight = 0;
	private Map<T, Map<T, Integer>> g;
	
	public Graph(){
		g = new HashMap<T, Map<T, Integer>>();
	}
	
	public void addEdge(T from, T to){
		addEdge(from, to, defaultWeight);
	}
	
	public boolean hasVertex(T v){
		return g.containsKey(v);
	}
	
	public void addVertex(T v){
		Map<T, Integer> eMap = new HashMap<T, Integer>();
		g.put(v, eMap);
	}
	
	public void addEdge(T from, T to, int weight){
		
		if(!hasVertex(from)) addVertex(from);
		if(!hasVertex(to))   addVertex(to);
		
		g.get(from).put(to, weight);
		g.get(to).put(from, weight);
		
	}
	
	public boolean hasEdge(T from, T to){
		if(!hasVertex(from) || !hasVertex(to)) return false;

		if(g.get(from).containsKey(to) &&
				g.get(to).containsKey(from))
			return true;
		
		return false;
	}
	
	public Iterable<T> getAdjacentVertices(T source){
		if(hasVertex(source)){
			return g.get(source).keySet();
		}
		return null;
	}
	
	public void printAll(){
		Set<Entry<T, Map<T, Integer>>> entry = g.entrySet();
		for(Entry<T, Map<T, Integer>> e : entry){
			for(Entry<T, Integer> ent : e.getValue().entrySet()){
				System.out.print(e.getKey() + "=" + ent.getKey() + ",");
			}
			System.out.println();
		}
	}
}

