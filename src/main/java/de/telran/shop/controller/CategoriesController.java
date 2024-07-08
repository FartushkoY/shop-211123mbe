package de.telran.shop.controller;

import de.telran.shop.dto.CategoriesDto;
import de.telran.shop.repository.CategoriesRepository;
import de.telran.shop.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoriesController {

    @Autowired
    CategoriesService categoriesService;

    @GetMapping
    List<CategoriesDto> getCategories() {
        return categoriesService.findAllService();
    }

    @GetMapping(value = "/{id}")
    public CategoriesDto getCategoriesById(@PathVariable Long id) {
        return categoriesService.findByIdService(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoriesDto createCategories(@RequestBody CategoriesDto categoriesDto) {
        return categoriesService.createCategories(categoriesDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public CategoriesDto updateCategories(@RequestBody CategoriesDto categoriesDto) {
        return categoriesService.updateCategories(categoriesDto);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void deleteCategories(@PathVariable Long id) {
        categoriesService.deleteCategories(id);
    }


}
