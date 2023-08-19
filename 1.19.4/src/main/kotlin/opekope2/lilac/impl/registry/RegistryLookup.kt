package opekope2.lilac.impl.registry

import net.minecraft.block.Block
import net.minecraft.entity.Entity
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.village.VillagerProfession
import net.minecraft.village.VillagerType
import net.minecraft.world.World
import opekope2.lilac.api.registry.IRegistryLookup
import kotlin.jvm.optionals.getOrNull

object RegistryLookup : IRegistryLookup {
    override fun lookupBlockId(block: Block): Identifier = Registries.BLOCK.getId(block)

    override fun lookupEntityId(entity: Entity): Identifier = Registries.ENTITY_TYPE.getId(entity.type)

    override fun lookupBiomeId(world: World, pos: BlockPos): Identifier =
        world.getBiome(pos).key.getOrNull()?.value ?: throw RuntimeException("Can't find biome at $pos (world=$world)")

    override fun lookupVillagerProfessionId(profession: VillagerProfession): Identifier =
        Registries.VILLAGER_PROFESSION.getId(profession)

    override fun lookupVillagerTypeId(type: VillagerType): Identifier = Registries.VILLAGER_TYPE.getId(type)
}