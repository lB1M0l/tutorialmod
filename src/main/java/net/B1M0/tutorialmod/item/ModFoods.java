package net.B1M0.tutorialmod.item;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.common.ForgeConfigSpec;

public class ModFoods {
    public static final FoodProperties CUCUMBER;

static {
    CUCUMBER= (new FoodProperties.Builder()).fast().nutrition(2).saturationMod(0.3f).effect(new MobEffectInstance(MobEffects.REGENERATION,300,5),1.0f).meat().build();
}

}
