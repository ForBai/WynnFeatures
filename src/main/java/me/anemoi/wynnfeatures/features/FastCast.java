package me.anemoi.wynnfeatures.features;

import me.anemoi.wynnfeatures.WynnFeatures;
import me.anemoi.wynnfeatures.events.MillisecondEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

import static me.anemoi.wynnfeatures.WynnFeatures.mc;
/*
 * Thanks to RoseGold for the Packet part of this
 * taken from https://github.com/RoseGoldIsntGay/RoseGoldClient
 * modified and edited by forbai
 */
public class FastCast {
    public static ArrayList<Packet> packetList = new ArrayList<>();
    private static int totalTicks = 0;

    static CPacketPlayerTryUseItem useItemPacket = new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND);
    static CPacketAnimation swingItemPacket = new CPacketAnimation(EnumHand.MAIN_HAND);

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent event) {
        if (mc.player == null || mc.world == null || mc.getConnection() == null) return;
        int key = Keyboard.getEventKey();
        if (!Keyboard.isKeyDown(key) || !isWynncraftWeapon()) return;
        if (key == WynnFeatures.keybinds.get(1).getKeyCode()) { //cast RRR
            castSpell(isArcher(), WynnFeatures.config.fastCastClickDelay, SpellType.RRR);
            return;
        }
        if (key == WynnFeatures.keybinds.get(2).getKeyCode()) { //cast RLR
            castSpell(isArcher(), WynnFeatures.config.fastCastClickDelay, SpellType.RLR);
            return;
        }
        if (key == WynnFeatures.keybinds.get(3).getKeyCode()) { //cast RLL
            castSpell(isArcher(), WynnFeatures.config.fastCastClickDelay, SpellType.RLL);
            return;
        }
        if (key == WynnFeatures.keybinds.get(4).getKeyCode()) { //cast RRL
            castSpell(isArcher(), WynnFeatures.config.fastCastClickDelay, SpellType.RRL);
        }
    }

    @SubscribeEvent
    public void onTick(MillisecondEvent event) {
        if (mc.player == null || mc.world == null || mc.getConnection() == null) return;
        totalTicks++;
        if (packetList.size() == 0) return;
        if (totalTicks % (WynnFeatures.config.fastCastClickDelay + 2) != 0) return;
        mc.getConnection().getNetworkManager().sendPacket(packetList.get(0));
        packetList.remove(0);
    }


    public static boolean isWynncraftWeapon() {
        ItemStack itemStack = mc.player.getHeldItemMainhand();
        if (itemStack.getTagCompound() == null || itemStack.getTagCompound().hasNoTags()) return false;
        return itemStack.getTagCompound().toString().contains("§a✔§7 Class Req:");
    }

    public static boolean isArcher() {
        ItemStack itemStack = mc.player.getHeldItemMainhand();
        if (isWynncraftWeapon()) {
            assert itemStack.getTagCompound() != null;
            return itemStack.getTagCompound().toString().contains("Class Req: Archer");
        }
        return false;
    }

    private static void castSpell(boolean isArcher, int msDelay, SpellType spellType) {
        if (mc.player == null || mc.world == null || mc.getConnection() == null) return;
        if (isArcher) {
            switch (spellType) {
                case RRR:
                    castLLLSpell(msDelay);
                    break;
                case RLR:
                    castLRLSpell(msDelay);
                    break;
                case RLL:
                    castLRRSpell(msDelay);
                    break;
                case RRL:
                    castLLRSpell(msDelay);
                    break;
            }
        } else {
            switch (spellType) {
                case RRR:
                    castRRRSpell(msDelay);
                    break;
                case RLR:
                    castRLRSpell(msDelay);
                    break;
                case RLL:
                    castRLLSpell(msDelay);
                    break;
                case RRL:
                    castRRLSpell(msDelay);
                    break;
            }
        }
    }


    private static void castLLLSpell(int msDelay) {
        packetList.add(swingItemPacket);
        packetList.add(swingItemPacket);
        packetList.add(swingItemPacket);
    }

    private static void castLRLSpell(int msDelay) {
        packetList.add(swingItemPacket);
        packetList.add(useItemPacket);
        packetList.add(swingItemPacket);
    }

    private static void castLRRSpell(int msDelay) {
        packetList.add(swingItemPacket);
        packetList.add(useItemPacket);
        packetList.add(useItemPacket);
    }

    private static void castLLRSpell(int msDelay) {
        packetList.add(swingItemPacket);
        packetList.add(swingItemPacket);
        packetList.add(useItemPacket);
    }

    private static void castRRRSpell(int msDelay) {
        packetList.add(useItemPacket);
        packetList.add(useItemPacket);
        packetList.add(useItemPacket);
    }

    private static void castRLRSpell(int msDelay) {
        packetList.add(useItemPacket);
        packetList.add(swingItemPacket);
        packetList.add(useItemPacket);
    }

    private static void castRLLSpell(int msDelay) {
        packetList.add(useItemPacket);
        packetList.add(swingItemPacket);
        packetList.add(swingItemPacket);
    }

    private static void castRRLSpell(int msDelay) {
        packetList.add(useItemPacket);
        packetList.add(useItemPacket);
        packetList.add(swingItemPacket);
    }


    public static enum SpellType {
        RRR, RLR, RLL, RRL
    }
}
