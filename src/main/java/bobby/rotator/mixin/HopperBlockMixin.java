package bobby.rotator.mixin;

import bobby.rotator.config.Configs;
import bobby.rotator.util.DirectionList4;
import net.minecraft.block.BlockState;
import net.minecraft.block.HopperBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HopperBlock.class)
public class HopperBlockMixin {

//    Direction direction = ctx.getSide().getOpposite();
//        return (BlockState)((BlockState)this.getDefaultState().with(FACING, direction.getAxis() == Direction.Axis.Y ? Direction.DOWN : direction)).with(ENABLED, true);

    @Inject(at = @At("HEAD"), method = "getPlacementState", cancellable = true)
    public void getPlacementState(ItemPlacementContext ctx, CallbackInfoReturnable<BlockState> cir){

        HopperBlock hopper = (HopperBlock)(Object)this;

        Direction direction = ctx.getSide().getOpposite();

        if(Configs.Toggle.MAIN_TOGGLE.getBooleanValue() && Configs.Toggle.OPPOSITE_PLACEMENT.getBooleanValue()){

            cir.setReturnValue(hopper.getDefaultState().with(HopperBlock.FACING, direction.getAxis() == Direction.Axis.Y ? Direction.DOWN : direction.getOpposite()));
            return;

        }

        if(Configs.Toggle.MAIN_TOGGLE.getBooleanValue() && Configs.Toggle.HOPPER_TOGGLE.getBooleanValue()){

            DirectionList4 dir = (DirectionList4) Configs.Generic.HOPPER_DIRECTIONS.getOptionListValue();

            switch (dir) {
                case NORTH -> cir.setReturnValue(hopper.getDefaultState().with(HopperBlock.FACING, Direction.NORTH));
                case SOUTH -> cir.setReturnValue(hopper.getDefaultState().with(HopperBlock.FACING, Direction.SOUTH));
                case EAST -> cir.setReturnValue(hopper.getDefaultState().with(HopperBlock.FACING, Direction.EAST));
                case WEST -> cir.setReturnValue(hopper.getDefaultState().with(HopperBlock.FACING, Direction.WEST));
                default -> {
                }
            }

        }

    }

}
