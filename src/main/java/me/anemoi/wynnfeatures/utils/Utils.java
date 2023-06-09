package me.anemoi.wynnfeatures.utils;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;

import static me.anemoi.wynnfeatures.WynnFeatures.mc;

public class Utils {

    public static boolean inWynncraft() {
        return mc.getCurrentServerData() != null && mc.getCurrentServerData().serverIP.contains("wynncraft.com");
    }

    public static void sendMessage(String string) {
        if (mc.ingameGUI == null || mc.player == null) return;
        mc.ingameGUI.getChatGUI().printChatMessage(new TextComponentString(ChatFormatting.AQUA + "[WF] " + ChatFormatting.RESET + string));
    }

    public static void sendMessage(ITextComponent msg) {
        if (mc.ingameGUI == null || mc.player == null) return;
        ITextComponent prefix = new TextComponentString(ChatFormatting.AQUA + "[WF] " + ChatFormatting.RESET);
        mc.ingameGUI.addChatMessage(ChatType.CHAT,
                new TextComponentString("")
                        .appendSibling(prefix)
                        .appendSibling(msg)
        );

    }

    public static String capitalizeString(String string) {
        string = cleanColour(string);
        String[] words = string.split("_");

        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
        }

        return String.join(" ", words);
    }

    public static String cleanColour(String in) {
        return in.replaceAll("(?i)\\u00A7.", "");
    }

    public static String cleanColourNotModifiers(String in) {
        return in.replaceAll("(?i)\\u00A7[0-9a-f]", "");
    }

    public static void writeToFile(File file, String text) {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(text);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readFile(File file) {
        try {
            InputStream is = file.toURI().toURL().openStream();
            int ptr = 0;
            StringBuilder builder = new StringBuilder();
            while ((ptr = is.read()) != -1) {
                builder.append((char) ptr);
            }
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getReadableNumber(double number, int decimals) {
        if (number >= 1000000000) {
            return String.format("%sB", Math.round(number / (1000000000 / Math.pow(10, decimals))) / Math.pow(10, decimals));
        } else if (number >= 1000000) {
            return String.format("%sM", Math.round(number / (1000000 / Math.pow(10, decimals))) / Math.pow(10, decimals));
        } else if (number >= 1000) {
            return String.format("%sK", Math.round(number / (1000 / Math.pow(10, decimals))) / Math.pow(10, decimals));
        }

        return String.valueOf((int) number);
    }

    public static String getCurrentWorld() {
        try {
            String name = Objects.requireNonNull(Objects.requireNonNull(Objects.requireNonNull(mc.getConnection()).getPlayerInfo(UUID.fromString("16ff7452-714f-3752-b3cd-c3cb2068f6af"))).getDisplayName()).getFormattedText();
            return name.substring(name.indexOf("[") + 1, name.indexOf("]"));
        } catch (NullPointerException | IndexOutOfBoundsException ignored) {
            return null;
        }
    }

    public static String getReadableTime(int minutes) {
        return (minutes >= 1440.0 ? (int) Math.floor((minutes / 1440.0)) + "d " : "") + (int) (Math.floor((minutes % 1440) / 60.0)) + "h " + minutes % 60 + "m";
    }
}
