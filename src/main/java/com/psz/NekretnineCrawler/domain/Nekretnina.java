package com.psz.NekretnineCrawler.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "nekretnina")
@Getter
@Setter
@ToString
public class Nekretnina {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String tip;
	
	private String starost = "Stara gradnja";
	
	private String ponuda;
	
	private BigDecimal cena;
	
	private String grad;
	
	private String deoGrada;
	
	private String mikrolokacija;
	
	private BigDecimal kvadratura;
	
	private BigDecimal povrsinaZemljista;
	
	private BigDecimal sprat;
	
	private Integer ukupnaSpratnost;
	
	private Boolean uknjizen = false;
	
	private String grejanje;
	
	private BigDecimal brojSoba;
	
	private Boolean parking = false;
	
	private Boolean lift = false;
	
	private Boolean terasa = false;
	
	private Boolean izdavanjeNaDan = false;
	
	private Boolean depozit = false;
	
	private BigDecimal mesecneRezije;
	
	private String link;
	
	private Boolean valid = true;
	
}
