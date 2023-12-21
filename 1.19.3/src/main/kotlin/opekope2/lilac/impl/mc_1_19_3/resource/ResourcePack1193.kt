package opekope2.lilac.impl.mc_1_19_3.resource

import net.minecraft.resource.ResourcePack
import net.minecraft.resource.metadata.PackResourceMetadata
import net.minecraft.text.Text
import opekope2.lilac.api.resource.IResourcePack

class ResourcePack1193(private val pack: ResourcePack) : IResourcePack {
    override fun getName(): String = pack.name

    override fun getDescription(): Text? = try {
        pack.parseMetadata(PackResourceMetadata.SERIALIZER)?.description
    } catch (_: RuntimeException) {
        null
    }
}
