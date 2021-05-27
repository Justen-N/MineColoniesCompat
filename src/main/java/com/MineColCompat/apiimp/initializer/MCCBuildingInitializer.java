package com.MineColCompat.apiimp.initializer;

import com.MineColCompat.blocks.MCCBlocks;
import com.MineColCompat.colony.buildings.EABuildingChickenHerder;
import com.MineColCompat.colony.buildings.MCCBuildings;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import com.minecolonies.coremod.colony.buildings.AbstractBuildingWorker;
import com.minecolonies.coremod.colony.buildings.modules.SettingsModule;
import com.minecolonies.coremod.colony.buildings.modules.settings.BoolSetting;
import com.minecolonies.coremod.colony.buildings.moduleviews.SettingsModuleView;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;


public class MCCBuildingInitializer {
    private MCCBuildingInitializer()
    {
        throw new IllegalStateException("Tried to initialize: MCCBuildingsInitializer but this is a Utility class.");
    }
    public static void init(final RegistryEvent.Register<BuildingEntry> event)
    {
        final IForgeRegistry<BuildingEntry> reg = GameRegistry.findRegistry(BuildingEntry.class);
        MCCBuildings.eaChickenHerder =new BuildingEntry.Builder()
                                    .setBuildingBlock(MCCBlocks.blockHutEAChickenHerder)
                                    .setBuildingProducer(EABuildingChickenHerder::new)
                                    .setBuildingViewProducer(() -> EABuildingChickenHerder.View::new)
                                    .setRegistryName(new ResourceLocation("minecoloniescompat",MCCBuildings.EA_CHICKEN_HERDER_ID))
                                    .addBuildingModuleProducer(() -> new SettingsModule().with(AbstractBuildingWorker.BREEDING, new BoolSetting(true)), SettingsModuleView::new)
                                    .createBuildingEntry();
        reg.register(MCCBuildings.eaChickenHerder);
    }
}
