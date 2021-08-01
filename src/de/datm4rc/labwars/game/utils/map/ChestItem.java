package de.datm4rc.labwars.game.utils.map;

import org.bukkit.inventory.ItemStack;

public class ChestItem {

    private ItemStack itemStack;
    private int probability;

    public ChestItem(ItemStack itemStack, int probability){
        this.itemStack = itemStack;
        this.probability = probability;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public int getProbability() {
        return probability;
    }
}
