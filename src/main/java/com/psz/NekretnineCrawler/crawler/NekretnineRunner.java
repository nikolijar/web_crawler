package com.psz.NekretnineCrawler.crawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.psz.NekretnineCrawler.service.NekretninaService;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

@Component
public class NekretnineRunner implements CommandLineRunner{
	
	@Autowired
	private NekretninaService service;

	@Override
	public void run(String... args) throws Exception {
		String[] crawlDomains = {"https://www.halooglasi.com/nekretnine/"};
        String crawlStorageFolder = "/nekretnine";
        int numberOfCrawlers = 3;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
//        config.setPolitenessDelay(300);
        
        PageFetcher fetcher = new PageFetcher(config);
        RobotstxtConfig robotsConfig = new RobotstxtConfig();
        robotsConfig.setEnabled(false);
        RobotstxtServer robotsSvr = new RobotstxtServer(robotsConfig, fetcher);

        CrawlController controller = new CrawlController(config, fetcher, robotsSvr);
        for (String domain : crawlDomains) {
            controller.addSeed(domain);
        }
        
        CrawlController.WebCrawlerFactory<NekretnineCrawler> factory = () -> new NekretnineCrawler(service);

        controller.start(factory, numberOfCrawlers);
		
	}

}
