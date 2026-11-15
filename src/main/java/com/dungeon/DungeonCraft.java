package com.dungeon;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class DungeonCraft {
    public static void registerRecipes(JavaPlugin plugin) {
        makeSet(plugin, "lua", BossGear.makeEssence("lua"));
        makeSet(plugin, "bang", BossGear.makeEssence("bang"));
        makeSet(plugin, "huvo", BossGear.makeEssence("huvo"));
        makeSet(plugin, "loi", BossGear.makeEssence("loi"));
    }
    private static void makeSet(JavaPlugin p, String type, org.bukkit.inventory.ItemStack essence) {
        Material mat = essence.getType();
        ShapedRecipe helmet = new ShapedRecipe(new NamespacedKey(p, type+"_helmet"), BossGear.makeArmor(type,"helmet"));
        helmet.shape("MMM","M M"); helmet.setIngredient('M', mat); Bukkit.addRecipe(helmet);
        ShapedRecipe chest = new ShapedRecipe(new NamespacedKey(p, type+"_chest"), BossGear.makeArmor(type,"chest"));
        chest.shape("M M","MMM","MMM"); chest.setIngredient('M', mat); Bukkit.addRecipe(chest);
        ShapedRecipe legs = new ShapedRecipe(new NamespacedKey(p, type+"_legs"), BossGear.makeArmor(type,"legs"));
        legs.shape("MMM","M M","M M"); legs.setIngredient('M', mat); Bukkit.addRecipe(legs);
        ShapedRecipe boots = new ShapedRecipe(new NamespacedKey(p, type+"_boots"), BossGear.makeArmor(type,"boots"));
        boots.shape("M M","M M"); boots.setIngredient('M', mat); Bukkit.addRecipe(boots);
        ShapedRecipe sword = new ShapedRecipe(new NamespacedKey(p, type+"_sword"), BossGear.makeSword(type));
        sword.shape(" M "," M "," S ");
        sword.setIngredient('M', mat);
        sword.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(sword);
    }
}
