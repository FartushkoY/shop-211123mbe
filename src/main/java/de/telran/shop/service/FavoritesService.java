package de.telran.shop.service;

import de.telran.shop.dto.FavoritesDto;
import de.telran.shop.dto.FavoritesRequestDto;
import de.telran.shop.entity.Categories;
import de.telran.shop.entity.Favorites;
import de.telran.shop.entity.Products;
import de.telran.shop.entity.Users;
import de.telran.shop.repository.FavoritesRepository;
import de.telran.shop.repository.ProductsRepository;
import de.telran.shop.repository.UsersRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoritesService {

    private final FavoritesRepository favoritesRepository;
    private final ProductsRepository productsRepository;
    private final UsersRepository usersRepository;

    @PostConstruct
    void createTestData() {
        Products products1 = Products.builder()
                .price(10.0)
                .name("Product1")
                .build();
        Products productSaved = productsRepository.save(products1);

        Users user1 = Users.builder()
                .name("User1")
                .build();
        Users userSaved = usersRepository.save(user1);

        Favorites favorites = new Favorites(0, userSaved, productSaved);
        Favorites favoritSaved = favoritesRepository.save(favorites);
    }

    public FavoritesDto getFavoritesById(Long id) {
        Favorites favorites = favoritesRepository.findById(id).orElse(null);

        return new FavoritesDto(favorites.getFavoriteID());
    }

    public FavoritesDto addFavorite(FavoritesRequestDto favoritesRequestDto) {
        Users user = usersRepository.findById(favoritesRequestDto.getUserID()).orElse(null);
        Products product = productsRepository.findById(favoritesRequestDto.getProductID()).orElse(null);
        Favorites favorite = new Favorites(0, user, product);
        Favorites favoriteSaved = favoritesRepository.save(favorite);
        return new FavoritesDto(favoriteSaved.getFavoriteID());
    }
}
