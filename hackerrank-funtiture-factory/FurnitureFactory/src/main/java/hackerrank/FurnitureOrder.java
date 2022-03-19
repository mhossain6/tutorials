package hackerrank;

import java.util.HashMap;

public class FurnitureOrder implements FurnitureOrderInterface {
    /**
     * TODO: Create a map of Furniture items to order quantities
     */

    /**
     * Initialize a new mapping of Furniture types to order quantities.
     */
    HashMap<Furniture, Integer> furnitureOrderQuantities;

    FurnitureOrder() {
        // TODO: Complete the constructor
        this.furnitureOrderQuantities = new HashMap<>();
    }

    public void addToOrder(final Furniture type, final int furnitureCount) {
        // TODO: Complete the method
        Integer ret = furnitureOrderQuantities.get(type);
        if (ret == null) {
            furnitureOrderQuantities.put(type, new Integer(furnitureCount));
        } else {
            furnitureOrderQuantities.put(type, ret + new Integer(furnitureCount));
        }
    }

    public HashMap<Furniture, Integer> getOrderedFurniture() {
        // TODO: Complete the method
        return this.furnitureOrderQuantities;
    }

    public float getTotalOrderCost() {
        // TODO: Complete the method
        final Float[] totalCost = {0.0f};
        furnitureOrderQuantities.forEach((k, v) -> {

            totalCost[0] += (k.cost() * v);
        });

        return totalCost[0];
    }

    public int getTypeCount(Furniture type) {
        // TODO: Complete the method
        Integer ret = furnitureOrderQuantities.get(type);
        return ret != null ? furnitureOrderQuantities.get(type).intValue() : 0;
    }

    public float getTypeCost(Furniture type) {
        // TODO: Complete the method
        Integer ret = furnitureOrderQuantities.get(type);

        return null == ret ? 0.0f : ret.floatValue() * type.cost();
    }

    public int getTotalOrderQuantity() {
        // TODO: Complete the method
        return furnitureOrderQuantities.values().stream().reduce(0, Integer::sum);
    }
}