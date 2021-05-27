package com.MineColCompat.colony.jobs;

import com.MineColCompat.entity.AI.EAHerders.EntityEAAIWorkChickenHerder;
import com.minecolonies.api.client.render.modeltype.BipedModelType;
import com.minecolonies.api.colony.ICitizenData;
import com.minecolonies.api.colony.jobs.ModJobs;
import com.minecolonies.api.colony.jobs.registry.JobEntry;
import com.minecolonies.coremod.colony.jobs.AbstractJob;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The Chicken Herder job
 */
public class EAJobChickenHerder extends AbstractJob<EntityEAAIWorkChickenHerder, EAJobChickenHerder>
{
    /**
     * Instantiates the placeholder job.
     *
     * @param entity the entity.
     */
    public EAJobChickenHerder(final ICitizenData entity)
    {
        super(entity);
    }

    @Override
    public JobEntry getJobRegistryEntry()
    {
        return ModJobs.chickenHerder;
    }

    @NotNull
    @Override
    public String getName()
    {
        return "com.minecolonies.coremod.job.ChickenHerder";
    }

    /**
     * Generate your AI class to register.
     *
     * @return your personal AI instance.
     */
    @Nullable
    @Override
    public EntityEAAIWorkChickenHerder generateAI()
    {
        return new EntityEAAIWorkChickenHerder(this);
    }

    @Override
    public boolean pickupSuccess(@NotNull final ItemStack pickedUpStack)
    {
        if (pickedUpStack.getItem() == Items.FEATHER || pickedUpStack.getItem() == Items.EGG)
        {
            return getCitizen().getRandom().nextInt((getCitizen().getCitizenSkillHandler().getLevel(getCitizen().getWorkBuilding().getPrimarySkill()))) > 1;
        }
        return true;
    }

    /**
     * Get the RenderBipedCitizen.Model to use when the Citizen performs this job role.
     *
     * @return Model of the citizen.
     */
    @NotNull
    @Override
    public BipedModelType getModel()
    {
        return BipedModelType.CHICKEN_FARMER;
    }
}
