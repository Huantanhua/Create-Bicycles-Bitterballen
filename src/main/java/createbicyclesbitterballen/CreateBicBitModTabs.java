package createbicyclesbitterballen;

import createbicyclesbitterballen.index.BlockRegistry;
import createbicyclesbitterballen.index.CreateBicBitModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreateBicBitModTabs {

	private static final DeferredRegister<CreativeModeTab> TAB_REGISTER =
			DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "create_bic_bit");

	public static final RegistryObject<CreativeModeTab> BICYCLES_BITTERBALLEN =
			TAB_REGISTER.register("bicycles_bitterballen",
					() -> CreativeModeTab.builder()
							.title(Component.translatable("item_group.create_bic_bit.bicycles_bitterballen"))
							.icon(() -> new ItemStack(CreateBicBitModItems.STROOPWAFEL.get()))
							.displayItems((parameters, tabData) -> {
								tabData.accept(CreateBicBitModItems.SWEET_DOUGH.get());
								tabData.accept(CreateBicBitModItems.KRUIDNOTEN.get());
								tabData.accept(CreateBicBitModItems.SPECULAAS.get());
								tabData.accept(CreateBicBitModItems.UNBAKED_STROOPWAFEL.get());
								tabData.accept(CreateBicBitModItems.STROOPWAFEL.get());
								tabData.accept(CreateBicBitModItems.WRAPPED_STROOPWAFEL.get());
								tabData.accept(CreateBicBitModItems.CHOCOLATE_GLAZED_STROOPWAFEL.get());
								tabData.accept(CreateBicBitModItems.WRAPPED_COATED_STROOPWAFEL.get());
								tabData.accept(CreateBicBitModItems.OLIEBOLLEN.get());
								tabData.accept(CreateBicBitModItems.RAW_CHEESE_SOUFFLE.get());
								tabData.accept(CreateBicBitModItems.CHEESE_SOUFFLE.get());
								tabData.accept(CreateBicBitModItems.RAW_KROKET.get());
								tabData.accept(CreateBicBitModItems.KROKET.get());
								tabData.accept(CreateBicBitModItems.KROKET_SANDWICH.get());
								tabData.accept(CreateBicBitModItems.KETCHUP_TOPPED_KROKET_SANDWICH.get());
								tabData.accept(CreateBicBitModItems.MAYONNAISE_TOPPED_KROKET_SANDWICH.get());
								tabData.accept(CreateBicBitModItems.MAYONNAISE_KETCHUP_TOPPED_KROKET_SANDWICH.get());
								tabData.accept(CreateBicBitModItems.RAW_BITTERBALLEN.get());
								tabData.accept(CreateBicBitModItems.BITTERBALLEN.get());
								tabData.accept(CreateBicBitModItems.RAW_EGGBALL.get());
								tabData.accept(CreateBicBitModItems.EGGBALL.get());
								tabData.accept(CreateBicBitModItems.RAW_FRIKANDEL.get());
								tabData.accept(CreateBicBitModItems.FRIKANDEL.get());
								tabData.accept(CreateBicBitModItems.FRIKANDEL_SANDWICH.get());
								tabData.accept(CreateBicBitModItems.KETCHUP_TOPPED_FRIKANDEL_SANDWICH.get());
								tabData.accept(CreateBicBitModItems.MAYONNAISE_TOPPED_FRIKANDEL_SANDWICH.get());
								tabData.accept(CreateBicBitModItems.MAYONNAISE_KETCHUP_TOPPED_FRIKANDEL_SANDWICH.get());
								tabData.accept(CreateBicBitModItems.RAW_FRIES.get());
								tabData.accept(CreateBicBitModItems.FRIES.get());
								tabData.accept(CreateBicBitModItems.WRAPPED_FRIES.get());
								tabData.accept(CreateBicBitModItems.WRAPPED_KETCHUP_TOPPED_FRIES.get());
								tabData.accept(CreateBicBitModItems.WRAPPED_MAYONNAISE_TOPPED_FRIES.get());
								tabData.accept(CreateBicBitModItems.WRAPPED_MAYONNAISE_KETCHUP_TOPPED_FRIES.get());
								tabData.accept(CreateBicBitModItems.RAW_CHURROS.get());
								tabData.accept(CreateBicBitModItems.CHURROS.get());
								tabData.accept(CreateBicBitModItems.WRAPPED_CHURROS.get());
								tabData.accept(CreateBicBitModItems.COATED_CHURROS.get());
								tabData.accept(CreateBicBitModItems.WRAPPED_COATED_CHURROS.get());
								tabData.accept(CreateBicBitModItems.RAW_HERRING.get());
								tabData.accept(CreateBicBitModItems.COOKED_HERRING.get());
								tabData.accept(CreateBicBitModItems.STAMPPOT_BOWL.get());
								tabData.accept(CreateBicBitModItems.ENDERBALL.get());
								tabData.accept(CreateBicBitModItems.CURDLED_MILK_BUCKET.get());
								tabData.accept(BlockRegistry.UNRIPE_CHEESE.get());
								tabData.accept(BlockRegistry.WAXED_UNRIPE_CHEESE.get());
								tabData.accept(BlockRegistry.YOUNG_CHEESE.get());
								tabData.accept(BlockRegistry.WAXED_YOUNG_CHEESE.get());
								tabData.accept(BlockRegistry.AGED_CHEESE.get());
								tabData.accept(BlockRegistry.WAXED_AGED_CHEESE.get());
								tabData.accept(CreateBicBitModItems.UNRIPE_CHEESE_WEDGE.get());
								tabData.accept(CreateBicBitModItems.YOUNG_CHEESE_WEDGE.get());
								tabData.accept(CreateBicBitModItems.AGED_CHEESE_WEDGE.get());
								tabData.accept(CreateBicBitModItems.CRUSHED_NETHERWART.get());
								tabData.accept(CreateBicBitModItems.KETCHUP_BOTTLE.get());
								tabData.accept(CreateBicBitModItems.MAYONNAISE_BOTTLE.get());
								tabData.accept(CreateBicBitModItems.KETCHUP_BUCKET.get());
								tabData.accept(CreateBicBitModItems.MAYONNAISE_BUCKET.get());
								tabData.accept(CreateBicBitModItems.CRUSHED_SUNFLOWER_SEEDS.get());
								tabData.accept(CreateBicBitModItems.SUNFLOWER_SEEDS.get());
								tabData.accept(CreateBicBitModItems.ROASTED_SUNFLOWER_SEEDS.get());
								tabData.accept(BlockRegistry.CRYSTALLISED_OIL.get());
								tabData.accept(CreateBicBitModItems.FRYING_OIL_BOTTLE.get());
								tabData.accept(CreateBicBitModItems.FRYING_OIL_BUCKET.get());
								tabData.accept(BlockRegistry.MECHANICAL_FRYER.get());
								tabData.accept(CreateBicBitModItems.BASKET.get());
								tabData.accept(CreateBicBitModItems.DIRTY_PAPER.get());
							})
							.build());

	public static void register(IEventBus modEventBus) {
		TAB_REGISTER.register(modEventBus);
	}
}