package me.tlopster.cwtroll.trolls;

import me.tlopster.cwtroll.core.CWPluginMain;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class BoomTroll {
    private final CWPluginMain plugin;

    public BoomTroll(CWPluginMain plugin) {
        this.plugin = plugin;
    }

    public void execute(Player p) {
        Location loc = p.getLocation();
        if (!p.getGameMode().equals(GameMode.SURVIVAL)) {
            boolean fallBlock = false;
            for (int i = 0; i <= 5; ++i) {
                Block block = new Location(loc.getWorld(), loc.getBlockX(), (loc.getBlockY() - i), loc.getBlockZ()).getBlock();
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
        p.setHealth(20.0);
        p.damage(5.0);
        if (!this.plugin.getNoTNTDamagePlayers().contains(p))
            this.plugin.getNoTNTDamagePlayers().add(p);
        loc.getWorld().createExplosion(loc, 3.0f, false);
    }
}

