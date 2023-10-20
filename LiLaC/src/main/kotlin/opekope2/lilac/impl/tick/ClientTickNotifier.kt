package opekope2.lilac.impl.tick

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.client.world.ClientWorld
import net.minecraft.server.world.ServerWorld

object ClientTickNotifier : TickNotifier(), ClientModInitializer, ClientTickEvents.EndWorldTick,
    ServerTickEvents.EndWorldTick {
    override fun onInitializeClient() {
        ClientTickEvents.END_WORLD_TICK.register(this)
        ServerTickEvents.END_WORLD_TICK.register(this)
    }

    override fun onEndTick(world: ClientWorld) {
        tick(world, real = true)
    }

    override fun onEndTick(world: ServerWorld) {
        tick(world, real = true)
    }
}
