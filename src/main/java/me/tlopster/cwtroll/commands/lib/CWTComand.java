package me.tlopster.cwtroll.commands.lib;

import com.google.common.collect.Lists;
import java.util.List;
import me.tlopster.cwtroll.commands.AbstractCommand;
import me.tlopster.cwtroll.core.CWPluginMain;
import me.tlopster.cwtroll.tools.Message;
import org.bukkit.command.CommandSender;

public class CWTComand
extends AbstractCommand {
    private final CWPluginMain plugin;

    public CWTComand(CWPluginMain plugin) {
        super("cwt");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if (sender.hasPermission("cwtroll.reload")) {
            if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    this.plugin.reloadConfig();
                    this.plugin.getConf().reload();
                    Message.load(this.plugin.getConfig(), this.plugin.isPlaceholderAPIEnabled());
                    Message.reload.send(sender);
                } else Message.usage_cwt.send(sender);
            } else Message.usage_cwt.send(sender);
        } else Message.noPermissions.send(sender);
    }

    @Override
    public List<String> complete(CommandSender sender, String[] args) {
        if (args.length == 1 && sender.hasPermission("cwtroll.reload"))
            return Lists.newArrayList("reload");
        else
            return Lists.newArrayList();
    }
}

