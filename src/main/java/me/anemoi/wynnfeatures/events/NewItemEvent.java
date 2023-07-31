package me.anemoi.wynnfeatures.events;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.Event;

public class NewItemEvent extends Event {
    public ItemStack stack;

    public NewItemEvent(ItemStack stack) {
        this.stack = stack;
    }
}
