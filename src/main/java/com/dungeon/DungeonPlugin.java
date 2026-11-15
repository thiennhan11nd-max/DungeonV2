package com.dungeon;

import org.bukkit.*;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;

public class DungeonPlugin extends JavaPlugin {
    public static HashMap<String, World> worlds = new HashMap<>();
    public static HashMap<String, Location> centers = new HashMap<>();
    public static World getWorld(String type) { return worlds.get(type); }
    public static Location getCenter(String type) { return centers.get(type); }
    public static boolean isDungeonWorld(String name) { return name.startsWith("dungeon_"); }

    @Override
    public void onEnable() {
        String[] types = {"lua", "bang", "huvo", "loi"};
        for (String t : types) {
            String worldName = "dungeon_" + t;
            WorldCreator wc = new WorldCreator(worldName);
            wc.environment(World.Environment.NORMAL);
            wc.type(WorldType.FLAT);
            World w = Bukkit.createWorld(wc);
            w.setGameRule(GameRule.DO_MOB_SPAWNING, false);
            w.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
            w.setGameRule(GameRule.MOB_GRIEFING, false);
            if (t.equals("bang")) {
                w.setTime(18000);
                w.setStorm(false);
                w.setThundering(false);
            } else {
                w.setTime(6000);
            }
            w.setDifficulty(Difficulty.HARD);
            worlds.put(t, w);
            Location center = new Location(w, 0, 65, 0);
            centers.put(t, center);
            DungeonArena.buildArena(w, center, t);
        }
        getCommand("dungeon").setExecutor(new DungeonCommand());
        getCommand("boss").setExecutor(new DungeonCommand());
        getCommand("setboss").setExecutor(new DungeonCommand());
        Bukkit.getPluginManager().registerEvents(new DungeonListener(), this);
        Bukkit.getPluginManager().registerEvents(new SetBonusListener(), this);
        DungeonCraft.registerRecipes(this);
        for (String t : types) DungeonSpawner.startSpawner(this, t);
        BossRespawn.initSpawns();
        Bukkit.getScheduler().runTaskLater(this, () -> BossRespawn.spawnAllBosses(this), 60L);
        getLogger().info("DungeonV2 da bat! 4 Boss - 4 World!");
    }
}
