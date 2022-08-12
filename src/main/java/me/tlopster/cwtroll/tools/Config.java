package me.tlopster.cwtroll.tools;

import me.tlopster.cwtroll.core.CWPluginMain;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * Класс для работы с конфигурацией. Чтобы получить значение (напр. boolean "debug", расположенный
 * по адресу "settings.debug"), сначала мы создаем приватное поле ("private boolean debug").
 * В методе "reload" мы присваиваем полю значение, получаемое из переменной config, имеющей
 * тип FileConfiguration. ("debug = config.getBoolean("settings.debug");"). Далее создаем
 * метод получения переменной ("getDebug()"), возвращающий приватное поле. Теперь через этот
 * метод мы можем получать значение "debug".
 *
 * Получайте этот класс через главный, методом "getConf".
 */
public class Config {
    private final CWPluginMain pl;
    private final FileConfiguration config;
    private boolean debug;
    private boolean saveLogs;

    public Config(CWPluginMain pl, FileConfiguration config) {
        this.pl = pl;
        this.config = config;
        this.reload();
    }

    public void reload() {
        this.pl.saveDefaultConfig();
        this.pl.reloadConfig();
        this.debug = this.config.getBoolean("settings.debug");
        this.saveLogs = this.config.getBoolean("settings.saveLogs");
    }

    public boolean getDebug() {
        return this.debug;
    }

    public boolean isSaveLogs() {
        return this.saveLogs;
    }

    public boolean trollIsBlockInRegion(String troll) {
        return this.config.getBoolean("trolls." + troll + ".blockInRegion");
    }

    private String color(String s) {
        return ChatColor.translateAlternateColorCodes((char)'&', (String)s);
    }
}

