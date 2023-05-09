package me.anemoi.wynnfeatures.utils;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;

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
        return (propertyString.toString()).equals("") ? blockID : blockID +"[" + propertyString + "]";
    }
}
