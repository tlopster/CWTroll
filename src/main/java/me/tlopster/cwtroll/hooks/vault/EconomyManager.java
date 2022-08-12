package me.tlopster.cwtroll.hooks.vault;

import me.tlopster.cwtroll.core.CWPluginMain;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * Класс для работы с экономикой Vault
 *
 * Методы:
 * takeMoney - списать деньги с баланса игрока (возвращает true, если операция проведена без ошибок, иначе false).
 * getMoney - получить баланс игрока. (возвращает -1, если не удалось выполнить операцию)
 * setMoney - установить баланс игрока (возвращает true, если операция проведена без ошибок, иначе false).
 *
 * Получайте этот класс через главный, методом "getEco".
 */
public class EconomyManager {
    private Economy e;
    private CWPluginMain plugin;
    private boolean enabled = true;

    public EconomyManager(CWPluginMain plugin) {
        this.plugin = plugin;
        this.init();
    }

    public void init() {
        RegisteredServiceProvider reg = Bukkit.getServicesManager().getRegistration(Economy.class);
        if (reg != null)
            this.e = (Economy)reg.getProvider();
        if (this.e == null) {
            this.plugin.getLog().sendWarning("Vault is not installed! Economy is Disabled.");
            this.enabled = false;
        }
    }

    public boolean takeMoney(Player p, double a) {
        if (this.enabled) {
            if (this.e.getBalance((OfflinePlayer)p) < a)
                return false;
            return this.e.withdrawPlayer((OfflinePlayer)p, a).transactionSuccess();
        }
        return false;
    }

    public double getMoney(Player p, double a) {
        if (this.enabled)
            return this.e.getBalance((OfflinePlayer)p);
        return -1.0;
    }

    public boolean setMoney(Player p, double a) {
        boolean b = true;
        if (this.enabled) {
            if (!this.e.withdrawPlayer((OfflinePlayer)p, this.e.getBalance((OfflinePlayer)p)).transactionSuccess())
                b = false;
            if (!this.e.depositPlayer((OfflinePlayer)p, a).transactionSuccess())
                b = false;
        }
        return b;
    }
}

