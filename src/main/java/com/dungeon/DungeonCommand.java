package com.dungeon;

import org.bukkit.Location;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class DungeonCommand implements CommandExecutor {
    @Override public boolean onCommand(CommandSender s, Command cmd, String l, String[] a) {
        if (!(s instanceof Player p)) return true;
        if (cmd.getName().equals("dungeon")) {
            if (a.length == 0) {
                p.sendMessage("§e/dungeon <lua|bang|huvo|loi>");
                p.sendMessage("§c- /dungeon lua -> Dungeon Lua");
                p.sendMessage("§b- /dungeon bang -> Dungeon Bang");
                p.sendMessage("§5- /dungeon huvo -> Dungeon Hu Vo");
                p.sendMessage("§e- /dungeon loi -> Dungeon Loi");
                return true;
            }
            String t = a[0].toLowerCase();
            if (DungeonPlugin.getWorld(t) == null) { p.sendMessage("§cKhong co dungeon nay!"); return true; }
            p.teleport(new Location(DungeonPlugin.getWorld(t), 0, 66, 35));
            p.sendMessage("§aDa vao Dungeon " + t.toUpperCase() + "!");
            return true;
        }
        if (cmd.getName().equals("boss")) {
            if (a.length == 0) { p.sendMessage("§e/boss <lua|bang|huvo|loi>"); return true; }
            BossManager.spawnBoss(DungeonPlugin.getPlugin(DungeonPlugin.class), a[0].toLowerCase(), p.getLocation());
            p.sendMessage("§cDa spawn boss " + a[0]);
            return true;
        }
        if (cmd.getName().equals("setboss")) {
            if (a.length == 0) { p.sendMessage("§e/setboss <lua|bang|huvo|loi>"); return true; }
            String t = a[0].toLowerCase();
            p.getInventory().addItem(BossGear.makeArmor(t,"helmet"));
            p.getInventory().addItem(BossGear.makeArmor(t,"chest"));
            p.getInventory().addItem(BossGear.makeArmor(t,"legs"));
            p.getInventory().addItem(BossGear.makeArmor(t,"boots"));
            p.getInventory().addItem(BossGear.makeSword(t));
            p.sendMessage("§aDa nhan full set " + t);
            return true;
        }
        return true;
    }
}
