package com.besson.endfield.trade;

import com.besson.endfield.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.List;

public class SupplyTrade {
    public final ItemStack input;
    public final int amount;
    public final ItemStack reward;
    public final int ex;

    public SupplyTrade(ItemStack input, int amount, ItemStack reward, int ex) {
        this.input = input;
        this.amount = amount;
        this.reward = reward;
        this.ex = ex;
    }

    public static List<SupplyTrade> getTradesForLevel(int level) {
        List<SupplyTrade> list = new ArrayList<>();
        if (level >= 1) {
            list.add(new SupplyTrade(new ItemStack(ModItems.LC_BATTERY), 10, new ItemStack(Items.IRON_INGOT), 50));
        }
        if (level >= 2) {
            list.add(new SupplyTrade(new ItemStack(ModItems.SC_BATTERY), 15, new ItemStack(Items.DIAMOND), 80));
        }
        if (level >= 3) {
            list.add(new SupplyTrade(new ItemStack(ModItems.HC_BATTERY), 30, new ItemStack(Items.NETHERITE_INGOT), 100));
        }
        return list;
    }
}
