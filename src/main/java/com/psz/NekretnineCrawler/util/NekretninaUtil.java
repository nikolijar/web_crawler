package com.psz.NekretnineCrawler.util;

import java.math.BigDecimal;
import java.util.List;

import com.psz.NekretnineCrawler.domain.Nekretnina;
import com.psz.NekretnineCrawler.domain.NekretninaPOJO;

public class NekretninaUtil {

	public static Nekretnina createNekretnina(NekretninaPOJO nekr, String url) {
		Nekretnina n = new Nekretnina();
		n.setBrojSoba(transformBrojSoba(nekr.getOtherFields().getBroj_soba_s()));
		n.setCena(nekr.getOtherFields().getCena_d());
		n.setMesecneRezije(nekr.getOtherFields().getMesecne_rezije_d());
		n.setStarost(nekr.getOtherFields().getTip_objekta_s());
		n.setGrad(nekr.getOtherFields().getGrad_s());
		n.setDeoGrada(nekr.getOtherFields().getLokacija_s());
		n.setMikrolokacija(nekr.getOtherFields().getMikrolokacija_s());
		n.setGrejanje(nekr.getOtherFields().getGrejanje_s());
		n.setKvadratura(nekr.getOtherFields().getKvadratura_d());
		n.setPovrsinaZemljista(nekr.getOtherFields().getPovrsina_placa_d());
		n.setSprat(transformSprat(nekr.getOtherFields().getSprat_s()));
		n.setUkupnaSpratnost(nekr.getOtherFields().getSprat_od_s());
		n.setTip(nekr.getOtherFields().getTip_nekretnine_s());
		fillWithDodatno(n, nekr.getOtherFields().getDodatno_ss());
		fillWithOstalo(n, nekr.getOtherFields().getOstalo_ss());
		n.setPonuda(getPonuda(url));
		n.setLink(url);

		return n;
	}

	private static String getPonuda(String url) {
		if (url.contains("prodaja")) {
			return "Prodaja";
		}
		if (url.contains("izdavanje")) {
			return "Izdavanje";
		}
		return null;
	}

	private static void fillWithDodatno(Nekretnina n, List<String> dodatno) {
		if (dodatno != null) {
			for (String s : dodatno) {
				if (s.equals("Uknjižen")) {
					n.setUknjizen(true);
					continue;
				}
				if (s.equals("Stan na dan") || s.equals("Kuća na dan")) {
					n.setIzdavanjeNaDan(true);
					continue;
				}
				if (s.equals("Depozit")) {
					n.setDepozit(true);
					continue;
				}
			}
		}
	}

	private static void fillWithOstalo(Nekretnina n, List<String> ostalo) {
		if (ostalo != null) {
			for (String s : ostalo) {
				if (s.equals("Parking") || s.equals("Garaža")) {
					n.setParking(true);
					continue;
				}
				if (s.equals("Lift")) {
					n.setLift(true);
					continue;
				}
				if (s.equals("Terasa") || s.equals("Lođa") || s.equals("Francuski balkon")) {
					n.setTerasa(true);
				}
			}
		}
	}

	private static BigDecimal transformBrojSoba(String brojSoba) {
		if (brojSoba != null) {
			if (brojSoba.equals("5+")) {
				return BigDecimal.valueOf(5.5);
			}
			return new BigDecimal(brojSoba);
		}
		return null;
	}

	private static BigDecimal transformSprat(String sprat) {
		if (sprat != null) {
			if (sprat.equals("SUT")) {
				return BigDecimal.valueOf(-1.0);
			}
			if (sprat.equals("PSUT")) {
				return BigDecimal.valueOf(-0.5);
			}
			if (sprat.equals("PR")) {
				return BigDecimal.ZERO;
			}
			if (sprat.equals("VPR")) {
				return BigDecimal.valueOf(0.5);
			}
			return new BigDecimal(sprat);
		}
		return null;
	}
	
}
