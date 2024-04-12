package com.appcardapio.cardapio.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appcardapio.cardapio.food.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
}
