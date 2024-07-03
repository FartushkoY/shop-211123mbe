package de.telran.shop.controller;

import de.telran.shop.dto.CategoriesDto;
import de.telran.shop.repository.CategoriesRepository;
import de.telran.shop.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
