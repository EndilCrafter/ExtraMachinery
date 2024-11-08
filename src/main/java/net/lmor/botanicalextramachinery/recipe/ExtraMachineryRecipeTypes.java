package net.lmor.botanicalextramachinery.recipe;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.Map;
import java.util.function.BiConsumer;

public class ExtraMachineryRecipeTypes {

    public static final RecipeType<GreenhouseRecipe> GREENHOUSE_TYPE = new ModRecipeType<>();
    public static final RecipeSerializer<GreenhouseRecipe> GREENHOUSE_SERIALIZER = new GreenhouseRecipe.Serializer();

    public static void submitRecipeTypes(BiConsumer<RecipeType<?>, ResourceLocation> r) {
        r.accept(GREENHOUSE_TYPE, GreenhouseRecipe.TYPE_ID);
    }

    public static void submitRecipeSerializers(BiConsumer<RecipeSerializer<?>, ResourceLocation> r) {
        r.accept(GREENHOUSE_SERIALIZER, GreenhouseRecipe.TYPE_ID);
    }

    private static class ModRecipeType<T extends Recipe<?>> implements RecipeType<T> {
        private ModRecipeType() {
        }

        public String toString() {
            return Registry.RECIPE_TYPE.getKey(this).toString();
        }
    }

}
