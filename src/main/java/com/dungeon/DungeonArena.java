package com.dungeon;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class DungeonArena {
    public static void buildArena(World w, Location c, String type) {
        Material floor = Material.DEEPSLATE_TILES;
        Material wall = Material.OBSIDIAN;
        if (type.equals("lua")) { floor = Material.NETHERRACK; wall = Material.MAGMA_BLOCK; }
        if (type.equals("bang")) { floor = Material.PACKED_ICE; wall = Material.BLUE_ICE; }
        if (type.equals("huvo")) { floor = Material.END_STONE; wall = Material.PURPUR_BLOCK; }
        if (type.equals("loi")) { floor = Material.GOLD_BLOCK; wall = Material.LIGHTNING_ROD; }
        int y = 64;
        for (int x = -40; x <= 40; x++) {
            for (int z = -40; z <= 40; z++) {
                w.getBlockAt(c.getBlockX()+x, y, c.getBlockZ()+z).setType(floor);
            }
        }
        for (int i = -40; i <= 40; i++) {
            for (int h = 1; h <= 6; h++) {
                w.getBlockAt(c.getBlockX()+i, y+h, c.getBlockZ()-40).setType(wall);
                w.getBlockAt(c.getBlockX()+i, y+h, c.getBlockZ()+40).setType(wall);
                w.getBlockAt(c.getBlockX()-40, y+h, c.getBlockZ()+i).setType(wall);
                w.getBlockAt(c.getBlockX()+40, y+h, c.getBlockZ()+i).setType(wall);
            }
        }
        w.getBlockAt(c.getBlockX(), y+1, c.getBlockZ()).setType(Material.BEACON);
    }
}
