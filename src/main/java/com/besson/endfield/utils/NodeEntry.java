package com.besson.endfield.utils;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public record NodeEntry(BlockPos pos, NodeType type) {
    public NbtCompound writeNbt() {
        NbtCompound nbt = new NbtCompound();
        nbt.putLong("pos", pos.asLong());
        nbt.putString("type", type.name());
        return nbt;
    }
    
    public static NodeEntry fromNbt(NbtCompound nbt) {
        BlockPos pos = BlockPos.fromLong(nbt.getLong("pos"));
        NodeType type = NodeType.valueOf(nbt.getString("type"));
        return new NodeEntry(pos, type);
    }
}
