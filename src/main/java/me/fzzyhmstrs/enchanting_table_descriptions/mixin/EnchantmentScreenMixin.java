package me.fzzyhmstrs.enchanting_table_descriptions.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.screen.ingame.EnchantmentScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mixin(EnchantmentScreen.class)
public class EnchantmentScreenMixin {
	@WrapOperation(method = "render", at = @At(value = "INVOKE", target = "java/util/List.add (Ljava/lang/Object;)Z"))
	private boolean enchanting_table_descriptions_addTooltipsToTable(List<Text> instance, Object text, Operation<Boolean> operation, @Local Enchantment enchantment){
		boolean bl = operation.call(instance,text);
		String descKey = enchantment.getTranslationKey() + ".desc";
		MutableText descText = Text.translatable(descKey);
		if (!Objects.equals(descKey,descText.getString())){
			return instance.add(descText.formatted(Formatting.DARK_GRAY));
		} else {
			return bl;
		}
	}
}