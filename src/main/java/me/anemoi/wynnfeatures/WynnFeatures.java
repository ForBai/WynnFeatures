package me.anemoi.wynnfeatures;

import gg.essential.api.utils.GuiUtil;
import me.anemoi.wynnfeatures.config.Config;
import me.anemoi.wynnfeatures.config.ConfigScreen;
import me.anemoi.wynnfeatures.events.MillisecondEvent;
import me.anemoi.wynnfeatures.events.SecondEvent;
import me.anemoi.wynnfeatures.extras.ExtrasConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Mod(modid = WynnFeatures.MODID, name = WynnFeatures.NAME, version = WynnFeatures.VERSION, clientSideOnly = true)
public class WynnFeatures {
    public static final String MODID = "wynnfeatures";
    public static final String NAME = "WynnFeatures";
    public static final String VERSION = "1.0.0";

    public static ArrayList<KeyBinding> keybinds = new ArrayList<>();
    public static Config config = new Config();
    public static final Minecraft mc = Minecraft.getMinecraft();

    public static File configDirectory;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        configDirectory = new File(event.getModConfigurationDirectory(), "wynnfeatures");
        if (!configDirectory.exists()) {
            configDirectory.mkdirs();
        }
        ExtrasConfig.init(event.getModConfigurationDirectory(),"extras");

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        keybinds.add(new KeyBinding("Gui", Keyboard.KEY_RSHIFT, "WynnFeatures")); //0

        MinecraftForge.EVENT_BUS.register(this);

        for (KeyBinding keyBinding : keybinds) {
            ClientRegistry.registerKeyBinding(keyBinding);
        }

        System.out.println(MODID + " Initialized. Version: " + VERSION);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        LocalDateTime now = LocalDateTime.now();
        Duration initialDelay = Duration.between(now, now);
        long initialDelaySeconds = initialDelay.getSeconds();

        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> MinecraftForge.EVENT_BUS.post(new SecondEvent()), initialDelaySeconds, 1, TimeUnit.SECONDS);
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> MinecraftForge.EVENT_BUS.post(new MillisecondEvent()), initialDelaySeconds, 1, TimeUnit.MILLISECONDS);
    }

    @SubscribeEvent
    public void keyEvent(InputEvent.KeyInputEvent event){
        if(keybinds.get(0).isPressed()){
            GuiUtil.open(new ConfigScreen(true));
        }
    }
}

