package de.telran.shop.repository;

import de.telran.shop.entity.Categories;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class CategoriesRepositoryTest {

    @Autowired
    private CategoriesRepository categoriesRepositoryTest;

    private Categories categoriesNewExpect;


    //@BeforeAll // всего один раз при начале тестирования
    @BeforeEach //перед каждым тестом
    void setUp() {
        categoriesNewExpect = new Categories();
        categoriesNewExpect.setName("Test1");
    }

    //@AfterAll // всего один раз при окончании тестирования
    @AfterEach //после каждого теста
    void tearDown() {
    }

    @Test
    void createCategoriesTest() {
        Categories categoriesActual =  categoriesRepositoryTest.save(categoriesNewExpect);

        assertEquals(categoriesNewExpect.getName(), categoriesActual.getName());
        assertNotNull(categoriesActual.getCategoryID());
    }

    @Test
    void getCategoriesTest() {
        // готовим тестовые данные
        Categories categoriesNewActual =  categoriesRepositoryTest.save(categoriesNewExpect);
        assertNotNull(categoriesNewActual.getCategoryID());
        // проверяем чтение данных
        List<Categories> categoriesListActual = categoriesRepositoryTest.findAll();
        assertNotNull(categoriesListActual);
        assertTrue(categoriesListActual.size()>0);
        assertEquals(categoriesListActual.size(), 1);
    }

    @Test
    void updateCategoriesTest() {
        // готовим тестовые данные
        Categories categoriesNewActual =  categoriesRepositoryTest.save(categoriesNewExpect);
        assertNotNull(categoriesNewActual.getCategoryID());

        // проверяем изменение данных
        Categories categoriesExpected = new Categories();
        categoriesExpected.setName("UpdatedName");
        categoriesExpected.setCategoryID(categoriesNewActual.getCategoryID());

        Categories categoriesActual =  categoriesRepositoryTest.save(categoriesExpected);
        assertNotNull(categoriesActual);
        assertEquals(categoriesExpected.getName(), categoriesActual.getName());
    }

    @Test
    void deleteCategoriesTest() {
        // готовим тестовые данные (добавляем)
        Categories categoriesNewActual =  categoriesRepositoryTest.save(categoriesNewExpect);
        assertNotNull(categoriesNewActual.getCategoryID());

        // проверяем чтение данных (база не пустая)
        Categories categoriesActual = categoriesRepositoryTest.findById(categoriesNewActual.getCategoryID()).orElse(null);
        assertNotNull(categoriesActual);
        assertEquals(categoriesNewActual.getCategoryID(), categoriesActual.getCategoryID());

        //удаляем строку
        categoriesRepositoryTest.delete(categoriesNewActual);

        // проверяем чтение данных что база пустая
        List<Categories> categoriesListActual = categoriesRepositoryTest.findAll();
        assertNotNull(categoriesListActual);
        assertFalse(categoriesListActual.size()>0);
        assertEquals(categoriesListActual.size(), 0);

    }

    // https://www.baeldung.com/junit-datajpatest-repository
    // доп.инфо
}