package opekope2.lilac.impl.resource

import net.minecraft.resource.ResourcePack
import net.minecraft.resource.metadata.PackResourceMetadata
import net.minecraft.text.Text
import opekope2.lilac.api.resource.IResourcePack

class ResourcePack(private val pack: ResourcePack) : IResourcePack {
    override fun getName(): String = pack.name

    override fun getDescription(): Text? = try {
        pack.parseMetadata(PackResourceMetadata.SERIALIZER)?.description
    } catch (_: RuntimeException) {
        null
    }
}
