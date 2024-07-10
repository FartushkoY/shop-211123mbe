package de.telran.shop.dto;

import de.telran.shop.entity.Favorites;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoritesRequestDto {

    private Long favoriteID;
    private Long userID;
    private Long productID;


}
