package com.dungeon;

import org.bukkit.World;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.ArrayList;
import java.util.List;

public class DungeonSpawner {
    private static final int MAX_MOBS = 20;

    public static void startSpawner(JavaPlugin plugin, String type) {
        new BukkitRunnable() {
            @Override public void run() {
                World w = DungeonPlugin.getWorld(type);
                Location c = DungeonPlugin.getCenter(type);
                if (w == null || c == null) return;

                // DEM CA THE GIOI - KHONG DEM QUANH SAN NUA
                List<Entity> mobs = new ArrayList<>();
                for (Entity e : w.getEntities()) {
                    if (e instanceof Monster && !e.getPersistentDataContainer().has(BossManager.BOSS_KEY)) {
                        mobs.add(e);
                    }
                }

                // NEU QUA 20 CON -> XOA BOT CON DU
                if (mobs.size() > MAX_MOBS) {
                    for (int i = MAX_MOBS; i < mobs.size(); i++) {
                        mobs.get(i).remove();
                    }
                    return;
                }

                // DU 20 THI DUNG - KHONG SPAWN NUA
                if (mobs.size() >= MAX_MOBS) return;

                // CHI SPAWN 1 CON / 5 GIAY
                Location loc = c.clone().add(Math.random()*60-30, 1, Math.random()*60-30);
                EntityType[] types = {EntityType.ZOMBIE, EntityType.SKELETON, EntityType.SPIDER, EntityType.CREEPER};
                Entity spawned = w.spawnEntity(loc, types[(int)(Math.random()*types.length)]);
                if (spawned instanceof LivingEntity le) {
                    le.setRemoveWhenFarAway(true);
                }
            }
        }.runTaskTimer(plugin, 0L, 100L);
    }
}
