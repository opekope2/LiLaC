package opekope2.lilac.impl.resource

import net.minecraft.resource.ResourcePack
import opekope2.lilac.api.resource.IResourcePack

class ResourcePack(private val pack: ResourcePack) : IResourcePack {
    override fun getName(): String = pack.name

    @Deprecated("Deprecated in Java")
    override fun getResourcePack(): ResourcePack = pack
}
