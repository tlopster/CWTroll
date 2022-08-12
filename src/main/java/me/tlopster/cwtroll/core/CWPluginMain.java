package me.tlopster.cwtroll.core;

import java.util.ArrayList;
import java.util.HashMap;
import me.tlopster.cwtroll.WorldGuard.RegionCore;
import me.tlopster.cwtroll.commands.lib.BoomCommand;
import me.tlopster.cwtroll.commands.lib.CWTComand;
import me.tlopster.cwtroll.commands.lib.KartoxaCommand;
import me.tlopster.cwtroll.commands.lib.KovCommand;
import me.tlopster.cwtroll.commands.lib.LaunchCommand;
import me.tlopster.cwtroll.commands.lib.LevitateCommand;
import me.tlopster.cwtroll.commands.lib.PushCommand;
import me.tlopster.cwtroll.listeners.PlayerListener;
import me.tlopster.cwtroll.schedulers.LevitateScheduler;
import me.tlopster.cwtroll.tools.Config;
import me.tlopster.cwtroll.tools.Message;
import me.tlopster.cwtroll.trolls.AnvilTroll;
import me.tlopster.cwtroll.trolls.BoomTroll;
import me.tlopster.cwtroll.trolls.KartoxaTroll;
import me.tlopster.cwtroll.trolls.LaunchTroll;
import me.tlopster.cwtroll.trolls.LevitateTroll;
import me.tlopster.cwtroll.trolls.PushTroll;
import me.tlopster.cwtroll.utils.LoggerUtil;
import me.tlopster.cwtroll.utils.Utilites;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class CWPluginMain
extends JavaPlugin {
    private static CWPluginMain instance;

    private HashMap<Player, Integer> levitatePlayers = new HashMap();

    private Utilites utilites;

    private RegionCore regionCore;

    private Config conf;
    private LoggerUtil log;

    // TROLLS LOADER //
    public AnvilTroll AnvilTroll = new AnvilTroll();
    public LaunchTroll LaunchTroll = new LaunchTroll(this);
    public PushTroll PushTroll = new PushTroll(this);
    public KartoxaTroll KartoxaTroll = new KartoxaTroll();
    public LevitateTroll LevitateTroll = new LevitateTroll(this);
    public BoomTroll BoomTroll = new BoomTroll(this);
    // TROLLS LOADER //

    private boolean PlaceholderAPIEnabled = false;

    private ArrayList<Player> noFallDamagePlayers = new ArrayList();
    private ArrayList<Player> noTNTDamagePlayers = new ArrayList();

    public void onEnable() {

        instance = this;

        this.saveDefaultConfig();

        this.conf = new Config(this, this.getConfig()); //Загрузка службы конфига
        this.log = new LoggerUtil(this, this.conf); //Загрузка службы логгирования

        this.utilites = new Utilites();
        this.regionCore = new RegionCore(); //Загрузка службы регионов

        this.PlaceholderAPIEnabled = false;

        // EVENTS LOADER //
        Bukkit.getPluginManager().registerEvents(new PlayerListener(this), (Plugin)this);
        // EVENTS LOADER //

        // COMMANDS LOADER //
        new CWTComand(this);
        new KovCommand(this);
        new LaunchCommand(this);
        new PushCommand(this);
        new KartoxaCommand(this);
        new LevitateCommand(this);
        new BoomCommand(this);
        // COMMANDS LOADER //

        // SCHEDULERS LOADER //
        new LevitateScheduler();
        // SCHEDULERS LOADER //

        Message.load(this.getConfig(), this.PlaceholderAPIEnabled);
    }

    public void onDisable() {
        // to do...
    }

    public static CWPluginMain getInstance() {
        return instance;
    }

    public Config getConf() {
        return this.conf;
    }

    public LoggerUtil getLog() {
        return this.log;
    }

    public RegionCore getRegionCore() {
        return this.regionCore;
    }

    public Utilites getUtilites() {
        return this.utilites;
    }

    public HashMap<Player, Integer> getLevitatePlayers() {
        return this.levitatePlayers;
    }

    public boolean isPlaceholderAPIEnabled() {
        return this.PlaceholderAPIEnabled;
    }

    public ArrayList<Player> getNoFallDamagePlayers() {
        return this.noFallDamagePlayers;
    }

    public ArrayList<Player> getNoTNTDamagePlayers() {
        return this.noTNTDamagePlayers;
    }
}

