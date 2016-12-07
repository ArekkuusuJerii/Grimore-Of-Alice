package arekkuusu.grimoireofalice.common.plugin.danmakucore.subentity;

import arekkuusu.grimoireofalice.common.lib.LibSubEntityName;
import arekkuusu.grimoireofalice.common.plugin.danmakucore.LibGOAShotData;
import net.katsstuff.danmakucore.data.Quat;
import net.katsstuff.danmakucore.entity.danmaku.DanmakuBuilder;
import net.katsstuff.danmakucore.entity.danmaku.EntityDanmaku;
import net.katsstuff.danmakucore.entity.danmaku.subentity.SubEntity;
import net.katsstuff.danmakucore.entity.danmaku.subentity.SubEntityType;
import net.katsstuff.danmakucore.impl.subentity.SubEntityTypeDefault;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class SubEntityLeaf extends SubEntityType {

	public SubEntityLeaf() {
		super(LibSubEntityName.LEAF);
	}

	@Override
	public SubEntity instantiate(World world, EntityDanmaku entityDanmaku) {
		return new Leaf(world, entityDanmaku);
	}

	public static class Leaf extends SubEntityTypeDefault.SubEntityDefault {

		public Leaf(World world, EntityDanmaku danmaku) {
			super(world, danmaku);
		}

		@Override
		public void subEntityTick() {
			super.subEntityTick();

			if (danmaku.isShotEndTime()) {
				for (int j = 0; j < 8; ++j) {
					danmaku.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, danmaku.posX, danmaku.posY, danmaku.posZ, 0, 0, 0);
				}
				if(!world.isRemote) {
					DanmakuBuilder.Builder danmaku = DanmakuBuilder.builder().setShot(LibGOAShotData.UFO).setSource(this.danmaku);
					world.spawnEntityInWorld(danmaku.build().asEntity());
					danmaku.setShot(danmaku.shot.setSize(6));
				}
				danmaku.setDead();
			}
		}

		@Override
		protected void impact(RayTraceResult raytrace) {
			for (int j = 0; j < 8; ++j) {
				danmaku.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, danmaku.posX, danmaku.posY, danmaku.posZ, 0.0D, 0.0D, 0.0D);
			}
			super.impact(raytrace);
		}
	}
}