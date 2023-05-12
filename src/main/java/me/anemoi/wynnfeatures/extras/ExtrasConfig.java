package me.anemoi.wynnfeatures.extras;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.anemoi.wynnfeatures.WynnFeatures;
import me.anemoi.wynnfeatures.extras.api.ExtraStuff;
import me.anemoi.wynnfeatures.extras.api.ExtraWaypoint;
import me.anemoi.wynnfeatures.utils.Utils;
import net.minecraft.util.math.BlockPos;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ExtrasConfig {

    public static File extrasConfigFile;

    public static void init(File configDirectory, String name) {
        extrasConfigFile = new File(configDirectory + "/" + WynnFeatures.MODID, name + ".json");
        try {
            if (!extrasConfigFile.exists())
                extrasConfigFile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadConfigs();
        saveConfigs();
    }

    public static void saveConfigs() {
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        JsonObject jsonObject = new JsonObject();
        JsonObject blocks = new JsonObject();
        JsonObject waypoints = new JsonObject();
        JsonObject stuff = new JsonObject();

        Extras.blocks.forEach((k, v) -> {
            JsonArray blockPos = new JsonArray();
            v.forEach(pos -> {
                blockPos.add(pos.getX() + ", " + pos.getY() + ", " + pos.getZ());
            });
            blocks.add(k, blockPos);
        });
        jsonObject.add("blocks", blocks);

        Extras.waypoints.forEach(extraWaypoint -> {
            JsonObject waypoint = new JsonObject();
            waypoint.addProperty("name", extraWaypoint.getName());
            waypoint.addProperty("visibleDistance", extraWaypoint.getVisibleDistance());
            waypoint.addProperty("color", extraWaypoint.getColor().getRed() + ", " + extraWaypoint.getColor().getGreen() + ", " + extraWaypoint.getColor().getBlue());
            waypoints.add(extraWaypoint.getPos().getX() + ", " + extraWaypoint.getPos().getY() + ", " + extraWaypoint.getPos().getZ(), waypoint);
        });
        jsonObject.add("waypoints", waypoints);

        Extras.stuff.forEach(extraStuff -> {
            JsonObject stuffObject = new JsonObject();
            stuffObject.addProperty("cmd", extraStuff.getCmd());
            stuffObject.addProperty("visibleDistance", extraStuff.getVisibleDistance());
            stuffObject.addProperty("color", extraStuff.getColor().getRed() + ", " + extraStuff.getColor().getGreen() + ", " + extraStuff.getColor().getBlue());
            stuffObject.addProperty("range", extraStuff.getRange());
            stuffObject.addProperty("onShift", extraStuff.isOnShift());
            stuff.add(extraStuff.getPos().getX() + ", " + extraStuff.getPos().getY() + ", " + extraStuff.getPos().getZ(), stuffObject);
        });
        jsonObject.add("stuff", stuff);

        String json = gson.toJson(jsonObject);
        Utils.writeToFile(extrasConfigFile, json);
    }

    public static void loadConfigs() {
        if (!extrasConfigFile.exists()) return;
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(Utils.readFile(extrasConfigFile), JsonObject.class);
        JsonObject blocks = jsonObject.getAsJsonObject("blocks");
        JsonObject waypoints = jsonObject.getAsJsonObject("waypoints");
        JsonObject stuff = jsonObject.getAsJsonObject("stuff");

        blocks.entrySet().forEach(entry -> {
            String blockName = entry.getKey();
            JsonArray blockPos = entry.getValue().getAsJsonArray();
            List<BlockPos> blockPosList = new ArrayList<>();
            blockPos.forEach(pos -> {
                String[] posArray = pos.getAsString().split(", ");
                blockPosList.add(new BlockPos(Integer.parseInt(posArray[0]), Integer.parseInt(posArray[1]), Integer.parseInt(posArray[2])));
            });
            Extras.blocks.put(blockName, blockPosList);
        });

        waypoints.entrySet().forEach(entry -> {
            String[] posArray = entry.getKey().split(", ");
            JsonObject waypoint = entry.getValue().getAsJsonObject();
            String name = waypoint.get("name").getAsString();
            int visibleDistance = waypoint.get("visibleDistance").getAsInt();
            String[] colorArray = waypoint.get("color").getAsString().split(", ");
            Color color = new Color(Integer.parseInt(colorArray[0]), Integer.parseInt(colorArray[1]), Integer.parseInt(colorArray[2]));
            Extras.addWaypoint(new ExtraWaypoint(name, Integer.parseInt(posArray[0]), Integer.parseInt(posArray[1]), Integer.parseInt(posArray[2])).withColor(color).withVisibleDistance(visibleDistance));
        });

        stuff.entrySet().forEach(entry -> {
            String[] posArray = entry.getKey().split(", ");
            JsonObject stuffObject = entry.getValue().getAsJsonObject();
            String cmd = stuffObject.get("cmd").getAsString();
            int visibleDistance = stuffObject.get("visibleDistance").getAsInt();
            String[] colorArray = stuffObject.get("color").getAsString().split(", ");
            Color color = new Color(Integer.parseInt(colorArray[0]), Integer.parseInt(colorArray[1]), Integer.parseInt(colorArray[2]));
            float range = stuffObject.get("range").getAsFloat();
            boolean onShift = stuffObject.get("onShift").getAsBoolean();
            Extras.addStuff(new ExtraStuff(cmd, Double.parseDouble(posArray[0]), Double.parseDouble(posArray[1]), Double.parseDouble(posArray[2])).withColor(color).withRange(range).withOnShift(onShift).withVisibleDistance(visibleDistance));
        });
    }

}
