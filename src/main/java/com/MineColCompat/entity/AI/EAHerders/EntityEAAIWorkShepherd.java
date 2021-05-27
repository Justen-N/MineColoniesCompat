package com.MineColCompat.entity.AI.EAHerders;

import com.minecolonies.api.entity.ai.statemachine.AITarget;
import com.minecolonies.api.entity.ai.statemachine.states.IAIState;
import com.minecolonies.api.util.InventoryUtils;
import com.minecolonies.api.util.constant.ToolType;
import com.minecolonies.api.util.constant.TranslationConstants;
import com.minecolonies.coremod.colony.buildings.workerbuildings.BuildingShepherd;
import com.minecolonies.coremod.colony.jobs.JobShepherd;
import com.minecolonies.coremod.entity.ai.citizen.herders.AbstractEntityAIHerder;
import mokiyoki.enhancedanimals.entity.EnhancedSheep;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.TranslationTextComponent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.minecolonies.api.entity.ai.statemachine.states.AIWorkerState.*;
import static com.minecolonies.api.util.constant.Constants.TICKS_SECOND;

/**
 * The AI behind the {@link JobShepherd} for Breeding, Killing and Shearing sheep.
 */
public class EntityEAAIWorkShepherd extends AbstractEntityAIHerder<JobShepherd, BuildingShepherd, EnhancedSheep>
{
    /**
     * Experience given per sheep sheared.
     */
    protected static final double EXP_PER_SHEEP = 5.0;

    /**
     * Max amount of animals per Hut Level.
     */
    private static final int MAX_ANIMALS_PER_LEVEL = 2;

    /**
     * Constants used for sheep dying calculations.
     */
    private static final int HUNDRED_PERCENT_CHANCE      = 100;
    private static final int NUMBER_OF_DYE_POSSIBILITIES = 15;

    /**
     * Creates the abstract part of the AI. Always use this constructor!
     *
     * @param job the job to fulfill
     */
    public EntityEAAIWorkShepherd(@NotNull final JobShepherd job)
    {
        super(job);
        super.registerTargets(
          new AITarget(SHEPHERD_SHEAR, this::shearSheep, TICKS_SECOND)
        );
    }

    @NotNull
    @Override
    public List<ToolType> getExtraToolsNeeded()
    {
        final List<ToolType> toolsNeeded = super.getExtraToolsNeeded();
        toolsNeeded.add(ToolType.SHEARS);
        return toolsNeeded;
    }

    @Override
    public Class<BuildingShepherd> getExpectedBuildingClass()
    {
        return BuildingShepherd.class;
    }

    @Override
    public ItemStack getBreedingItem()
    {
        final ItemStack stack = new ItemStack(Items.WHEAT);
        stack.setCount(2);
        return stack;
    }

    @Override
    public int getMaxAnimalMultiplier()
    {
        return MAX_ANIMALS_PER_LEVEL;
    }

    @Override
    public IAIState decideWhatToDo()
    {

        final IAIState result = super.decideWhatToDo();

        final List<EnhancedSheep> animals = new ArrayList<>(searchForAnimals());
        final EnhancedSheep shearingSheep = animals.stream().filter(sheepie -> !sheepie.getSheared() && !sheepie.isChild()).findFirst().orElse(null);

        if (result.equals(START_WORKING) && shearingSheep != null)
        {
            return SHEPHERD_SHEAR;
        }

        return result;
    }

    @Override
    public double getButcheringAttackDamage()
    {
        return Math.max(1.0, getSecondarySkillLevel() / 10.0);
    }

    @Override
    public Class<EnhancedSheep> getAnimalClass()
    {
        return EnhancedSheep.class;
    }

    /**
     * Shears a sheep, with a chance of dying it!
     *
     * @return The next {@link IAIState}
     */
    private IAIState shearSheep()
    {
        worker.getCitizenStatusHandler().setLatestStatus(new TranslationTextComponent(TranslationConstants.COM_MINECOLONIES_COREMOD_STATUS_SHEPHERD_SHEARING));

        final List<EnhancedSheep> sheeps = searchForAnimals();

        if (sheeps.isEmpty())
        {
            return DECIDE;
        }

        if (!equipTool(Hand.MAIN_HAND, ToolType.SHEARS))
        {
            return PREPARING;
        }

        final EnhancedSheep sheep = sheeps.stream().filter(sheepie -> !sheepie.getSheared()).findFirst().orElse(null);

        if (worker.getHeldItemMainhand() != null && sheep != null)
        {
            if (walkingToAnimal(sheep))
            {
                return getState();
            }

            int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, worker.getHeldItemMainhand());
            enchantmentLevel *= Math.max(1.0, (getPrimarySkillLevel() / 5.0));

            worker.swingArm(Hand.MAIN_HAND);

            final List<ItemStack> items = new ArrayList<>();
            if (!this.world.isRemote)
            {
                sheep.setSheared(true);
                int qty = 1 + worker.getRandom().nextInt(enchantmentLevel + 1);

                for(int j = 0; j < qty; ++j)
                {
                    //items.add(new ItemStack(WOOL_BY_COLOR.get(sheep.getFleeceDyeColour())));
                }
            }
            sheep.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0F, 1.0F);

            dyeSheepChance(sheep);

            worker.getCitizenItemHandler().damageItemInHand(Hand.MAIN_HAND, 1);

            worker.getCitizenExperienceHandler().addExperience(XP_PER_ACTION);
            incrementActionsDoneAndDecSaturation();

            for (final ItemStack item : items)
            {
                InventoryUtils.transferItemStackIntoNextBestSlotInItemHandler(item, (worker.getInventoryCitizen()));
            }
        }

        return DECIDE;
    }

    /**
     * Possibly dyes a sheep based on their Worker Hut Level
     *
     * @param sheep the {@link SheepEntity} to possibly dye.
     */
    private void dyeSheepChance(final EnhancedSheep sheep)
    {
        if (worker.getCitizenColonyHandler().getWorkBuilding() != null && ((BuildingShepherd) worker.getCitizenColonyHandler().getWorkBuilding()).getSetting(BuildingShepherd.DYEING).getValue())
        {
            final int chanceToDye = worker.getCitizenColonyHandler().getWorkBuilding().getBuildingLevel();

            final int rand = world.rand.nextInt(HUNDRED_PERCENT_CHANCE);

            if (rand <= chanceToDye)
            {
                final int dyeInt = world.rand.nextInt(NUMBER_OF_DYE_POSSIBILITIES);
                sheep.setFleeceDyeColour(DyeColor.byId(dyeInt));
            }
        }
    }
}
