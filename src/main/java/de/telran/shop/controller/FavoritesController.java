package de.telran.shop.controller;

import de.telran.shop.dto.FavoritesDto;
import de.telran.shop.dto.FavoritesRequestDto;
import de.telran.shop.service.FavoritesService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/favorites")
public class FavoritesController {

    private final FavoritesService favoritesService;



    @GetMapping(value = "/{id}")
    public FavoritesDto getFavoritesById(@PathVariable Long id) {
        return favoritesService.getFavoritesById(id);
    }

    @PostMapping
    public FavoritesDto addFavorite(@RequestBody FavoritesRequestDto favoritesRequestDto) {
        return favoritesService.addFavorite(favoritesRequestDto);
    }





}
