package net.lmor.botanicalextramachinery.blocks.greenhouse;

import com.google.common.collect.Range;
import de.melanx.botanicalmachinery.blocks.base.BotanicalTile;
import net.lmor.botanicalextramachinery.config.LibXServerConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.moddingx.libx.inventory.BaseItemStackHandler;
import org.moddingx.libx.inventory.IAdvancedItemHandlerModifiable;
import vazkii.botania.api.block_entity.BindableSpecialFlowerBlockEntity;
import vazkii.botania.api.block_entity.GeneratingFlowerBlockEntity;
import vazkii.botania.api.mana.ManaCollector;
import vazkii.botania.common.block.BotaniaFlowerBlocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BlockEntityGreenhouse extends BotanicalTile {

    private final BaseItemStackHandler inventory;

    private final int FIRST_INPUT_SLOT_FLOWERS = 0;
    private final int LAST_INPUT_SLOT_FLOWERS = 6;
    private final int FIRST_INPUT_SLOT_FUEL = 7;
    private final int LAST_INPUT_SLOT_FUEL = 34;

    private int slotLimit = LibXServerConfig.GreenhouseSettings.slotLimit;

    private List<Block> availableFlowers = new ArrayList<>();

    Map<Item, Map<Item, Integer>> flowerFuelMap = new HashMap<>();

    public BlockEntityGreenhouse(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, LibXServerConfig.GreenhouseSettings.manaStorage);

        defaultGenAvailableFlowers();

        inventory = BaseItemStackHandler.builder(LAST_INPUT_SLOT_FUEL + 1)
                .validator(this::isFlowerToAvailableFlowers, Range.closedOpen(FIRST_INPUT_SLOT_FLOWERS, LAST_INPUT_SLOT_FLOWERS + 1))
                .validator(this::isFuelToFlower, Range.closedOpen(FIRST_INPUT_SLOT_FUEL, LAST_INPUT_SLOT_FUEL + 1))
                .slotLimit(slotLimit, Range.closedOpen(FIRST_INPUT_SLOT_FLOWERS, LAST_INPUT_SLOT_FLOWERS + 1)).output()
                .contentsChanged(() -> {this.setChanged();this.setDispatchable();})
                .build();

    }

    private boolean isFuelToFlower(ItemStack itemStack){
        if (level == null || level.isClientSide()) return false;
        for (Block block: availableFlowers){
            if (itemStack.getItem().asItem() == block.getStateDefinition().getOwner().asItem()) return false;
        }
        return true;
    }

    private boolean isFlowerToAvailableFlowers(ItemStack itemStack){
        if (level == null || level.isClientSide()) return false;

        ItemStack flowerSlot = ItemStack.EMPTY;
        for (int i = FIRST_INPUT_SLOT_FLOWERS; i <= LAST_INPUT_SLOT_FLOWERS; i++){
            if (!this.inventory.getStackInSlot(i).isEmpty()){
                flowerSlot = this.inventory.getStackInSlot(i);
                break;
            }
        }

        if (flowerSlot.isEmpty()){
            for (Block block: availableFlowers){
                System.out.println("---------------------------------------");
                System.out.println(block.getStateDefinition().getOwner().asItem());
                System.out.println(itemStack.getItem().asItem());

                if (itemStack.getItem().asItem() == block.getStateDefinition().getOwner().asItem()){
                    return true;
                }
            }
            return false;
        } else{
            return itemStack.getItem().asItem() == flowerSlot.getItem().asItem();
        }
    }

    private void defaultGenAvailableFlowers(){
        addFlowers(BotaniaFlowerBlocks.endoflameFloating);
        addFlowers(BotaniaFlowerBlocks.entropinnyumFloating);
        addFlowers(BotaniaFlowerBlocks.gourmaryllisFloating);
        addFlowers(BotaniaFlowerBlocks.kekimurusFloating);
        addFlowers(BotaniaFlowerBlocks.munchdewFloating);
        addFlowers(BotaniaFlowerBlocks.narslimmusFloating);
        addFlowers(BotaniaFlowerBlocks.rosaArcanaFloating);
    }

    public void addFlowers(Block flower){
        availableFlowers.add(flower);
    }

    public void drops(){
        if (level == null) return;

        IAdvancedItemHandlerModifiable inventory = this.getInventory().getUnrestricted();
        for (int i = 0; i < inventory.getSlots(); i++){
            ItemStack itemStack = inventory.getStackInSlot(i);
            if (itemStack.isEmpty()) continue;
            ItemEntity ie = new ItemEntity(this.level, (double)this.worldPosition.getX() + 0.5, (double)this.worldPosition.getY() + 0.7, (double)this.worldPosition.getZ() + 0.5, itemStack.copy());
            this.level.addFreshEntity(ie);
        }
    }

    @Override
    protected Predicate<Integer> getExtracts(Supplier<IItemHandlerModifiable> supplier) {
        return null;
    }

    @NotNull
    @Override
    public BaseItemStackHandler getInventory() {
        return this.inventory;
    }

    @Override
    public int getComparatorOutput() {
        return 0;
    }

    @Override
    public void tick() {

    }
}
