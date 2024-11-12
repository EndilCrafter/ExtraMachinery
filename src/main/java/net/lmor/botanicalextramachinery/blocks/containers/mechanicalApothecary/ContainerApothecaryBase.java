package net.lmor.botanicalextramachinery.blocks.containers.mechanicalApothecary;

import de.melanx.botanicalmachinery.helper.UnrestrictedOutputSlot;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalApothecary.BlockEntityApothecaryBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.moddingx.libx.menu.BlockEntityMenu;

public class ContainerApothecaryBase extends BlockEntityMenu<BlockEntityApothecaryBase> {
    public ContainerApothecaryBase(MenuType<? extends BlockEntityMenu<?>> type, int windowId, Level level, BlockPos pos, Inventory playerContainer, Player player) {
        super(type, windowId, level, pos, playerContainer, player, 10, 19);
        IItemHandlerModifiable inventory = (this.blockEntity).getInventory();

        this.addSlot(new SlotItemHandler(inventory, 0, 84, 84));

        int index = this.addSlotBox(inventory, 1, 24, 24, 3, 18, 3, 18);
        this.addSlotBox(inventory, index, 109, 24, 3, 18, 3, 18, UnrestrictedOutputSlot::new);
        this.layoutPlayerInventorySlots(12, 124);
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
