package opekope2.lilac.impl.mc_1_19.resource

import net.minecraft.client.MinecraftClient
import net.minecraft.resource.Resource
import net.minecraft.util.Identifier
import opekope2.lilac.annotation.RequiresMinecraftVersion
import opekope2.lilac.api.resource.IResourceAccess
import opekope2.lilac.api.resource.IResourcePack
import opekope2.lilac.api.resource.IResourceReader
import opekope2.lilac.exception.ResourceNotFoundException
import opekope2.lilac.impl.mc_1_18.resource.ResourcePack1180
import opekope2.lilac.util.MinecraftVersion
import java.io.InputStream
import kotlin.jvm.optionals.getOrNull

class ResourceReader1190(private val id: Identifier, private val resource: Resource?) : IResourceReader {
    private val resourcePack by lazy {
        val packName = resource?.resourcePackName ?: throw ResourceNotFoundException(id)
        manager.streamResourcePacks().filter { it.name == packName }.findFirst().getOrNull()
    }

    constructor(id: Identifier) : this(id, manager.getResource(id).getOrNull())

    override fun exists(): Boolean = resource != null

    override fun getId(): Identifier = id

    override fun getResourcePack(): IResourcePack =
        ResourcePack1180(resourcePack ?: throw ResourceNotFoundException(id))

    override fun getInputStream(): InputStream = resource?.inputStream ?: throw ResourceNotFoundException(id)

    @RequiresMinecraftVersion(
        minVersion = MinecraftVersion.MINECRAFT_1_19,
        maxVersion = MinecraftVersion.MINECRAFT_1_19_2
    )
    companion object ResourceAccess : IResourceAccess, (Identifier, Resource) -> IResourceReader by ::ResourceReader1190 {
        private val manager by lazy { MinecraftClient.getInstance().resourceManager }

        override fun getResource(resourcePath: Identifier): IResourceReader = ResourceReader1190(resourcePath)
    }
}
