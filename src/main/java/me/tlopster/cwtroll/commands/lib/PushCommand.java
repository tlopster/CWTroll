package me.tlopster.cwtroll.commands.lib;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import java.util.ArrayList;
import me.tlopster.cwtroll.commands.AbstractCommand;
import me.tlopster.cwtroll.core.CWPluginMain;
import me.tlopster.cwtroll.tools.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PushCommand
extends AbstractCommand {
    private final CWPluginMain plugin;

    public PushCommand(CWPluginMain plugin) {
        super("push");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player))
            Message.onlyPlayer.send(sender);
        else if (sender.hasPermission("cwtroll.command.push")) {
            if (args.length >= 1) {
                Player p = Bukkit.getPlayer(args[0]);
                if (p != null) {
                    if (p.hasPermission("cwtroll.bypass.push") && !sender.hasPermission("cwtroll.exempt.push"))
                        Message.cantTrollThisPlayer.send(sender);
                    else if(!sender.hasPermission("cwrelog.bypass.pvp")
                            && me.tlopster.cwrelog.core.CWPluginMain.getInstance().getPlayersInPVP().containsKey(p))
                        Message.playerInPVP.send(sender);
                    else {
                        ArrayList<ProtectedRegion> playerIntoRegions = this.plugin.getRegionCore().IntoRegionsList(p);
                        if (this.plugin.getConf().trollIsBlockInRegion("push")
                                && !sender.hasPermission("cwtroll.regionbypass.push") && playerIntoRegions.size() > 0
                                && !playerIntoRegions.get(0).getOwners().contains(((Player)sender).getUniqueId())
                                && !playerIntoRegions.get(0).getMembers().contains(((Player)sender).getUniqueId()))
                            Message.playerInRegion.send(sender);
                        else {
                            Message.trollMessages_push_sender.replace("%player%", p.getName()).send(sender);
                            Message.trollMessages_push_player.replace("%sender%", sender.getName()).send((CommandSender)p);
                            this.plugin.PushTroll.execute(p);
                            this.plugin.getLog().sendLogger(ChatColor.YELLOW + this.plugin.getUtilites().getStrTime() + " "
                                    + ChatColor.RED + sender.getName()
                                    + ChatColor.WHITE + " пнул "
                                    + ChatColor.GREEN + p.getName()
                            );
                        }
                    }
                } else Message.playerNotFound.send(sender);
            } else Message.usage_push.send(sender);
        } else Message.noPermissions.send(sender);
    }
}

