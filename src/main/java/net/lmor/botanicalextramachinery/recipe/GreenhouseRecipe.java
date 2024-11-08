package net.lmor.botanicalextramachinery.recipe;

import com.google.common.base.Preconditions;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.lmor.botanicalextramachinery.ExtraMachineryBlocks;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.common.crafting.RecipeSerializerBase;

import java.util.*;

public class GreenhouseRecipe implements net.lmor.botanicalextramachinery.recipe.InterfaceRecipe.GreenhouseRecipe {
    private final ResourceLocation id;
    private final List<ItemStack> fuels;
    private final ItemStack input;
    private final Map<ItemStack, Integer> mapMana;

    public GreenhouseRecipe(ResourceLocation id, List<ItemStack> fuels, ItemStack input, Map<ItemStack, Integer> mapMana) {
        Preconditions.checkArgument(fuels.size() > 0, "The amount of fuel must be greater than 0");

        this.id = id;
        this.fuels = fuels;
        this.input = input;
        this.mapMana = mapMana;
    }

    public boolean ifFuelToRecipe(ItemStack itemStack){
        for (ItemStack fuel: fuels){
            if (itemStack.getItem() == fuel.getItem()){
                return true;
            }
        }

        return false;
    }

    @Override
    public Map<ItemStack, Integer> getMapMana(){
        return this.mapMana;
    }


    @Override
    public boolean matches(ItemStack stack) {
        return this.input.getItem() == stack.getItem();
    }

    @Override
    public List<ItemStack> getFuels() {
        return this.fuels;
    }

    @Override
    public boolean canCraftInDimensions(int x, int y) {
        return false;
    }

    @Override
    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    public ItemStack getFlower() {
        return this.input;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ExtraMachineryRecipeTypes.GREENHOUSE_SERIALIZER;
    }

    @Override
    public int getManaToConsume(ItemStack fuel) {
        for (ItemStack itemToMana: mapMana.keySet()){
            if (itemToMana.getItem() == fuel.getItem()){
                return mapMana.get(itemToMana);
            }
        }

        return 0;
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ExtraMachineryBlocks.greenhouse);
    }

    public static class Serializer extends RecipeSerializerBase<GreenhouseRecipe> {
        @Override
        public GreenhouseRecipe fromJson(@NotNull ResourceLocation id, @NotNull JsonObject json) {
            ItemStack ing = ShapedRecipe.itemStackFromJson(GsonHelper.convertToJsonObject(json.get("flower"), "input flower"));
            JsonElement fuels = json.get("fuels");

            List<ItemStack> fuelsStack = new ArrayList();
            Map<ItemStack, Integer> mapMana = new HashMap<>();


            if (fuels.isJsonArray()) {
                Iterator iter = fuels.getAsJsonArray().iterator();


                while(iter.hasNext()) {
                    JsonElement elem = (JsonElement)iter.next();

                    JsonObject obj = GsonHelper.convertToJsonObject(elem, "output stack");
                    JsonElement mana = obj.get("mana");

                    Ingredient ingredient = Ingredient.fromJson(obj);

                    for (ItemStack fuel : ingredient.getItems()) {

                        System.out.println("---------------------------");
                        System.out.println(fuel);

                        fuelsStack.add(fuel);
                        mapMana.put(fuel, GsonHelper.convertToInt(mana, "mana"));
                    }
                }
            } else {
                JsonObject obj = GsonHelper.convertToJsonObject(fuels, "output stack");
                JsonElement mana = obj.get("mana");

                ItemStack fuel = ShapedRecipe.itemStackFromJson(obj);

                fuelsStack.add(fuel);
                mapMana.put(fuel, GsonHelper.convertToInt(mana, "mana"));
            }

            return new GreenhouseRecipe(id, fuelsStack, ing, mapMana);
        }

        @Override
        public @Nullable GreenhouseRecipe fromNetwork(@NotNull ResourceLocation id, FriendlyByteBuf buf) {
            ItemStack input = buf.readItem();

            List<ItemStack> fuels = new ArrayList<>();
            Map<ItemStack, Integer> mapMana = new HashMap<>();

            for(int i = 0; i < buf.readVarInt(); ++i) {
                ItemStack item = buf.readItem();

                fuels.add(item);
                mapMana.put(item, buf.readInt());
            }

            return new GreenhouseRecipe(id, fuels, input, mapMana);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, GreenhouseRecipe recipe) {

            ItemStack input = recipe.getFlower();
            buf.writeItem(input);

            buf.writeVarInt(recipe.getFuels().size());
            List<ItemStack> mapMana = recipe.getFuels();

            for (ItemStack fuel: mapMana){
                buf.writeItem(fuel);
                buf.writeInt(recipe.getManaToConsume(fuel));
            }

        }
    }

}
