package net.B1M0.tutorialmod.item.custom;

import net.B1M0.tutorialmod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;

public class DowsingRodItem extends Item {
    public DowsingRodItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        if (pContext.getLevel().isClientSide()) {

            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            BlockPos NewPos;
            int Dig_down;
            boolean foundBlock = false;


            for (int i = 0; i <= positionClicked.getY() + 64; i++) {
                Block blockBelow = pContext.getLevel().getBlockState(positionClicked.below(i)).getBlock();

                        if (isValuableBlock(blockBelow)) {
                            Dig_down = i;
                            outputValuableCoordinates(positionClicked.below(i), player, blockBelow, Dig_down);
                            foundBlock = true;
                            break;
                        }
            }


            if (!foundBlock) {
                player.sendMessage(new TranslatableComponent("item.tutorialmod.dowsing_rod.no_valuables"), player.getUUID());
            }
        }



        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(), (player)->player.broadcastBreakEvent(player.getUsedItemHand()));
        return super.useOn(pContext);
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block blockBelow, int Dig_down) {
        String itemName = new ItemStack(blockBelow).getHoverName().getString();
        player.sendMessage(new TranslatableComponent("chat.tutorialmod.found_item", itemName, blockPos.getX(), blockPos.getY(), blockPos.getZ()), player.getUUID());
        player.sendMessage(new TranslatableComponent("chat.tutorialmod.found_item_dig_down", Dig_down), player.getUUID());
    }

    private boolean isValuableBlock(Block block){
        return block == ModBlocks.CITRINE_ORE.get();
    }
}
