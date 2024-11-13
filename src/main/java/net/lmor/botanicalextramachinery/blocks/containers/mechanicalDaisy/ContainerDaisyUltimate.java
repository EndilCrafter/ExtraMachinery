package net.lmor.botanicalextramachinery.blocks.containers.mechanicalDaisy;

import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalDaisy.BlockEntityDaisyUltimate;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.moddingx.libx.menu.BlockEntityMenu;

import javax.annotation.Nonnull;

public class ContainerDaisyUltimate extends BlockEntityMenu<BlockEntityDaisyUltimate> {
    private static final int SIZE_INVENTORY = 18;

    public ContainerDaisyUltimate(MenuType<? extends BlockEntityMenu<?>> type, int windowId, Level level, BlockPos pos, Inventory playerContainer, Player player) {
        super(type, windowId, level, pos, playerContainer, player, SIZE_INVENTORY + 1, SIZE_INVENTORY + 1);

        IItemHandlerModifiable inventory = this.blockEntity.getInventory();

        this.addSlot(new SlotItemHandler(inventory, 0, 84, 35));
        this.addSlot(new SlotItemHandler(inventory, 1, 106, 41));
        this.addSlot(new SlotItemHandler(inventory, 2, 112, 63));
        this.addSlot(new SlotItemHandler(inventory, 3, 106, 85));
        this.addSlot(new SlotItemHandler(inventory, 4, 84, 91));
        this.addSlot(new SlotItemHandler(inventory, 5, 62, 85));
        this.addSlot(new SlotItemHandler(inventory, 6, 56, 63));
        this.addSlot(new SlotItemHandler(inventory, 7, 62, 41));
        this.addSlot(new SlotItemHandler(inventory, 8, 130, 41));
        this.addSlot(new SlotItemHandler(inventory, 9, 130, 85));
        this.addSlot(new SlotItemHandler(inventory, 10, 38, 41));
        this.addSlot(new SlotItemHandler(inventory, 11, 38, 85));
        this.addSlot(new SlotItemHandler(inventory, 12, 140, 63));
        this.addSlot(new SlotItemHandler(inventory, 13, 28, 63));
        this.addSlot(new SlotItemHandler(inventory, 14, 106, 20));
        this.addSlot(new SlotItemHandler(inventory, 15, 106, 106));
        this.addSlot(new SlotItemHandler(inventory, 16, 62, 20));
        this.addSlot(new SlotItemHandler(inventory, 17, 62, 106));

        this.addSlot(new SlotItemHandler(this.blockEntity.getInventoryUpgrade(), 0, 154, 106));

        this.layoutPlayerInventorySlots(12, 135);
    }

    @Nonnull
    public ItemStack quickMoveStack(@Nonnull Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot)this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();

            if (index < (SIZE_INVENTORY + 1)) {
                if (!this.moveItemStackTo(stack, SIZE_INVENTORY, 36 + (SIZE_INVENTORY + 1), true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(stack, itemstack);
            } else {
                if (!this.moveItemStackTo(stack, 0, (SIZE_INVENTORY + 1), false)) {
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
