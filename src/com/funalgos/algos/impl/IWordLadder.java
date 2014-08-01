package com.funalgos.algos.impl;

import javax.ws.rs.core.Response;

public interface IWordLadder {
	Response generateLadder(String firstWord, String lastWord);
}
