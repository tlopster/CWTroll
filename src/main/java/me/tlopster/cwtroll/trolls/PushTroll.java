package me.tlopster.cwtroll.trolls;

import me.tlopster.cwtroll.core.CWPluginMain;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class PushTroll {
    private final CWPluginMain plugin;

    public PushTroll(CWPluginMain plugin) {
        this.plugin = plugin;
    }

    public void execute(Player p) {
        Block block;
        int i;
        boolean fallBlock;
        Location loc = p.getLocation();
        if (!p.getGameMode().equals(GameMode.SURVIVAL)) {
            fallBlock = false;
            for (i = 0; i <= 5; ++i) {
                block = new Location(loc.getWorld(), loc.getBlockX(), (loc.getBlockY() - i), loc.getBlockZ()).getBlock();
                if (block.getType().equals(Material.AIR)) continue;
                fallBlock = true;
            }
            if (!fallBlock && !this.plugin.getNoFallDamagePlayers().contains(p))
                this.plugin.getNoFallDamagePlayers().add(p);
            p.setGameMode(GameMode.SURVIVAL);
            p.setHealth(20.0);
            p.setFoodLevel(20);
            p.setAllowFlight(false);
        }
        if (p.getHealth() < 2.0)
            p.setHealth(2.0);
        p.damage(1.0);
        p.setVelocity(p.getLocation().getDirection().setY(0.3));
        p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_HURT, 10.0f, 0.9f);
        fallBlock = false;
        for (i = 0; i <= 5; ++i) {
            block = new Location(loc.getWorld(), loc.getBlockX(), (loc.getBlockY() - i), loc.getBlockZ()).getBlock();
            if (block.getType().equals(Material.AIR)) continue;
            fallBlock = true;
        }
        if (!fallBlock && !this.plugin.getNoFallDamagePlayers().contains(p))
            this.plugin.getNoFallDamagePlayers().add(p);
    }
}

