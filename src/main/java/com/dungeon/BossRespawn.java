package com.dungeon;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.HashMap;

public class BossRespawn {
    public static HashMap<String, Location> bossSpawns = new HashMap<>();
    public static void initSpawns() {
        bossSpawns.put("lua", new Location(DungeonPlugin.getWorld("lua"), 0, 66, 0));
        bossSpawns.put("bang", new Location(DungeonPlugin.getWorld("bang"), 0, 66, 0));
        bossSpawns.put("huvo", new Location(DungeonPlugin.getWorld("huvo"), 0, 66, 0));
        bossSpawns.put("loi", new Location(DungeonPlugin.getWorld("loi"), 0, 66, 0));
    }
    public static void scheduleRespawn(JavaPlugin plugin, String type) {
        Location loc = bossSpawns.get(type);
        if (loc == null) return;
        Bukkit.broadcastMessage("§c§lBoss " + type.toUpperCase() + " da chet! Se hoi sinh sau 60s o dungeon_" + type);
        new BukkitRunnable() {
            int countdown = 60;
            @Override public void run() {
                if (countdown == 30) Bukkit.broadcastMessage("§eBoss " + type + " hoi sinh sau 30s!");
                if (countdown == 10) Bukkit.broadcastMessage("§eBoss " + type + " hoi sinh sau 10s!");
                if (countdown <= 0) {
                    BossManager.spawnBoss(plugin, type, loc);
                    Bukkit.broadcastMessage("§c§lBoss " + type.toUpperCase() + " DA HOI SINH o dungeon_" + type + "!");
                    cancel(); return;
                }
                countdown--;
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }
    public static void spawnAllBosses(JavaPlugin plugin) {
        for (String type : bossSpawns.keySet()) BossManager.spawnBoss(plugin, type, bossSpawns.get(type));
        Bukkit.broadcastMessage("§a§l4 Boss da xuat hien o 4 the gioi rieng!");
    }
}
