package opekope2.lilac.api.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import opekope2.lilac.api.ILilacApi;
import org.jetbrains.annotations.NotNull;

/**
 * A wrapper interface for {@link ToastManager}.
 */
public interface IToastManager {
    /**
     * @see ToastManager#add(Toast)
     */
    void add(@NotNull IToast toast);

    /**
     * @see ToastManager#clear()
     */
    void clear();

    /**
     * @see ToastManager#getNotificationDisplayTimeMultiplier()
     */
    double getNotificationDisplayTimeMultiplier();

    /**
     * @see ToastManager#getClient()
     */
    MinecraftClient getClient();

    /**
     * Gets a wrapper {@link IToastManager} for the given {@link ToastManager} instance.
     *
     * @param toastManager The {@link ToastManager} to wrap
     */
    @NotNull
    static IToastManager get(@NotNull ToastManager toastManager) {
        return ILilacApi.getImplementation().getToastManager(toastManager);
    }
}
