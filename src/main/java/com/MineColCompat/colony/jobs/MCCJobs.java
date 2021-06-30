package com.MineColCompat.colony.jobs;

import com.minecolonies.api.colony.jobs.registry.JobEntry;
import com.minecolonies.api.util.constant.Constants;
import net.minecraft.util.ResourceLocation;

public final class MCCJobs {
    public static final ResourceLocation EA_CHICKEN_HERDER_ID = new ResourceLocation(Constants.MOD_ID, "eachickenherder");
    public static JobEntry eaChickenHerder;

    private MCCJobs()
    {
        throw new IllegalStateException("Tried to initialize: MCCJobs but this is a Utility class.");
    }
}
