package cc.unilock.sheepish.asm;

import com.google.common.collect.ImmutableSet;
import com.mojang.logging.LogUtils;
import cpw.mods.modlauncher.api.ITransformer;
import cpw.mods.modlauncher.api.ITransformerVotingContext;
import cpw.mods.modlauncher.api.TargetType;
import cpw.mods.modlauncher.api.TransformerVoteResult;
import net.neoforged.neoforgespi.coremod.ICoreMod;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.ClassNode;
import org.slf4j.Logger;

import java.util.Collections;
import java.util.Set;

public class SheepishCoreMod implements ICoreMod {
	private static final Logger LOGGER = LogUtils.getLogger();

	@Override
	public Iterable<? extends ITransformer<?>> getTransformers() {
		LOGGER.info("SheepishCoreMod");

		return Collections.singleton(
				new ITransformer<ClassNode>() {
					@Override
					public @NotNull ClassNode transform(ClassNode input, ITransformerVotingContext context) {
						for (var annotation : input.visibleAnnotations) {
							LOGGER.info("visibleAnnotations: "+annotation.toString());
						}
						for (var annotation : input.invisibleAnnotations) {
							LOGGER.info("invisibleAnnotations: "+annotation.toString());
						}
						return input;
					}

					@Override
					public @NotNull TransformerVoteResult castVote(ITransformerVotingContext context) {
						return TransformerVoteResult.YES;
					}

					@Override
					public @NotNull Set<Target<ClassNode>> targets() {
						return ImmutableSet.of(
								Target.targetClass("vectorwing.farmersdelight.integration.jei.JEIPlugin"),
								Target.targetClass("de.ellpeck.actuallyadditions.mod.jei.JEIActuallyAdditionsPlugin")
						);
					}

					@Override
					public @NotNull TargetType<ClassNode> getTargetType() {
						return TargetType.CLASS;
					}
				}
		);
	}
}
