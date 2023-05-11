package me.anemoi.wynnfeatures.utils;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;

import static me.anemoi.wynnfeatures.WynnFeatures.mc;

public class BlockUtils {
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
            xC = (int) mc.player.posX + Integer.parseInt(x.replace("~", ""));
        } else {
            xC = Integer.parseInt(x);
        }
        if (y.startsWith("~")) {
            yC = (int) mc.player.posY + Integer.parseInt(y.replace("~", ""));
        } else {
            yC = Integer.parseInt(y);
        }
        if (z.startsWith("~")) {
            zC = (int) mc.player.posZ + Integer.parseInt(z.replace("~", ""));
        } else {
            zC = Integer.parseInt(z);
        }
        return new BlockPos(xC, yC, zC);
    }

}
