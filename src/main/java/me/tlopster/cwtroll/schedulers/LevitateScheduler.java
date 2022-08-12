package me.tlopster.cwtroll.schedulers;

import java.util.Iterator;
import java.util.Map;
import me.tlopster.cwtroll.core.CWPluginMain;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class LevitateScheduler {
    private final CWPluginMain plugin = CWPluginMain.getInstance();

    public LevitateScheduler() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this.plugin, () -> {
            for (Iterator<Map.Entry<Player, Integer>> itr = this.plugin.getLevitatePlayers().entrySet().iterator(); itr.hasNext();) {
                    Map.Entry<Player, Integer> obj = itr.next();
                    Player p = obj.getKey();
                    p.setGameMode(GameMode.SURVIVAL);
                    p.setAllowFlight(false);
                    if (obj.getValue() > 0) {
                        obj.setValue(obj.getValue() - 1);
                        p.setVelocity(new Vector(0.0, 0.3, 0.0));
                    } else {
                        if (!this.plugin.getNoFallDamagePlayers().contains(p))
                            this.plugin.getNoFallDamagePlayers().add(p);
                        itr.remove();
                    }
            }
        }, 0L, 1L);
    }
}

