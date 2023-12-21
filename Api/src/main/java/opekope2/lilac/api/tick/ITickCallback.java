package opekope2.lilac.api.tick;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.World;
import net.minecraft.world.tick.TickManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Interface to handle game ticks. Subscribe to {@link #EVENT}.
 */
public interface ITickCallback {
    /**
     * The end of world tick event.
     */
    Event<ITickCallback> EVENT = EventFactory.createArrayBacked(
            ITickCallback.class,
            listeners -> (world, shouldTick, data) -> {
                for (ITickCallback listener : listeners) {
                    listener.onTick(world, shouldTick, data);
                }
            }
    );

    /**
     * Gets called at the end of a world tick.
     *
     * @param world      The ticking world. Should be checked if it is client or server world
     * @param shouldTick If the game has advanced a tick (not frozen)
     * @param data       Additional data for the handlers. This can be used to force update something, while the game is frozen
     * @see net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents#END_WORLD_TICK
     * @see net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents#END_WORLD_TICK
     * @see TickManager#shouldTick()
     */
    void onTick(@NotNull World world, boolean shouldTick, @Nullable Object data);
}
