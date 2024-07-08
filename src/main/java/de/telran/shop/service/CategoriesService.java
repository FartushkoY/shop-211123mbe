package de.telran.shop.service;

import de.telran.shop.dto.CategoriesDto;
import de.telran.shop.entity.Categories;
import de.telran.shop.repository.CategoriesRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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

    public CategoriesDto createCategories(@RequestBody CategoriesDto categoriesDto) {
        Categories categories = new Categories(null, categoriesDto.getName(), null);
        Categories categoriesReturn = categoriesRepository.save(categories);
        CategoriesDto categoriesDtoReturn = new CategoriesDto(categoriesReturn.getCategoryID(), categoriesReturn.getName());
        return categoriesDtoReturn;
    }

    public CategoriesDto updateCategories(CategoriesDto categoriesDto) {
        Categories categories = new Categories(categoriesDto.getCategoryID(), categoriesDto.getName(), null);
        Categories categoriesReturn = categoriesRepository.save(categories);
        CategoriesDto categoriesDtoReturn = new CategoriesDto(categoriesReturn.getCategoryID(), categoriesReturn.getName());
        return categoriesDtoReturn;
    }

    public void deleteCategories(Long id) {
        //categoriesRepository.deleteById(id);
        Categories categories = categoriesRepository.findById(id).orElse(null);
        if(categories==null) {
            throw new RuntimeException("Нету такого объекта с Id = "+id);
        } else {
            categoriesRepository.delete(categories);
        }

    }


}
