package me.tlopster.cwtroll.trolls;

import me.tlopster.cwtroll.core.CWPluginMain;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class LaunchTroll {
    private final CWPluginMain plugin;

    public LaunchTroll(CWPluginMain plugin) {
        this.plugin = plugin;
    }

    public void execute(Player p) {
        p.setGameMode(GameMode.SURVIVAL);
        p.setAllowFlight(false);
        p.setVelocity(new Vector(0, 10, 0));
        if (!this.plugin.getNoFallDamagePlayers().contains(p))
            this.plugin.getNoFallDamagePlayers().add(p);
        p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_LAUNCH, 10.0f, 10.0f);
    }
}

