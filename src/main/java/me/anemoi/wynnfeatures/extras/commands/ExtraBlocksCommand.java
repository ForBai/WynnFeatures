package me.anemoi.wynnfeatures.extras.commands;

import me.anemoi.wynnfeatures.extras.Extras;
import me.anemoi.wynnfeatures.extras.api.ExtraBlock;
import me.anemoi.wynnfeatures.utils.BlockUtils;
import me.anemoi.wynnfeatures.utils.Utils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

import java.util.Arrays;
import java.util.List;

import static me.anemoi.wynnfeatures.WynnFeatures.mc;

public class ExtraBlocksCommand extends CommandBase {
    @Override
    public String getName() {
        return "extrablocks";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        //generate me a full usage message for the implemanted commands with a description(explanation)
        StringBuilder usage = new StringBuilder();
        usage.append("Usage: /extrablocks <cmd> <args>\n");
        usage.append("Commands:\n");
        usage.append("setBlock <block> <x> <y> <z> - sets a block at the given position\n");
        usage.append("setBlock <block> - sets a block at the position of the player\n");
        usage.append("fill <block> <x1> <y1> <z1> <x2> <y2> <z2> - fills the given area with the given block\n");
        usage.append("fill <block> <x1> <y1> <z1> - fills the given area with the given block from the position of the player\n");
        usage.append("fill remove/none/r <x1> <y1> <z1> <x2> <y2> <z2> - removes all the extra blocks in the given area\n");
        usage.append("fill remove/none/r <x1> <y1> <z1> - removes all the extra blocks in the given area from the position of the player\n");
        usage.append("replacenear <block> <block> <range> - replaces all the blocks in the given range with the given block from the position of the player\n");
        usage.append("replacenear <block> <block> <x> <y> <z> <range> - replaces all the blocks in the given range with the given block from the given position\n");
        usage.append("replacenear remove/none/r <block> <range> - removes all the extra blocks in the given range but only if they are the given block from the position of the player\n");
        usage.append("replacenear remove/none/r <block> <x> <y> <z> <range> - removes all the extra blocks in the given range but only if they are the given block from the given position\n");
        usage.append("removenear <amount> - removes the nearest <amount> extra blocks from the position of the player\n");
        return "/" + getName() + " <stuff>";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("em", "extrab");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        List<String> argsList = Arrays.asList(args);
        if (argsList.isEmpty()) {
            Utils.sendMessage("Entering Edit Mode! to exit type \"/em\"");
            Utils.sendMessage("Not Implemented Yet");
            return;
        }

        if (argsList.get(0).equalsIgnoreCase("set") || argsList.get(0).equalsIgnoreCase("setBlock")) {
            //example would be /em setBlock minecraft:stained_glass[color=white] 0 0 0
            //or /em setBlock minecraft:stained_glass[color=white]
            //the second example uses the position of the player
            if (argsList.get(1).startsWith("minecraft:")) {
                if (argsList.size() == 5) {
                    IBlockState block = BlockUtils.getStateFromString(argsList.get(1));
                    int x = Integer.parseInt(argsList.get(2));
                    int y = Integer.parseInt(argsList.get(3));
                    int z = Integer.parseInt(argsList.get(4));
                    Extras.addBlock(new ExtraBlock(block, new BlockPos(x, y, z)));
                } else if (argsList.size() == 2) {
                    IBlockState block = BlockUtils.getStateFromString(argsList.get(1));
                    BlockPos pos = new BlockPos(sender.getPositionVector());
                    Extras.addBlock(new ExtraBlock(block, pos));
                } else {
                    Utils.sendMessage("Please give all the required arguments!");
                    return;
                }
            } else if (argsList.get(1).startsWith("remove") || argsList.get(1).startsWith("none") || argsList.get(1).startsWith("r")) {
                if (argsList.size() == 5) {
                    IBlockState block = BlockUtils.getStateFromString(argsList.get(1));
                    int x = Integer.parseInt(argsList.get(2));
                    int y = Integer.parseInt(argsList.get(3));
                    int z = Integer.parseInt(argsList.get(4));
                    Extras.removeBlock(new ExtraBlock(block, new BlockPos(x, y, z)));
                } else if (argsList.size() == 2) {
                    IBlockState block = BlockUtils.getStateFromString(argsList.get(1));
                    BlockPos pos = new BlockPos(sender.getPositionVector());
                    Extras.removeBlock(new ExtraBlock(block, pos));
                } else {
                    Utils.sendMessage("Please give all the required arguments!");
                    return;
                }
            } else {
                Utils.sendMessage("Please specify a valid block");
                return;
            }
        } else if (argsList.get(0).equalsIgnoreCase("fill")) {
            //example would be /em fill minecraft:stained_glass[color=white] 0 0 0 2 2 2
            //or /em fill minecraft:stained_glass[color=white] 2 2 2
            //the second example uses the position of the player
            if (argsList.get(1).startsWith("minecraft:")) {
                if (argsList.size() == 5) {
                    IBlockState block = BlockUtils.getStateFromString(argsList.get(1));
                    BlockPos pos = new BlockPos(sender.getPositionVector());
                    List<BlockPos> posList = BlockUtils.getBlocksInBox(pos, BlockUtils.parseBlockPos(argsList.get(2), argsList.get(3), argsList.get(4)));
                    posList.forEach(blockPos -> Extras.addBlock(new ExtraBlock(block, blockPos)));
                } else if (argsList.size() == 8) {
                    IBlockState block = BlockUtils.getStateFromString(argsList.get(1));
                    List<BlockPos> posList = BlockUtils.getBlocksInBox(BlockUtils.parseBlockPos(argsList.get(2), argsList.get(3), argsList.get(4)), BlockUtils.parseBlockPos(argsList.get(5), argsList.get(6), argsList.get(7)));
                    posList.forEach(blockPos -> Extras.addBlock(new ExtraBlock(block, blockPos)));
                } else {
                    Utils.sendMessage("Please give all the required arguments!");
                    return;
                }
            } else if (argsList.get(1).equalsIgnoreCase("remove") || argsList.get(1).equalsIgnoreCase("none") || argsList.get(1).equalsIgnoreCase("r")) {
                if (argsList.size() == 5) {
                    IBlockState block = BlockUtils.getStateFromString(argsList.get(1));
                    int x = Integer.parseInt(argsList.get(2));
                    int y = Integer.parseInt(argsList.get(3));
                    int z = Integer.parseInt(argsList.get(4));
                    Extras.removeBlock(new ExtraBlock(block, new BlockPos(x, y, z)));
                } else if (argsList.size() == 2) {
                    IBlockState block = BlockUtils.getStateFromString(argsList.get(1));
                    BlockPos pos = new BlockPos(sender.getPositionVector());
                    Extras.removeBlock(new ExtraBlock(block, pos));
                } else {
                    Utils.sendMessage("Please give all the required arguments!");
                    return;
                }
            } else {
                Utils.sendMessage("Please specify a valid block");
                return;
            }
        } else if (argsList.get(0).equalsIgnoreCase("replacenear")) {
            //example would be /em replacenear minecraft:air minecraft:stained_glass[color=white] 15
            //or /em replacenear minecraft:air minecraft:stained_glass[color=white] 0 0 0 15
            //this would replace all air blocks within a 15 block radius with white stained-glass
            //the second example uses the given position
            if (argsList.get(1).startsWith("minecraft:") && argsList.get(2).startsWith("minecraft:")) {
                if (argsList.size() == 4) {
                    IBlockState block = BlockUtils.getStateFromString(argsList.get(1));
                    IBlockState replaceBlock = BlockUtils.getStateFromString(argsList.get(2));
                    int radius = Integer.parseInt(argsList.get(3));
                    BlockPos pos = new BlockPos(sender.getPositionVector());
                    List<BlockPos> posList = BlockUtils.getBlocksInSphere(pos, radius);
                    posList.forEach(blockPos -> {
                        if (mc.world.getBlockState(blockPos) == block) {
                            Extras.addBlock(new ExtraBlock(replaceBlock, blockPos));
                        }
                    });
                } else if (argsList.size() == 7) {
                    IBlockState block = BlockUtils.getStateFromString(argsList.get(1));
                    IBlockState replaceBlock = BlockUtils.getStateFromString(argsList.get(2));
                    BlockPos pos = BlockUtils.parseBlockPos(argsList.get(3), argsList.get(4), argsList.get(5));
                    int radius = Integer.parseInt(argsList.get(6));
                    List<BlockPos> posList = BlockUtils.getBlocksInBox(pos.subtract(new Vec3i(radius, radius, radius)), pos.add(radius, radius, radius));
                    posList.forEach(blockPos -> {
                        if (mc.world.getBlockState(blockPos) == block) {
                            Extras.addBlock(new ExtraBlock(replaceBlock, blockPos));
                        }
                    });
                } else {
                    Utils.sendMessage("Please give all the required arguments!");
                    return;
                }
            } else if ((argsList.get(1).startsWith("remove") || argsList.get(1).equalsIgnoreCase("none") || argsList.get(1).equalsIgnoreCase("r")) && argsList.get(2).startsWith("minecraft:")) {
                if (argsList.size() == 4) {
                    IBlockState block = BlockUtils.getStateFromString(argsList.get(2));
                    int radius = Integer.parseInt(argsList.get(3));
                    BlockPos pos = new BlockPos(sender.getPositionVector());
                    List<BlockPos> posList = BlockUtils.getBlocksInSphere(pos, radius);
                    posList.forEach(blockPos -> {
                        if (mc.world.getBlockState(blockPos) == block) {
                            Extras.removeBlock(new ExtraBlock(block, blockPos));
                        }
                    });
                } else if (argsList.size() == 7) {
                    IBlockState block = BlockUtils.getStateFromString(argsList.get(2));
                    BlockPos pos = BlockUtils.parseBlockPos(argsList.get(3), argsList.get(4), argsList.get(5));
                    int radius = Integer.parseInt(argsList.get(6));
                    List<BlockPos> posList = BlockUtils.getBlocksInBox(pos.subtract(new Vec3i(radius, radius, radius)), pos.add(radius, radius, radius));
                    posList.forEach(blockPos -> {
                        if (mc.world.getBlockState(blockPos) == block) {
                            Extras.removeBlock(new ExtraBlock(block, blockPos));
                        }
                    });
                } else {
                    Utils.sendMessage("Please give all the required arguments!");
                    return;
                }
            } else {
                Utils.sendMessage("Please specify a valid block");
                return;
            }
        } else if (argsList.get(0).equalsIgnoreCase("removenear")){
            //removes the nearest extra block you can also set an amount of blocks to remove
            if (argsList.size() == 1) {
                Extras.removeBlock(Extras.getNearestBlock(32));
            } else if (argsList.size() == 2) {
                int amount = Integer.parseInt(argsList.get(1));
                for (int i = 0; i < amount; i++) {
                    Extras.removeBlock(Extras.getNearestBlock(32));
                }
            } else {
                Utils.sendMessage("Please give all the required arguments!");
                return;
            }
        }else {
            Utils.sendMessage("Please specify a valid command!");
            return;
        }
    }
}
