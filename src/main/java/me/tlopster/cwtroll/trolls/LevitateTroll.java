package me.tlopster.cwtroll.trolls;

import me.tlopster.cwtroll.core.CWPluginMain;
import org.bukkit.entity.Player;

public class LevitateTroll {
    private final CWPluginMain plugin;

    public LevitateTroll(CWPluginMain plugin) {
        this.plugin = plugin;
    }

    public void execute(Player p) {
        if (!this.plugin.getLevitatePlayers().containsKey(p))
            this.plugin.getLevitatePlayers().put(p, 200);
    }
}

