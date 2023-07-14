package opekope2.lilac.api.tick;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.NotNull;

/**
 * Interface to handle game ticks and "forced ticks".
 * Subscribe to these events with {@link ITickNotifier#addHandler(ITickHandler)}.
 */
public interface ITickHandler {
    /**
     * Gets called at the end of a world tick.
     *
     * @param world The ticking world
     * @see ClientTickEvents#END_WORLD_TICK
     */
    void onTick(@NotNull ClientWorld world);

    /**
     * Gets called by {@link ITickNotifier#forceTick()}.
     * May be called arbitrary times between two distinct {@link #onTick(ClientWorld)} calls.
     *
     * @see ITickNotifier#forceTick()
     */
    void onForcedTick();
}
