package com.psz.NekretnineCrawler.domain;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NekretninaPOJO {
	
	private NekretninaUnutrasnjaKlasa OtherFields;
	
	@Getter
	@Setter
	public class NekretninaUnutrasnjaKlasa {
		
		private String broj_soba_s;
		
		private BigDecimal cena_d;
		
		private BigDecimal mesecne_rezije_d;
		
		private String tip_objekta_s;
		
		private String grad_s;
		
		private String lokacija_s;
		
		private String mikrolokacija_s;
		
		private String grejanje_s;
		
		private BigDecimal kvadratura_d;
		
		private BigDecimal povrsina_placa_d;
		
		private String sprat_s;
		
		private Integer sprat_od_s;
		
		private String tip_nekretnine_s;
		
		private List<String> ostalo_ss;
		
		private List<String> dodatno_ss;
		
	}

}
