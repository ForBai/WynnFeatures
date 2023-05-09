package me.anemoi.wynnfeatures.extras;

import me.anemoi.wynnfeatures.extras.api.ExtraBlock;
import me.anemoi.wynnfeatures.extras.api.ExtraStuff;
import me.anemoi.wynnfeatures.extras.api.ExtraWaypoint;
import me.anemoi.wynnfeatures.utils.BlockUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Extras {
    //public static List<ExtraBlock> blocks = new ArrayList<>();
    public static HashMap<String, List<BlockPos>> blocks = new HashMap<>();
    public static List<ExtraWaypoint> waypoints = new ArrayList<>();
    public static List<ExtraStuff> stuff = new ArrayList<>();

    public static void addBlock(ExtraBlock block) {
        if (blocks.containsKey(BlockUtils.getStringFromState(block.getState()))) {
            blocks.get(BlockUtils.getStringFromState(block.getState())).add(block.getPos());
        } else {
            List<BlockPos> list = new ArrayList<>();
            list.add(block.getPos());
            blocks.put(BlockUtils.getStringFromState(block.getState()), list);
        }
    }

    public static void removeBlock(ExtraBlock block) {
        if (blocks.containsKey(BlockUtils.getStringFromState(block.getState()))) {
            blocks.get(BlockUtils.getStringFromState(block.getState())).remove(block.getPos());
        }
    }

    public static void addWaypoint(ExtraWaypoint waypoint) {
        waypoints.add(waypoint);
    }

    public static void removeWaypoint(ExtraWaypoint waypoint) {
        waypoints.remove(waypoint);
    }

    public static void addStuff(ExtraStuff stuf) {
        stuff.add(stuf);
    }

    public static void removeStuff(ExtraStuff stuf) {
        stuff.remove(stuf);
    }

    public static void clearAll(){
        blocks.clear();
        waypoints.clear();
        stuff.clear();
    }



}
