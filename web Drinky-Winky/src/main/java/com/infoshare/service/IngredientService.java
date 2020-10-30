package com.infoshare.service;

import com.infoshare.dao.IngredientDao;
import com.infoshare.dto.IngredientDTO;
import com.infoshare.model.Ingredient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class IngredientService {
    private Logger logger = LoggerFactory.getLogger(getClass().getName());
    @Inject
    private IngredientDao ingredientDao;

    public void loadIngredient(List<Ingredient> ingredients) {
        ingredientDao.loadIngredient(ingredients);
        logger.info("List of has been loaded");
    }

    public void addIngredient(IngredientDTO ingredientDTO) {
        ingredientDao.addIngredient(ingredientDTO);
        logger.info("Ingredient has been added");
    }

    public Ingredient editIngredient(Long id, IngredientDTO ingredientDTO) {
        return ingredientDao.editIngredient(id, ingredientDTO);
    }

    public Ingredient getIngredientByName(String name) {
        logger.info("Get Ingredient by name");
        return ingredientDao.getIngredientByName(name);
    }

    public Ingredient getIngredientById(Long id) {
        return ingredientDao.getIngredientById(id);
    }

    public void deleteCategoryById(Long id) {
        ingredientDao.deleteIngredientById(id);
    }

    public Ingredient findIngredient(String name) {
        return ingredientDao.findIngredient(name);
    }

    public List<String> getIngredientsList() {
        return ingredientDao.getIngredientsList();
    }
}

