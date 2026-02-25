package com.besson.endfield.utils;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.world.PersistentState;

import java.util.HashSet;
import java.util.Set;

public class NodeState extends PersistentState {
    public Set<NodeEntry> nodeEntries = new HashSet<>();
    
    public NodeState() {}
    
    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        NbtList list = new NbtList();
        for (NodeEntry entry : nodeEntries) {
            list.add(entry.writeNbt());
        }
        nbt.put("nodes", list);
        return nbt;
    }
    
    public static NodeState fromNbt(NbtCompound nbt) {
        NodeState state = new NodeState();
        NbtList list = nbt.getList("nodes", NbtElement.COMPOUND_TYPE);
        for (int i = 0; i < list.size(); i++) {
            state.nodeEntries.add(NodeEntry.fromNbt(list.getCompound(i)));
        }
        return state;
    }
}

