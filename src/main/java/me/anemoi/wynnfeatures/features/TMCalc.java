package me.anemoi.wynnfeatures.features;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.anemoi.wynnfeatures.WynnFeatures;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.concurrent.atomic.AtomicLong;

public class TMCalc {
    @SubscribeEvent
    public void onDrawScreenEvent(GuiScreenEvent.DrawScreenEvent event) {
        if (!WynnFeatures.config.tradeMarketCalculator) return;
        if (!(event.getGui() instanceof GuiChest)) return;
        GuiChest chest = (GuiChest) event.getGui();
        IInventory inventory = ((ContainerChest) chest.inventorySlots).getLowerChestInventory();
        if (inventory == null) return;
        if (inventory.getDisplayName().getFormattedText().contains("Trade Overview")) {
            AtomicLong totalA = new AtomicLong();
            chest.inventorySlots.inventorySlots.forEach(slot -> {
                //10-14 19-23 28-32
                if ((slot.slotNumber >= 10 && slot.slotNumber <= 14) || (slot.slotNumber >= 19 && slot.slotNumber <= 23) || (slot.slotNumber >= 28 && slot.slotNumber <= 32)) {
                    if (slot.getHasStack() && ChatFormatting.stripFormatting(slot.getStack().getDisplayName()).startsWith("Selling")) {
                        String amount = ChatFormatting.stripFormatting(slot.getStack().getDisplayName()).split(" ")[1];
                        //print the first line of the lore
                        String price = ChatFormatting.stripFormatting(slot.getStack().getTooltip(chest.mc.player, ITooltipFlag.TooltipFlags.NORMAL).get(1)).split(" ")[0].replace("²", "");
                        long plusTotal = Long.parseLong(amount.replace(",", "")) * Long.parseLong(price.replace(",", ""));
                        totalA.addAndGet(plusTotal);
                    }
                }
            });
            long total = totalA.get();
            // 1stx = 64 liquid emeralds = 1 stack of liquid emerald
            // 1¼² = 64 emerald blocks = 1 liquid emerald
            // 1²½ = 64 emeralds = 1 emerald block
            // 1² = 1 = emerald
            // first converted total price is rounded to the highest type of emerald
            String totalString = String.valueOf(total);
            StringBuilder converted = new StringBuilder();
            do {
                if (total >= 64 * 64 * 64) {
                    converted.append("§a").append(total / (64 * 64 * 64));
                    total -= (total / (64 * 64 * 64)) * (64 * 64 * 64);
                    converted.append(total > 0 ? "§2stx §7/ " : "§2stx");
                } else if (total >= 64 * 64) {
                    converted.append("§a").append(total / (64 * 64));
                    total -= (total / (64 * 64)) * (64 * 64);
                    converted.append(total > 0 ? "§2¼² §7/ " : "§2¼²");
                } else if (total >= 64) {
                    converted.append("§a").append(total / 64);
                    total -= (total / 64) * 64;
                    converted.append(total > 0 ? "§2²½ §7/ " : "§2²½");
                } else {
                    converted.append("§a").append(total);
                    total -= total;
                    converted.append("§2²");
                }
            } while (total > 0);
            //converted + " §7(§a" + totalString + "§2²§7) total"
            //set the display name to the original name + the converted total price
            chest.drawCenteredString(chest.mc.fontRenderer, converted + " §7(§a" + totalString + "§2²§7) total", event.getGui().width / 2, event.getGui().width / 7, 4210752);
        }
    }
}
