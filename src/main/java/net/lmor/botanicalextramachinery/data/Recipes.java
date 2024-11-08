package net.lmor.botanicalextramachinery.data;

import net.lmor.botanicalextramachinery.ExtraMachineryBlocks;
import net.lmor.botanicalextramachinery.ExtraMachineryItems;
import net.minecraft.data.DataGenerator;
import org.moddingx.libx.annotation.data.Datagen;
import org.moddingx.libx.datagen.provider.recipe.RecipeProviderBase;
import org.moddingx.libx.datagen.provider.recipe.crafting.CompressionExtension;
import org.moddingx.libx.datagen.provider.recipe.crafting.CraftingExtension;
import org.moddingx.libx.mod.ModX;
import vazkii.botania.common.block.BotaniaBlocks;
import vazkii.botania.common.block.BotaniaFlowerBlocks;
import vazkii.botania.common.item.BotaniaItems;

@Datagen
public class Recipes extends RecipeProviderBase implements CraftingExtension, CompressionExtension  {
    public Recipes(ModX mod, DataGenerator generator) {super(mod, generator);}

    @Override
    protected void setup() {
        this.compress(ExtraMachineryItems.malachiteDragonstone, ExtraMachineryBlocks.malachiteDragonstoneBlock);
        this.compress(ExtraMachineryItems.saffronDragonstone, ExtraMachineryBlocks.saffronDragonstoneBlock);
        this.compress(ExtraMachineryItems.shadowDragonstone, ExtraMachineryBlocks.shadowDragonstoneBlock);
        this.compress(ExtraMachineryItems.crimsonDragonstone, ExtraMachineryBlocks.crimsonDragonstoneBlock);

        this.compress(ExtraMachineryItems.malachiteIngot, ExtraMachineryBlocks.malachiteIngotBlock);
        this.compress(ExtraMachineryItems.saffronIngot, ExtraMachineryBlocks.saffronIngotBlock);
        this.compress(ExtraMachineryItems.shadowIngot, ExtraMachineryBlocks.shadowIngotBlock);
        this.compress(ExtraMachineryItems.crimsonIngot, ExtraMachineryBlocks.crimsonIngotBlock);

        this.machine(ExtraMachineryBlocks.baseIndustrialAgglomerationFactory, BotaniaItems.manaRingGreater, ExtraMachineryItems.malachiteIngot, de.melanx.botanicalmachinery.ModBlocks.industrialAgglomerationFactory, ExtraMachineryBlocks.malachiteDragonstoneBlock);
        this.machine(ExtraMachineryBlocks.baseManaPool, BotaniaItems.manaRingGreater, ExtraMachineryItems.malachiteIngot, de.melanx.botanicalmachinery.ModBlocks.mechanicalManaPool, ExtraMachineryBlocks.malachiteDragonstoneBlock);
        this.machine(ExtraMachineryBlocks.baseRunicAltar, BotaniaItems.manaRingGreater, ExtraMachineryItems.malachiteIngot, de.melanx.botanicalmachinery.ModBlocks.mechanicalRunicAltar, ExtraMachineryBlocks.malachiteDragonstoneBlock);
        this.machine(ExtraMachineryBlocks.baseDaisy, BotaniaItems.manaRingGreater, ExtraMachineryItems.malachiteIngot, de.melanx.botanicalmachinery.ModBlocks.mechanicalDaisy, ExtraMachineryBlocks.malachiteDragonstoneBlock);
        this.machine(ExtraMachineryBlocks.baseApothecary, BotaniaItems.manaRingGreater, ExtraMachineryItems.malachiteIngot, de.melanx.botanicalmachinery.ModBlocks.mechanicalApothecary, ExtraMachineryBlocks.malachiteDragonstoneBlock);
        this.machine(ExtraMachineryBlocks.baseAlfheimMarket, BotaniaItems.manaRingGreater, ExtraMachineryItems.malachiteIngot, de.melanx.botanicalmachinery.ModBlocks.alfheimMarket, ExtraMachineryBlocks.malachiteDragonstoneBlock);
        this.machine(ExtraMachineryBlocks.baseOrechid, BotaniaItems.manaRingGreater, ExtraMachineryItems.malachiteIngot, BotaniaFlowerBlocks.orechid, ExtraMachineryBlocks.malachiteDragonstoneBlock);

        this.machine(ExtraMachineryBlocks.upgradedIndustrialAgglomerationFactory, BotaniaBlocks.terrasteelBlock, ExtraMachineryItems.saffronIngot, ExtraMachineryBlocks.baseIndustrialAgglomerationFactory, ExtraMachineryBlocks.saffronDragonstoneBlock);
        this.machine(ExtraMachineryBlocks.upgradedManaPool, BotaniaBlocks.terrasteelBlock, ExtraMachineryItems.saffronIngot, ExtraMachineryBlocks.baseManaPool, ExtraMachineryBlocks.saffronDragonstoneBlock);
        this.machine(ExtraMachineryBlocks.upgradedRunicAltar, BotaniaBlocks.terrasteelBlock, ExtraMachineryItems.saffronIngot, ExtraMachineryBlocks.baseRunicAltar, ExtraMachineryBlocks.saffronDragonstoneBlock);
        this.machine(ExtraMachineryBlocks.upgradedDaisy, BotaniaBlocks.terrasteelBlock, ExtraMachineryItems.saffronIngot, ExtraMachineryBlocks.baseDaisy, ExtraMachineryBlocks.saffronDragonstoneBlock);
        this.machine(ExtraMachineryBlocks.upgradedApothecary, BotaniaBlocks.terrasteelBlock, ExtraMachineryItems.saffronIngot, ExtraMachineryBlocks.baseApothecary, ExtraMachineryBlocks.saffronDragonstoneBlock);
        this.machine(ExtraMachineryBlocks.upgradedAlfheimMarket, BotaniaBlocks.terrasteelBlock, ExtraMachineryItems.saffronIngot, ExtraMachineryBlocks.baseAlfheimMarket, ExtraMachineryBlocks.saffronDragonstoneBlock);
        this.machine(ExtraMachineryBlocks.upgradedOrechid, BotaniaBlocks.terrasteelBlock, ExtraMachineryItems.saffronIngot, ExtraMachineryBlocks.baseOrechid, ExtraMachineryBlocks.saffronDragonstoneBlock);

        this.machine(ExtraMachineryBlocks.advancedIndustrialAgglomerationFactory, ExtraMachineryBlocks.malachiteIngotBlock, ExtraMachineryItems.shadowIngot, ExtraMachineryBlocks.upgradedIndustrialAgglomerationFactory, ExtraMachineryBlocks.shadowDragonstoneBlock);
        this.machine(ExtraMachineryBlocks.advancedManaPool, ExtraMachineryBlocks.malachiteIngotBlock, ExtraMachineryItems.shadowIngot, ExtraMachineryBlocks.upgradedManaPool, ExtraMachineryBlocks.shadowDragonstoneBlock);
        this.machine(ExtraMachineryBlocks.advancedRunicAltar, ExtraMachineryBlocks.malachiteIngotBlock, ExtraMachineryItems.shadowIngot, ExtraMachineryBlocks.upgradedRunicAltar, ExtraMachineryBlocks.shadowDragonstoneBlock);
        this.machine(ExtraMachineryBlocks.advancedDaisy, ExtraMachineryBlocks.malachiteIngotBlock, ExtraMachineryItems.shadowIngot, ExtraMachineryBlocks.upgradedDaisy, ExtraMachineryBlocks.shadowDragonstoneBlock);
        this.machine(ExtraMachineryBlocks.advancedApothecary, ExtraMachineryBlocks.malachiteIngotBlock, ExtraMachineryItems.shadowIngot, ExtraMachineryBlocks.upgradedApothecary, ExtraMachineryBlocks.shadowDragonstoneBlock);
        this.machine(ExtraMachineryBlocks.advancedAlfheimMarket, ExtraMachineryBlocks.malachiteIngotBlock, ExtraMachineryItems.shadowIngot, ExtraMachineryBlocks.upgradedAlfheimMarket, ExtraMachineryBlocks.shadowDragonstoneBlock);
        this.machine(ExtraMachineryBlocks.advancedOrechid, ExtraMachineryBlocks.malachiteIngotBlock, ExtraMachineryItems.shadowIngot, ExtraMachineryBlocks.upgradedOrechid, ExtraMachineryBlocks.shadowDragonstoneBlock);

        this.machine(ExtraMachineryBlocks.ultimateIndustrialAgglomerationFactory, ExtraMachineryBlocks.saffronIngotBlock, ExtraMachineryItems.crimsonIngot, ExtraMachineryBlocks.advancedIndustrialAgglomerationFactory, ExtraMachineryBlocks.crimsonDragonstoneBlock);
        this.machine(ExtraMachineryBlocks.ultimateManaPool, ExtraMachineryBlocks.saffronIngotBlock, ExtraMachineryItems.crimsonIngot, ExtraMachineryBlocks.advancedManaPool, ExtraMachineryBlocks.crimsonDragonstoneBlock);
        this.machine(ExtraMachineryBlocks.ultimateRunicAltar, ExtraMachineryBlocks.saffronIngotBlock, ExtraMachineryItems.crimsonIngot, ExtraMachineryBlocks.advancedRunicAltar, ExtraMachineryBlocks.crimsonDragonstoneBlock);
        this.machine(ExtraMachineryBlocks.ultimateDaisy, ExtraMachineryBlocks.saffronIngotBlock, ExtraMachineryItems.crimsonIngot, ExtraMachineryBlocks.advancedDaisy, ExtraMachineryBlocks.crimsonDragonstoneBlock);
        this.machine(ExtraMachineryBlocks.ultimateApothecary, ExtraMachineryBlocks.saffronIngotBlock, ExtraMachineryItems.crimsonIngot, ExtraMachineryBlocks.advancedApothecary, ExtraMachineryBlocks.crimsonDragonstoneBlock);
        this.machine(ExtraMachineryBlocks.ultimateAlfheimMarket, ExtraMachineryBlocks.saffronIngotBlock, ExtraMachineryItems.crimsonIngot, ExtraMachineryBlocks.advancedAlfheimMarket, ExtraMachineryBlocks.crimsonDragonstoneBlock);
        this.machine(ExtraMachineryBlocks.ultimateOrechid, ExtraMachineryBlocks.saffronIngotBlock, ExtraMachineryItems.crimsonIngot, ExtraMachineryBlocks.advancedOrechid, ExtraMachineryBlocks.crimsonDragonstoneBlock);

        this.upgrade_2(ExtraMachineryItems.catalystManaInfinity, ExtraMachineryBlocks.crimsonDragonstoneBlock, ExtraMachineryBlocks.ultimateManaPool);
        this.upgrade(ExtraMachineryItems.catalystSpeed, ExtraMachineryBlocks.upgradedIndustrialAgglomerationFactory);
        this.upgrade(ExtraMachineryItems.catalystSeedInfinity, ExtraMachineryBlocks.ultimateApothecary);
        this.upgrade(ExtraMachineryItems.catalystLivingRockInfinity, ExtraMachineryBlocks.upgradedRunicAltar);
        this.upgrade(ExtraMachineryItems.catalystWaterInfinity, ExtraMachineryBlocks.advancedApothecary);
        this.upgrade(ExtraMachineryItems.catalystStoneInfinity, ExtraMachineryItems.catalystLivingRockInfinity);
    }

    private void machine(Object output, Object special1, Object special2, Object special3, Object special4) {
        this.shaped(output, "aba", "bcb", "ddd", 'a', special1, 'b', special2, 'c', special3, 'd', special4);
    }

    private void upgrade(Object output, Object special1) {
        this.shaped(output, "abc", "bdb", "cba", 'a', ExtraMachineryBlocks.saffronDragonstoneBlock, 'b', ExtraMachineryBlocks.shadowDragonstoneBlock, 'c', ExtraMachineryBlocks.crimsonDragonstoneBlock, 'd', special1);
    }

    private void upgrade_2(Object output, Object special1, Object special2) {
        this.shaped(output, "aaa", "aba", "aaa", 'a', special1, 'b', special2);
    }

}
