package me.anemoi.wynnfeatures.config;

import gg.essential.vigilance.Vigilant;
import gg.essential.vigilance.data.*;
import me.anemoi.wynnfeatures.utils.BlockUtils;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Config extends Vigilant {
    public static Config INSTANCE = new Config();

    @Property(type = PropertyType.SWITCH, name = "Show Barriers", description = "Replaces Barriers with a other Block", category = "§1§rFeatures", subcategory = "Barrier")
    public boolean showBarriers = true;

    @Property(type = PropertyType.SLIDER, name = "Barrier Range", description = "The Range in which Barriers will be replaced", category = "§1§rFeatures", subcategory = "Barrier", max = 100, min = 1)
    public int barrierRange = 15;

    //block type, could be Stone CobbleStone White Wool Diamond Block
//    @Property(type = PropertyType.SELECTOR, name = "Replace Block", description = "The Block that will replace the Barriers", category = "§1§rFeatures", subcategory = "Barrier",options = Config.BLOCKS)
    public String replaceBlock = "stone";

    @Property(type = PropertyType.SWITCH, name = "Container Search", description = "Enabled/Disables the Container Search Function", category = "§1§rFeatures", subcategory = "General")
    public boolean containerSearch = true;

    @Property(type = PropertyType.SWITCH, name = "Trade Market Calculator", description = "Calculates the money you will get when everything is sold and some other Information", category = "§1§rFeatures", subcategory = "General")
    public boolean tradeMarketCalculator = true;

    /***********
     *  SCRAP  *
     ***********/

    @Property(type = PropertyType.SWITCH, name = "Show Auto Scrap Button", description = "Shows a Button in the Blacksmith GUI to automatically scrap all items", category = "§1§rFeatures", subcategory = "Scrap")
    public boolean autoScrapButton = true;

    @Property(type = PropertyType.SWITCH, name = "Normal", description = "Enabled/Disables scraping Normal Items", category = "§1§rFeatures", subcategory = "Scrap")
    public boolean normalScrap = true;

    @Property(type = PropertyType.SWITCH, name = "Common", description = "Enabled/Disables scraping Common Items", category = "§1§rFeatures", subcategory = "Scrap")
    public boolean commonScrap = true;

    @Property(type = PropertyType.SLIDER, name = "Scrap Click Delay", description = "The Delay between each Scrap Click in ms", category = "§1§rFeatures", subcategory = "Scrap", max = 1000, min = 1)
    public int scrapClickDelay = 100;

    /***********
     *  EXTRAS *
     ***********/
    @Property(type = PropertyType.SWITCH, name = "Extras", description = "Enabled/Disables the Full Extras Function", category = "§1§rExtras", subcategory = "General")
    public boolean extrasToggled = true;

    @Property(type = PropertyType.SWITCH, name = "Extra Blocks", description = "Enabled/Disables the Full Extra Blocks Function", category = "§1§rExtras", subcategory = "General")
    public boolean extraBlocksToggled = true;

    @Property(type = PropertyType.SWITCH, name = "Extra Waypoints", description = "Enabled/Disables the Full Extra Waypoints Function", category = "§1§rExtras", subcategory = "General")
    public boolean extraWaypointsToggled = true;

    @Property(type = PropertyType.SWITCH, name = "Extra Stuff", description = "Enabled/Disables the Full Extra Stuff Function", category = "§1§rExtras", subcategory = "General")
    public boolean extraStuffToggled = true;

    //extrablock settings:
    /*
    "Show Block Range" slider 1..255 "the range in which block are rendered"
    "Range Mode" selector {"Sphere", "Cube"}
    "Refresh Type" selector {"Millisecond", "Tick","Tick+","Second"}
    "Mid Click Selector" switch
     */
    @Property(type = PropertyType.SLIDER, name = "Show Block Range", description = "the range in which block are rendered", category = "§1§rExtras", subcategory = "Extra Blocks", min = 1, max = 255)
    public int extraBlocksRange = 32;
    @Property(type = PropertyType.SELECTOR, name = "Range Mode", description = "", category = "§1§rExtras", subcategory = "Extra Blocks", options = {"Sphere", "Cube"})
    public int extraBlocksRangeMode = 0;
    @Property(type = PropertyType.SELECTOR, name = "Refresh Type", description = "", category = "§1§rExtras", subcategory = "Extra Blocks", options = {"Millisecond", "Tick", "Tick+", "Second"})
    public int extraBlocksRefreshType = 2;
    @Property(type = PropertyType.SWITCH, name = "Mid Click Selector", description = "", category = "§1§rExtras", subcategory = "Extra Blocks")
    public boolean extraBlocksMidClickSelector = false;

    @Property(type = PropertyType.SWITCH, name = "Show Name", description = "Shows the Name of the Waypoint", category = "§1§rExtras", subcategory = "Extra Waypoints")
    public boolean extraWaypointsShowName = true;

    @Property(type = PropertyType.SWITCH, name = "Show Cmd", description = "Shows the Cmd of the Cmd Stuff point", category = "§1§rExtras", subcategory = "Extra Stuff")
    public boolean extraStuffShowCmd = true;

    @Property(type = PropertyType.SWITCH, name = "Show Circle", description = "Shows a Circle instead of a Block Box", category = "§1§rExtras", subcategory = "Extra Stuff")
    public boolean extraStuffShowCircle = true;


    /**********
     *  GUI   *
     **********/
    @Property(
            type = PropertyType.SWITCH,
            name = "Use Smooth Font",
            description = "Uses a smoother font to render text. §cRequires restart",
            category = "§1§rGui",
            subcategory = "Font"
    )
    public boolean customFont = false;

    @Property(type = PropertyType.COLOR, name = "Gui Lines", description = "", category = "§1§rGui", subcategory = "Colors")
    public Color guiLines = new Color(0xF0A000);

    @Property(type = PropertyType.COLOR, name = "Selected Category Text", description = "", category = "§1§rGui", subcategory = "Colors")
    public Color selectedCategory = new Color(0xF2A90A);

    @Property(type = PropertyType.COLOR, name = "Hovered Category Text", description = "", category = "§1§rGui", subcategory = "Colors")
    public Color hoveredCategory = new Color(0xF8A00C);

    @Property(type = PropertyType.COLOR, name = "Default Category Text", description = "", category = "§1§rGui", subcategory = "Colors")
    public Color defaultCategory = new Color(0xFFFFFF);

    @Property(type = PropertyType.COLOR, name = "Feature Box Outline", description = "", category = "§1§rGui", subcategory = "Colors")
    public Color featureBoxOutline = new Color(0xa9a9a9);

    @Property(type = PropertyType.COLOR, name = "Feature Description Text", description = "", category = "§1§rGui", subcategory = "Colors")
    public Color featureDescription = new Color(0xbbbbbb);

    @Property(type = PropertyType.COLOR, name = "Main Box Background", description = "", category = "§1§rGui", subcategory = "Colors")
    public Color mainBackground = new Color(25, 25, 25, 200);

    @Property(type = PropertyType.COLOR, name = "Search Box Background", description = "", category = "§1§rGui", subcategory = "Colors")
    public Color searchBoxBackground = new Color(120, 120, 120, 60);

    @Property(type = PropertyType.COLOR, name = "Button Background", description = "", category = "§1§rGui", subcategory = "Colors")
    public Color editGuiUnhovered = new Color(0, 0, 0, 50);

    @Property(type = PropertyType.COLOR, name = "Button Hover Background", description = "", category = "§1§rGui", subcategory = "Colors")
    public Color editGuiHovered = new Color(0, 0, 0, 75);

    @Property(type = PropertyType.COLOR, name = "Feature Box Background", description = "", category = "§1§rGui", subcategory = "Colors")
    public Color featureBoxBackground = new Color(35, 35, 35, 120);

    @Property(type = PropertyType.COLOR, name = "Edit Gui Text", description = "", category = "§1§rGui", subcategory = "Colors")
    public Color editGuiText = new Color(0xFFFFFF);

    @Property(type = PropertyType.COLOR, name = "Title Text", description = "", category = "§1§rGui", subcategory = "Colors")
    public Color titleColor = new Color(0xFFAA00);

    @Property(type = PropertyType.COLOR, name = "Version Text", description = "", category = "§1§rGui", subcategory = "Colors")
    public Color versionColor = new Color(0xFFFFFF);


    public Config() {
        super(new File("./config/wynnfeatures/config.toml"), "§aRoseGoldClient", new JVMAnnotationPropertyCollector(), new ConfigSorting());
        initialize();
    }

    public static class ConfigSorting extends SortingBehavior {
        @NotNull
        @Override
        public Comparator<Category> getCategoryComparator() {
            return (o1, o2) -> {
                if (o1.getName().equals("WynnFeatures")) {
                    return -1;
                } else if (o2.getName().equals("WynnFeatures")) {
                    return 1;
                } else {
                    return o1.getName().compareTo(o2.getName());
                }
            };
        }
    }
}
