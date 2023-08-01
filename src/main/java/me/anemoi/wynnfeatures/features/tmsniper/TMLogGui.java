package me.anemoi.wynnfeatures.features.tmsniper;

import cc.polyfrost.polyui.PolyUI;
import cc.polyfrost.polyui.color.Color;
import cc.polyfrost.polyui.component.Drawable;
import cc.polyfrost.polyui.component.impl.Block;
import cc.polyfrost.polyui.component.impl.Image;
import cc.polyfrost.polyui.event.MouseClicked;
import cc.polyfrost.polyui.layout.Layout;
import cc.polyfrost.polyui.layout.impl.FlexLayout;
import cc.polyfrost.polyui.property.impl.BlockProperties;
import cc.polyfrost.polyui.renderer.data.PolyImage;
import cc.polyfrost.polyui.renderer.impl.GLWindow;
import cc.polyfrost.polyui.renderer.impl.NVGRenderer;
import cc.polyfrost.polyui.unit.Units;
import cc.polyfrost.polyui.unit.Vec2;

import java.util.ArrayList;

public class TMLogGui {
    public static void main() {
        //start a new jvm and attach lwjgl 3.3.2 in it and then run the gui
        new Thread(() -> {
            try {
                GLWindow window = new GLWindow("Java Window", 800, 800);
                ArrayList<Drawable> things = new ArrayList<>(50);

                for (int i = 0; i < 51; ++i) {
                    int finalI = i;
                    things.add(new Block(Units.flex(), new Vec2<>(Units.pixels(Math.random() * 40.0 + 40.0), Units.pixels(Math.random() * 40.0 + 40.0)), (new MouseClicked(0)).to((component) -> {
                        System.out.println("Mouse clicked! " + finalI);
                    }), (new MouseClicked(0, 2)).to((component) -> {
                        System.out.println("Mouse double-clicked! " + finalI);
                    }), (new MouseClicked(1)).to((component) -> {
                        System.out.println("Mouse right clicked! " + finalI);
                    })));
                }

                PolyUI polyUI = new PolyUI("", new NVGRenderer((float) window.getWidth(), (float) window.getHeight()), Layout.drawables(new Block(new BlockProperties(new Color.Gradient(new Color(1.0F, 0.0F, 0.0F), new Color(0.0F, 1.0F, 1.0F))), Units.times(Units.pixels(20), Units.pixels(600)), Units.times(Units.pixels(120), Units.pixels(120))), new Block(new BlockProperties(new Color.Chroma(Units.seconds(5))), Units.times(Units.pixels(200), Units.pixels(600)), Units.times(Units.pixels(120), Units.pixels(120))), new Image(new PolyImage("/s.png", 120.0F, 120.0F), Units.times(Units.pixels(380), Units.pixels(600))), new FlexLayout(Units.times(Units.pixels(20), Units.pixels(30)), Units.percent(80), things.toArray(new Drawable[50]))));
                window.open(polyUI);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
