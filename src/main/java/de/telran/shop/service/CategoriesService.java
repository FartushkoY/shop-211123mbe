package de.telran.shop.service;

import de.telran.shop.dto.CategoriesDto;
import de.telran.shop.entity.Categories;
import de.telran.shop.repository.CategoriesRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {
    @Autowired
    CategoriesRepository categoriesRepository;

    @PostConstruct
    void createTestData() {
        Categories categoriesNewExpect1 = new Categories();
        categoriesNewExpect1.setName("Test1");
        categoriesRepository.save(categoriesNewExpect1);

        Categories categoriesNewExpect2 = new Categories();
        categoriesNewExpect2.setName("Test2");
        categoriesRepository.save(categoriesNewExpect2);
    }

    public List<CategoriesDto> findAllService() {
        List<Categories> categoriesList = categoriesRepository.findAll();

        List<CategoriesDto>  categoriesDtoList = categoriesList.stream()
                .map(c -> new CategoriesDto(c.getCategoryID(), c.getName()))
                .toList();

        return categoriesDtoList;
    }

    public CategoriesDto findByIdService(Long id) {
        Categories categories = categoriesRepository.findById(id).orElse(null);
        CategoriesDto categoriesDto = new CategoriesDto(categories.getCategoryID(), categories.getName());
        return categoriesDto;
    }
}
