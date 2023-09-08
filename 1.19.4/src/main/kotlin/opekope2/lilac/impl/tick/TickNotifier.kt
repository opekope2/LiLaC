package opekope2.lilac.impl.tick

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.client.world.ClientWorld
import net.minecraft.server.world.ServerWorld
import net.minecraft.world.World
import opekope2.lilac.api.tick.ITickHandler
import opekope2.lilac.api.tick.ITickNotifier

object TickNotifier : ModInitializer, ITickNotifier, ClientTickEvents.EndWorldTick, ServerTickEvents.EndWorldTick {
    private val handlers = mutableListOf<ITickHandler>()

    override fun onInitialize() {
        ClientTickEvents.END_WORLD_TICK.register(this)
        ServerTickEvents.END_WORLD_TICK.register(this)
    }

    override fun addHandler(handler: ITickHandler) {
        handlers += handler
    }

    override fun tick(world: World) = handlers.forEach { handler -> handler.onTick(world, false) }

    override fun onEndTick(world: ClientWorld) = handlers.forEach { handler -> handler.onTick(world, true) }

    override fun onEndTick(world: ServerWorld) = handlers.forEach { handler -> handler.onTick(world, true) }
}
