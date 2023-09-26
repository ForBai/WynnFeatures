package me.anemoi.wynnfeatures.features.tmsniper;

import me.anemoi.wynnfeatures.events.NewItemEvent;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TMLog {
    public static List<ItemStack> oldItems = new ArrayList<>();
    public static List<ItemStack> newItems = new ArrayList<>();

    @SubscribeEvent
    public void onTick(GuiScreenEvent.DrawScreenEvent event) {
        if (!(event.getGui() instanceof GuiChest)) return;
        GuiChest chest = (GuiChest) event.getGui();
        IInventory inventory = ((ContainerChest) chest.inventorySlots).getLowerChestInventory();
        if (inventory == null) return;
        if (inventory.getDisplayName().getFormattedText().toLowerCase(Locale.ROOT).contains("trade market")) {
            updateItems(inventory);
        }
    }

    @SubscribeEvent
    public void onInitGui(GuiScreenEvent.InitGuiEvent event) {
        if (!(event.getGui() instanceof GuiChest)) return;
        GuiChest chest = (GuiChest) event.getGui();
        IInventory inventory = ((ContainerChest) chest.inventorySlots).getLowerChestInventory();
        if (inventory == null) return;
        if (inventory.getDisplayName().getFormattedText().toLowerCase(Locale.ROOT).contains("trade market")) {
            System.out.println("Trade Market Opened");
            //TODO: External gui
            System.out.println("Opened Custom Log Gui");
        }
    }

    @SubscribeEvent
    public void onNewItemEvent(NewItemEvent event) {
        
    }

    public static void updateItems(IInventory inventory) {
        oldItems = newItems;
        newItems.clear();
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            newItems.add(inventory.getStackInSlot(i));
            if (!oldItems.contains(inventory.getStackInSlot(i))) {
                MinecraftForge.EVENT_BUS.post(new NewItemEvent(inventory.getStackInSlot(i)));
            }
        }
    }

}
