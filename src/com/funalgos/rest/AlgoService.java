package com.funalgos.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.funalgos.algos.impl.IWordLadder;

@Path("/rest")
@Produces(MediaType.APPLICATION_JSON)
public class AlgoService {
	
	IWordLadder wordLadder;

	@GET
	@Path("/wordladder/{firstword}/{secondword}")
	public Response wordLadder(@Context HttpServletRequest request,
			@PathParam("firstword") String firstWord,
			@PathParam("secondword") String secondWord){
		
		return wordLadder.generateLadder(firstWord, secondWord);
		
	}
	
	public IWordLadder getWordLadder() {
		return wordLadder;
	}

	public void setWordLadder(IWordLadder wordLadder) {
		this.wordLadder = wordLadder;
	}

}
