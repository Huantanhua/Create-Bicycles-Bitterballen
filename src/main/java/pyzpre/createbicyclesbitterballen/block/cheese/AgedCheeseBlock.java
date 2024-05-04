package pyzpre.createbicyclesbitterballen.block.cheese;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import pyzpre.createbicyclesbitterballen.index.BlockRegistry;
import pyzpre.createbicyclesbitterballen.index.CreateBicBitModItems;

public class AgedCheeseBlock extends Block {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_2;
    public static final BooleanProperty WAXED = BooleanProperty.create("waxed");
    private static final VoxelShape SHAPE = makeShape();
    public AgedCheeseBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 2).setValue(WAXED, false));
    }
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return SHAPE;
    }
    public static VoxelShape makeShape() {
        return Shapes.box(1 / 16D, 0 / 16D, 1 / 16D, 15 / 16D, 8 / 16D, 15 / 16D);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getItemInHand(hand);
        Item usedItem = itemStack.getItem();

        if (!world.isClientSide) {
            if (usedItem == Items.HONEYCOMB) {
                if (handleWaxing(world, pos, state, player, itemStack)) {
                    return InteractionResult.SUCCESS;
                }
            } else {
                TagKey<Item> shearsTag = TagKey.create(Registries.ITEM, new ResourceLocation("c", "shears"));
                TagKey<Item> knivesTag = TagKey.create(Registries.ITEM, new ResourceLocation("c", "tools/knives"));

                if (itemStack.is(shearsTag) || itemStack.is(knivesTag)) {
                    if (handleShearing(world, pos, state)) {
                        playShearingEffect(world, pos);
                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }

        return super.use(state, world, pos, player, hand, hit);
    }

    private boolean handleWaxing(Level world, BlockPos pos, BlockState state, Player player, ItemStack itemStack) {
        if (!state.getValue(WAXED)) {
            world.setBlock(pos, state.setValue(WAXED, true), 3);
            if (!player.isCreative()) {
                itemStack.shrink(1);
            }
            playWaxOnEffect(world, pos);
            world.scheduleTick(pos, this, 1);

            return true;
        }
        return false;
    }
    private void transition(ServerLevel world, BlockPos pos, BlockState state) {
        BlockState agedCheeseState = BlockRegistry.WAXED_AGED_CHEESE.get().defaultBlockState().setValue(AGE, 2).setValue(WAXED, true);
        world.setBlockAndUpdate(pos, agedCheeseState);
    }
    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        super.tick(state, world, pos, random);
        if (state.getValue(WAXED)) {
            transition(world, pos, state);
        }
    }

    private boolean handleShearing(Level world, BlockPos pos, BlockState state) {
        int age = state.getValue(AGE);
        if (age == 1 || age == 2) {
            dropCheeseProducts(world, pos, age);
            world.removeBlock(pos, false);
            playShearingEffect(world, pos);
            return true;
        }
        return false;
    }
    private void playWaxOnEffect(Level world, BlockPos pos) {
        world.playSound(null, pos, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1.0F, 1.0F);

        if (world instanceof ServerLevel) {
            ServerLevel serverWorld = (ServerLevel) world;
            for (int i = 0; i < 20; i++) {
                double d0 = serverWorld.random.nextGaussian() * 0.1D;
                double d1 = serverWorld.random.nextGaussian() * 0.1D;
                double d2 = serverWorld.random.nextGaussian() * 0.1D;
                double x = pos.getX() + 0.5 + serverWorld.random.nextGaussian() * 0.5;
                double y = pos.getY() + 0.6;
                double z = pos.getZ() + 0.5 + serverWorld.random.nextGaussian() * 0.5;
                serverWorld.sendParticles(ParticleTypes.WAX_ON, x, y, z, 1, d0, d1, d2, 0.0D);
            }
        }
    }
    private void playWaxOffEffect(Level world, BlockPos pos) {
        world.playSound(null, pos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
    }
    private void dropCheeseProducts(Level world, BlockPos pos, int age) {
        ItemStack dropItem = (age == 1) ? new ItemStack(CreateBicBitModItems.YOUNG_CHEESE_WEDGE) : new ItemStack(CreateBicBitModItems.AGED_CHEESE_WEDGE);
        RandomSource random = world.random;

        for (int i = 0; i < 4; i++) {
            double d0 = random.nextFloat() * 0.7F + 0.15F;
            double d1 = random.nextFloat() * 0.7F + 0.060000002F + 0.6D;
            double d2 = random.nextFloat() * 0.7F + 0.15F;

            ItemStack itemStackCopy = dropItem.copy();
            ItemEntity itemEntity = new ItemEntity(world, pos.getX() + d0, pos.getY() + d1, pos.getZ() + d2, itemStackCopy);
            itemEntity.setDefaultPickUpDelay();
            world.addFreshEntity(itemEntity);
        }
    }


    private void playShearingEffect(Level world, BlockPos pos) {
        world.playSound(null, pos, SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.0F, 1.0F);
    }


    @Override
    protected void createBlockStateDefinition(net.minecraft.world.level.block.state.StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE, WAXED);
    }
}
