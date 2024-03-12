package net.B1M0.tutorialmod.item.custom;

import com.mojang.logging.LogUtils;
import net.B1M0.tutorialmod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import org.jline.utils.Log;
import org.slf4j.Logger;


public class DowsingRodItem3by3 extends Item {
    private static final Logger LOGGER = LogUtils.getLogger();

    public DowsingRodItem3by3(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(pContext.getLevel().isClientSide()) {


            BlockPos positionClickedCenter = pContext.getClickedPos(); // Позиция клика/Центр
            Player player = pContext.getPlayer();
            boolean foundBlock = false; // Есть ли ценный блок
            BlockPos blockPos1 = positionClickedCenter.offset(positionClickedCenter.getX()-1, positionClickedCenter.getY(),positionClickedCenter.getZ()+1); // Позиция левого верхнего угла
            BlockPos blockPos2 = positionClickedCenter.offset(positionClickedCenter.getX()+1, positionClickedCenter.getY(),positionClickedCenter.getZ()-1); // Позиция правого нижнего угла
            int Dig_down;
            BlockPos currentPos;
            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    // Текущая позиция в рамках 3x3 области
                    currentPos = positionClickedCenter.offset(x, 0, z);
                    for (int i = 0; i <= currentPos.getY() + 64; i++) {
                        Block blockBelow = pContext.getLevel().getBlockState(currentPos.below(i)).getBlock();
                       if (isValuableBlock(blockBelow)) {
                           outputValuableCoordinates(blockPos1,blockPos2,player,blockBelow, currentPos);
                            Dig_down = i;
                            foundBlock = true;
                            break;
                       }
                    }

                }
            }
            //Поиск руды в облости 3*3


            if(!foundBlock){
                player.sendMessage(new TranslatableComponent("item.tutorialmod.dowsing_rod.no_valuables"),player.getUUID());
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),(player)->player.broadcastBreakEvent(player.getUsedItemHand()));
        return super.useOn(pContext);
    }

    // Метод отправки сообщения
    private void outputValuableCoordinates(BlockPos blockPos1, BlockPos blockPos2, Player player, Block blockBelow,BlockPos currentPos){
        ItemStack itemName = new ItemStack(blockBelow); // Сохраняет имя блока
        LOGGER.info("Ключ Локализации: {}","chat.tutorialmod.found_item.3_by_3");
        LOGGER.info("Имя предмета: {}, Координаты: ({}, {}, {})", itemName, blockPos1.getX(), blockPos1.getY(), blockPos1.getZ());
        //player.sendMessage(new TranslatableComponent("chat.tutorialmod.found_item.3_by_3", itemName.getHoverName(), blockPos1.getX(), blockPos1.getY(), blockPos1.getZ(),
          //      blockPos2.getX(), blockPos2.getY(), blockPos2.getZ()), player.getUUID()); // Отправка сообщения о нахождение руды
        player.sendMessage(new TranslatableComponent("chat.tutorialmod.found_item.3_by_3_coordinate", itemName.getHoverName(),currentPos.getX(),currentPos.getY(),currentPos.getZ()),player.getUUID());    }

    private boolean isValuableBlock(Block block) {

        return block == ModBlocks.CITRINE_ORE.get();
    } // Проверка на ценность руды

}
