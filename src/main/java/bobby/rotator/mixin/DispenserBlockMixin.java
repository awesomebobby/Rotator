package bobby.rotator.mixin;

import bobby.rotator.config.Configs;
import bobby.rotator.util.DirectionList6;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.DropperBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DispenserBlock.class)
public class DispenserBlockMixin{

//    return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerLookDirection().getOpposite());

    @Inject(at = @At("HEAD"), method = "getPlacementState", cancellable = true)
    public void getPlacementState(ItemPlacementContext ctx, CallbackInfoReturnable<BlockState> cir){

        Block block = ((Block)(Object)this);

        if(block instanceof DropperBlock){

            if(Configs.Toggle.MAIN_TOGGLE.getBooleanValue() && Configs.Toggle.OPPOSITE_PLACEMENT.getBooleanValue()){

                cir.setReturnValue(block.getDefaultState().with(DispenserBlock.FACING, ctx.getPlayerLookDirection().getOpposite().getOpposite()));
                return;

            }

            if(Configs.Toggle.MAIN_TOGGLE.getBooleanValue() && Configs.Toggle.DROPPER_TOGGLE.getBooleanValue()) {

                DirectionList6 dir = (DirectionList6) Configs.Generic.DROPPER_DIRECTIONS.getOptionListValue();

                switch (dir) {
                    case DOWN -> cir.setReturnValue(block.getDefaultState().with(DropperBlock.FACING, Direction.DOWN));
                    case UP -> cir.setReturnValue(block.getDefaultState().with(DropperBlock.FACING, Direction.UP));
                    case NORTH -> cir.setReturnValue(block.getDefaultState().with(DropperBlock.FACING, Direction.NORTH));
                    case SOUTH -> cir.setReturnValue(block.getDefaultState().with(DropperBlock.FACING, Direction.SOUTH));
                    case EAST -> cir.setReturnValue(block.getDefaultState().with(DropperBlock.FACING, Direction.EAST));
                    case WEST -> cir.setReturnValue(block.getDefaultState().with(DropperBlock.FACING, Direction.WEST));
                    default -> {
                    }
                }

            }

        }
        else{

            if(Configs.Toggle.MAIN_TOGGLE.getBooleanValue() && Configs.Toggle.OPPOSITE_PLACEMENT.getBooleanValue()){

                cir.setReturnValue(block.getDefaultState().with(DispenserBlock.FACING, ctx.getPlayerLookDirection().getOpposite().getOpposite()));
                return;

            }

            if(Configs.Toggle.MAIN_TOGGLE.getBooleanValue() && Configs.Toggle.DISPENSER_TOGGLE.getBooleanValue()) {

                DirectionList6 dir = (DirectionList6) Configs.Generic.DISPENSER_DIRECTIONS.getOptionListValue();

                switch (dir) {
                    case DOWN -> cir.setReturnValue(block.getDefaultState().with(DispenserBlock.FACING, Direction.DOWN));
                    case UP -> cir.setReturnValue(block.getDefaultState().with(DispenserBlock.FACING, Direction.UP));
                    case NORTH -> cir.setReturnValue(block.getDefaultState().with(DispenserBlock.FACING, Direction.NORTH));
                    case SOUTH -> cir.setReturnValue(block.getDefaultState().with(DispenserBlock.FACING, Direction.SOUTH));
                    case EAST -> cir.setReturnValue(block.getDefaultState().with(DispenserBlock.FACING, Direction.EAST));
                    case WEST -> cir.setReturnValue(block.getDefaultState().with(DispenserBlock.FACING, Direction.WEST));
                    default -> {
                    }
                }

                cir.cancel();

            }

        }

    }

}
