package net.lmor.botanicalextramachinery.blocks.greenhouse;

import de.melanx.botanicalmachinery.blocks.base.BotanicalTile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.moddingx.libx.menu.BlockEntityMenu;

import javax.annotation.Nonnull;

public class ContainerGreenhouse extends BlockEntityMenu<BlockEntityGreenhouse> {

    static int INVENTORY = 35;

    public ContainerGreenhouse(@Nullable MenuType<? extends BlockEntityMenu<?>> type, int windowId, Level level, BlockPos pos, Inventory playerContainer, Player player) {
        super(type, windowId, level, pos, playerContainer, player, 35, INVENTORY);
        IItemHandlerModifiable inventory = ((BotanicalTile)this.blockEntity).getInventory();

        int index = this.addSlotBox(inventory, 0, 30, 23, 7, 18, 1, 18);
        this.addSlotBox(inventory, index, 30, 44, 7, 18, 4, 18);

        this.layoutPlayerInventorySlots(12, 153);
    }

    @Nonnull
    public ItemStack quickMoveStack(@Nonnull Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot)this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();

            if (index < (INVENTORY - 1)) {
                if (!this.moveItemStackTo(stack, (INVENTORY - 1), 36 + (INVENTORY - 1), true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(stack, itemstack);
            } else {
                if (!this.moveItemStackTo(stack, 0, (INVENTORY - 1), false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack);
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        if (level != null && level.getBlockState(pos).isAir()) {
            player.closeContainer();
            return false;
        }
        return super.stillValid(player);
    }
}
