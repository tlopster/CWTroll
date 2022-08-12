/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.sk89q.worldguard.bukkit.WorldGuardPlugin
 *  com.sk89q.worldguard.protection.ApplicableRegionSet
 *  com.sk89q.worldguard.protection.managers.RegionManager
 *  com.sk89q.worldguard.protection.regions.ProtectedRegion
 *  org.bukkit.entity.Player
 */
package me.tlopster.cwtroll.WorldGuard;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import java.util.ArrayList;
import org.bukkit.entity.Player;

public class RegionCore {
    public ArrayList<ProtectedRegion> IntoRegionsList(Player p) {
        RegionManager rgm = WorldGuardPlugin.inst().getRegionManager(p.getWorld());
        ApplicableRegionSet ars = rgm.getApplicableRegions(p.getLocation());

        ArrayList<ProtectedRegion> regions = new ArrayList<ProtectedRegion>();

        for (ProtectedRegion prg : ars)
            regions.add(prg);

        return regions;
    }
}

