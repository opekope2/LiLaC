package opekope2.lilac.api.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

/**
 * A Minecraft version independent interface of {@link Toast}.
 */
public interface IToast {
    /**
     * @see Toast#getType()
     */
    default Object getType() {
        return Toast.TYPE;
    }

    /**
     * @see Toast#getWidth()
     */
    default int getWidth() {
        return 5 * Toast.BASE_HEIGHT;
    }

    /**
     * @see Toast#getHeight()
     */
    default int getHeight() {
        return Toast.BASE_HEIGHT;
    }

    /**
     * @see Toast#getRequiredSpaceCount()
     */
    default int getRequiredSpaceCount() {
        return MathHelper.ceilDiv(getHeight(), Toast.BASE_HEIGHT);
    }

    /**
     * @see Toast#draw(DrawContext, ToastManager, long)
     */
    Toast.Visibility draw(@NotNull IDrawContext1180 context, @NotNull IToastManager manager, long startTime);
}
