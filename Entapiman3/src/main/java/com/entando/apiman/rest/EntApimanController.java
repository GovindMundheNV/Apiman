package com.entando.apiman.rest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entando.apiman.entity.WebClientEntity;
import com.entando.apiman.k8sserviceimpl.EntServiceDetailImpl;

import io.kubernetes.client.openapi.ApiException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fetcher")
public class EntApimanController {

//	@Autowired
//	InternalServiceFetcher iFetcher;
//	// ExternalServiceFetcher eFetcher;
//
//	@GetMapping("/")
//	public List<String> InternalData() throws FileNotFoundException, IOException, ApiException {
//
//		return iFetcher.fetchService();
//
//	}
	

	@Autowired
	EntServiceDetailImpl entServiceDetailImpl;
	

	@GetMapping("/save")
	public void addurl() throws FileNotFoundException, IOException, ApiException {
		System.out.println("inside controller");

	entServiceDetailImpl.saveUrl();
	
	
	
	
	//	entServiceDetailImpl.fetchAndSaveK8sServices("Entando");
		
	//	entServiceDetailImpl.fetchK8sServices("entando", "entando");
	}	
@PostMapping("/orgcreate")
	
	public List<WebClientEntity> create(@RequestBody List<WebClientEntity> listApi) throws FileNotFoundException, IOException, ApiException {
		listApi = new ArrayList<WebClientEntity>(entServiceDetailImpl.len);
		
		List<WebClientEntity> listOutputApi = new ArrayList<WebClientEntity>(entServiceDetailImpl.len); 
		
		for(String f1:entServiceDetailImpl.fetchK8sServices("Internal", "entando"))
		{
			WebClientEntity empl=new WebClientEntity();
			
			empl.setName(f1);
			empl.setDescription("okdone" );
			
			Mono<WebClientEntity> outputApiMono;
			outputApiMono = entServiceDetailImpl.create(empl);
			listOutputApi.add(outputApiMono.block());
		}
		
		
		
               
                  return listOutputApi;
    }
//	
//	
//	@Autowired
//	ExternalServiceFetcher efetcher;
//
//	@GetMapping("/external")
//	public List<String> externalData() throws FileNotFoundException, IOException, ApiException {
//
//		return efetcher.xfetchService();
//	}
}
