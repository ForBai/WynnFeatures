package me.anemoi.wynnfeatures.features;

import me.anemoi.wynnfeatures.WynnFeatures;
import me.anemoi.wynnfeatures.utils.BlockUtils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Arrays;

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
        if (mc.world == null || mc.player == null /*|| !Utils.inWynncraft()*/ || !WynnFeatures.config.extraBlocksToggled)
            return;
        //Arrays.asList(Blocks)
    }



}
