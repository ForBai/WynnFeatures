package me.anemoi.wynnfeatures.events;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.common.eventhandler.Event;

public class DrawSlotEvent extends Event {
    private final GuiContainer gui;
    private final Slot slot;

    public DrawSlotEvent(GuiContainer gui, Slot slot) {
        this.gui = gui;
        this.slot = slot;
    }

    public GuiContainer getGui() {
        return gui;
    }

    public Slot getSlot() {
        return slot;
    }

    public static class Post extends DrawSlotEvent {
        public Post(GuiContainer gui, Slot slot) {
            super(gui, slot);
        }
    }

    public static class Pre extends DrawSlotEvent {
        public Pre(GuiContainer gui, Slot slot) {
            super(gui, slot);
        }
    }

}
