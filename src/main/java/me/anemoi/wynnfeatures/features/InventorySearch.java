package me.anemoi.wynnfeatures.features;

import me.anemoi.wynnfeatures.WynnFeatures;
import me.anemoi.wynnfeatures.events.DrawSlotEvent;
import me.anemoi.wynnfeatures.utils.RenderUtils;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.util.Objects;

import static me.anemoi.wynnfeatures.WynnFeatures.mc;

public class InventorySearch {
    private boolean recordingText = false;
    private String text = "";

    @SubscribeEvent
    public void onKey(GuiScreenEvent.KeyboardInputEvent.Pre event) {
        if (mc.currentScreen instanceof GuiContainer || !WynnFeatures.config.containerSearch) return;
        if (!Keyboard.getEventKeyState()) return;
        if (Keyboard.isRepeatEvent()) return;
        int key = Keyboard.getEventKey();

        if (key == Keyboard.KEY_F && GuiScreen.isCtrlKeyDown()) {
            recordingText = !recordingText;
            text = "";
            return;
        }
        if (!recordingText) return;

        event.setCanceled(true);
        char charachter = Keyboard.getEventCharacter();
        switch (key) {
            case Keyboard.KEY_ESCAPE:
            case Keyboard.KEY_RETURN:
                recordingText = false;
                break;
            case Keyboard.KEY_BACK:
                text = text.substring(0, Math.max(0, text.length() - 1));
                break;
            default:
                if (ChatAllowedCharacters.isAllowedCharacter(charachter)) {
                    text += GuiScreen.isShiftKeyDown() ? Character.toUpperCase(charachter) : Character.toLowerCase(charachter);
                }
        }
    }

    @SubscribeEvent
    public void onDraw(GuiScreenEvent.DrawScreenEvent.Post event) {
        if (!WynnFeatures.config.containerSearch || !recordingText) return;
        GuiScreen gui = event.getGui();
        GlStateManager.pushMatrix();
        GlStateManager.scale(1.5f, 1.5f, 1.5f);
        mc.fontRenderer.drawString(
                text + "_",
                gui.width / 2f / 1.5f - mc.fontRenderer.getStringWidth(text + "_") / 2f,
                ((gui.height - 166) / 2f - mc.fontRenderer.FONT_HEIGHT - 5) / 1.5f,
                Color.RED.getRGB(),
                false
        );
        GlStateManager.scale(1f / 1.5f, 1f / 1.5f, 1f / 1.5f);
        GlStateManager.popMatrix();
    }

    @SubscribeEvent
    public void onGuiClose(GuiOpenEvent event) {
        recordingText = false;
        text = "";
    }

    @SubscribeEvent
    public void onSlotPre(DrawSlotEvent.Pre event) {
        if (!WynnFeatures.config.containerSearch || !recordingText) return;
        Slot slot = event.getSlot();
        if (slot.getHasStack()) {
            if (text.startsWith("lore:") && slot.getStack().hasTagCompound() && Objects.requireNonNull(slot.getStack().getTagCompound()).hasKey("display") && slot.getStack().getTagCompound().getCompoundTag("display").hasKey("Lore")) {
                for (String lore : slot.getStack().getTagCompound().getCompoundTag("display").getString("Lore").split("\n")) {
                    if (lore.contains(text.substring(5))) {
                        Gui.drawRect(
                                slot.xPos,
                                slot.yPos,
                                slot.xPos + 16,
                                slot.yPos + 16,
                                RenderUtils.withAlpha(Color.WHITE, 169).getRGB()
                        );
                    }
                }
            } else if (slot.getStack().getDisplayName().contains(text)) {
                Gui.drawRect(
                        slot.xPos,
                        slot.yPos,
                        slot.xPos + 16,
                        slot.yPos + 16,
                        RenderUtils.withAlpha(Color.WHITE, 169).getRGB()
                );
            }
        }
    }

    @SubscribeEvent
    public void onSlotPost(DrawSlotEvent.Post event) {
        if (!WynnFeatures.config.containerSearch || !recordingText) return;
        Slot slot = event.getSlot();
        if (slot.getHasStack()) {
            if (text.startsWith("lore:") && slot.getStack().hasTagCompound() && Objects.requireNonNull(slot.getStack().getTagCompound()).hasKey("display") && slot.getStack().getTagCompound().getCompoundTag("display").hasKey("Lore")) {
                for (String lore : slot.getStack().getTagCompound().getCompoundTag("display").getString("Lore").split("\n")) {
                    if (lore.contains(text.substring(5))) {
                        Gui.drawRect(
                                slot.xPos,
                                slot.yPos,
                                slot.xPos + 16,
                                slot.yPos + 16,
                                RenderUtils.withAlpha(Color.WHITE, 169).getRGB()
                        );
                    }
                }
            } else if (slot.getStack().getDisplayName().contains(text)) {
                Gui.drawRect(
                        slot.xPos,
                        slot.yPos,
                        slot.xPos + 16,
                        slot.yPos + 16,
                        RenderUtils.withAlpha(Color.WHITE, 169).getRGB()
                );
            }
        }
    }


}
