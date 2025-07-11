package cc.unilock.sheepish;

import folk.sisby.kaleido.api.ReflectiveConfig;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.Comment;
import folk.sisby.kaleido.lib.quiltconfig.api.values.TrackedValue;
import folk.sisby.kaleido.lib.quiltconfig.api.values.ValueList;
import net.neoforged.fml.loading.FMLPaths;

public class SheepishConfig extends ReflectiveConfig {
	public static final SheepishConfig CONFIG = SheepishConfig.createToml(FMLPaths.CONFIGDIR.get(), "", "sheepish", SheepishConfig.class);

	@Comment("Allow players to eat food regardless of hunger bar fullness")
	public final TrackedValue<Boolean> alwaysEat = value(false);

	@Comment("Disable Actually Additions and Farmer's Delight JEI plugins")
	public final TrackedValue<Boolean> disableJeiPlugins = value(false);

	@Comment("Disable custom splash texts from various mods")
	public final TrackedValue<Boolean> disableSplashes = value(false);

	@Comment({
			"Enable Emojiful's pixelated Twemoji set, without the \"Blobs\", \"Discord\", or \"Pepe\" categories",
			"Requires \"Emojiful.EmojiTypes.custom = false\" in \"emojiful-client.toml\""
	})
	public final TrackedValue<Boolean> emojifulPixelatedTwemoji = value(false);

	@Comment("Prevent AbstractHorse offspring from having lesser stats than either of its parents")
	public final TrackedValue<Boolean> horseStonks = value(false);

	@Comment("Enchantments to ignore when randomly enchanting loot")
	public final TrackedValue<ValueList<String>> ignoredEnchantments = list(
			"",
			"nova_structures:shulker_boss",
			"nova_structures:shulker_miniboss"
	);

	@Comment("Prevent all entities from trampling farmland")
	public final TrackedValue<Boolean> noTrample = value(false);

	@Comment({
			"Allow obtaining the Hex Sigil, Garnet, and Onyx from Omni Utils",
			"- Hex Sigil: drops from entities in #c:bosses",
			"- Garnet: 30% chance drop from blocks in #c:redstone_ores",
			"- Onyx: 15% chance drop from blocks in #c:coal_ores"
	})
	public final TrackedValue<Boolean> omniUtils = value(false);
}
