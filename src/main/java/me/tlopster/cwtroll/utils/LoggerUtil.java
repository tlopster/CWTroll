package me.tlopster.cwtroll.utils;

import me.tlopster.cwtroll.core.CWPluginMain;
import me.tlopster.cwtroll.tools.Config;
import org.bukkit.ChatColor;

import java.io.*;
import java.time.ZonedDateTime;

/**
 * Система логирования.
 * Сохраняет логи в файлы, если опция "saveLogs" включена.
 * Поддерживает 4 вида логов:
 * sendLogger - отправить обычный лог
 * sendError - отправить ошибку
 * sendWarning - отправить предупреждение
 * sendDebug - отправить дебаг-лог. Отображается, если
 * включена опция "debug".
 */
public class LoggerUtil {
    private final CWPluginMain pl;

    private final Config config;

    private String prefix;

    public LoggerUtil(CWPluginMain pl, Config config) {
        this.pl = pl;
        this.config = config;
        this.prefix = ChatColor.RED + "[" + ChatColor.GRAY + pl.getDescription().getName() + ChatColor.RED + "] " + ChatColor.GRAY;
    }


    public void sendDebug(String path) {
        if(config.getDebug()) {
            String debugPrefix = ChatColor.YELLOW + "[DEBUG] ";
            System.out.println(prefix + debugPrefix + path);
        }
        Write("[DEBUG] " + path);
    }
    public void sendLogger(String path) {
        Write("[LOG] " + path);
        System.out.println(prefix + path);
    }

    public void sendWarning(String path) {
        Write("[WARNING] " + path);
        String warningPrefix = ChatColor.YELLOW + "[CWRgProtector] ";
        System.out.println(warningPrefix + path);
    }

    public void sendError(String path) {
        Write("[ERROR] " + path);
        String errorPrefix = ChatColor.DARK_RED + "[ERROR] [CWRgProtector] ";
        System.out.println(errorPrefix + path);

    }

    private void Write(String path) {
        if(config.isSaveLogs()) {
            path = path + "\n";
            File list = new File(pl.getDataFolder() + File.separator + "logs");
            if(!list.exists()) {
                try {
                    list.mkdirs();
                    list.createNewFile();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            File log = new File(pl.getDataFolder() + File.separator + "logs" + File.separator + "log_" + ZonedDateTime.now().getHour() +
                    "_hours_" + ZonedDateTime.now().getDayOfMonth() + "_day_" + ZonedDateTime.now().getMonth() + "_" + ZonedDateTime.now().getYear()  + ".txt");
            try {
                log.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            try {
                FileWriter writer = new FileWriter(log, true);
                BufferedWriter bufferWriter = new BufferedWriter(writer);
                bufferWriter.write(path);
                bufferWriter.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}