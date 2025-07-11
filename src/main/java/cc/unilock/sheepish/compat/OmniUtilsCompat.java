package cc.unilock.sheepish.compat;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.level.BlockDropsEvent;
import uwu.lopyluna.omni_util.register.AllItems;

import static cc.unilock.sheepish.SheepishConfig.CONFIG;

public class OmniUtilsCompat {
	public static void init() {
		if (CONFIG.omniUtils.value()) {
			NeoForge.EVENT_BUS.addListener(BlockDropsEvent.class, event -> {
				if (event.getLevel().registryAccess().holder(Enchantments.SILK_TOUCH).map(enchantment -> event.getTool().getEnchantmentLevel(enchantment) == 0).orElse(true)) {
					if (event.getTool().isCorrectToolForDrops(event.getState())) {
						if (event.getState().is(BlockTags.COAL_ORES) && event.getLevel().getRandom().nextInt(100) < 15) {
							event.getDrops().add(new ItemEntity(event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), AllItems.ONYX.asStack()));
						}
						if (event.getState().is(BlockTags.REDSTONE_ORES) && event.getLevel().getRandom().nextInt(100) < 30) {
							event.getDrops().add(new ItemEntity(event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), AllItems.ONYX.asStack()));
						}
					}
				}
			});
			NeoForge.EVENT_BUS.addListener(LivingDropsEvent.class, event -> {
				if (event.getEntity().getType().is(Tags.EntityTypes.BOSSES)) {
					event.getDrops().add(new ItemEntity(event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), AllItems.HEX_SIGIL.asStack()));
				}
			});
		}
	}
}
