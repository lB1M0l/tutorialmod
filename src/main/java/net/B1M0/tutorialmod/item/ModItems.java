package net.B1M0.tutorialmod.item;

import net.B1M0.tutorialmod.TutorialMod;
import net.B1M0.tutorialmod.item.custom.DowsingRodItem;
import net.B1M0.tutorialmod.item.custom.DowsingRodItem3by3;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);


    public static final RegistryObject<Item> CITRINE = ITEMS.register("citrine",
            ()->new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> RAW_CITRINE = ITEMS.register("raw_citrine",
            ()->new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> DOWSING_ROD = ITEMS.register("dowsing_rod",
            ()->new DowsingRodItem(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB).durability(6)));
    public static final RegistryObject<Item> DOWSING_ROD_3_BY_3 = ITEMS.register("dowsing_rod_3_by_3",
            ()->new DowsingRodItem3by3(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB).durability(10)));
    public static final RegistryObject<Item> CUCUMBER = ITEMS.register("cucumber",
            ()->new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB).food(ModFoods.CUCUMBER)));



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
