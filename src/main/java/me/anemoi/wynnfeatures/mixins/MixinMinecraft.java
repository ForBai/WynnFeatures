package me.anemoi.wynnfeatures.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = net.minecraft.client.Minecraft.class, priority = 999)
public class MixinMinecraft {
    @Inject(method = "init", at = @At(value = "HEAD"))
    private void init(CallbackInfo ci) {
        for (int i = 0; i < 20; i++) {
            System.out.println("WynnFeatures is loaded!");
        }
    }
}
