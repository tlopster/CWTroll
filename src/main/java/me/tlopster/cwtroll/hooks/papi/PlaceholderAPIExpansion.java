package me.tlopster.cwtroll.hooks.papi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

import org.bukkit.entity.Player;

public class PlaceholderAPIExpansion extends PlaceholderExpansion {
    public PlaceholderAPIExpansion() {}

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getAuthor() {
        return "tlopster";
    }

    /**
     * Строка, которая будет находиться перед плейсхолдером.
     * Напр. плейсхолдер: playername, идентификатор: test.
     * Плесхолдер будет такой: %test_playername%
     */
    @Override
    public String getIdentifier() {
        return ("cwtroll");
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    /**
     * Регистрация плейсхолдеров. p - игрок, которому заменяться плейсхолдер.
     * identifier - название плейсхолдера (без идентификатора (getIdentifier))
     */
    @Override
    public String onPlaceholderRequest(Player p, String identifier) {
        // %test_playername% (возвращает имя игрока)
        if (identifier.equals("playername")) {
            return p.getName();
        }
        // %test_placeholder2% (возвращает строку "placeholder2 works")
        if (identifier.equals("placeholder2")) {
            return "placeholder2 works";
        }

        return null;
    }
}