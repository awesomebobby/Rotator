package bobby.rotator.mixin;

import bobby.rotator.config.Configs;
import bobby.rotator.util.DirectionList6;
import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.ObserverBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ObserverBlock.class)
public class ObserverBlockMixin extends FacingBlock {

    protected ObserverBlockMixin(Settings settings) {
        super(settings);
    }

//    return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerLookDirection().getOpposite().getOpposite());

    @Inject(at = @At("HEAD"), method = "getPlacementState", cancellable = true)
    public void getPlacementState(ItemPlacementContext ctx, CallbackInfoReturnable<BlockState> cir){

        if(Configs.Toggle.MAIN_TOGGLE.getBooleanValue() && Configs.Toggle.OPPOSITE_PLACEMENT.getBooleanValue()){

            cir.setReturnValue(this.getDefaultState().with(FACING, ctx.getPlayerLookDirection().getOpposite()));
            return;

        }

        if(Configs.Toggle.MAIN_TOGGLE.getBooleanValue() && Configs.Toggle.OBSERVER_TOGGLE.getBooleanValue()){

            DirectionList6 dir = (DirectionList6) Configs.Generic.OBSERVER_DIRECTIONS.getOptionListValue();

            switch (dir) {
                case DOWN -> cir.setReturnValue(this.getDefaultState().with(FACING, Direction.DOWN));
                case UP -> cir.setReturnValue(this.getDefaultState().with(FACING, Direction.UP));
                case NORTH -> cir.setReturnValue(this.getDefaultState().with(FACING, Direction.NORTH));
                case SOUTH -> cir.setReturnValue(this.getDefaultState().with(FACING, Direction.SOUTH));
                case EAST -> cir.setReturnValue(this.getDefaultState().with(FACING, Direction.EAST));
                case WEST -> cir.setReturnValue(this.getDefaultState().with(FACING, Direction.WEST));
                default -> {
                }
            }

        }

    }

}
