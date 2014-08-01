package com.funalgos.helpers;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.funalgos.custom.datastructures.Graph;


public  class FindPath<T extends Comparable<? super T>> {
	
	Map<T,T> path = new HashMap<T,T>();
	Map<T,Integer> pathLengths = new HashMap<T,Integer>();
	
	public Iterable<T> findPath(Graph<T> g, T source, T dest){
		Queue<T> qu = new LinkedList<T>();
		if(g.hasVertex(source) &&
				g.hasVertex(dest)){
			qu.add(source);
			pathLengths.put(source, 0);
			while(! qu.isEmpty()){
				T current = qu.remove();
				for(T neighbor : g.getAdjacentVertices(current)){
					if(!pathLengths.containsKey(neighbor)){
						qu.add(neighbor);
						pathLengths.put(neighbor, pathLengths.get(current) + 1);
						path.put(neighbor, current);
					}
				}
			}
			return getPath(path, source, dest);
		}
		return null;
	}
	
	private Iterable<T> getPath(Map<T,T> path, T source, T dest){
		Deque<T> stack = new ArrayDeque<T>();
		stack.addFirst(dest);
			while(path.containsKey(dest)){
				T next = path.get(dest);
				stack.addFirst(next);
				dest = next;
			}
		List<T> pathList = new ArrayList<T>();
		while(!stack.isEmpty()){
			pathList.add(stack.removeFirst());
		}
		return pathList;
	}

}
