package org.kj6682.i.products;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luigi on 30/07/2017.
 */
@Api(value = "products", description = "Products API")
@RestController
@RequestMapping("/api")
class Controller {

    @Autowired
    private ProductRepository repository;

    @GetMapping("/products")
    List<Product> list(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "0") int size) {
        if (page == 0 && size == 0) {
            return repository.findAll();
        }

        List<Product> list = new ArrayList<>();
        repository.findAll(new PageRequest(page, size)).iterator().forEachRemaining(list::add);
        return list;

    }

    @PostMapping(value = "/products")
    ResponseEntity<?> create(@RequestBody Product product) {

        Assert.notNull(product, "Product can not be empty");

        Product result = repository.save(product);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/products/{id}")
    void delete(@PathVariable(required = true) Long id) {
        repository.delete(id);
    }

    private static class ProductNotFoundException extends RuntimeException {
        ProductNotFoundException(String id) {
            super("could not find product '" + id + "'.");
        }
    }

}
