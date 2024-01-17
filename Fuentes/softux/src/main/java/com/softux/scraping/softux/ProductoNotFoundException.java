package com.softux.scraping.softux;

class ProductoNotFoundException extends RuntimeException {

	ProductoNotFoundException(Long id) {
	    super("Could not find product " + id);
	  }
	}
