package com.works.restcontrollers;


import com.works.entities.Categories;
import com.works.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryRestController {
    final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping("/saveCategory")
    public ResponseEntity save(@RequestBody Categories categories) {
        return categoryService.saveCategory(categories);
    } //Burada category kaydetme yazdım.

    @PutMapping("/updateCategory")
    public ResponseEntity update(@Valid @RequestBody Categories categories) {
        return categoryService.updateCategory(categories);
    }  //Burada category isimi değiştirme  yazdım.

    @DeleteMapping("/deleteCategory")
    public ResponseEntity delete(@RequestParam String id) {
        return categoryService.deleteCategory(id);
    }
    //Burada id ye göre category silme yaptım

    @GetMapping("/listCategory")
    public ResponseEntity list() {
        return categoryService.getCategories();
    }
    //Burada tüm categorileri listeleme servisi yazdım
}
