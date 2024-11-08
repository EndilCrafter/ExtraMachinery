package net.lmor.botanicalextramachinery.blocks.greenhouse;

import com.google.common.collect.Range;
import de.melanx.botanicalmachinery.blocks.base.BotanicalTile;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import net.lmor.botanicalextramachinery.config.LibXServerConfig;
import net.lmor.botanicalextramachinery.recipe.ExtraMachineryRecipeTypes;
import net.lmor.botanicalextramachinery.recipe.GreenhouseRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;
import org.moddingx.libx.inventory.BaseItemStackHandler;
import org.moddingx.libx.inventory.IAdvancedItemHandlerModifiable;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BlockEntityGreenhouse extends BotanicalTile  {

    private final BaseItemStackHandler inventory;

    private final int FIRST_INPUT_SLOT_FLOWERS = 0;
    private final int LAST_INPUT_SLOT_FLOWERS = 6;
    private final int FIRST_INPUT_SLOT_FUEL = 7;
    private final int LAST_INPUT_SLOT_FUEL = 34;

    private Map<GreenhouseRecipe, Integer> recipe;

    private List<Integer> fuelsSlotItem = new ArrayList<>();

    private int slotLimit = LibXServerConfig.GreenhouseSettings.slotLimit;
    private int sleep = LibXServerConfig.GreenhouseSettings.sleep;

    public BlockEntityGreenhouse(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, LibXServerConfig.GreenhouseSettings.manaStorage);

        inventory = BaseItemStackHandler.builder(LAST_INPUT_SLOT_FUEL + 1)
                .validator(this::isFlowerToAvailableFlowers, Range.closedOpen(FIRST_INPUT_SLOT_FLOWERS, LAST_INPUT_SLOT_FLOWERS + 1))
                .validator(this::isFuelToFlower, Range.closedOpen(FIRST_INPUT_SLOT_FUEL, LAST_INPUT_SLOT_FUEL + 1))
                .slotLimit(slotLimit, Range.closedOpen(FIRST_INPUT_SLOT_FLOWERS, LAST_INPUT_SLOT_FLOWERS + 1)).output()
                .contentsChanged(() -> {this.setChanged();this.setDispatchable();})
                .contentsChanged(this::addFuel)
                .build();
    }

    private void addFuel(int slot){
        System.out.println(slot);
        if (slot == LAST_INPUT_SLOT_FUEL){
            this.recipe = null;
        }

        if (slot >= FIRST_INPUT_SLOT_FUEL && slot <= LAST_INPUT_SLOT_FUEL){
            if (!this.getInventory().getStackInSlot(slot).isEmpty() && !fuelsSlotItem.contains(slot)){
                fuelsSlotItem.add(slot);
            }
            else if (this.getInventory().getStackInSlot(slot).isEmpty() && fuelsSlotItem.contains(slot)){
                fuelsSlotItem.remove((Object) slot);
            }
        }

        Collections.sort(fuelsSlotItem);

    }

    private boolean isFuelToFlower(ItemStack itemStack){
        if (this.level != null){
            for(GreenhouseRecipe rc: this.level.getRecipeManager().getAllRecipesFor(ExtraMachineryRecipeTypes.GREENHOUSE_TYPE)){
                for (ItemStack fuel: rc.getFuels()){
                    if (fuel.getItem() == itemStack.getItem()){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isFlowerToAvailableFlowers(ItemStack itemStack){
        if (this.level != null){
            for(GreenhouseRecipe rc: this.level.getRecipeManager().getAllRecipesFor(ExtraMachineryRecipeTypes.GREENHOUSE_TYPE)){
                if (rc.matches(itemStack)){
                    return true;
                }
            }
        }
        return false;
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
    public void tick() {
        if (this.level != null && !this.level.isClientSide()){

            if (sleep > 0){
                sleep--;
            }


            if (level == null || level.isClientSide() ||
                    fuelsSlotItem.size() == 0 || getCurrentMana() == getMaxMana() || sleep > 0) return;

            for (int slotFlower = FIRST_INPUT_SLOT_FLOWERS; slotFlower <= LAST_INPUT_SLOT_FLOWERS; slotFlower++){
                if (inventory.getStackInSlot(slotFlower).isEmpty()) continue;

                GreenhouseRecipe recipePre = null;
                ItemStack flower = inventory.getStackInSlot(slotFlower);
                for (GreenhouseRecipe rc: this.level.getRecipeManager().getAllRecipesFor(ExtraMachineryRecipeTypes.GREENHOUSE_TYPE)){
                    if (rc.matches(flower)){
                        recipePre = rc;
                        break;
                    }
                }

                if (recipePre == null) continue;

                ItemStack fuel = null;
                int countFuel = 0;
                int slotFuel = -1;

                for (Integer sf: fuelsSlotItem){
                    if (recipePre.ifFuelToRecipe(inventory.getStackInSlot(sf))){
                        fuel = getInventory().getStackInSlot(sf);
                        slotFuel = sf;
                        countFuel = fuel.getCount();
                        break;
                    }
                }

                if (fuel == null) continue;

                int countEat = getInventory().getStackInSlot(slotFlower).getCount();
                int manaAdd = recipePre.getManaToConsume(fuel);

                if (manaAdd + getCurrentMana() > getMaxMana()) break;
                else if (manaAdd + getCurrentMana() == getMaxMana()) countEat = 1;
                else {
                    while (countEat * manaAdd > getMaxMana() || countEat > countFuel){
                        countEat--;
                    }
                }

                countEat = Math.min(countEat, getInventory().getStackInSlot(slotFuel).getCount());

                IAdvancedItemHandlerModifiable inventory = this.getInventory().getUnrestricted();
                inventory.extractItem(slotFuel, countEat, false);
                this.receiveMana(countEat * manaAdd);
            }

            sleep = LibXServerConfig.GreenhouseSettings.sleep;
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
}
