package com.funalgos.helpers;

import java.util.Set;

public interface IReadWordFile {
	
	void loadDictionary();
	Set<String> getDict();
	int[] getLastWordLenIndex();
	int[] getStartWordLenIndex();

}
