package me.tlopster.cwtroll.trolls;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class AnvilTroll {
    public void execute(Player target) {
        Location loc = target.getLocation();
        double Y1 = Math.round(loc.getY());
        double X = loc.getX();
        double Z = loc.getZ();
        double X2 = Math.floor(X) - 0.5;
        double Z2 = Math.floor(Z) + 0.5;
        loc.setY(Y1);
        loc.setX(X2 + 1.0);
        loc.setZ(Z2);
        target.teleport(loc);
        for (double i = Y1 + 1.0; i < Y1 + 30.0; i += 1.0) {
            loc.setY(i);
            loc.getBlock().setType(Material.AIR);
        }
        loc.setY(Y1 += 25.0);
        loc.getBlock().setType(Material.ANVIL);
        loc.setY(Y1 += 1.0);
        loc.getBlock().setType(Material.ANVIL);
    }
}

