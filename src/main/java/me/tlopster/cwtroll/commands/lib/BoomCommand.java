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

public class BoomCommand
extends AbstractCommand {
    private final CWPluginMain plugin;

    public BoomCommand(CWPluginMain plugin) {
        super("boom");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player))
            Message.onlyPlayer.send(sender);
        else if (sender.hasPermission("cwtroll.command.boom")) {
            if (args.length >= 1) {
                Player p = Bukkit.getPlayer(args[0]);
                if (p != null) {
                    if (p.hasPermission("cwtroll.bypass.boom") && !sender.hasPermission("cwtroll.exempt.boom"))
                        Message.cantTrollThisPlayer.send(sender);
                    else if(!sender.hasPermission("cwrelog.bypass.pvp")
                            && me.tlopster.cwrelog.core.CWPluginMain.getInstance().getPlayersInPVP().containsKey(p))
                        Message.playerInPVP.send(sender);
                    else {
                        ArrayList<ProtectedRegion> playerIntoRegions = this.plugin.getRegionCore().IntoRegionsList(p);
                        if (this.plugin.getConf().trollIsBlockInRegion("boom")
                                && !sender.hasPermission("cwtroll.regionbypass.boom") && playerIntoRegions.size() > 0
                                && !playerIntoRegions.get(0).getOwners().contains(((Player)sender).getUniqueId())
                                && !playerIntoRegions.get(0).getMembers().contains(((Player)sender).getUniqueId()))
                            Message.playerInRegion.send(sender);
                        else {
                            this.plugin.BoomTroll.execute(p);
                            Message.trollMessages_boom_sender.replace("%player%", p.getName()).send(sender);
                            Message.trollMessages_boom_player.replace("%sender%", sender.getName()).send((CommandSender)p);
                            this.plugin.getLog().sendLogger(ChatColor.YELLOW + this.plugin.getUtilites().getStrTime() + " "
                                    + ChatColor.RED + sender.getName()
                                    + ChatColor.WHITE + " взорвал "
                                    + ChatColor.GREEN + p.getName()
                            );
                        }
                    }
                } else Message.playerNotFound.send(sender);
            } else Message.usage_boom.send(sender);
        } else Message.noPermissions.send(sender);
    }
}

