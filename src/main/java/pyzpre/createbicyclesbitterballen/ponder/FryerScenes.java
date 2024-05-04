package pyzpre.createbicyclesbitterballen.ponder;

import com.google.common.collect.ImmutableList;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.processing.basin.BasinBlockEntity;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock.HeatLevel;
import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import com.simibubi.create.foundation.ponder.Selection;
import com.simibubi.create.foundation.ponder.element.InputWindowElement;
import com.simibubi.create.foundation.utility.IntAttached;
import com.simibubi.create.foundation.utility.NBTHelper;
import com.simibubi.create.foundation.utility.Pointing;
import pyzpre.createbicyclesbitterballen.block.mechanicalfryer.MechanicalFryerEntity;
import pyzpre.createbicyclesbitterballen.index.CreateBicBitModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import pyzpre.createbicyclesbitterballen.index.FluidsRegistry;


public class FryerScenes {
    public static void frying(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("mechanical_fryer", "Processing Items with the Mechanical Fryer");
        scene.configureBasePlate(0, 0, 5);
        scene.world.setBlock(util.grid.at(1, 1, 2), AllBlocks.BLAZE_BURNER.getDefaultState().setValue(BlazeBurnerBlock.HEAT_LEVEL, HeatLevel.KINDLED), false);
        scene.world.showSection(util.select.layer(0), Direction.UP);
        scene.idle(5);
        scene.world.showSection(util.select.fromTo(1, 4, 3, 1, 4, 5), Direction.DOWN);
        scene.idle(5);
        scene.world.showSection(util.select.position(1, 1, 2), Direction.DOWN);
        scene.idle(5);
        scene.world.showSection(util.select.position(1, 2, 2), Direction.DOWN);
        scene.idle(5);
        scene.world.showSection(util.select.position(1, 4, 2), Direction.SOUTH);
        scene.idle(5);
        scene.world.showSection(util.select.fromTo(2, 3, 2, 4, 4, 2), Direction.SOUTH);
        scene.world.showSection(util.select.fromTo(2, 3, 3, 2, 3, 4), Direction.SOUTH);
        scene.world.showSection(util.select.fromTo(1, 2, 3, 1, 2, 4), Direction.SOUTH);
        scene.idle(20);

        BlockPos basin = util.grid.at(1, 2, 2);
        BlockPos fryer = util.grid.at(1, 4, 2);
        Selection fryerSelection = util.select.position(fryer);
        Vec3 basinSide = util.vector.blockSurface(basin, Direction.WEST);

        ItemStack oil = new ItemStack(FluidsRegistry.FRYING_OIL.getBucket().get());
        ItemStack raw = new ItemStack(CreateBicBitModItems.RAW_BITTERBALLEN.get());
        ItemStack fried = new ItemStack(CreateBicBitModItems.BITTERBALLEN.get());

        scene.overlay.showText(60)
                .pointAt(basinSide)
                .placeNearTarget()
                .text("With a Fryer and a Basin, some foods can be deep fried");
        scene.idle(70);

        scene.overlay.showControls(new InputWindowElement(util.vector.topOf(basin), Pointing.RIGHT).withItem(oil), 80);
        scene.overlay.showText(80)
                .pointAt(basinSide)
                .placeNearTarget()
                .attachKeyFrame()
                .text("Fill the Basin with Frying Oil");
        scene.idle(90);


        scene.overlay.showText(80)
                .pointAt(basinSide.subtract(0, 1, 0))
                .placeNearTarget()
                .attachKeyFrame()
                .text("Frying requires the heat of a Blaze Burner");
        scene.idle(80);
        scene.overlay.showControls(new InputWindowElement(util.vector.topOf(fryer), Pointing.LEFT).withItem(raw), 30);
        scene.idle(30);
        Class<MechanicalFryerEntity> type = MechanicalFryerEntity.class;
        scene.world.modifyBlockEntity(fryer, type, pte -> pte.tick());
        scene.world.createItemOnBeltLike(fryer, Direction.UP, raw);
        scene.idle(80);
        scene.world.modifyBlockEntityNBT(util.select.position(basin), BasinBlockEntity.class, nbt -> {
            nbt.put("VisualizedItems",
                    NBTHelper.writeCompoundList(ImmutableList.of(IntAttached.with(1, fried)), ia -> ia.getValue()
                            .serializeNBT()));
        });
        scene.idle(20);
        scene.world.modifyBlockEntityNBT(fryerSelection, type,
                nbt -> nbt.put("OutputInventory", ItemStack.EMPTY.serializeNBT()));

        scene.world.createItemOnBelt(util.grid.at(2, 3, 2), Direction.UP, fried);
        scene.idle(30);



        scene.idle(80);
    }
}