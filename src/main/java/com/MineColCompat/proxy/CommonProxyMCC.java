package com.MineColCompat.proxy;

import com.MineColCompat.apiimp.initializer.MCCBuildingInitializer;
import com.MineColCompat.apiimp.initializer.MCCJobsInitializer;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import com.minecolonies.api.colony.jobs.registry.JobEntry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(modid = "minecoloniescompat", bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class CommonProxyMCC {
    @SubscribeEvent
    public static void registerJobTypes(final RegistryEvent.Register<JobEntry> event)
    {
        MCCJobsInitializer.init(event);
    }

    @SubscribeEvent
    public static void registerBuildingTypes(@NotNull final RegistryEvent.Register<BuildingEntry> event)
    {
        MCCBuildingInitializer.init(event);
    }
}
