package opekope2.lilac.impl.tick

import net.minecraft.world.World
import opekope2.lilac.api.tick.ITickHandler
import opekope2.lilac.api.tick.ITickNotifier

abstract class TickNotifier : ITickNotifier {
    private val handlers = mutableListOf<ITickHandler>()

    override fun addHandler(handler: ITickHandler) {
        handlers += handler
    }

    protected fun tick(world: World, real: Boolean) {
        handlers.forEach { handler -> handler.onTick(world, real) }
    }

    override fun tick(world: World) = tick(world, false)
}
