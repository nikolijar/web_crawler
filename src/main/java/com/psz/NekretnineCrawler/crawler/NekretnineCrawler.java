package com.psz.NekretnineCrawler.crawler;

import java.io.IOException;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.psz.NekretnineCrawler.domain.Nekretnina;
import com.psz.NekretnineCrawler.domain.NekretninaPOJO;
import com.psz.NekretnineCrawler.service.NekretninaService;
import com.psz.NekretnineCrawler.util.NekretninaUtil;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class NekretnineCrawler extends WebCrawler {

	private final static Pattern FILTERS = Pattern.compile(". * (\\. (css | js | bmp | gif | jpe? g | ico"
			+ "| png | tiff? | mid | mp2 | mp3 | mp4" + "| wav | avi | mov | mpeg | ram | m4v | pdf"
			+ "| rm | smil | wmv | swf | wma | zip | rar | gz)) $");
	private final static Pattern URL_PATTERN = Pattern.compile(
			"https://www.halooglasi.com/nekretnine/(izdavanje-stanova|prodaja-stanova|izdavanje-kuca|prodaja-kuca).*");
	
	private final static String RECOGNITION_STRING = "QuidditaEnvironment.CurrentClassified=";
	private final static String DELIMITER = "}; ";
	private static NekretninaService service;

	public NekretnineCrawler(NekretninaService service) throws IOException {
		this.service = service;
	}

	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL().toLowerCase();
		if (FILTERS.matcher(href).matches()) {
			return false;
		}

		if (!URL_PATTERN.matcher(href).matches()) {
			return false;
		}
		return true;
	}

	@Override
	public void visit(Page page) {
		String url = page.getWebURL().getURL();
		System.out.println("URL:" + url);
		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			String html = htmlParseData.getHtml();
			Document doc = Jsoup.parse(html);
			Elements links = doc.select("script");
			for (Element link : links) {
				String data = link.data();
				if (data.contains(RECOGNITION_STRING)) {
					String[] lines = data.split("\n");
					String line = lines[2];
					String[] parts = line.split(DELIMITER);
					String jsonPart = parts[0].substring(RECOGNITION_STRING.length() + 1) + "}";
					System.out.println("Parsira:" + jsonPart);
					Gson gson = new GsonBuilder().create();
					NekretninaPOJO nekr = gson.fromJson(jsonPart, NekretninaPOJO.class);
					Nekretnina n = NekretninaUtil.createNekretnina(nekr, url);
					try{service.save(n);}
					catch (Throwable e) {
						System.out.println(e);
					}
					System.out.println(n);
				}
			}
		}
	}

	
}
