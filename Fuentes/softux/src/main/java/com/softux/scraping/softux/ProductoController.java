package com.softux.scraping.softux;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ProductoController {

  private final ProductoRepository repository;

  ProductoController(ProductoRepository repository) {
    this.repository = repository;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/scraping/products")
  List<Producto> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/scraping/products/search")
  Producto searchProduct(@RequestBody Producto product) {
	 
    return repository.findAll(Example.of(product)).get(0);
  }
  
  @PostMapping("/scraping/products")
  Producto newProduct(@RequestBody Producto newEmployee) {
    return repository.save(newEmployee);
  }

  // Single item
  
  @GetMapping("/scraping/products/{id}")
  Producto one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new ProductoNotFoundException(id));
  }

  @PutMapping("/scraping/products/{id}")
  Producto replaceProduct(@RequestBody Producto newProducto, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(employee -> {
        employee.setProducto(newProducto.getProducto());
        employee.setMarca(newProducto.getMarca());
        employee.setPresentacion(newProducto.getPresentacion());
        employee.setTamano(newProducto.getTamano());
        employee.setUrl(newProducto.getUrl());
        employee.setCanal(newProducto.getCanal());
        employee.setPrecio(newProducto.getPrecio());
        return repository.save(employee);
      })
      .orElseGet(() -> {
    	  newProducto.setId(id);
        return repository.save(newProducto);
      });
  }

  @DeleteMapping("/scraping/products/{id}")
  void deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
