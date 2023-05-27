package me.anemoi.wynnfeatures.utils;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;

import static me.anemoi.wynnfeatures.WynnFeatures.mc;

public class BlockUtils {
    public static final String[] BLOCKS = {"air", "stone", "grass", "dirt", "cobblestone", "planks", "sapling", "bedrock", "flowing_water", "water", "flowing_lava", "lava", "sand", "gravel", "gold_ore", "iron_ore", "coal_ore", "log", "leaves", "sponge", "glass", "lapis_ore", "lapis_block", "dispenser", "sandstone", "noteblock", "bed", "golden_rail", "detector_rail", "sticky_piston", "web", "tallgrass", "deadbush", "piston", "piston_head", "wool", "piston_extension", "yellow_flower", "red_flower", "brown_mushroom", "red_mushroom", "gold_block", "iron_block", "double_stone_slab", "stone_slab", "brick_block", "tnt", "bookshelf", "mossy_cobblestone", "obsidian", "torch", "fire", "mob_spawner", "oak_stairs", "chest", "redstone_wire", "diamond_ore", "diamond_block", "crafting_table", "wheat", "farmland", "furnace", "lit_furnace", "standing_sign", "wooden_door", "ladder", "rail", "stone_stairs", "wall_sign", "lever", "stone_pressure_plate", "iron_door", "wooden_pressure_plate", "redstone_ore", "lit_redstone_ore", "unlit_redstone_torch", "redstone_torch", "stone_button", "snow_layer", "ice", "snow", "cactus", "clay", "reeds", "jukebox", "fence", "pumpkin", "netherrack", "soul_sand", "glowstone", "portal", "lit_pumpkin", "cake", "unpowered_repeater", "powered_repeater", "stained_glass", "trapdoor", "monster_egg", "stonebrick", "brown_mushroom_block", "red_mushroom_block", "iron_bars", "glass_pane", "melon_block", "pumpkin_stem", "melon_stem", "vine", "fence_gate", "brick_stairs", "stone_brick_stairs", "mycelium", "waterlily", "nether_brick", "nether_brick_fence", "nether_brick_stairs", "nether_wart", "enchanting_table", "brewing_stand", "cauldron", "end_portal", "end_portal_frame", "end_stone", "dragon_egg", "redstone_lamp", "lit_redstone_lamp", "double_wooden_slab", "wooden_slab", "cocoa", "sandstone_stairs", "emerald_ore", "ender_chest", "tripwire_hook", "tripwire", "emerald_block", "spruce_stairs", "birch_stairs", "jungle_stairs", "command_block", "beacon", "cobblestone_wall", "flower_pot", "carrots", "potatoes", "wooden_button", "skull", "anvil", "trapped_chest", "light_weighted_pressure_plate", "heavy_weighted_pressure_plate", "unpowered_comparator", "powered_comparator", "daylight_detector", "redstone_block", "quartz_ore", "hopper", "quartz_block", "quartz_stairs", "activator_rail", "dropper", "stained_hardened_clay", "stained_glass_pane", "leaves2", "log2", "acacia_stairs", "dark_oak_stairs", "slime", "barrier", "iron_trapdoor", "prismarine", "sea_lantern", "hay_block", "carpet", "hardened_clay", "coal_block", "packed_ice", "double_plant", "standing_banner", "wall_banner", "daylight_detector_inverted", "red_sandstone", "red_sandstone_stairs", "double_stone_slab2", "stone_slab2", "spruce_fence_gate", "birch_fence_gate", "jungle_fence_gate", "dark_oak_fence_gate", "acacia_fence_gate", "spruce_fence", "birch_fence", "jungle_fence", "dark_oak_fence", "acacia_fence", "spruce_door", "birch_door", "jungle_door", "acacia_door", "dark_oak_door", "end_rod", "chorus_plant", "chorus_flower", "purpur_block", "purpur_pillar", "purpur_stairs", "purpur_double_slab", "purpur_slab", "end_bricks", "beetroots", "grass_path", "end_gateway", "repeating_command_block", "chain_command_block", "frosted_ice", "magma", "nether_wart_block", "red_nether_brick", "bone_block", "structure_void", "observer", "white_shulker_box", "orange_shulker_box", "magenta_shulker_box", "light_blue_shulker_box", "yellow_shulker_box", "lime_shulker_box", "pink_shulker_box", "gray_shulker_box", "silver_shulker_box", "cyan_shulker_box", "purple_shulker_box", "blue_shulker_box", "brown_shulker_box", "green_shulker_box", "red_shulker_box", "black_shulker_box", "white_glazed_terracotta", "orange_glazed_terracotta", "magenta_glazed_terracotta", "light_blue_glazed_terracotta", "yellow_glazed_terracotta", "lime_glazed_terracotta", "pink_glazed_terracotta", "gray_glazed_terracotta", "silver_glazed_terracotta", "cyan_glazed_terracotta", "purple_glazed_terracotta", "blue_glazed_terracotta", "brown_glazed_terracotta", "green_glazed_terracotta", "red_glazed_terracotta", "black_glazed_terracotta", "concrete", "concrete_powder", "structure_block"};

    public static IBlockState getStateFromString(String blockStateString) {
        //if there are no properties, just return the block
        if (!blockStateString.contains("[")) {
            return Block.getBlockFromName(blockStateString).getDefaultState();
        }
        // Split the string into the block ID and property string
        String[] parts = blockStateString.split("\\[|\\]");
        String blockID = parts[0];
        String propertyString = parts[1];

        // Get the block object for the given block ID
        Block block = Block.getBlockFromName(blockID);
        if (block == null) {
            return null;
        }

        // Split the property string into individual properties
        String[] properties = propertyString.split(",");

        // Set the default block state
        IBlockState blockState = block.getDefaultState();

        // Set the block state properties based on the property string
        for (String property : properties) {
            String[] propertyParts = property.split("=");
            String propertyName = propertyParts[0];
            String propertyValue = propertyParts[1];

            // Get the block state property object for the given property name
            IProperty blockStateProperty = null;
            ImmutableMap<IProperty<?>, Comparable<?>> blockStateProperties = blockState.getProperties();
            for (IProperty<?> prop : blockStateProperties.keySet()) {
                if (prop.getName().equals(propertyName)) {
                    blockStateProperty = prop;
                    break;
                }
            }
            if (blockStateProperty == null) {
                continue;
            }

            // Get the property value object for the given property value string
            Comparable propertyValueObject = null;
            for (Object value : blockStateProperty.getAllowedValues()) {
                if (value.toString().equals(propertyValue)) {
                    propertyValueObject = (Comparable) value;
                    break;
                }
            }
            if (propertyValueObject == null) {
                continue;
            }

            // Set the block state property
            blockState = blockState.withProperty(blockStateProperty, propertyValueObject);
        }

        return blockState;
    }

    public static String getStringFromState(IBlockState state) {
        String blockID = state.getBlock().getRegistryName().toString();
        StringBuilder propertyString = new StringBuilder();
        ImmutableMap<IProperty<?>, Comparable<?>> blockStateProperties = state.getProperties();
        int i = 0;
        for (IProperty<?> prop : blockStateProperties.keySet()) {
            i++;
            propertyString.append(prop.getName()).append("=").append(blockStateProperties.get(prop)).append(i == blockStateProperties.keySet().size() ? "" : ",");
        }
        return (propertyString.toString()).equals("") ? blockID : blockID + "[" + propertyString + "]";
    }

    public static boolean isPosInSphere(BlockPos pos, BlockPos center, int radius) {
        return Math.sqrt(Math.pow(pos.getX() - center.getX(), 2) + Math.pow(pos.getY() - center.getY(), 2) + Math.pow(pos.getZ() - center.getZ(), 2)) <= radius;
    }

    public static boolean isPosInSphere(BlockPos pos, BlockPos center, float radius) {
        return Math.sqrt(Math.pow(pos.getX() - center.getX(), 2) + Math.pow(pos.getY() - center.getY(), 2) + Math.pow(pos.getZ() - center.getZ(), 2)) <= radius;
    }

    public static boolean isPosInSphere(Vec3d pos, Vec3d center, int radius) {
        return Math.sqrt(Math.pow(pos.x - center.x, 2) + Math.pow(pos.y - center.y, 2) + Math.pow(pos.z - center.z, 2)) <= radius;
    }

    public static boolean isPosInSphere(Vec3d pos, Vec3d center, float radius) {
        return Math.sqrt(Math.pow(pos.x - center.x, 2) + Math.pow(pos.y - center.y, 2) + Math.pow(pos.z - center.z, 2)) <= radius;
    }

    public static boolean isPosInSphere(Vec3d pos, Vec3d center, double radius) {
        return Math.sqrt(Math.pow(pos.x - center.x, 2) + Math.pow(pos.y - center.y, 2) + Math.pow(pos.z - center.z, 2)) <= radius;
    }

    public static boolean isPosInCube(BlockPos pos, BlockPos center, int radius) {
        return Math.abs(pos.getX() - center.getX()) <= radius && Math.abs(pos.getY() - center.getY()) <= radius && Math.abs(pos.getZ() - center.getZ()) <= radius;
    }

    public static boolean isPosInCube(BlockPos pos, BlockPos center, float radius) {
        return Math.abs(pos.getX() - center.getX()) <= radius && Math.abs(pos.getY() - center.getY()) <= radius && Math.abs(pos.getZ() - center.getZ()) <= radius;
    }

    public static boolean isPosInCube(Vec3d pos, Vec3d center, int radius) {
        return Math.abs(pos.x - center.x) <= radius && Math.abs(pos.y - center.y) <= radius && Math.abs(pos.z - center.z) <= radius;
    }

    public static boolean isPosInCube(Vec3d pos, Vec3d center, float radius) {
        return Math.abs(pos.x - center.x) <= radius && Math.abs(pos.y - center.y) <= radius && Math.abs(pos.z - center.z) <= radius;
    }

    public static boolean isPosInCube(Vec3d pos, Vec3d center, double radius) {
        return Math.abs(pos.x - center.x) <= radius && Math.abs(pos.y - center.y) <= radius && Math.abs(pos.z - center.z) <= radius;
    }

    public static boolean isPosInCylinder(BlockPos pos, BlockPos center, int radius, int height) {
        return Math.sqrt(Math.pow(pos.getX() - center.getX(), 2) + Math.pow(pos.getZ() - center.getZ(), 2)) <= radius && Math.abs(pos.getY() - center.getY()) <= height;
    }

    public static boolean isPosInCylinder(BlockPos pos, BlockPos center, float radius, float height) {
        return Math.sqrt(Math.pow(pos.getX() - center.getX(), 2) + Math.pow(pos.getZ() - center.getZ(), 2)) <= radius && Math.abs(pos.getY() - center.getY()) <= height;
    }

    public static boolean isPosInCylinder(Vec3d pos, Vec3d center, int radius, int height) {
        return Math.sqrt(Math.pow(pos.x - center.x, 2) + Math.pow(pos.z - center.z, 2)) <= radius && Math.abs(pos.y - center.y) <= height;
    }

    public static boolean isPosInCylinder(Vec3d pos, Vec3d center, float radius, float height) {
        return Math.sqrt(Math.pow(pos.x - center.x, 2) + Math.pow(pos.z - center.z, 2)) <= radius && Math.abs(pos.y - center.y) <= height;
    }

    public static List<BlockPos> getBlocksInBox(BlockPos start, BlockPos end) {
        List<BlockPos> blocks = new ArrayList<>();
        for (int x = start.getX(); x <= end.getX(); x++) {
            for (int y = start.getY(); y <= end.getY(); y++) {
                for (int z = start.getZ(); z <= end.getZ(); z++) {
                    blocks.add(new BlockPos(x, y, z));
                }
            }
        }
        return blocks;
    }

    public static List<BlockPos> getBlocksInAABB(AxisAlignedBB box) {
        List<BlockPos> blocks = new ArrayList<>();
        for (int x = (int) box.minX; x <= box.maxX; x++) {
            for (int y = (int) box.minY; y <= box.maxY; y++) {
                for (int z = (int) box.minZ; z <= box.maxZ; z++) {
                    blocks.add(new BlockPos(x, y, z));
                }
            }
        }
        return blocks;
    }

    public static List<BlockPos> getBlocksInSphere(BlockPos center, int radius) {
        List<BlockPos> blocks = new ArrayList<>();
        for (int x = center.getX() - radius; x <= center.getX() + radius; x++) {
            for (int y = center.getY() - radius; y <= center.getY() + radius; y++) {
                for (int z = center.getZ() - radius; z <= center.getZ() + radius; z++) {
                    if (isPosInSphere(new BlockPos(x, y, z), center, radius)) {
                        blocks.add(new BlockPos(x, y, z));
                    }
                }
            }
        }
        return blocks;
    }

    public static BlockPos parseBlockPos(String x, String y, String z) {
        int xC;
        int yC;
        int zC;
        if (x.startsWith("~")) {
            xC = (int) mc.player.posX + (x.equals("~") ? 0 : Integer.parseInt(x.replace("~", "")));
        } else {
            xC = Integer.parseInt(x);
        }
        if (y.startsWith("~")) {
            yC = (int) mc.player.posY + (y.equals("~") ? 0 : Integer.parseInt(y.replace("~", "")));
        } else {
            yC = Integer.parseInt(y);
        }
        if (z.startsWith("~")) {
            zC = (int) mc.player.posZ + (z.equals("~") ? 0 : Integer.parseInt(z.replace("~", "")));
        } else {
            zC = Integer.parseInt(z);
        }
        return new BlockPos(xC, yC, zC);
    }

    public static String[] blocks() {
        //return all mincecraft blocks out of Block.REGISTRY
        return Block.REGISTRY.getKeys().stream().map(ResourceLocation::toString).toArray(String[]::new);
    }

    public static String[] blocks2() {
        //return all mincecraft blocks out of Block.REGISTRY and remove "minecraft:"
        return Block.REGISTRY.getKeys().stream().map(ResourceLocation::toString).map(s -> s.replace("minecraft:", "")).toArray(String[]::new);
    }

    public static void iHateAnnotations() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (String s : blocks2()) {
            sb.append("\"").append(s).append("\", ");
        }
        sb.append("}");
        System.out.println(sb);
    }

}

