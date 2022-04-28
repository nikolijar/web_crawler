package com.psz.NekretnineCrawler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psz.NekretnineCrawler.domain.Nekretnina;

@Service
public class NekretninaService {

	@Autowired
	private NekretninaRepository repo;
	
	public void save(Nekretnina n) {
		
		repo.save(n);
	}
	
}
