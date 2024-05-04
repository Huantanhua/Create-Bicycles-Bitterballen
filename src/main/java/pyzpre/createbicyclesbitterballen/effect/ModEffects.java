package pyzpre.createbicyclesbitterballen.effect;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import pyzpre.createbicyclesbitterballen.CreateBitterballen;

public class ModEffects {
    public static final MobEffect UNANCHORED = new UnanchoredEffect(MobEffectCategory.NEUTRAL, 800000980);
    public static final MobEffect OILED_UP = new OiledUpEffect(MobEffectCategory.NEUTRAL, 888000900);

    public static void register() {
        Registry.register(BuiltInRegistries.MOB_EFFECT, new ResourceLocation(CreateBitterballen.MOD_ID, "unanchored"), UNANCHORED);
        Registry.register(BuiltInRegistries.MOB_EFFECT, new ResourceLocation(CreateBitterballen.MOD_ID, "oiled_up"), OILED_UP);
    }
}

