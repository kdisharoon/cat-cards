package com.techelevator.services;

import com.techelevator.model.CatFact;
import org.springframework.stereotype.Component;

import com.techelevator.model.CatPic;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCatPicService implements CatPicService {

	private final String API_BASE_URL = "https://random-cat-api.netlify.app/.netlify/functions/api";
	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public CatPic getPic() {
		return restTemplate.getForObject(API_BASE_URL, CatPic.class);
	}

}	
