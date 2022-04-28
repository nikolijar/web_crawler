package com.psz.NekretnineCrawler.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.psz.NekretnineCrawler.domain.Nekretnina;

public interface NekretninaRepository extends JpaRepository<Nekretnina, Integer>{

}
