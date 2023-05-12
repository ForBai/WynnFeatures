package me.anemoi.wynnfeatures.extras.commands;

import me.anemoi.wynnfeatures.extras.Extras;
import me.anemoi.wynnfeatures.extras.ExtrasConfig;
import me.anemoi.wynnfeatures.extras.api.ExtraWaypoint;
import me.anemoi.wynnfeatures.utils.Utils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AddWaypointCommand extends CommandBase {
    @Override
    public String getName() {
        return "addwaypoint";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "Usage: /addwaypoint <waypoint> [args]\n" +
                "Available args: " +
                "-visibleDistance=<distance> or -vDistance=<distance> (default: 32)\n" +
                "-color=<color> (default: #ff0000ff) u can also enter it like this: -color=(255 0 0 255)\n";
    }

    @Override
    public List<String> getAliases() {
        return Collections.singletonList("addwp");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        List<String> argsList = Arrays.asList(args);
        if (argsList.isEmpty()) {
            Utils.sendMessage("Please specify a cmd and possible arguments");
            Utils.sendMessage(getUsage(sender));
            return;
        }
        BlockPos pos = new BlockPos(sender.getPositionVector());
        StringBuilder name = new StringBuilder();
        float visibleDistance = 32;
        Color color = new Color(255, 0, 0, 255);
        for (String arg : argsList) {
            if (arg.startsWith("-visibleDistance=") || arg.startsWith("-vDistance=")) {
                visibleDistance = Float.parseFloat(arg.split("=")[1]);
            } else if (arg.startsWith("-color=")) {
                // example would be -color=(255 0 0 255) but if it is -color=#ff0000ff then it will be converted to the first example
                if (arg.split("=")[1].startsWith("#")) {
                    String hex = arg.split("=")[1].replace("#", "");
                    color = new Color(
                            Integer.valueOf(hex.substring(0, 2), 16),
                            Integer.valueOf(hex.substring(2, 4), 16),
                            Integer.valueOf(hex.substring(4, 6), 16),
                            Integer.valueOf(hex.substring(6, 8), 16)
                    );
                } else if (arg.split("=")[1].startsWith("0x")) {
                    String hex = arg.split("=")[1].replace("0x", "");
                    color = new Color(
                            Integer.valueOf(hex.substring(0, 2), 16),
                            Integer.valueOf(hex.substring(2, 4), 16),
                            Integer.valueOf(hex.substring(4, 6), 16),
                            Integer.valueOf(hex.substring(6, 8), 16)
                    );
                } else if (arg.split("=")[1].startsWith("(") && arg.split("=")[1].endsWith(")")) {
                    String[] colorArgs = arg.split("=")[1].replace("(", "").replace(")", "").split("x");
                    color = new Color(Integer.parseInt(colorArgs[0]), Integer.parseInt(colorArgs[1]), Integer.parseInt(colorArgs[2]), Integer.parseInt(colorArgs[3]));
                } else {
                    Utils.sendMessage("Invalid color format");
                    Utils.sendMessage("Valid formats are: #ff0000ff, 0xff0000ff, (255 0 0 255)");
                    return;
                }
            } else {
                name.append(" ").append(arg);
            }
        }
        if (name.charAt(0) == ' ') {
            name.deleteCharAt(0);
        }
        Extras.addWaypoint(new ExtraWaypoint(name.toString(), pos).withColor(color).withVisibleDistance(visibleDistance));
        ExtrasConfig.saveConfigs();
    }
}
