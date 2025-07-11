package cc.unilock.sheepish;

import cc.unilock.sheepish.compat.AlmostUnifiedCompat;
import cc.unilock.sheepish.compat.AnsharCompat;
import cc.unilock.sheepish.compat.ExcessiveBuildingCompat;
import cc.unilock.sheepish.compat.OmniUtilsCompat;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.RegistryAccess;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.LoadingModList;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.slf4j.Logger;

import static cc.unilock.sheepish.SheepishConfig.CONFIG;

@Mod(Sheepish.MOD_ID)
public class Sheepish {
    public static final String MOD_ID = "sheepish";
    public static final Logger LOGGER = LogUtils.getLogger();

    protected static final boolean AKASHICTOME = LoadingModList.get().getModFileById("akashictome") != null;
    protected static final boolean ALMOSTUNIFIED = LoadingModList.get().getModFileById("almostunified") != null;
    protected static final boolean ANSHAR = LoadingModList.get().getModFileById("anshar") != null;
    protected static final boolean CERULEAN = LoadingModList.get().getModFileById("cerulean") != null;
    protected static final boolean EMOJIFUL = LoadingModList.get().getModFileById("emojiful") != null;
    protected static final boolean EXCESSIVE_BUILDING = LoadingModList.get().getModFileById("excessive_building") != null;
    protected static final boolean OMNI_UTIL = LoadingModList.get().getModFileById("omni_util") != null;

    public Sheepish() {
        NeoForge.EVENT_BUS.addListener(EventPriority.LOWEST, BlockEvent.FarmlandTrampleEvent.class, event -> {
            if (CONFIG.noTrample.value()) event.setCanceled(true);
        });

        if (ALMOSTUNIFIED) {
            AlmostUnifiedCompat.init();
        }
        if (ANSHAR) {
            AnsharCompat.init();
		}
        if (EXCESSIVE_BUILDING) {
            ExcessiveBuildingCompat.init();
        }
        if (OMNI_UTIL) {
            OmniUtilsCompat.init();
        }
    }

    public static RegistryAccess getRegistryAccess() {
        if (ServerLifecycleHooks.getCurrentServer() == null) {
            if (FMLLoader.getDist().isClient() && Minecraft.getInstance().level != null) {
                return Minecraft.getInstance().level.registryAccess();
            }
        } else {
            return ServerLifecycleHooks.getCurrentServer().registryAccess();
        }
        throw new IllegalStateException();
    }
}
