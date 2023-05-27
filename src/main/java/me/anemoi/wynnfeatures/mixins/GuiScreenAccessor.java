package me.anemoi.wynnfeatures.mixins;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(value = {GuiScreen.class}, priority = 2000)
public interface GuiScreenAccessor {

    //now i acces the buttonList field
    @Accessor
    public List<GuiButton> getButtonList();

    //now i acces the labelList field
    @Accessor
    public List<GuiButton> getLabelList();


}

