package net.lmor.botanicalextramachinery.entities.manaSpark;

import net.lmor.botanicalextramachinery.ExtraMachineryEntities;
import net.lmor.botanicalextramachinery.ExtraMachineryItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;


public class EntityCrimsonManaSpark extends EntityManaSparkPattern {

    public EntityCrimsonManaSpark(EntityType<?> entityEntityType, Level level) {
        super(entityEntityType, level);
        this.TRANSFER_RATE = 1000000;
    }

    public EntityCrimsonManaSpark(Level level){
        this(ExtraMachineryEntities.CRIMSON_SPARK, level);
    }

    @Override
    protected Item getSparkItem() {
        return ExtraMachineryItems.crimsonSpark;
    }
}