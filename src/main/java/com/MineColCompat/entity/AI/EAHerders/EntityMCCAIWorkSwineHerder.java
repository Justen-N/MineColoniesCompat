package com.MineColCompat.entity.AI.EAHerders;

import com.minecolonies.coremod.colony.buildings.workerbuildings.BuildingSwineHerder;
import com.minecolonies.coremod.colony.jobs.JobSwineHerder;
import com.minecolonies.coremod.entity.ai.citizen.herders.AbstractEntityAIHerder;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.jetbrains.annotations.NotNull;

/**
 * The AI behind the {@link JobSwineHerder} for Breeding and Killing Pigs.
 */
public class EntityMCCAIWorkSwineHerder extends AbstractEntityAIHerder<JobSwineHerder, BuildingSwineHerder, PigEntity>
{
    /**
     * Max amount of animals per Hut Level.
     */
    private static final int MAX_ANIMALS_PER_LEVEL = 2;

    /**
     * Creates the abstract part of the AI. Always use this constructor!
     *
     * @param job the job to fulfill
     */
    public EntityMCCAIWorkSwineHerder(@NotNull final JobSwineHerder job)
    {
        super(job);
    }

    @Override
    public Class<BuildingSwineHerder> getExpectedBuildingClass()
    {
        return BuildingSwineHerder.class;
    }

    @Override
    public ItemStack getBreedingItem()
    {
        final ItemStack stack = new ItemStack(Items.CARROT);
        stack.setCount(2);
        return stack;
    }

    @Override
    public int getMaxAnimalMultiplier()
    {
        return MAX_ANIMALS_PER_LEVEL;
    }

    @Override
    public double getButcheringAttackDamage()
    {
        return Math.max(1.0, getPrimarySkillLevel() / 10.0);
    }

    @Override
    protected boolean canFeedChildren()
    {
        return getSecondarySkillLevel() >= LIMIT_TO_FEED_CHILDREN;
    }

    @Override
    public Class<PigEntity> getAnimalClass()
    {
        return PigEntity.class;
    }
}
