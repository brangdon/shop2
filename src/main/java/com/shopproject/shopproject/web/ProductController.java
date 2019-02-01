package com.shopproject.shopproject.web;

import com.shopproject.shopproject.domain.Product;
import com.shopproject.shopproject.domain.User;
import com.shopproject.shopproject.repository.ProductRepository;
import com.shopproject.shopproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

/**
 * Created by admin on 24.01.2019.
 */
@RestController
@RequestMapping(value = "/products")
@CrossOrigin(origins = {"http://localhost:3000", "http://10.254.5.76:3000", "http://10.254.5.65:3000", "http://10.254.5.64:3000", "http://10.254.5.77:3000"})
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping(path = "/all", produces = "application/json; charset=UTF-8")
    public
    @ResponseBody
    Iterable<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Product getProduct(@PathVariable(value = "id") Long productID) {
        Optional<Product> opt = productRepository.findById(productID);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            return null;
        }
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    String addProduct(@RequestBody Product product) {

        Product p = new Product(product);
        productRepository.save(p);

        String json = "[{\"message\":\"success\"}}]";
        return json;
    }

    @GetMapping("/addauth")
    public @ResponseBody String currentUser(@AuthenticationPrincipal UserDetails userDetails,@RequestBody Product product){

        if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            Product p = new Product(product);
            productRepository.save(p);
            String json = "[{\"message\":\"success\"}}]";
            return json;
        }


        String json = "[{\"message\":\"fail\"}}]";
        return json;


    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Long productID) {
        Optional<Product> opt = productRepository.findById(productID);
        Product product = opt.get();

        productRepository.delete(product);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable(value = "id") Long productId,
                           @Valid @RequestBody Product product) {

        Product p = productRepository.findById(productId).get();

        p.setAuthor(product.getAuthor());
        p.setDescription(product.getDescription());
        p.setPrice(product.getPrice());
        p.setTitle(product.getTitle());
        p.setCategories(product.getCategories());
        p.setPhoto(product.getPhoto());

        Product updatedProduct = productRepository.save(p);
        return updatedProduct;
    }
}
