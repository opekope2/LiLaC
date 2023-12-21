package opekope2.lilac.impl.mc_1_19_3.registry

import net.minecraft.block.Block
import net.minecraft.entity.Entity
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.village.VillagerProfession
import net.minecraft.village.VillagerType
import net.minecraft.world.World
import opekope2.lilac.annotation.RequiresMinecraftVersion
import opekope2.lilac.api.registry.IRegistryLookup
import opekope2.lilac.util.MinecraftVersion
import kotlin.jvm.optionals.getOrNull

@RequiresMinecraftVersion(
    minVersion = MinecraftVersion.MINECRAFT_1_19_3,
)
object RegistryLookup1193 : IRegistryLookup {
    override fun lookupItemId(item: Item): Identifier = Registries.ITEM.getId(item)

    override fun lookupBlockId(block: Block): Identifier = Registries.BLOCK.getId(block)

    override fun lookupEntityId(entity: Entity): Identifier = Registries.ENTITY_TYPE.getId(entity.type)

    override fun lookupBiomeId(world: World, pos: BlockPos): Identifier =
        world.getBiome(pos).key.getOrNull()?.value ?: throw RuntimeException("Can't find biome at `$pos` in `$world`")

    override fun lookupVillagerProfessionId(profession: VillagerProfession): Identifier =
        Registries.VILLAGER_PROFESSION.getId(profession)

    override fun lookupVillagerTypeId(type: VillagerType): Identifier = Registries.VILLAGER_TYPE.getId(type)
}
