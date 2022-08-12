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

public class LaunchCommand
extends AbstractCommand {
    private final CWPluginMain plugin;

    public LaunchCommand(CWPluginMain plugin) {
        super("launch");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Message.onlyPlayer.send(sender);
        } else if (sender.hasPermission("cwtroll.command.launch")) {
            if (args.length >= 1) {
                Player p = Bukkit.getPlayer(args[0]);
                if (p != null) {
                    if (p.hasPermission("cwtroll.bypass.launch") && !sender.hasPermission("cwtroll.exempt.launch"))
                        Message.cantTrollThisPlayer.send(sender);
                    else if(!sender.hasPermission("cwrelog.bypass.pvp")
                            && me.tlopster.cwrelog.core.CWPluginMain.getInstance().getPlayersInPVP().containsKey(p))
                        Message.playerInPVP.send(sender);
                    else {
                        ArrayList<ProtectedRegion> playerIntoRegions = this.plugin.getRegionCore().IntoRegionsList(p);
                        if (this.plugin.getConf().trollIsBlockInRegion("launch")
                                && !sender.hasPermission("cwtroll.regionbypass.launch") && playerIntoRegions.size() > 0
                                && !playerIntoRegions.get(0).getOwners().contains(((Player)sender).getUniqueId())
                                && !playerIntoRegions.get(0).getMembers().contains(((Player)sender).getUniqueId()))
                            Message.playerInRegion.send(sender);
                        else {
                            Message.trollMessages_launch_sender.replace("%player%", p.getName()).send(sender);
                            Message.trollMessages_launch_player.replace("%sender%", sender.getName()).send((CommandSender)p);
                            this.plugin.LaunchTroll.execute(p);
                            this.plugin.getLog().sendLogger(ChatColor.YELLOW + this.plugin.getUtilites().getStrTime() + " "
                                    + ChatColor.RED + sender.getName()
                                    + ChatColor.WHITE + " подкинул "
                                    + ChatColor.GREEN + p.getName()
                            );
                        }
                    }
                } else {
                    Message.playerNotFound.send(sender);
                }
            } else {
                Message.usage_launch.send(sender);
            }
        } else {
            Message.noPermissions.send(sender);
        }
    }
}

