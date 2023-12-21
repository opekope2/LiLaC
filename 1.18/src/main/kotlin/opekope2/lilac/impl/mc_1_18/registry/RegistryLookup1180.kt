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
import opekope2.lilac.annotation.RequiresMinecraftVersion
import opekope2.lilac.api.registry.IRegistryLookup
import opekope2.lilac.util.MinecraftVersion
import kotlin.jvm.optionals.getOrNull

@RequiresMinecraftVersion(
    minVersion = MinecraftVersion.MINECRAFT_1_18,
    maxVersion = MinecraftVersion.MINECRAFT_1_18_1
)
object RegistryLookup1180 : IRegistryLookup {
    override fun lookupItemId(item: Item): Identifier = Registry.ITEM.getId(item)

    override fun lookupBlockId(block: Block): Identifier = Registry.BLOCK.getId(block)

    override fun lookupEntityId(entity: Entity): Identifier = Registry.ENTITY_TYPE.getId(entity.type)

    override fun lookupBiomeId(world: World, pos: BlockPos): Identifier =
        world.getBiomeKey(pos).getOrNull()?.value ?: throw RuntimeException("Can't find biome at `$pos` in `$world`")

    override fun lookupVillagerProfessionId(profession: VillagerProfession): Identifier =
        Registry.VILLAGER_PROFESSION.getId(profession)

    override fun lookupVillagerTypeId(type: VillagerType): Identifier = Registry.VILLAGER_TYPE.getId(type)
}
