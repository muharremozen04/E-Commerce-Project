package com.works.services;


import com.works.entities.Product;
import com.works.repositories.CategoriesRepository;
import com.works.repositories.ProductRepository;
import com.works.utils.REnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity saveProduct(Product product) {         //Burada ürün kaydetme yazdım.
        Map<String, Object> hm = new LinkedHashMap<>();
        productRepository.save(product);
        hm.put("status", true);
        hm.put("result", product);
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity getProducts() {                     //Burada tüm kayıtları listeleme servisi yazdım
        Map<String, Object> hm = new HashMap<>();
        hm.put("Products", productRepository.findAll());
        return new ResponseEntity(hm, HttpStatus.OK);
    }


    public ResponseEntity deleteProductById(String id) {   //Burada id ye göre silme servisi yazdım
        Map<String, Object> hm = new HashMap<>();
        try {
            int iid = Integer.parseInt(id);
            productRepository.deleteById((long) iid);
            hm.put("status", true);
        } catch (Exception ex) {
            hm.put("message", "id request : " + id);
            hm.put("status", false);
            return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity productSearch(String q) {
        Map<Object, Object> hm = new LinkedHashMap<>();
        List<Product> productList = productRepository.findByProductNameContainsIgnoreCase(q);

        hm.put(REnum.status, true);
        hm.put(REnum.result, productList);

        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity updateProduct(Product product) {
        Map<Object, Object> hm = new LinkedHashMap<>();
        try {

            Optional<Product> oldProdcut = productRepository.findById(product.getPid());
            if (oldProdcut.isPresent()) {
                productRepository.saveAndFlush(product);

                hm.put(REnum.status, true);
                hm.put(REnum.result, product);
                return new ResponseEntity(hm, HttpStatus.OK);
            } else {
                hm.put(REnum.status, false);
                hm.put(REnum.message, "Böyle Bir Product Bulunamadı");
                return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception exception) {
            hm.put(REnum.status, false);
            hm.put(REnum.message, exception.getMessage());
            return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        }
    }
}
