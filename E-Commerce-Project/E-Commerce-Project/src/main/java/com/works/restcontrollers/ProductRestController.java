package com.works.restcontrollers;


import com.works.entities.Product;
import com.works.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductRestController {
    final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @PutMapping("/updateProduct")
    public ResponseEntity update(@Valid @RequestBody Product product) {
        return productService.updateProduct(product);
    }  //Burada ürün değiştirme update yazdım.

    @GetMapping("/searchProduct")
    public ResponseEntity productSearch(@RequestParam(defaultValue = "")  String q){
        return productService.productSearch(q);
    }

    @PostMapping("/saveProduct")
    public ResponseEntity save(@RequestBody Product product) {
        return productService.saveProduct(product);
    } //Burada ürün kaydetme yazdım.

    @GetMapping("/listProduct")
    public ResponseEntity list() {
        return productService.getProducts();
    }
    //Burada tüm kayıtları listeleme servisi yazdım


    @DeleteMapping("/deleteProduct")
    public ResponseEntity delete(@RequestParam String id) {
        return productService.deleteProductById(id);
    }
    //Burada id ye göre ürün silme yaptım
}
