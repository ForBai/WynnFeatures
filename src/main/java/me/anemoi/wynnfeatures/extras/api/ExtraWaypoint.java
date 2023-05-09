package me.anemoi.wynnfeatures.extras.api;

import net.minecraft.util.math.BlockPos;

import java.awt.*;

public class ExtraWaypoint {
    private String name;
    private BlockPos pos;
    private float visibleDistance = 256;
    private Color color = new Color(255, 0, 0, 255);

    public ExtraWaypoint(String name, BlockPos pos) {
        this.name = name;
        this.pos = pos;
    }
    public ExtraWaypoint(String name, int x, int y, int z) {
        this.name = name;
        this.pos = new BlockPos(x, y, z);
    }

    public ExtraWaypoint withVisibleDistance(float visibleDistance) {
        this.visibleDistance = visibleDistance;
        return this;
    }

    public ExtraWaypoint withColor(Color color) {
        this.color = color;
        return this;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BlockPos getPos() {
        return pos;
    }

    public void setPos(BlockPos pos) {
        this.pos = pos;
    }

    public float getVisibleDistance() {
        return visibleDistance;
    }

    public void setVisibleDistance(float visibleDistance) {
        this.visibleDistance = visibleDistance;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
