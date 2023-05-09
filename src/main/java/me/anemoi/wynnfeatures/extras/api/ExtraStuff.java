package me.anemoi.wynnfeatures.extras.api;

import net.minecraft.util.math.Vec3d;

import java.awt.*;

public class ExtraStuff {
    private Vec3d pos;
    private String cmd;
    private float visibleDistance = 32;
    private Color color = new Color(255, 0, 0, 255);
    private float range = 1;
    private boolean onShift = false;

    public ExtraStuff(String cmd, Vec3d pos) {
        this.cmd = cmd;
        this.pos = pos;
    }
    public ExtraStuff(String cmd, double x, double y, double z) {
        this.cmd = cmd;
        this.pos = new Vec3d(x, y, z);
    }

    public ExtraStuff withVisibleDistance(float visibleDistance) {
        this.visibleDistance = visibleDistance;
        return this;
    }

    public ExtraStuff withColor(Color color) {
        this.color = color;
        return this;
    }

    public ExtraStuff withRange(float range) {
        this.range = range;
        return this;
    }

    public ExtraStuff withOnShift(boolean onShift) {
        this.onShift = onShift;
        return this;
    }

    public Vec3d getPos() {
        return pos;
    }

    public String getCmd() {
        return cmd;
    }

    public float getVisibleDistance() {
        return visibleDistance;
    }

    public Color getColor() {
        return color;
    }

    public float getRange() {
        return range;
    }

    public boolean isOnShift() {
        return onShift;
    }

    public void setPos(Vec3d pos) {
        this.pos = pos;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public void setVisibleDistance(float visibleDistance) {
        this.visibleDistance = visibleDistance;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public void setOnShift(boolean onShift) {
        this.onShift = onShift;
    }

    @Override
    public String toString() {
        return "ExtraStuff{" +
                "pos=" + pos +
                ", cmd='" + cmd + '\'' +
                ", visibleDistance=" + visibleDistance +
                ", color=" + color +
                ", range=" + range +
                ", onShift=" + onShift +
                '}';
    }
}
