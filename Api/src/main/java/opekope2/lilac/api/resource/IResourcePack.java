package opekope2.lilac.api.resource;

import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A wrapper interface for Minecraft's ResourcePack class.
 */
public interface IResourcePack {
    /**
     * Gets the name of the resource pack.
     */
    @NotNull
    String getName();

    /**
     * Gets the description of the resource pack from its {@code pack.mcmeta}.
     */
    @Nullable
    Text getDescription();
}
