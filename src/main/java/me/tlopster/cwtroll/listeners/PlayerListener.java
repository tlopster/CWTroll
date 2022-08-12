package me.tlopster.cwtroll.listeners;

import com.Zrips.CMI.events.CMIPlayerTeleportEvent;
import me.tlopster.cwtroll.core.CWPluginMain;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerListener
implements Listener {
    private final CWPluginMain plugin;

    public PlayerListener(CWPluginMain plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if (e.getDeathMessage().contains("squashed by a falling anvil"))
            e.setKeepInventory(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntityType().equals(EntityType.PLAYER)) {
            Player p = (Player)e.getEntity();
            if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL) && this.plugin.getNoFallDamagePlayers().contains(p)) {
                e.setCancelled(true);
                this.plugin.getNoFallDamagePlayers().remove(p);
            } else if (e.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) && this.plugin.getNoTNTDamagePlayers().contains(p)) {
                e.setCancelled(true);
                this.plugin.getNoTNTDamagePlayers().remove(p);
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Location loc = p.getLocation();
        if (loc.getBlock().getType().equals(Material.AIR)) {
            boolean fallBlock = false;
            for (int i = 0; i <= 5; ++i) {
                Block block = new Location(loc.getWorld(), loc.getBlockX(), (loc.getBlockY() - i), loc.getBlockZ()).getBlock();
                if (block.getType().equals(Material.AIR)) continue;
                fallBlock = true;
            }
            if (!fallBlock && !this.plugin.getNoFallDamagePlayers().contains(p))
                this.plugin.getNoFallDamagePlayers().add(p);
        }
    }

    @EventHandler
    public void onKick(PlayerKickEvent e) {
        if (e.getReason().equals("Flying is not enabled on this server"))
            e.setCancelled(true);
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {
        if(plugin.getLevitatePlayers().containsKey(e.getPlayer()))
            e.setCancelled(true);
    }

    @EventHandler
    public void onCMITeleport(CMIPlayerTeleportEvent e) {
        if(plugin.getLevitatePlayers().containsKey(e.getPlayer()))
            e.setCancelled(true);
    }
}

