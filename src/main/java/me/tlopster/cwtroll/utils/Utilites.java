package me.tlopster.cwtroll.utils;

import java.time.LocalDateTime;

public class Utilites {
    public String getStrTime() {
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        return String.format("[%02d:%02d:%02d]", hour, minute, second);
    }

    public boolean isNumeric(String string) {
        if (string == null || string.equals(""))
            return false;
        try {
            int intValue = Integer.parseInt(string);
            return true;
        }
        catch (NumberFormatException numberFormatException) {
            return false;
        }
    }
}

