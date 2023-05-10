package me.anemoi.wynnfeatures.extras;

import me.anemoi.wynnfeatures.WynnFeatures;
import me.anemoi.wynnfeatures.events.MillisecondEvent;
import me.anemoi.wynnfeatures.events.SecondEvent;
import me.anemoi.wynnfeatures.extras.api.ExtraBlock;
import me.anemoi.wynnfeatures.extras.api.ExtraStuff;
import me.anemoi.wynnfeatures.extras.api.ExtraWaypoint;
import me.anemoi.wynnfeatures.utils.BlockUtils;
import me.anemoi.wynnfeatures.utils.RenderUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static me.anemoi.wynnfeatures.WynnFeatures.mc;

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

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (WynnFeatures.config.extraBlocksRefreshType == 1) renderBlocks();
    }

    @SubscribeEvent
    public void onMillisecond(MillisecondEvent event) {
        if (WynnFeatures.config.extraBlocksRefreshType == 0) renderBlocks();
    }

    @SubscribeEvent
    public void onTickPlus(TickEvent.PlayerTickEvent event) {
        if (WynnFeatures.config.extraBlocksRefreshType == 2) {
            if (event.phase == TickEvent.Phase.END)
                renderBlocks();
            else if (event.phase == TickEvent.Phase.START)
                renderBlocks();
        }
    }

    @SubscribeEvent
    public void onSecond(SecondEvent event) {
        if (WynnFeatures.config.extraBlocksRefreshType == 3) renderBlocks();
    }

    @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) {
        if (mc.world == null || mc.player == null /*|| !Utils.inWynncraft()*/)
            return;

        if (WynnFeatures.config.extraStuffToggled) {
            stuff.forEach(point -> {
                if (BlockUtils.isPosInCube(point.getPos(), new Vec3d(mc.player.posX, mc.player.posY, mc.player.posZ), point.getVisibleDistance())) {
                    if (WynnFeatures.config.extraStuffShowCmd) {
                        RenderUtils.renderWaypointTextEasy(point.getCmd(), point.getPos(), event.getPartialTicks());
                    }
                    if (WynnFeatures.config.extraStuffShowCircle) {
                        RenderUtils.renderCircleLine(point.getPos().addVector(0, 0.001, 0), point.getRange(), point.getColor(), event.getPartialTicks(), 3);
                    } else {
                        RenderUtils.drawBlockOutline(new BlockPos(point.getPos().x, point.getPos().y, point.getPos().z), point.getColor(), 3, true, 0, event.getPartialTicks());
                    }
                    //everytime the player enters the range of the point, execute the command but only once
                    if (BlockUtils.isPosInCylinder(new Vec3d(mc.player.posX, mc.player.posY, mc.player.posZ), point.getPos(), point.getRange(), 1)){
                        if (!point.isExectued()) {
                            executeExtraStuffCmd(point);
                            point.setExectued(true);
                        }
                    }

                }
            });
        }
        if (WynnFeatures.config.extraWaypointsToggled) {
            waypoints.forEach(extraWaypoint -> {
                if (BlockUtils.isPosInCube(extraWaypoint.getPos(), new BlockPos(mc.player.posX, mc.player.posY, mc.player.posZ), extraWaypoint.getVisibleDistance())) {
                    if (WynnFeatures.config.extraWaypointsShowName) {
                        RenderUtils.renderWaypointTextEasy(extraWaypoint.getName(), new Vec3d(extraWaypoint.getPos()), event.getPartialTicks());
                    }
                    RenderUtils.drawBlockOutline(extraWaypoint.getPos(), extraWaypoint.getColor(), 3, true, 0, event.getPartialTicks());
                }
            });
        }
    }

    private void executeExtraStuffCmd(ExtraStuff extraStuff) {
        if (extraStuff.getCmd().startsWith("/")) {
            mc.player.sendChatMessage(extraStuff.getCmd());
        } else {
            //send as client side command

        }
    }

    private void renderBlocks() {
        if (mc.world == null || mc.player == null /*|| !Utils.inWynncraft()*/ || !WynnFeatures.config.extraBlocksToggled)
            return;
        blocks.forEach((k, v) -> {
            v.forEach(pos -> {
                IBlockState state = BlockUtils.getStateFromString(k);
                if (state == null) return;
                if (mc.world.getBlockState(pos).getBlock() != state.getBlock()) {
                    if (WynnFeatures.config.extraBlocksRangeMode == 0 && BlockUtils.isPosInSphere(pos, new BlockPos(mc.player.posX, mc.player.posY, mc.player.posZ), WynnFeatures.config.extraBlocksRange)) {
                        mc.world.setBlockState(pos, state);
                    } else if (WynnFeatures.config.extraBlocksRangeMode == 1 && BlockUtils.isPosInCube(pos, new BlockPos(mc.player.posX, mc.player.posY, mc.player.posZ), WynnFeatures.config.extraBlocksRange)) {
                        mc.world.setBlockState(pos, state);
                    }

                }
            });
        });
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

    public static void clearAll() {
        blocks.clear();
        waypoints.clear();
        stuff.clear();
    }


}
