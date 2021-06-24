package com.MineColCompat.blocks.huts;

import com.MineColCompat.colony.buildings.MCCBuildings;
import com.minecolonies.api.blocks.AbstractBlockHut;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;

public class BlockHutEAChickenHerder extends AbstractBlockHut<BlockHutEAChickenHerder> {
    public BlockHutEAChickenHerder()
    {
        //No different from Abstract parent
        super();
    }

    @Override
    public String getName() {
        return "blockhuteachickenherder";
    }

    @Override
    public BuildingEntry getBuildingEntry() {
        return MCCBuildings.ChickenHerder;
    }
}
