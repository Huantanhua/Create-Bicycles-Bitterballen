package pyzpre.createbicyclesbitterballen.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class KetchupMayo extends Item {
    public KetchupMayo(Properties p_41383_) {
        super(p_41383_);
    }
    @Override
    public void appendHoverText(ItemStack itemstack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, tooltip, flag);
        tooltip.add(Component.literal("§9Resistance (0:10)"));
        tooltip.add(Component.literal("§9Fire Resistance (0:10)"));
    }
}
