package com.besson.endfield.recipe;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public record ReactorResource(Type type, ItemVariant itemVariant, FluidVariant fluidVariant) {
    public static ReactorResource item(ItemVariant variant) {
        return new ReactorResource(Type.ITEM, variant, FluidVariant.blank());
    }

    public static ReactorResource fluid(FluidVariant variant) {
        return new ReactorResource(Type.FLUID, ItemVariant.blank(), variant);
    }

    public boolean isItem() {
        return type == Type.ITEM;
    }

    public boolean isFluid() {
        return type == Type.FLUID;
    }

    public boolean isBlank() {
        return switch (type) {
            case ITEM -> itemVariant.isBlank();
            case FLUID -> fluidVariant.isBlank();
        };
    }

    public long getMaxAmount() {
        return switch (type) {
            case ITEM -> 64;
            case FLUID -> 50_000;
        };
    }

    public ItemStack asStack(int count) {
        return isItem() ? itemVariant.toStack(count) : ItemStack.EMPTY;
    }

    public NbtCompound toNbt() {
        NbtCompound nbt = new NbtCompound();
        nbt.putString("type", type.asString());
        if (isItem()) {
            nbt.put("variant", itemVariant.toNbt());
        } else {
            nbt.put("variant", fluidVariant.toNbt());
        }
        return nbt;
    }

    public static ReactorResource fromNbt(NbtCompound nbt) {
        Type type = Type.fromString(nbt.getString("type"));
        return switch (type) {
            case ITEM -> item(ItemVariant.fromNbt(nbt.getCompound("variant")));
            case FLUID -> fluid(FluidVariant.fromNbt(nbt.getCompound("variant")));
        };
    }

    public enum Type {
        ITEM("item"),
        FLUID("fluid");

        private final String id;

        Type(String id) {
            this.id = id;
        }

        public String asString() {
            return id;
        }

        public static Type fromString(String id) {
            for (Type type : values()) {
                if (type.id.equals(id)) return type;
            }
            return ITEM;
        }
    }
}
