package me.anemoi.wynnfeatures.config;

import gg.essential.vigilance.Vigilant;
import gg.essential.vigilance.data.*;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.File;
import java.util.Comparator;

public class Config extends Vigilant {
    public static Config INSTANCE = new Config();

    @Property(type = PropertyType.SWITCH, name = "Show Barriers", description = "Replaces Barriers with a other Block", category = "§1§rFeatures", subcategory = "Barrier")
    public boolean showBarriers = true;

    @Property(type = PropertyType.SLIDER, name = "Barrier Range", description = "The Range in which Barriers will be replaced", category = "§1§rFeatures", subcategory = "Barrier", max = 100, min = 1)
    public int barrierRange = 15;

    @Property(type = PropertyType.SELECTOR, name = "Replace Block", description = "The Block that will replace the Barriers", category = "§1§rFeatures", subcategory = "Barrier", options = {"air", "stone", "grass", "dirt", "cobblestone", "planks", "sapling", "bedrock", "flowing_water", "water", "flowing_lava", "lava", "sand", "gravel", "gold_ore", "iron_ore", "coal_ore", "log", "leaves", "sponge", "glass", "lapis_ore", "lapis_block", "dispenser", "sandstone", "noteblock", "bed", "golden_rail", "detector_rail", "sticky_piston", "web", "tallgrass", "deadbush", "piston", "piston_head", "wool", "piston_extension", "yellow_flower", "red_flower", "brown_mushroom", "red_mushroom", "gold_block", "iron_block", "double_stone_slab", "stone_slab", "brick_block", "tnt", "bookshelf", "mossy_cobblestone", "obsidian", "torch", "fire", "mob_spawner", "oak_stairs", "chest", "redstone_wire", "diamond_ore", "diamond_block", "crafting_table", "wheat", "farmland", "furnace", "lit_furnace", "standing_sign", "wooden_door", "ladder", "rail", "stone_stairs", "wall_sign", "lever", "stone_pressure_plate", "iron_door", "wooden_pressure_plate", "redstone_ore", "lit_redstone_ore", "unlit_redstone_torch", "redstone_torch", "stone_button", "snow_layer", "ice", "snow", "cactus", "clay", "reeds", "jukebox", "fence", "pumpkin", "netherrack", "soul_sand", "glowstone", "portal", "lit_pumpkin", "cake", "unpowered_repeater", "powered_repeater", "stained_glass", "trapdoor", "monster_egg", "stonebrick", "brown_mushroom_block", "red_mushroom_block", "iron_bars", "glass_pane", "melon_block", "pumpkin_stem", "melon_stem", "vine", "fence_gate", "brick_stairs", "stone_brick_stairs", "mycelium", "waterlily", "nether_brick", "nether_brick_fence", "nether_brick_stairs", "nether_wart", "enchanting_table", "brewing_stand", "cauldron", "end_portal", "end_portal_frame", "end_stone", "dragon_egg", "redstone_lamp", "lit_redstone_lamp", "double_wooden_slab", "wooden_slab", "cocoa", "sandstone_stairs", "emerald_ore", "ender_chest", "tripwire_hook", "tripwire", "emerald_block", "spruce_stairs", "birch_stairs", "jungle_stairs", "command_block", "beacon", "cobblestone_wall", "flower_pot", "carrots", "potatoes", "wooden_button", "skull", "anvil", "trapped_chest", "light_weighted_pressure_plate", "heavy_weighted_pressure_plate", "unpowered_comparator", "powered_comparator", "daylight_detector", "redstone_block", "quartz_ore", "hopper", "quartz_block", "quartz_stairs", "activator_rail", "dropper", "stained_hardened_clay", "stained_glass_pane", "leaves2", "log2", "acacia_stairs", "dark_oak_stairs", "slime", "barrier", "iron_trapdoor", "prismarine", "sea_lantern", "hay_block", "carpet", "hardened_clay", "coal_block", "packed_ice", "double_plant", "standing_banner", "wall_banner", "daylight_detector_inverted", "red_sandstone", "red_sandstone_stairs", "double_stone_slab2", "stone_slab2", "spruce_fence_gate", "birch_fence_gate", "jungle_fence_gate", "dark_oak_fence_gate", "acacia_fence_gate", "spruce_fence", "birch_fence", "jungle_fence", "dark_oak_fence", "acacia_fence", "spruce_door", "birch_door", "jungle_door", "acacia_door", "dark_oak_door", "end_rod", "chorus_plant", "chorus_flower", "purpur_block", "purpur_pillar", "purpur_stairs", "purpur_double_slab", "purpur_slab", "end_bricks", "beetroots", "grass_path", "end_gateway", "repeating_command_block", "chain_command_block", "frosted_ice", "magma", "nether_wart_block", "red_nether_brick", "bone_block", "structure_void", "observer", "white_shulker_box", "orange_shulker_box", "magenta_shulker_box", "light_blue_shulker_box", "yellow_shulker_box", "lime_shulker_box", "pink_shulker_box", "gray_shulker_box", "silver_shulker_box", "cyan_shulker_box", "purple_shulker_box", "blue_shulker_box", "brown_shulker_box", "green_shulker_box", "red_shulker_box", "black_shulker_box", "white_glazed_terracotta", "orange_glazed_terracotta", "magenta_glazed_terracotta", "light_blue_glazed_terracotta", "yellow_glazed_terracotta", "lime_glazed_terracotta", "pink_glazed_terracotta", "gray_glazed_terracotta", "silver_glazed_terracotta", "cyan_glazed_terracotta", "purple_glazed_terracotta", "blue_glazed_terracotta", "brown_glazed_terracotta", "green_glazed_terracotta", "red_glazed_terracotta", "black_glazed_terracotta", "concrete", "concrete_powder", "structure_block"})
    public int replaceBlock = 1;

    @Property(type = PropertyType.SWITCH, name = "Container Search", description = "Enabled/Disables the Container Search Function", category = "§1§rFeatures", subcategory = "General")
    public boolean containerSearch = true;

    @Property(type = PropertyType.SWITCH, name = "Trade Market Calculator", description = "Calculates the money you will get when everything is sold and some other Information", category = "§1§rFeatures", subcategory = "General")
    public boolean tradeMarketCalculator = true;

    @Property(type = PropertyType.SWITCH, name = "Show World Uptime on Tab", description = "Shows the World Uptime on the Tablist", category = "§1§rFeatures", subcategory = "General")
    public boolean showWorldUptimeOnTab = true;

    @Property(type = PropertyType.SLIDER, name = "Fast Cast Click Delay",description = "The Delay between each Cast Click in ms", category = "§1§rFeatures", subcategory = "General", max = 1000, min = 1)
    public int fastCastClickDelay = 90;

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
