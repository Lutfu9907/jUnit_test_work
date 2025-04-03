package org.example;

public class CoffeeMaker {
	
	private static RecipeBook recipeBook;
	
    private static Inventory inventory;

	public CoffeeMaker() {
	    recipeBook = new RecipeBook();
		inventory = new Inventory();
	}

	public static boolean addRecipe(Recipe r) {
		return recipeBook.addRecipe(r);
	}

	public String deleteRecipe(int recipeToDelete) {
		return recipeBook.deleteRecipe(recipeToDelete);
	}

	public String editRecipe(int recipeToEdit, Recipe r) {

		return recipeBook.editRecipe(recipeToEdit, r);
	}

    public synchronized void addInventory(String amtCoffee, String amtMilk, String amtSugar, String amtChocolate) throws InventoryException {
		int coffeeAmount = Integer.parseInt(amtCoffee);
		int milkAmount = Integer.parseInt(amtMilk);
		int sugarAmount = Integer.parseInt(amtSugar);
		int chocolateAmount = Integer.parseInt(amtChocolate);

		if (coffeeAmount <= 0 || milkAmount <= 0 || sugarAmount <= 0 || chocolateAmount <= 0) {
			throw new InventoryException("All amounts must be positive integers");
		}

	    inventory.addCoffee(amtCoffee);
	    inventory.addMilk(amtMilk);
	    inventory.addSugar(amtSugar);
	    inventory.addChocolate(amtChocolate);
    }


    public synchronized String checkInventory() {
        return inventory.toString();
    }

    public synchronized int makeCoffee(int recipeToPurchase, int amtPaid) {
        int change = 0;
        
        if (getRecipes()[recipeToPurchase] == null) {
        	change = amtPaid;
        } else if (getRecipes()[recipeToPurchase].getPrice() <= amtPaid) {
        	if (inventory.useIngredients(getRecipes()[recipeToPurchase])) {
        		change = amtPaid - getRecipes()[recipeToPurchase].getPrice();
        	} else {
        		change = amtPaid;
        	}
        } else {
        	change = amtPaid;
        }
        
        return change;
    }
	public synchronized Recipe[] getRecipes() {
		return recipeBook.getRecipes();
	}
}
