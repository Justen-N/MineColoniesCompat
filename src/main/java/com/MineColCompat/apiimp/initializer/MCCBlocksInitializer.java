package com.MineColCompat.apiimp.initializer;

import com.MineColCompat.blocks.MCCBlocks;
import com.MineColCompat.blocks.huts.BlockHutMCCChickenHerder;
import com.minecolonies.api.creativetab.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = "minecoloniescompat", bus = Mod.EventBusSubscriber.Bus.MOD)
public class MCCBlocksInitializer {
    private MCCBlocksInitializer()
    {
        throw new IllegalStateException("Tried to initialize: MCCBlockInitializer but this is a Utility class.");
    }
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        MCCBlocksInitializer.init(event.getRegistry());
    }

    public static void init(final IForgeRegistry<Block> registry)
    {
        MCCBlocks.blockHutMCCChickenHerder = new BlockHutMCCChickenHerder().registerBlock(registry);
    }
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        MCCBlocksInitializer.registerBlockItem(event.getRegistry());
    }
    public static void registerBlockItem(final IForgeRegistry<Item> registry)
    {
        final Item.Properties properties = new Item.Properties().group(ModCreativeTabs.MINECOLONIES);
        MCCBlocks.blockHutMCCChickenHerder.registerBlockItem(registry,properties);
    }
}
