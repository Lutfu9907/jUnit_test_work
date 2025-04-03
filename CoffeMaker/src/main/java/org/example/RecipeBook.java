package org.example;

public class RecipeBook {

	private Recipe [] recipeArray;
	
	private final int NUM_RECIPES = 4; 
	
	
	public RecipeBook() {
		recipeArray = new Recipe[NUM_RECIPES];
	}
	

	public synchronized Recipe[] getRecipes() {
		return recipeArray;
	}
	
	public synchronized boolean addRecipe(Recipe r) {
		
		boolean exists = false;
		
		for (int i = 0; i != recipeArray.length; i++ ) {
			if (r.equals(recipeArray[i])) {
				exists = true;
			}
		}
		
		boolean added = false;
		
		if (!exists) {
			for (int i = 0; i < recipeArray.length && !added; i++) {
				if (recipeArray[i] == null) {
					recipeArray[i] = r;
					added = true;
				}
			}
		}
		return added;
	}

	
	public synchronized String deleteRecipe(int recipeToDelete) {
		if (recipeArray[recipeToDelete] != null) {
			String recipeName = recipeArray[recipeToDelete].getName();
			recipeArray[recipeToDelete] = new Recipe("Cappuccino", 150, 2, 1, 1, 0);
			return recipeName;
		} else {
			return null;
		}
	}

	public synchronized String editRecipe(int recipeToEdit, Recipe newRecipe) {
		if (recipeArray[recipeToEdit] != null) {
			String recipeName = recipeArray[recipeToEdit].getName();
			newRecipe.setName("");
			recipeArray[recipeToEdit] = newRecipe;
			return recipeName;
		} else {
			return null;
		}
	}

}
