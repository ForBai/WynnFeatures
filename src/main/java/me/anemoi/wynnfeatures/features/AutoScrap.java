package me.anemoi.wynnfeatures.features;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.anemoi.wynnfeatures.WynnFeatures;
import me.anemoi.wynnfeatures.mixins.GuiScreenAccessor;
import me.anemoi.wynnfeatures.utils.Utils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static me.anemoi.wynnfeatures.WynnFeatures.mc;

public class AutoScrap {
    private boolean scraping = false;

    @SubscribeEvent
    public void onInitGui(GuiScreenEvent.InitGuiEvent event) {
        if (!WynnFeatures.config.autoScrapButton) return;
        if (!(event.getGui() instanceof GuiChest)) return;
        GuiChest chest = (GuiChest) event.getGui();
        IInventory inventory = ((ContainerChest) chest.inventorySlots).getLowerChestInventory();
        if (inventory == null) return;
        if (inventory.getDisplayName().getFormattedText().contains("like to scrap")) {
            ((GuiScreenAccessor) event.getGui()).getButtonList().add(new GuiButton(0, event.getGui().width / 2, event.getGui().width / 7, 100, 20, "Scrap"));
        }
    }

    @SubscribeEvent
    public void onInitGui(GuiScreenEvent.ActionPerformedEvent event) {
        if (!WynnFeatures.config.autoScrapButton) return;
        if (!(event.getGui() instanceof GuiChest)) return;
        GuiChest chest = (GuiChest) event.getGui();
        IInventory inventory = ((ContainerChest) chest.inventorySlots).getLowerChestInventory();
        if (inventory == null) return;
        if (inventory.getDisplayName().getFormattedText().contains("like to scrap")) {
            GuiButton clickedButton = event.getButton();
            if (clickedButton.id == 0) {
                scraping = true;
                Utils.sendMessage("Auto Scrap: " + (scraping ? "Enabled" : "Disabled"));
            }
        }
    }

    @SubscribeEvent
    public void onMouseClick(GuiScreenEvent.MouseInputEvent.Pre event) {
        if (!WynnFeatures.config.autoScrapButton) return;
        if (!(event.getGui() instanceof GuiChest)) return;
        GuiChest chest = (GuiChest) event.getGui();
        IInventory inventory = ((ContainerChest) chest.inventorySlots).getLowerChestInventory();
        if (inventory == null) return;
        if (inventory.getDisplayName().getFormattedText().contains("like to scrap")) {
            if (scraping) {
                event.setCanceled(true);
            }
        }
    }

    int delayPassed = 0;

    @SubscribeEvent
    public void onTick(GuiScreenEvent.DrawScreenEvent event) {
        if (!WynnFeatures.config.autoScrapButton) return;
        if (!(event.getGui() instanceof GuiChest)) return;
        GuiChest chest = (GuiChest) event.getGui();
        IInventory inventory = ((ContainerChest) chest.inventorySlots).getLowerChestInventory();
        if (inventory == null) return;
        if (inventory.getDisplayName().getFormattedText().contains("like to scrap")) {
            delayPassed++;
            if (scraping && delayPassed >= WynnFeatures.config.scrapClickDelay) {
                //find an item that is either common or normal and click it
                chest.inventorySlots.inventorySlots.forEach(slot -> {
                    String rarity = ChatFormatting.stripFormatting(slot.getStack().getTooltip(chest.mc.player, ITooltipFlag.TooltipFlags.NORMAL).get(slot.getStack().getTooltip(chest.mc.player, ITooltipFlag.TooltipFlags.NORMAL).size() - 1)).split(" ")[0];
                    if ((rarity.contains("Normal") && WynnFeatures.config.normalScrap)) {
                        mc.playerController.windowClick(chest.inventorySlots.windowId, slot.slotNumber, 2, ClickType.SWAP, mc.player);
                        delayPassed = 0;
                    }
                });
            }

        }
    }
}



