package com.MineColCompat.apiimp.initializer;

import com.MineColCompat.colony.jobs.MCCJobChickenHerder;
import com.MineColCompat.colony.jobs.MCCJobs;
import com.minecolonies.api.colony.jobs.registry.JobEntry;
import com.minecolonies.coremod.colony.jobs.views.DefaultJobView;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class MCCJobsInitializer {
    private MCCJobsInitializer()
    {
        throw new IllegalStateException("Tried to initialize: MCCJobsInitializer but this is a Utility class.");
    }
    public static void init(final RegistryEvent.Register<JobEntry> event){
        final IForgeRegistry<JobEntry> reg = GameRegistry.findRegistry(JobEntry.class);

        MCCJobs.eaChickenHerder = new JobEntry.Builder()
                                .setJobProducer(MCCJobChickenHerder::new)
                                .setJobViewProducer(() -> DefaultJobView::new)
                                .setRegistryName(MCCJobs.EA_CHICKEN_HERDER_ID)
                                .createJobEntry();
        reg.register(MCCJobs.eaChickenHerder);
    }

}
