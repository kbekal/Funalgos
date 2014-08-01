package com.funalgos.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.core.io.Resource;

public class ReadWordFile implements IReadWordFile {
	
	private static final int MAX_WORD_LENGTH = 50;
	private Resource dictionaryFile;
	private Set<String> dict = new LinkedHashSet<String>();
	private int[] lastWordLenIndex = new int[MAX_WORD_LENGTH];
	private int[] startWordLenIndex = new int[MAX_WORD_LENGTH];
	
	public int[] getStartWordLenIndex() {
		return startWordLenIndex;
	}

	public void setStartWordLenIndex(int[] startWordLenIndex) {
		this.startWordLenIndex = startWordLenIndex;
	}

	public int[] getLastWordLenIndex() {
		return lastWordLenIndex;
	}

	public void setLastWordLenIndex(int[] lastWordLenIndex) {
		this.lastWordLenIndex = lastWordLenIndex;
	}

	public Set<String> getDict() {
		return dict;
	}

	public void setDict(Set<String> dict) {
		this.dict = dict;
	}

	public Resource getDictionaryFile() {
		return dictionaryFile;
	}

	public void setDictionaryFile(Resource dictionaryFile) {
		this.dictionaryFile = dictionaryFile;
	}

	public void loadDictionary(){
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(
						new InputStreamReader(dictionaryFile.getInputStream()));
			String word, newWord="";
			int wordLen = 0, len = 0, i = 0;
			for(;(word = reader.readLine()) != null;i++){
				newWord = word.trim();
				if(!newWord.isEmpty()){
					dict.add(newWord);
					len = newWord.length();
					if(len > wordLen){
						lastWordLenIndex[wordLen] = i-1;
						startWordLenIndex[len] = i;
						wordLen = len;
					}
				}
				
			}
			lastWordLenIndex[len] = i-1;
			
		} catch (IOException e){
			e.printStackTrace();
		}finally{
			if(reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}

}
