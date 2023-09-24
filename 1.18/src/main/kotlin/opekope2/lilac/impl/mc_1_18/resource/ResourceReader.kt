package opekope2.lilac.impl.mc_1_18.resource

import net.minecraft.client.MinecraftClient
import net.minecraft.util.Identifier
import opekope2.lilac.api.resource.IResourceAccess
import opekope2.lilac.api.resource.IResourcePack
import opekope2.lilac.api.resource.IResourceReader
import opekope2.lilac.exception.ResourceNotFoundException
import java.io.IOException
import java.io.InputStream
import kotlin.jvm.optionals.getOrNull

class ResourceReader(private val id: Identifier) : IResourceReader {
    private val resource = try {
        manager.getResource(id)
    } catch (_: IOException) {
        null
    }
    private val resourcePack by lazy {
        val packName = resource?.resourcePackName ?: throw ResourceNotFoundException(id)
        manager.streamResourcePacks().filter { it.name == packName }.findFirst().getOrNull()
    }

    override fun exists(): Boolean = resource != null

    override fun getId(): Identifier = id

    override fun getResourcePack(): IResourcePack =
        ResourcePack(resourcePack ?: throw ResourceNotFoundException(id))

    override fun getInputStream(): InputStream = resource?.inputStream ?: throw ResourceNotFoundException(id)

    companion object ResourceAccess : IResourceAccess {
        private val manager by lazy { MinecraftClient.getInstance().resourceManager }

        override fun getResource(resourcePath: Identifier): IResourceReader = ResourceReader(resourcePath)
    }
}
