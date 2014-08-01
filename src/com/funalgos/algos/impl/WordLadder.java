package com.funalgos.algos.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Response;

import com.funalgos.custom.datastructures.Graph;
import com.funalgos.helpers.FindPath;
import com.funalgos.helpers.IReadWordFile;
import com.google.gson.Gson;



public class WordLadder implements IWordLadder {

	Map<Integer, Graph<String>> gMap = new HashMap<Integer, Graph<String>>();
	String[] dictArray;
	IReadWordFile readWordFile;

	public Response generateLadder(String firstWord, String lastWord) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		if (firstWord.length() != lastWord.length()){
			String error = gson.toJson("String lengths donot match");
			return Response.status(200).entity(error).build();
		}
			

		
		Graph<String> wordGraph;
		if ((wordGraph = gMap.get(firstWord.length())) == null) {
			wordGraph = prepareGraph(firstWord.length());
		}
		// wordGraph.printAll();
		Iterable<String> path = findLadder(wordGraph, firstWord, lastWord);
		List<String> pathList = new ArrayList<String>();
		if (path != null) {
			for (String s : path) {
				pathList.add(s);
			}
		} else {
			String json = gson.toJson("No word ladder");
			return Response.status(200).entity(json).build();
		}
		
		return Response.status(200).entity(gson.toJson(pathList)).build();

	}
	
	private boolean isOffByOneChar(String a, String b){
		int len = a.length();
		if(len == b.length()){
			int count = 0;
			for(int i=0; i<len; i++){
				if(a.charAt(i) != b.charAt(i))
					count++;
					
			}
			if(count == 1){
				return true;
			}
		}
		return false;
	}
	
	public Iterable<String> findLadder(Graph<String> g, String start, String end){
		
		FindPath<String> pathFinder = new FindPath<String>();
		return pathFinder.findPath(g, start, end);
	}
	
	private Graph<String> prepareGraph(int inputLen){
		Graph<String> localGraph = new Graph<String>();
		int[] endIndexArray = readWordFile.getLastWordLenIndex();
		int[] startIndexArray = readWordFile.getStartWordLenIndex();
		int len = dictArray.length;
		for(int i=startIndexArray[inputLen]; i<=endIndexArray[inputLen];i++){
			/*if(inputLen != dictArray[i].length())
				continue;*/
			
			for(int j=i+1;j<=endIndexArray[inputLen];j++){
				/*if(inputLen != dictArray[j].length())
					continue;*/
				
				if(isOffByOneChar(dictArray[i],dictArray[j])){
					localGraph.addEdge(dictArray[i], dictArray[j]);
				}
			}
		}
		gMap.put(inputLen, localGraph);
		System.out.println("Preparing graph for word length " + inputLen + " -- Done.");
		return localGraph;
	}
	
	public IReadWordFile getReadWordFile() {
		return readWordFile;
	}

	public void setReadWordFile(IReadWordFile readWordFile) {
		this.readWordFile = readWordFile;
		this.readWordFile.loadDictionary();
		
		Set<String> dict = this.readWordFile.getDict();

		dictArray = dict.toArray(new String[dict.size()]);
		
		for(int i=1; i<=30;i++){
			prepareGraph(i);
		}
	}

}
