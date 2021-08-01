package de.datm4rc.labwars.game.utils.map;

import de.datm4rc.labwars.game.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;

import java.util.ArrayList;

public class ChestManager {

    public ArrayList<ChestItem> fillChestList(){
        ArrayList<ChestItem> chestItems = new ArrayList<>();
        //Blöcke
        chestItems.add(new ChestItem(new ItemBuilder(Material.STONE, 32).toItemStack(), 65));
        chestItems.add(new ChestItem(new ItemBuilder(Material.BRICK, 64).toItemStack(), 45));
        chestItems.add(new ChestItem(new ItemBuilder(Material.ENDER_STONE, 36).toItemStack(), 55));
        chestItems.add(new ChestItem(new ItemBuilder(Material.WOOD, 43).toItemStack(), 73));
        chestItems.add(new ChestItem(new ItemBuilder(Material.BRICK, 2).toItemStack(), 45));
        //Schwerter
        chestItems.add(new ChestItem(new ItemBuilder(Material.STONE_SWORD, 1).addEnchant(Enchantment.DAMAGE_ALL, 1).toItemStack(), 35));
        chestItems.add(new ChestItem(new ItemBuilder(Material.WOOD_SWORD, 1).addEnchant(Enchantment.DAMAGE_ALL, 2).toItemStack(), 55));
        chestItems.add(new ChestItem(new ItemBuilder(Material.IRON_SWORD, 1).toItemStack(), 55));
        //Rüstung
        chestItems.add(new ChestItem(new ItemBuilder(Material.DIAMOND_HELMET, 1).toItemStack(), 18));
        chestItems.add(new ChestItem(new ItemBuilder(Material.DIAMOND_BOOTS, 1).toItemStack(), 20));
        chestItems.add(new ChestItem(new ItemBuilder(Material.LEATHER_BOOTS, 1).toItemStack(), 65));
        chestItems.add(new ChestItem(new ItemBuilder(Material.LEATHER_CHESTPLATE, 1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack(), 65));
        chestItems.add(new ChestItem(new ItemBuilder(Material.LEATHER_LEGGINGS, 1).toItemStack(), 65));
        chestItems.add(new ChestItem(new ItemBuilder(Material.LEATHER_HELMET, 1).toItemStack(), 65));
        chestItems.add(new ChestItem(new ItemBuilder(Material.CHAINMAIL_LEGGINGS, 1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack(), 50));
        chestItems.add(new ChestItem(new ItemBuilder(Material.CHAINMAIL_HELMET, 1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack(), 50));
        chestItems.add(new ChestItem(new ItemBuilder(Material.IRON_CHESTPLATE, 1).addEnchant(Enchantment.DAMAGE_ALL, 1).toItemStack(), 40));
        chestItems.add(new ChestItem(new ItemBuilder(Material.IRON_CHESTPLATE, 1).toItemStack(), 45));
        //Coins
        chestItems.add(new ChestItem(new ItemBuilder(Material.EXP_BOTTLE, 8).setName("§cCoins").toItemStack(), 40));
        chestItems.add(new ChestItem(new ItemBuilder(Material.EXP_BOTTLE, 16).setName("§cCoins").toItemStack(), 30));
        chestItems.add(new ChestItem(new ItemBuilder(Material.EXP_BOTTLE, 32).setName("§cCoins").toItemStack(), 10));
        return chestItems;
    }

}
