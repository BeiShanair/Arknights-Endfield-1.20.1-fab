package com.besson.endfield.entity;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.IndustrialExplosiveEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItemEntity {
    public static final EntityType<IndustrialExplosiveEntity> INDUSTRIAL_EXPLOSIVE =
            Registry.register(Registries.ENTITY_TYPE, new Identifier(ArknightsEndfield.MOD_ID, "industrial_explosive"),
                    EntityType.Builder.<IndustrialExplosiveEntity>create(IndustrialExplosiveEntity::new, SpawnGroup.MISC)
                            .setDimensions(0.25f, 0.25f)
                            .maxTrackingRange(4)
                            .build("industrial_explosive"));

    public static void register() {

    }
}
