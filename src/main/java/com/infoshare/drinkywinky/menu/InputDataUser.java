package com.infoshare.drinkywinky.menu;

import com.infoshare.drinkywinky.model.Drink;
import com.infoshare.drinkywinky.repositories.Repository;
import com.infoshare.drinkywinky.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputDataUser {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final Scanner SCANNER = new Scanner(System.in);
    private static String userInput = "";
    private static String id = Utils.getRandomId(7); //number of id char length !!!!
    private static String dateModified = "2020.04.05"; //please fix this field !!!!
    private static List<String> ingredientsList = new ArrayList<>();
    private static List<String> measuresList = new ArrayList<>();
    private static String name;
    private static String category;
    private static String alcoholic;
    private static String recipe;
    private static String glass;

    public static void scannerInputFromUser() {
        STDOUT.info("Enter drink NAME: \n");
        name = SCANNER.nextLine();
        STDOUT.info("Enter CATEGORIES of a drink: \n");
        category = SCANNER.nextLine();
        STDOUT.info("Give the RECIPE: \n");
        recipe = SCANNER.nextLine();
        STDOUT.info("Write the kind of GLASS: \n");
        glass = SCANNER.nextLine();
        ingredientInputFromUser();
        addNewDrink();
        id = Utils.getRandomId(7);
    }

    /**
     * @param userIngredients names of ingredients separate with coma
     * @return list containing the names of the ingredients
     */
    private static List<String> addIngredients(String userIngredients) {
        ingredientsList.add(userIngredients);
        return ingredientsList;
    }

    /**
     * @param userMeasures names of measures separate with coma
     * @return list containing the value of measures
     */
    private static List<String> addMeasures(String userMeasures) {
        measuresList.add(userMeasures);
        return measuresList;
    }

    private static void isAlcoholic() {
        STDOUT.info("Is it ALCOHOLIC drink? Y/Yes or N/No \n");
        if (SCANNER.nextLine().equalsIgnoreCase("y"))
            alcoholic = "Alcoholic";
        else
            alcoholic = "No-Alcoholic";
    }

    private static void addNewDrink() {
        Repository.getInstance()
                .add(new Drink(
                        id,
                        name,
                        category,
                        recipe,
                        alcoholic,
                        dateModified,
                        glass,
                        ingredientsList,
                        measuresList));
    }

    private static void ingredientInputFromUser() {
        STDOUT.info("Enter an ingredient. If you're finished press Enter." +
                "\n If you add all to quit press Enter\n");
        userInput = SCANNER.nextLine();
        if (userInput.equalsIgnoreCase("")) {
            isAlcoholic();
        } else {
            addIngredients(userInput);
            measureInputFromUser();
        }
    }

    private static void measureInputFromUser() {
        STDOUT.info("Enter measurement. If you're finished press Enter to input next ingredient.\n");
        if (SCANNER.hasNextLine()) {
            addMeasures(SCANNER.nextLine());
        }
        ingredientInputFromUser();
    }
}

