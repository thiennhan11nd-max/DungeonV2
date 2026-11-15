package com.dungeon;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.*;

public class BossGear {
    public static ItemStack makeEssence(String type) {
        Material m = Material.BLAZE_POWDER; String name = "§c§lTINH HOA LUA"; String lore = "§7Rot tu Hoa Vuong - Dung de che Set Lua";
        if (type.equals("bang")) { m = Material.PACKED_ICE; name = "§b§lTINH HOA BANG"; lore = "§7Rot tu Bang Hau - Dung de che Set Bang"; }
        if (type.equals("huvo")) { m = Material.ENDER_PEARL; name = "§5§lTINH HOA HU VO"; lore = "§7Rot tu Hu Vo Chua - Dung de che Set Hu Vo"; }
        if (type.equals("loi")) { m = Material.LIGHTNING_ROD; name = "§e§lTINH HOA SAM"; lore = "§7Rot tu Loi Than - Dung de che Set Sam"; }
        ItemStack it = new ItemStack(m);
        ItemMeta meta = it.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore, "§e§lDO HIEM - CHI BOSS MOI ROT"));
        meta.addEnchant(Enchantment.UNBREAKING, 1, true);
        it.setItemMeta(meta);
        return it;
    }

    public static ItemStack makeArmor(String type, String part) {
        Material m = Material.NETHERITE_HELMET;
        String color = "§c"; String setName = "LUA"; double hpBonus = 4;
        if (type.equals("bang")) { color = "§b"; setName = "BANG"; }
        if (type.equals("huvo")) { color = "§5"; setName = "HU VO"; }
        if (type.equals("loi")) { color = "§e"; setName = "LOI THAN"; }
        if (part.equals("chest")) { m = Material.NETHERITE_CHESTPLATE; hpBonus = 8; }
        if (part.equals("legs")) { m = Material.NETHERITE_LEGGINGS; hpBonus = 6; }
        if (part.equals("boots")) { m = Material.NETHERITE_BOOTS; hpBonus = 4; }
        ItemStack it = new ItemStack(m);
        ItemMeta meta = it.getItemMeta();
        meta.setDisplayName(color + "§lGIAP " + setName + " - " + part.toUpperCase());
        meta.setLore(Arrays.asList("§7Set: " + setName, "§a+ " + hpBonus + " Mau toi da", "§6Mac full 4 mon de kich hoat Noi Tai!", "§8Set: " + type));
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.PROTECTION, 6, true);
        meta.addEnchant(Enchantment.UNBREAKING, 5, true);
        meta.addEnchant(Enchantment.MENDING, 1, true);
        if (type.equals("lua")) meta.addEnchant(Enchantment.FIRE_PROTECTION, 6, true);
        if (type.equals("bang")) meta.addEnchant(Enchantment.BLAST_PROTECTION, 6, true);
        if (type.equals("huvo")) meta.addEnchant(Enchantment.PROJECTILE_PROTECTION, 6, true);
        if (type.equals("loi")) meta.addEnchant(Enchantment.THORNS, 5, true);
        AttributeModifier mod = new AttributeModifier(UUID.randomUUID(), "dungeon_hp", hpBonus, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR);
        meta.addAttributeModifier(Attribute.MAX_HEALTH, mod);
        it.setItemMeta(meta);
        return it;
    }

    public static ItemStack makeSword(String type) {
        ItemStack s = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = s.getItemMeta();
        String color = "§c"; String name = "KIEM LUA";
        if (type.equals("bang")) { color = "§b"; name = "KIEM BANG"; }
        if (type.equals("huvo")) { color = "§5"; name = "KIEM HU VO"; }
        if (type.equals("loi")) { color = "§e"; name = "KIEM LOI THAN"; }
        meta.setDisplayName(color + "§l" + name);
        meta.setLore(Arrays.asList("§7Kiem cua boss " + type, "§e§l+ 25 Sat Thuong", "§aChem boss cuc tham!", "§8Set: " + type));
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.SHARPNESS, 8, true);
        meta.addEnchant(Enchantment.LOOTING, 4, true);
        meta.addEnchant(Enchantment.UNBREAKING, 5, true);
        meta.addEnchant(Enchantment.MENDING, 1, true);
        if (type.equals("lua")) meta.addEnchant(Enchantment.FIRE_ASPECT, 3, true);
        if (type.equals("bang")) meta.addEnchant(Enchantment.KNOCKBACK, 3, true);
        if (type.equals("huvo")) meta.addEnchant(Enchantment.SWEEPING_EDGE, 5, true);
        if (type.equals("loi")) meta.addEnchant(Enchantment.KNOCKBACK, 2, true);
        AttributeModifier dmg = new AttributeModifier(UUID.randomUUID(), "dungeon_dmg", 25, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, dmg);
        AttributeModifier spd = new AttributeModifier(UUID.randomUUID(), "dungeon_spd", 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, spd);
        int cmd = 1;
        if (type.equals("bang")) cmd = 2;
        if (type.equals("huvo")) cmd = 3;
        if (type.equals("loi")) cmd = 4;
        meta.setCustomModelData(cmd);
        s.setItemMeta(meta);
        return s;
    }
}
