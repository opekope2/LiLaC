package opekope2.lilac.impl.tick

import net.fabricmc.loader.api.Version
import net.minecraft.world.World
import opekope2.lilac.api.tick.ITickCallback
import opekope2.lilac.util.Util.checkModVersion

abstract class AbstractTickHandler {
    private val shouldTick =
        if (checkModVersion("minecraft") { it >= Version.parse("1.20.3") }) ::shouldTick1203
        else ::shouldTick1180

    private fun shouldTick1180(@Suppress("UNUSED_PARAMETER") world: World) = true

    private fun shouldTick1203(world: World) = world.tickManager.shouldTick()

    fun tick(world: World) = ITickCallback.EVENT.invoker().onTick(world, shouldTick(world), null)
}
