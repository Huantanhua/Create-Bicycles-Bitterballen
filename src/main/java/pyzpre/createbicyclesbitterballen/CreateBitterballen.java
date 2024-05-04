package pyzpre.createbicyclesbitterballen;


import com.simibubi.create.foundation.utility.Components;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.client.renderer.RenderType;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.simibubi.create.Create;
import com.simibubi.create.foundation.data.CreateRegistrate;


import io.github.fabricators_of_create.porting_lib.util.EnvExecutor;
import net.fabricmc.api.ModInitializer;
import pyzpre.createbicyclesbitterballen.effect.ModEffects;
import pyzpre.createbicyclesbitterballen.events.ClientEvents;
import pyzpre.createbicyclesbitterballen.events.CommonEvents;
import pyzpre.createbicyclesbitterballen.index.*;

import java.util.Random;

import static com.simibubi.create.foundation.utility.Lang.resolveBuilders;
import static pyzpre.createbicyclesbitterballen.index.SunflowerInteractionHandler.grantAdvancementCriterion;


public class CreateBitterballen implements ModInitializer, ClientModInitializer {
	public static final String NAME = "CreateBitterballen";
	public static final Logger LOGGER = LoggerFactory.getLogger(NAME);
	public static final String MOD_ID = "create_bic_bit";
	public static final CreateRegistrate REGISTRATE =
			CreateRegistrate.create("create_bic_bit");
	@Deprecated
	public static final Random RANDOM = new Random();

	@Override
	public void onInitialize() {


		SoundsRegistry.prepare();
		SunflowerInteractionHandler.init();
		BlockRegistry.register();
		CreateBicBitModItems.register();
		FluidsRegistry.register();
		BlockEntityRegistry.register();
		RecipeRegistry.register();
		REGISTRATE.register();
		ModEffects.register();
		CreateBicBitModTabs.register();
		PotatoCannonProjectiles.register();

		LOGGER.info("Create addon mod [{}] is loading alongside Create [{}]!", NAME, Create.VERSION);
		LOGGER.info(EnvExecutor.unsafeRunForDist(
				() -> () -> "{} is accessing Porting Lib from the client!",
				() -> () -> "{} is accessing Porting Lib from the server!"
		), NAME);

		}

	public static MutableComponent translate(String key, Object... args) {
		return Components.translatable(key, resolveBuilders(args));
	}
	public static ResourceLocation asResource(String path) {
		return new ResourceLocation(CreateBitterballen.MOD_ID, path);
	}
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.CRYSTALLISED_OIL.get(), RenderType.translucent());
		BlockRenderLayerMap.INSTANCE.putFluid(FluidsRegistry.FRYING_OIL.get(), RenderType.translucent());
		BlockRenderLayerMap.INSTANCE.putFluid(FluidsRegistry.FRYING_OIL.getSource(), RenderType.translucent());
		BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.SUNFLOWERSTEM.get(), RenderType.cutout());
		PartialsRegistry.init();
		ClientEvents.register();
		LootTables.modifyLootTables();
		CommonEvents.register();
		PonderIndex.register();

	}
	{
		ServerTickEvents.END_SERVER_TICK.register(this::onServerTick);
	}

	private void onServerTick(MinecraftServer server) {
		for (ServerLevel world : server.getAllLevels()) {
			for (Player player : world.players()) {
				if (isInFryingOil(player, world)) {
					applyLevitationEffect(player);
				}
			}
		}
	}

	private boolean isInFryingOil(Player player, Level world) {
		BlockPos pos = player.blockPosition();
		FluidState fluidState = world.getFluidState(pos);

		// Get the fluid type from the FluidState
		Fluid fluid = fluidState.getType();

		// Check if the fluid at the position is the same as your custom frying oil fluid
		return fluid.isSame(FluidsRegistry.FRYING_OIL.get());
	}

	private void applyLevitationEffect(Player player) {
		MobEffectInstance levitation = new MobEffectInstance(ModEffects.OILED_UP, 200, 0);
		player.addEffect(levitation);
	}

}