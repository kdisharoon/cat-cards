package com.techelevator.services;

import org.springframework.stereotype.Component;

import com.techelevator.model.CatFact;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCatFactService implements CatFactService {

	private final String API_BASE_URL = "https://catfact.ninja/fact";
	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public CatFact getFact() {
		return restTemplate.getForObject(API_BASE_URL, CatFact.class);
	}

}
