package net.lmor.botanicalextramachinery.entities.manaSpark;

import net.lmor.botanicalextramachinery.ExtraMachineryEntities;
import net.lmor.botanicalextramachinery.ExtraMachineryItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;


public class EntityMalachiteManaSpark extends EntityManaSparkPattern {

    public EntityMalachiteManaSpark(EntityType<?> entityEntityType, Level level) {
        super(entityEntityType, level);
        this.TRANSFER_RATE = 25000;
    }

    public EntityMalachiteManaSpark(Level level){
        this(ExtraMachineryEntities.MALACHITE_SPARK, level);
    }

    @Override
    protected Item getSparkItem() {
        return ExtraMachineryItems.malachiteSpark;
    }
}