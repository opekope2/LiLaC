package opekope2.lilac.api.registry;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.VillagerProfession;
import net.minecraft.village.VillagerType;
import net.minecraft.world.World;
import opekope2.lilac.annotation.RequiresImplementation;
import opekope2.lilac.api.ILilacApi;
import org.jetbrains.annotations.NotNull;

/**
 * A wrapper interface for searching the Minecraft registry.
 */
public interface IRegistryLookup {
    /**
     * Finds the ID of the given {@code item} in the registry.
     *
     * @param item The item instance to look up
     */
    @NotNull
    Identifier lookupItemId(@NotNull Item item);

    /**
     * Finds the ID of the given {@code block} in the registry.
     *
     * @param block The block instance to look up
     */
    @NotNull
    Identifier lookupBlockId(@NotNull Block block);

    /**
     * Finds the ID of the given {@code entity} in the registry.
     *
     * @param entity The entity instance to look up
     */
    @NotNull
    Identifier lookupEntityId(@NotNull Entity entity);

    /**
     * Finds the biome ID at the given world position.
     *
     * @param world The world to look in
     * @param pos   The position to lookup biome
     */
    @NotNull
    Identifier lookupBiomeId(@NotNull World world, @NotNull BlockPos pos);

    /**
     * Finds the ID of the given villager {@code profession} in the registry.
     *
     * @param profession The villager profession instance to look up
     */
    @NotNull
    Identifier lookupVillagerProfessionId(@NotNull VillagerProfession profession);

    /**
     * Finds the ID of the given villager {@code type} in the registry.
     *
     * @param type The villager type to look up
     */
    @NotNull
    Identifier lookupVillagerTypeId(@NotNull VillagerType type);

    /**
     * Returns the implementation of {@link IRegistryLookup}.
     */
    @NotNull
    @RequiresImplementation
    static IRegistryLookup getInstance() {
        return ILilacApi.getImplementation().getRegistryLookup();
    }
}
