/*
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.potion;

import arekkuusu.grimoireofalice.common.lib.LibPotionName;
import net.minecraft.entity.EntityLivingBase;

public class PotionElixir extends PotionMod {

	public PotionElixir() {
		super(LibPotionName.ELIXIRPOTION, false, 0x0078E4, 1);
		setBeneficial();
	}

	@Override
	public void performEffect(EntityLivingBase entity, int amplifier) {
		//NO-OP
	}
}
