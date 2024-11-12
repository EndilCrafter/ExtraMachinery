package net.lmor.botanicalextramachinery.blocks.containers.mechanicalIndustrialAgglomerationFactory;

import de.melanx.botanicalmachinery.helper.UnrestrictedOutputSlot;
import io.github.noeppi_noeppi.libx.menu.BlockEntityMenu;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalIndustrialAgglomerationFactory.BlockEntityIndustrialAgglomerationFactoryAdvanced;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class ContainerIndustrialAgglomerationFactoryAdvanced extends BlockEntityMenu<BlockEntityIndustrialAgglomerationFactoryAdvanced> {
    public ContainerIndustrialAgglomerationFactoryAdvanced(MenuType<? extends BlockEntityMenu<?>> type, int windowId, Level level, BlockPos pos, Inventory playerContainer, Player player) {
        super(type, windowId, level, pos, playerContainer, player, 9, 15);
        IItemHandlerModifiable inventory = (this.blockEntity).getInventory();

        this.addSlot(new SlotItemHandler(inventory, 0, 9, 58));
        this.addSlot(new SlotItemHandler(inventory, 1, 158, 58));

        int index = this.addSlotBox(inventory, 2, 30, 22, 7, 18, 1, 18);
        this.addSlotBox(inventory, index, 39, 58, 6, 18, 1, 18, UnrestrictedOutputSlot::new);

        this.layoutPlayerInventorySlots(12, 101);
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
