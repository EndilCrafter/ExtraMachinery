package net.lmor.botanicalextramachinery.recipe.InterfaceRecipe;

import net.lmor.botanicalextramachinery.ExtraMachinery;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.recipe.StateIngredient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface GreenhouseRecipe extends Recipe<Container> {
    ResourceLocation TYPE_ID = new ResourceLocation(ExtraMachinery.MOD_ID, "greenhouse");

    boolean matches(ItemStack var1);

    @NotNull List<ItemStack> getFuels();

    default @NotNull List<ItemStack> getRecipeFuels(@NotNull ItemStack input) {
        List<ItemStack> fuels = new ArrayList<>();
        for (ItemStack fuel: this.getFuels()){
            fuels.add(fuel);
        }

        return fuels;
    }

    int getManaToConsume(ItemStack fuel);

    Map<ItemStack, Integer> getMapMana();

    default @NotNull RecipeType<?> getType() {
        return (RecipeType) Registry.RECIPE_TYPE.getOptional(TYPE_ID).get();
    }

    default @NotNull ItemStack assemble(@NotNull Container inv) {
        return ItemStack.EMPTY;
    }

    default boolean matches(@NotNull Container inv, @NotNull Level world) {
        return false;
    }

    default boolean isSpecial() {
        return true;
    }
}
