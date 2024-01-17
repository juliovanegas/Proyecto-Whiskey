package com.softux.scraping.softux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(ProductoRepository repository) {

    return args -> {
      //log.info("Preloading " + repository.save(new Producto("Whisky", "Macallan","Double Cask 12 años","700ml","https://www.carulla.com/whisky-double-cask-mature-12yo-146458/p","Carulla","$ 437.700")));
      //log.info("Preloading " + repository.save(new Producto("Whisky", "Macallan","Double Cask 12 años","700ml","https://www.dislicores.com/whisky-macallan-double-cask-12-years-old-ref960047/p?skuId=960047","Dislicores","$ 438.700")));
      //log.info("Preloading " + repository.save(new Producto("Whisky", "Macallan","Double Cask 12 años","700ml","https://www.tiendasjumbo.co/whisky-macallan-12-anos-doble-barril-botx700ml/p","Jumbo","$ 439.700")));
    };
  }
}
