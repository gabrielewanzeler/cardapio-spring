package com.appcardapio.cardapio.food;

import java.math.BigDecimal;


public record FoodRequestDTO(String title, String image, BigDecimal price) {
}