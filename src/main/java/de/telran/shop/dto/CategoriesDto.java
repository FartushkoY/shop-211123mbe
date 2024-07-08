package de.telran.shop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoriesDto {

    private long categoryID;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    //@JsonProperty("nameCategories")
    private String name;
}
