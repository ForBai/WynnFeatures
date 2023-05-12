package me.anemoi.wynnfeatures.mixins;

import me.anemoi.wynnfeatures.events.DrawSlotEvent;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiContainer.class)
public abstract class MixinGuiContainer {
    @Shadow
    protected abstract Slot getSlotAtPosition(int x, int y);

    @Inject(method = "drawSlot", at = @At("HEAD"))
    private void onDrawSlot(Slot slot, CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post(new DrawSlotEvent.Pre((GuiContainer) (Object) this, slot));
    }

    @Inject(method = "drawSlot", at = @At("RETURN"))
    private void onDrawSlotPost(Slot slot, CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post(new DrawSlotEvent.Post((GuiContainer) (Object) this, slot));
    }
}
