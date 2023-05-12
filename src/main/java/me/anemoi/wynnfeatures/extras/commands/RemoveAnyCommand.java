package me.anemoi.wynnfeatures.extras.commands;

import me.anemoi.wynnfeatures.extras.Extras;
import me.anemoi.wynnfeatures.extras.ExtrasConfig;
import me.anemoi.wynnfeatures.utils.Utils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

import java.util.Arrays;
import java.util.List;

public class RemoveAnyCommand extends CommandBase {
    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return null;
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("removewaypoint", "removewp", "delwp", "deletewaypoint", "deletewp", "delstuff", "deletestuff", "del");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        int amount = 1;
        if (args.length == 1) {
            amount = Integer.parseInt(args[0]);
        }
        //find the nearest wapoint or stuff and remove it
        for (int i = 0; i < amount; i++) {
            //remove the waypoint or stuff
            double wpDistance = Extras.getNearestWaypoint2(32).getRight();
            double stuffDistance = Extras.getNearestStuff2(32).getRight();
            if (wpDistance == 32 && stuffDistance == 32) {
                Utils.sendMessage("There is nothing to remove in a 32 block radius!");
                return;
            } else if (wpDistance < stuffDistance) {
                Extras.removeWaypoint(Extras.getNearestWaypoint(32));
                ExtrasConfig.saveConfigs();
            } else {
                Extras.removeStuff(Extras.getNearestStuff(32));
                ExtrasConfig.saveConfigs();
            }
        }
    }
}
