package com.example.lavariptide.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TridentItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TridentItem.class)
public class TridentItemMixin {
    @WrapOperation(
        method = {"use", "releaseUsing"},
        at = @At(value = "INVOKE",
                 target = "Lnet/minecraft/world/entity/player/Player;isInWaterOrRain()Z")
    )
    private boolean lavariptide$allowLava(Player player, Operation<Boolean> original) {
        return original.call(player) || player.isInLava();
    }
}
