package opekope2.lilac.impl.mc_1_19_4.resource

import net.minecraft.client.MinecraftClient
import net.minecraft.util.Identifier
import opekope2.lilac.api.resource.IResourceAccess
import opekope2.lilac.api.resource.IResourcePack
import opekope2.lilac.api.resource.IResourceReader
import opekope2.lilac.exception.ResourceNotFoundException
import java.io.InputStream
import kotlin.jvm.optionals.getOrNull

class ResourceReader(private val id: Identifier) : IResourceReader {
    private val resource = manager.getResource(id).getOrNull()

    override fun exists(): Boolean = resource != null

    override fun getId(): Identifier = id

    override fun getResourcePack(): IResourcePack =
        ResourcePack(resource?.pack ?: throw ResourceNotFoundException(id))

    override fun getInputStream(): InputStream = resource?.inputStream ?: throw ResourceNotFoundException(id)

    companion object ResourceAccess : IResourceAccess {
        private val manager by lazy { MinecraftClient.getInstance().resourceManager }

        override fun getResource(resourcePath: Identifier): IResourceReader = ResourceReader(resourcePath)
    }
}
