package opekope2.lilac.impl.tick

import net.fabricmc.api.DedicatedServerModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.server.world.ServerWorld

@Suppress("unused")
class ServerTickHandler : AbstractTickHandler(), DedicatedServerModInitializer, ServerTickEvents.EndWorldTick {
    override fun onInitializeServer() {
        ServerTickEvents.END_WORLD_TICK.register(this)
    }

    override fun onEndTick(world: ServerWorld) = tick(world)
}
