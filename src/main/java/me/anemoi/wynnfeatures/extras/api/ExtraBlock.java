package me.anemoi.wynnfeatures.extras.api;

import me.anemoi.wynnfeatures.utils.BlockUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;

public class ExtraBlock {
    private IBlockState state;
    private BlockPos pos;

    public ExtraBlock(IBlockState state, BlockPos pos) {
        this.state = state;
        this.pos = pos;
    }

    public ExtraBlock(String state, BlockPos pos) {
        this.state = BlockUtils.getStateFromString(state);
        this.pos = pos;
    }

    public ExtraBlock(String state, int x, int y, int z) {
        this.state = BlockUtils.getStateFromString(state);
        this.pos = new BlockPos(x, y, z);
    }

    public IBlockState getState() {
        return state;
    }

    public void setState(IBlockState state) {
        this.state = state;
    }

    public BlockPos getPos() {
        return pos;
    }

    public void setPos(BlockPos pos) {
        this.pos = pos;
    }


}
