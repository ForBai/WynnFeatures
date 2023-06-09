/*
 * Thanks to https://github.com/AviciaGuild for avomod which this is based on.
 * https://github.com/AviciaGuild/avomod/blob/main/LICENSE.txt
 * changed and ported to 1.12.2 by Anemoi/ForBai
 */
package me.anemoi.wynnfeatures.features;

import me.anemoi.wynnfeatures.WynnFeatures;
import me.anemoi.wynnfeatures.utils.RenderUtils;
import me.anemoi.wynnfeatures.utils.UpTimeHelper;
import me.anemoi.wynnfeatures.utils.Utils;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static me.anemoi.wynnfeatures.WynnFeatures.mc;

public class UpTime {

    private static UpTimeHelper upTimes = null;
    private float posX = 0f;
    private float posY = 0f;
    private int dragX = 0;
    private int dragY = 0;
    private boolean mousestate = false;

    public static void updateUpTimes() {
        Thread thread = new Thread(() -> {
            try {
                upTimes = new UpTimeHelper();
                // Re-runs the update function every 5 minutes, to keep up with new worlds being started.
                // The current world's age is based on a timestamp, so we don't need to update for its sake
                Thread.sleep(60 * 5 * 1000);
                updateUpTimes();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        if (!thread.isAlive()) {
            thread.start();
        }
    }

    @SubscribeEvent
    public void onRenderHud(RenderGameOverlayEvent event) {
        if (mc.player == null || mc.world == null || !WynnFeatures.config.showWorldUptimeOnTab) return;
        if (!mc.ingameGUI.getChatGUI().getChatOpen() && !mc.gameSettings.keyBindPlayerList.isKeyDown()) return;
        int rectangleHeight = 12;
        FontRenderer fontRenderer = mc.fontRenderer;
        if (Utils.getCurrentWorld() != null) {
            String yourWorldText = "Your world §b" + Utils.getCurrentWorld() + "§f: " + Utils.getReadableTime(upTimes.getAge(Utils.getCurrentWorld()));
            int rectangleWidth = mc.fontRenderer.getStringWidth(yourWorldText) + 4;
            //RenderUtils.drawRect(posX, posY + rectangleHeight, rectangleWidth, rectangleHeight, new Color(0, 0, 255, 100).getRGB());
            fontRenderer.drawString(yourWorldText, (int) (posX + 2), (int) (posY + 2 + rectangleHeight), Color.WHITE.getRGB());
        }
        String newestWorld = upTimes.getNewestWorld();
        if (newestWorld != null) {
            String newestWorldText = "Newest world §b" + newestWorld + "§f: " + Utils.getReadableTime(upTimes.getAge(newestWorld));
            int rectangleWidth = mc.fontRenderer.getStringWidth(newestWorldText) + 4;
            //RenderUtils.drawRect(posX, posY, rectangleWidth, rectangleHeight, new Color(0, 0, 255, 100).getRGB());
            fontRenderer.drawString(newestWorldText, (int) (posX + 2), (int) (posY + 2), Color.WHITE.getRGB());
        }


        if (mc.currentScreen instanceof GuiChat) {
            if (isHovering()) {
                if (Mouse.isButtonDown(0) && mousestate) {
                    posX = (normaliseX() - dragX);
                    posY = (normaliseY() - dragY);
                }
            }
        }
        if (Mouse.isButtonDown(0) && isHovering()) {
            if (!mousestate) {
                dragX = (int) (normaliseX() - posX);
                dragY = (int) (normaliseY() - posY);
            }
            mousestate = true;
        } else {
            mousestate = false;
        }
    }


    public int normaliseX() {
        return (int) (Mouse.getX() / 2f);
    }

    public int normaliseY() {
        ScaledResolution sr = new ScaledResolution(mc);
        return (-Mouse.getY() + sr.getScaledHeight() + sr.getScaledHeight()) / 2;
    }

    public boolean isHovering() {
        return normaliseX() > posX - 10 && normaliseX() < posX + 100 && normaliseY() > posY && normaliseY() < posY + 100;
    }

    public void size(double width, double height, double animation) {
        GL11.glTranslated(width, height, 0.0);
        GL11.glScaled(animation, animation, 1.0);
        GL11.glTranslated(-width, -height, 0.0);
    }


}
