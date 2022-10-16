package com.works.services;

import com.works.entities.Categories;
import com.works.repositories.CategoriesRepository;
import com.works.utils.REnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoryService {
    final CategoriesRepository categoriesRepository;

    public CategoryService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public ResponseEntity saveCategory(Categories categories) {         //Burada ürün kaydetme yazdım.
        Map<String, Object> hm = new LinkedHashMap<>();
        categoriesRepository.save(categories);
        hm.put("status", true);
        hm.put("result", categories);
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity updateCategory(Categories categories) {
        Map<Object, Object> hm = new LinkedHashMap<>();
        try {

            Optional<Categories> oldCategories = categoriesRepository.findById(categories.getCid());
            if (oldCategories.isPresent()) {
                categoriesRepository.saveAndFlush(categories);

                hm.put(REnum.status, true);
                hm.put(REnum.result, categories);
                return new ResponseEntity(hm, HttpStatus.OK);
            } else {
                hm.put(REnum.status, false);
                hm.put(REnum.message, "Böyle Bir Category Bulunamadı");
                return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception exception) {
            hm.put(REnum.status, false);
            hm.put(REnum.message, exception.getMessage());
            return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity deleteCategory(String id) {   //Burada id ye göre silme servisi yazdım
        Map<String, Object> hm = new HashMap<>();
        try {
            int iid = Integer.parseInt(id);
            categoriesRepository.deleteById((long) iid);
            hm.put("status", true);
        } catch (Exception ex) {
            hm.put("message", "id request : " + id);
            hm.put("status", false);
            return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(hm, HttpStatus.OK);
    }
    public ResponseEntity getCategories() {                     //Burada tüm kayıtları listeleme servisi yazdım
        Map<String, Object> hm = new HashMap<>();
        hm.put("customers", categoriesRepository.findAll());
        return new ResponseEntity(hm, HttpStatus.OK);
    }
}
