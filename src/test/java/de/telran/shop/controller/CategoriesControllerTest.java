package de.telran.shop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.shop.dto.CategoriesDto;
import de.telran.shop.service.CategoriesService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoriesController.class)
class CategoriesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriesService categoriesServiceMock;

    @Autowired
    private ObjectMapper objectMapper;

    private static CategoriesDto expectedCategories;

    @BeforeAll
    static void setUpOnce() {
        expectedCategories =  new CategoriesDto(1L, "New Test");
        System.out.println("Код выполняется 1 раз перед тестами!");
    }

    @AfterAll
    static void tearDownOnce() {
        System.out.println("Код выполняется 1 раз после тестов!");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Код выполняется перед тестом");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Код после теста!");
    }

    @Test
    void getCategoriesTest() throws Exception {
        when(categoriesServiceMock.findAllService())
                .thenReturn(List.of(new CategoriesDto(1L, "Test1")));
        this.mockMvc.perform(get("/categories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..categoryID").exists())
                .andExpect(jsonPath("$..categoryID").value(1))
                .andExpect(jsonPath("$..name").value("Test1"))
                ;
    }

    @Test
    void getCategoriesByIdTest() throws Exception {
        when(categoriesServiceMock.findByIdService(anyLong()))
                .thenReturn(new CategoriesDto(1L, "Test1"));
        this.mockMvc.perform(get("/categories/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryID").exists())
                .andExpect(jsonPath("$.categoryID").value(1));
    }

    @Test
    void createCategories() throws Exception {
        CategoriesDto newCategories = new CategoriesDto(0, "New Test");
    //    CategoriesDto expectedCategories = new CategoriesDto(1L, "New Test");
        when(categoriesServiceMock.createCategories(newCategories))
                .thenReturn(expectedCategories);
        String requestBody = objectMapper.writeValueAsString(newCategories);

        this.mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.categoryID").exists())
                .andExpect(jsonPath("$.categoryID").value(1));

    }

    @Test
    void updateCategoriesTest() throws Exception {
      //  CategoriesDto expectedCategories = new CategoriesDto(1L, "New Test");
        when(categoriesServiceMock.updateCategories(any(CategoriesDto.class)))
                .thenReturn(expectedCategories);
        String requestBody = objectMapper.writeValueAsString(expectedCategories);

        this.mockMvc.perform(put("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryID").exists())
                .andExpect(jsonPath("$.categoryID").value(1));
    }

    @Test
    void deleteCategories() throws Exception {
        this.mockMvc.perform(delete("/categories/{id}", 1))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}