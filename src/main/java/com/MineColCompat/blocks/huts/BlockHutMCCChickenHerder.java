package com.MineColCompat.blocks.huts;

import com.MineColCompat.colony.buildings.MCCBuildings;
import com.minecolonies.api.blocks.AbstractBlockHut;
import com.minecolonies.api.colony.buildings.registry.BuildingEntry;

public class BlockHutMCCChickenHerder extends AbstractBlockHut<BlockHutMCCChickenHerder> {
    public BlockHutMCCChickenHerder()
    {
        //No different from Abstract parent
        super();
    }

    @Override
    public String getName() {
        return "blockhutmccchickenherder";
    }

    @Override
    public BuildingEntry getBuildingEntry() {
        return MCCBuildings.MCCChickenHerder;
    }
}
