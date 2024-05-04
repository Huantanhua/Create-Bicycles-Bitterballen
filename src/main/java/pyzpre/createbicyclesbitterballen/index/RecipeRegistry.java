package pyzpre.createbicyclesbitterballen.index;

import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeSerializer;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import com.simibubi.create.foundation.utility.Lang;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandlerContainer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import pyzpre.createbicyclesbitterballen.CreateBitterballen;
import pyzpre.createbicyclesbitterballen.block.mechanicalfryer.DeepFryingRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Supplier;



public enum RecipeRegistry implements IRecipeTypeInfo {

    DEEP_FRYING(DeepFryingRecipe::new);

    private final ResourceLocation id;
    private final RecipeSerializer<?> serializerObject;
    @Nullable
    private final RecipeType<?> typeObject;
    private final Supplier<RecipeType<?>> type;

    RecipeRegistry(Supplier<RecipeSerializer<?>> serializerSupplier) {
        String name = Lang.asId(name());
        id = CreateBitterballen.asResource(name);
        serializerObject = Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, id, serializerSupplier.get());
        typeObject = simpleType(id);
        Registry.register(BuiltInRegistries.RECIPE_TYPE, id, typeObject);
        type = () -> typeObject;
    }
    RecipeRegistry(ProcessingRecipeBuilder.ProcessingRecipeFactory<?> processingFactory) {
        this(() -> new ProcessingRecipeSerializer<>(processingFactory));
    }

    public static <T extends Recipe<?>> RecipeType<T> simpleType(ResourceLocation id) {
        String stringId = id.toString();
        return new RecipeType<T>() {
            @Override
            public String toString() {
                return stringId;
            }
        };
    }
    public static void register() {
    }
    @Override
    public <T extends RecipeType<?>> T getType() {

        return (T) type.get();
    }
    public <C extends Container, T extends Recipe<C>> Optional<T> find(ItemStackHandlerContainer inv, Level world) {
        return (Optional<T>) world.getRecipeManager()
                .getRecipeFor(getType(), inv, world);
    }
    public RecipeType<DeepFryingRecipe> get() {
        // Since the type field is a Supplier<RecipeType<?>>, we cast it to the specific RecipeType we need
        return (RecipeType<DeepFryingRecipe>) type.get();
    }
    @Override
    public ResourceLocation getId() {
        return id;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends RecipeSerializer<?>> T getSerializer() {
        return (T) serializerObject;
    }



}