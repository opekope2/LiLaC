package opekope2.lilac.impl.mc_1_18.registry

import net.minecraft.block.Block
import net.minecraft.entity.Entity
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.registry.Registry
import net.minecraft.village.VillagerProfession
import net.minecraft.village.VillagerType
import net.minecraft.world.World
import opekope2.lilac.api.registry.IRegistryLookup
import kotlin.jvm.optionals.getOrNull

object RegistryLookup : IRegistryLookup {
    override fun lookupItemId(item: Item): Identifier = Registry.ITEM.getId(item)

    override fun lookupBlockId(block: Block): Identifier = Registry.BLOCK.getId(block)

    override fun lookupEntityId(entity: Entity): Identifier = Registry.ENTITY_TYPE.getId(entity.type)

    override fun lookupBiomeId(world: World, pos: BlockPos): Identifier =
        world.getBiomeKey(pos).getOrNull()?.value ?: throw RuntimeException("Can't find biome at `$pos` in `$world`")

    override fun lookupVillagerProfessionId(profession: VillagerProfession): Identifier =
        Registry.VILLAGER_PROFESSION.getId(profession)

    override fun lookupVillagerTypeId(type: VillagerType): Identifier = Registry.VILLAGER_TYPE.getId(type)
}
