package com.dungeon;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.persistence.PersistentDataType;

public class DungeonListener implements Listener {
    @EventHandler public void onBreak(BlockBreakEvent e) {
        if (DungeonPlugin.isDungeonWorld(e.getPlayer().getWorld().getName())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("§cKhong duoc pha block trong Dungeon!");
        }
    }
    @EventHandler public void onPlace(BlockPlaceEvent e) {
        if (DungeonPlugin.isDungeonWorld(e.getPlayer().getWorld().getName())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("§cKhong duoc dat block trong Dungeon!");
        }
    }
    @EventHandler public void onDeath(EntityDeathEvent e) {
        LivingEntity en = e.getEntity();
        if (en.getKiller() != null && en.getPersistentDataContainer().has(BossManager.BOSS_KEY)) {
            String type = en.getPersistentDataContainer().get(BossManager.BOSS_KEY, PersistentDataType.STRING);
            e.getDrops().clear();
            BossManager.handleBossDeath(en, en.getKiller());
            BossRespawn.scheduleRespawn(DungeonPlugin.getPlugin(DungeonPlugin.class), type);
        }
    }
}
