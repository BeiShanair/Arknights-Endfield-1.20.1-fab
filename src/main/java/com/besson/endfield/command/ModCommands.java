package com.besson.endfield.command;

import com.besson.endfield.utils.storage.GlobalStorageManager;
import com.besson.endfield.utils.storage.StorageState;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.ItemStackArgument;
import net.minecraft.command.argument.ItemStackArgumentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

import java.util.stream.IntStream;

public class ModCommands {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess) {
        dispatcher.register(CommandManager.literal("storage")
                .then(CommandManager.literal("deposit")
                        .then(CommandManager.argument(
                                "item", ItemStackArgumentType.itemStack(commandRegistryAccess))
                            .then(CommandManager.argument(
                                    "amount", IntegerArgumentType.integer(1))
                                        .executes(ModCommands::deposit)
                            )
                        )
                )
                .then(CommandManager.literal("withdraw")
                        .then(CommandManager.argument(
                                "item", ItemStackArgumentType.itemStack(commandRegistryAccess))
                            .then(CommandManager.argument(
                                    "amount", IntegerArgumentType.integer(1))
                                    .executes(ModCommands::withdraw)
                            )
                        )
                )
                .then(CommandManager.literal("setcap")
                        .requires(s -> s.hasPermissionLevel(2))
                        .then(CommandManager.argument(
                                "amount", LongArgumentType.longArg(1))
                                .executes(ModCommands::setCap)
                        )
                )
        );
    }

    private static int deposit(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {

        ServerPlayerEntity player = ctx.getSource().getPlayer();

        ItemStackArgument itemArg = ItemStackArgumentType.getItemStackArgument(ctx, "item");
        int amount = IntegerArgumentType.getInteger(ctx, "amount");

        Item item = itemArg.getItem();
        
        int availableAmount = IntStream.range(0, player.getInventory().size()).mapToObj(i -> player.getInventory().getStack(i)).filter(stack -> !stack.isEmpty() && stack.getItem() == item).mapToInt(ItemStack::getCount).sum();

        if (availableAmount < amount) {
            ctx.getSource().sendFeedback(
                    () -> Text.translatable("commands.endfield.deposit.warn", availableAmount, amount),
                    false
            );
            return 0;
        }
        
        int remainingToRemove = amount;
        for (int i = 0; i < player.getInventory().size() && remainingToRemove > 0; i++) {
            ItemStack stack = player.getInventory().getStack(i);
            if (!stack.isEmpty() && stack.getItem() == item) {
                int removeCount = Math.min(stack.getCount(), remainingToRemove);
                stack.decrement(removeCount);
                remainingToRemove -= removeCount;
                if (stack.isEmpty()) {
                    player.getInventory().setStack(i, ItemStack.EMPTY);
                }
            }
        }

        GlobalStorageManager manager = new GlobalStorageManager(player.getServerWorld());

        ItemStack stack = itemArg.createStack(amount, false);
        long inserted = manager.insert(stack);

        ctx.getSource().sendFeedback(
                () -> Text.translatable("commands.endfield.deposit", inserted),
                false
        );

        return 1;
    }

    private static int withdraw(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {

        ServerPlayerEntity player = ctx.getSource().getPlayer();

        ItemStackArgument itemArg = ItemStackArgumentType.getItemStackArgument(ctx, "item");
        int amount = IntegerArgumentType.getInteger(ctx, "amount");

        Item item = itemArg.getItem();
        GlobalStorageManager manager = new GlobalStorageManager(player.getServerWorld());
        ItemStack extracted = manager.extract(item, amount);
        int inserted = extracted.getCount();
        player.getInventory().insertStack(extracted);
        ctx.getSource().sendFeedback(
                () -> Text.translatable("commands.endfield.withdraw", inserted),
                false
        );

        return 1;
    }

    private static int setCap(CommandContext<ServerCommandSource> ctx) {
        long cap = LongArgumentType.getLong(ctx, "amount");
        if (cap < 0) {
            ctx.getSource().sendError(Text.translatable("commands.endfield.cap.negative"));
            return 0;
        }
        ServerWorld world = ctx.getSource().getWorld();
        StorageState state = GlobalStorageManager.get(world).getState();
        state.setGlobalCapacity(cap);
        ctx.getSource().sendFeedback(
                () -> Text.translatable("commands.endfield.cap", cap),
                true
        );

        return 1;
    }
}
