package opekope2.lilac.api.tick;

import net.minecraft.world.World;
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
     * @see net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents#END_WORLD_TICK
     * @see net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents#END_WORLD_TICK
     */
    void onTick(@NotNull World world);

    /**
     * Gets called by {@link ITickNotifier#forceTick(World)}.
     * May be called arbitrary times between two distinct {@link #onTick(World)} calls.
     *
     * @see ITickNotifier#forceTick(World)
     */
    void onForcedTick(@NotNull World world);
}
