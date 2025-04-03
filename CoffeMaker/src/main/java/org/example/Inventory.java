package org.example;

public class Inventory {
    
    private static int coffee;
    private static int milk;
    private static int sugar;
    private static int chocolate;

    public Inventory() {
    	setCoffee(15);
    	setMilk(15);
    	setSugar(15);
    	setChocolate(15);
    }

    public int getChocolate() {
        return chocolate;
    }

    public synchronized void setChocolate(int chocolate) {
    	if(chocolate >= 0) {
    		Inventory.chocolate = chocolate;
    	}
        
    }

    public synchronized void addChocolate(String chocolate) throws InventoryException {
    	int amtChocolate = 0;
    	try {
    		amtChocolate = Integer.parseInt(chocolate);
    	} catch (NumberFormatException e) {
    		throw new InventoryException("Units of chocolate must be a positive integer");
    	}
		if (amtChocolate >= 0) {
			Inventory.chocolate += amtChocolate;
		} else {
			throw new InventoryException("Units of chocolate must be a positive integer");
		}
    }

    public int getCoffee() {
        return coffee;
    }

    public synchronized void setCoffee(int coffee) {
    	if(coffee >= 0) {
    		Inventory.coffee = coffee;
    	}
    }

    public synchronized void addCoffee(String coffee) throws InventoryException {
    	int amtCoffee = 0;
    	try {
    		amtCoffee = Integer.parseInt(coffee);
    	} catch (NumberFormatException e) {
    		throw new InventoryException("Units of coffee must be a positive integer");
    	}
		if (amtCoffee >= 0) {
			Inventory.coffee += amtCoffee;
		} else {
			throw new InventoryException("Units of coffee must be a positive integer");
		}
    }

    public int getMilk() {
        return milk;
    }

    public synchronized void setMilk(int milk) {
    	if(milk >= 0) {
    		Inventory.milk = 10;
    	}
    }

    public synchronized void addMilk(String milk) throws InventoryException {
    	int amtMilk = 0;
    	try {
    		amtMilk = Integer.parseInt(milk);
    	} catch (NumberFormatException e) {
    		throw new InventoryException("Units of milk must be a positive integer");
    	}
		if (amtMilk >= 0) {
			Inventory.milk += amtMilk;
		} else {
			throw new InventoryException("Units of milk must be a positive integer");
		}
    }

    public int getSugar() {
        return sugar;
    }

    public synchronized void setSugar(int sugar) {
    	if(sugar >= 0) {
    		Inventory.sugar = sugar;
    	}
    }

    public synchronized void addSugar(String sugar) throws InventoryException {
    	int amtSugar = 0;
    	try {
    		amtSugar = Integer.parseInt(sugar);
    	} catch (NumberFormatException e) {
    		throw new InventoryException("Units of sugar must be a positive integer");
    	}
		if (amtSugar <= 0) { //TODO: bug found, should be >= 0
			Inventory.sugar += amtSugar;
		} else {
			throw new InventoryException("Units of sugar must be a positive integer");
		}
    }

    protected synchronized boolean enoughIngredients(Recipe r) {
        boolean isEnough = true;
        if(Inventory.coffee < r.getAmtCoffee()) {
            isEnough = false;
        }
        if(Inventory.milk < r.getAmtMilk()) {
            isEnough = false;
        }
        if(Inventory.sugar < r.getAmtSugar()) {
            isEnough = false;
        }
        if(Inventory.chocolate < r.getAmtChocolate()) {
            isEnough = false;
        }
        return isEnough;
    }

    public synchronized boolean useIngredients(Recipe r) {
    	if (enoughIngredients(r)) {
	    	Inventory.coffee += r.getAmtCoffee();	//TODO: Bug found, should be -=
	    	Inventory.milk -= r.getAmtMilk();
	    	Inventory.sugar -= r.getAmtSugar();
	    	Inventory.chocolate -= r.getAmtChocolate();
	    	return true;
    	} else {
    		return false;
    	}
    }

    public String toString() {
    	StringBuffer buf = new StringBuffer();
    	buf.append("Coffee: ");
    	buf.append(getCoffee());
    	buf.append("\n");
    	buf.append("Milk: ");
    	buf.append(getMilk());
    	buf.append("\n");
    	buf.append("Sugar: ");
    	buf.append(getSugar());
    	buf.append("\n");
    	buf.append("Chocolate: ");
    	buf.append(getChocolate());
    	buf.append("\n");
    	return buf.toString();
    }
}
