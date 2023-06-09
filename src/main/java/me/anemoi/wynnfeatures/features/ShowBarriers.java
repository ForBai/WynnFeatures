package me.anemoi.wynnfeatures.features;

import me.anemoi.wynnfeatures.WynnFeatures;
import me.anemoi.wynnfeatures.utils.BlockUtils;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static me.anemoi.wynnfeatures.WynnFeatures.mc;

public class ShowBarriers {
    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END)
            renderBlocks();
        else if (event.phase == TickEvent.Phase.START)
            renderBlocks();
    }

    private void renderBlocks() {
        if (mc.world == null || mc.player == null || !WynnFeatures.config.showBarriers)
            return;
        BlockUtils.getBlocksInSphere(mc.player.getPosition(), WynnFeatures.config.barrierRange).forEach(pos -> {
            if (mc.world.getBlockState(pos).getBlock() == Blocks.BARRIER) {
                mc.world.setBlockState(pos, BlockUtils.getStateFromString("minecraft:" + BlockUtils.BLOCKS[WynnFeatures.config.replaceBlock]));
            }
        });
    }

}
