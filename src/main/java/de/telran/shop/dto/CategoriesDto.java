package de.telran.shop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CategoriesDto {

    private long categoryID;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    //@JsonProperty("nameCategories")
    private String name;
}
