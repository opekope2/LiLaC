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
     * @param world The ticking world. Should be checked if it is client or server world
     * @param real  True, if the tick was called from the game, false if it was called by {@link ITickNotifier#tick(World)}
     * @see net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents#END_WORLD_TICK
     * @see net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents#END_WORLD_TICK
     */
    void onTick(@NotNull World world, boolean real);
}
