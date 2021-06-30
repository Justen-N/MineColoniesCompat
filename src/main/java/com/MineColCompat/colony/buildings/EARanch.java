package com.MineColCompat.colony.buildings;

import com.minecolonies.api.colony.ICitizenData;
import com.minecolonies.api.colony.IColony;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import com.minecolonies.api.colony.jobs.IJob;
import com.minecolonies.api.entity.citizen.Skill;
import com.minecolonies.coremod.colony.buildings.AbstractBuildingWorker;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
/*
* Possible working tree for future changes, stubbed for now.
 */
public class EARanch extends AbstractBuildingWorker {
    public EARanch(@NotNull IColony c, BlockPos l) {
        super(c, l);
    }

    @Override
    public @NotNull IJob<?> createJob(ICitizenData iCitizenData) {
        return null;
    }

    @Override
    public @NotNull String getJobName() {
        return null;
    }

    @Override
    public @NotNull Skill getPrimarySkill() {
        return null;
    }

    @Override
    public @NotNull Skill getSecondarySkill() {
        return null;
    }

    @Override
    public BuildingEntry getBuildingRegistryEntry() {
        return null;
    }

    @Override
    public String getSchematicName() {
        return null;
    }

    @Override
    public int getMaxBuildingLevel() {
        return 0;
    }
}
