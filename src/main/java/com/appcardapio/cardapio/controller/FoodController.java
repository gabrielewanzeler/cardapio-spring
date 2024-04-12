package com.appcardapio.cardapio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.appcardapio.cardapio.domain.repository.FoodRepository;
import com.appcardapio.cardapio.food.Food;
import com.appcardapio.cardapio.food.FoodRequestDTO;
import com.appcardapio.cardapio.food.FoodResponseDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/foods")
public class FoodController {
    @Autowired
    private final FoodRepository repository;

    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveFood(@RequestBody FoodRequestDTO data){
        Food foodData = new Food(data);
        repository.save(foodData);

    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List <FoodResponseDTO> getAll(){
        List <FoodResponseDTO> foodList = repository.findAll().stream().map(FoodResponseDTO::new).toList();
        return foodList;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public void deleteFood(@PathVariable Long id){
        Optional<Food> foodToDelete = repository.findById(id);
        if (foodToDelete.isPresent()) {
            repository.delete(foodToDelete.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comida não encontrada com o ID fornecido");
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public void updateFood(@PathVariable Long id, @RequestBody FoodRequestDTO updatedData){
        Optional<Food> existingFood = repository.findById(id);
        if (existingFood.isPresent()) {
            Food foodToUpdate = new Food(updatedData);
            foodToUpdate.setId(id);
            repository.save(foodToUpdate);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comida não encontrada com o ID fornecido");
        }
    }
    
}