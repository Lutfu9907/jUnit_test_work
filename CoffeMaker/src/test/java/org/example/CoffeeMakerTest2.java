package org.example;

import org.example.InventoryException;
import org.example.RecipeException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CoffeeMakerTest2 {

    private RecipeBook recipeBook;


    @Test
    public void addRecipe() {
        CoffeeMaker coffeeMaker = new CoffeeMaker();
        Recipe r = new Recipe("Cappuccino", 150, 2, 1, 1, 0);
        coffeeMaker.addRecipe(r);

        if (coffeeMaker.getRecipes().length >= 3) {
            System.out.println("Tarif eklenemiyor. Maksimum tarif sayısına ulaşıldı.");
            return;
        }

        for (Recipe recipe : coffeeMaker.getRecipes()) {
            if (recipe != null && recipe.getName().equals(r.getName())) {
                System.out.println("Tarif eklenemiyor. Tarif adı zaten mevcut.");
                return;
            }
        }

        try {
            int price = Integer.parseInt(String.valueOf(r.getPrice()));

            if (price < 0) {
                System.out.println("Tarif eklenemiyor. Fiyat negatif olamaz.");
                return;
            }

        } catch (NumberFormatException e) {
            System.out.println("Tarif eklenemiyor. Fiyat geçerli bir tam sayı olmalıdır.");
            return;
        }

        if (coffeeMaker.addRecipe(r)) {
            System.out.println("Tarif başarıyla eklendi.");
        } else {
            System.out.println("Tarif eklenirken bir hata oluştu.");
        }
    }


    @Test
    void deleteRecipe() {
        CoffeeMaker coffeeMaker = new CoffeeMaker();

        Recipe r1 = new Recipe("Cappuccino", 150, 2, 1, 1, 0);
        Recipe r2 = new Recipe("Espresso", 100, 1, 0, 2, 0);
        Recipe r3 = new Recipe("Latte", 200, 2, 2, 1, 0);
        coffeeMaker.addRecipe(r1);
        coffeeMaker.addRecipe(r2);
        coffeeMaker.addRecipe(r3);

        String recipeNameToDelete = "Espresso";
        boolean isDeleted = Boolean.parseBoolean(coffeeMaker.deleteRecipe(Integer.parseInt(recipeNameToDelete)));

        assertTrue(isDeleted, "Tarif silinemedi");

        for (Recipe recipe : coffeeMaker.getRecipes()) {
            if (recipe != null && recipe.getName().equals(recipeNameToDelete)) {
                assertNull(recipe, "Tarif silinmedi");
            }
        }
    }


    @Test
    void editRecipe() {
        CoffeeMaker coffeeMaker = new CoffeeMaker();

        Recipe r1 = new Recipe("Cappuccino", 150, 2, 1, 1, 0);
        Recipe r2 = new Recipe("Espresso", 100, 1, 0, 2, 0);
        Recipe r3 = new Recipe("Latte", 200, 2, 2, 1, 0);

        coffeeMaker.addRecipe(r1);
        coffeeMaker.addRecipe(r2);
        coffeeMaker.addRecipe(r3);

        Recipe newRecipe = new Recipe("Turkish Coffee", 120, 2, 0, 2, 0);

        String editResult = coffeeMaker.editRecipe(Integer.parseInt("Espresso"), newRecipe);

        assertNotNull(editResult, "Tarif düzenlenemedi");

        boolean isRecipeFound = false;
        for (Recipe recipe : coffeeMaker.getRecipes()) {
            if (recipe != null && recipe.getName().equals(newRecipe.getName())) {
                isRecipeFound = true;
                break;
            }
        }
        assertTrue(isRecipeFound, "Düzenlenen tarif kahve makinesinde bulunamadı");
    }

    @Test
    void addInventory() {

        CoffeeMaker coffeeMaker = new CoffeeMaker();

        String amtCoffee = "3";
        String amtMilk = "2";
        String amtSugar = "3";
        String amtChocolate = "0";


        String initialInventory = coffeeMaker.checkInventory();


        try {
            coffeeMaker.addInventory(amtCoffee, amtMilk, amtSugar, amtChocolate);
        } catch (InventoryException e) {
            fail("Envanter eklenirken hata oluştu: " + e.getMessage());
        }


        String finalInventory = coffeeMaker.checkInventory();

        assertNotEquals(initialInventory, finalInventory, "Envanter güncellenemedi");

        String expectedFinalInventory = String.format("Coffee: %s%nMilk: %s%nSugar: %s%nChocolate: %s%n", amtCoffee, amtMilk, amtSugar, amtChocolate);
        assertEquals(expectedFinalInventory, finalInventory, "Envanter doğru bir şekilde güncellenmedi");

        System.out.println("Test başarıyla tamamlandı.");
    }


    @Test
    void checkInventory() {
        CoffeeMaker coffeeMaker = new CoffeeMaker();

        String expectedInitialInventory = "Coffee: 0\nMilk: 0\nSugar: 0\nChocolate: 0\n";
        assertEquals(expectedInitialInventory, coffeeMaker.checkInventory(), "Başlangıç envanteri doğru değil");

        try {
            coffeeMaker.addInventory("3", "2", "1", "0");
        } catch (InventoryException e) {
            e.printStackTrace();
        }

        String expectedFinalInventory = "Coffee: 3\nMilk: 2\nSugar: 1\nChocolate: 0\n";

        assertEquals(expectedFinalInventory, coffeeMaker.checkInventory(), "Envanter doğru değil");



    }
}



