package opekope2.lilac.api.tick;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.world.World;
import opekope2.lilac.annotation.RequiresImplementation;
import opekope2.lilac.api.ILilacApi;
import org.jetbrains.annotations.NotNull;

/**
 * Interface to notify handlers about game ticks, or "forced ticks" (when the game is paused, but update is needed).
 *
 * @see ClientTickEvents#END_WORLD_TICK
 */
public interface ITickNotifier {
    /**
     * Subscribes to a game tick event.
     *
     * @param handler The event handler to subscribe with
     * @see ITickHandler
     */
    void addHandler(@NotNull ITickHandler handler);

    /**
     * Subscribes to a game tick event, but with Kotlin syntax.
     *
     * @param handler The event handler to subscribe with
     * @see ITickHandler
     */
    default void plusAssign(@NotNull ITickHandler handler) {
        addHandler(handler);
    }

    /**
     * Force-updates every subscribed game tick handler.
     *
     * @param world The ticking world
     * @see ITickHandler#onForcedTick(World)
     */
    void forceTick(@NotNull World world);

    /**
     * Returns the implementation of {@link ITickNotifier}.
     */
    @NotNull
    @RequiresImplementation
    static ITickNotifier getInstance() {
        return ILilacApi.getImplementation().getTickNotifier();
    }
}
