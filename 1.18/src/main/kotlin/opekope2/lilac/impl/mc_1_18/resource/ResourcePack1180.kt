package opekope2.lilac.impl.mc_1_18.resource

import net.minecraft.resource.ResourcePack
import net.minecraft.resource.metadata.PackResourceMetadata
import net.minecraft.text.Text
import opekope2.lilac.api.resource.IResourcePack

class ResourcePack1180(private val pack: ResourcePack) : IResourcePack {
    override fun getName(): String = pack.name

    override fun getDescription(): Text? = try {
        pack.parseMetadata(PackResourceMetadata.READER)?.description
    } catch (_: RuntimeException) {
        null
    }
}
