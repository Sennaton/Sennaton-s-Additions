
package org.sennaton.sennaton_additions.client.renderer;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.Util;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import org.sennaton.sennaton_additions.SennatonMob.NynaVariant;
import org.sennaton.sennaton_additions.Sennaton_Additions;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.sennaton.sennaton_additions.client.renderer.NynaRenderer;
import software.bernie.geckolib.GeckoLib;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.cache.texture.AutoGlowingTexture;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import org.sennaton.sennaton_additions.SennatonMob.model.NynaModel;
import org.sennaton.sennaton_additions.SennatonMob.NynaEntity;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;
import software.bernie.geckolib.renderer.layer.ItemArmorGeoLayer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;


public class NynaRenderer extends GeoEntityRenderer<NynaEntity> {
	// Pre-define our bone names for easy and consistent reference later
	private static final String LEFT_HAND = "bipedHandLeft";
	private static final String RIGHT_HAND = "bipedHandRight";
	private static final String LEFT_BOOT = "armorBipedLeftFoot";
	private static final String RIGHT_BOOT = "armorBipedRightFoot";
	private static final String LEFT_ARMOR_LEG = "armorBipedLeftLeg";
	private static final String RIGHT_ARMOR_LEG = "armorBipedRightLeg";
	private static final String LEGGINGS_WAIST = "armorBipedWaist";
	private static final String CHESTPLATE = "armorBipedBody";
	private static final String RIGHT_SLEEVE = "armorBipedRightArm";
	private static final String LEFT_SLEEVE = "armorBipedLeftArm";
	private static final String HELMET = "armorBipedHead";
	public static final Map<NynaVariant, ResourceLocation> LOCATION_BY_VARIANT =
			Util.make(Maps.newEnumMap(NynaVariant.class), (p_114874_) -> {
				p_114874_.put(NynaVariant.NYNA,
						new ResourceLocation(Sennaton_Additions.MODID, "textures/entity/nyna/nyna.png"));
				p_114874_.put(NynaVariant.FRIGID_NYNA,
						new ResourceLocation(Sennaton_Additions.MODID, "textures/entity/nyna/frigid_nyna.png"));
				p_114874_.put(NynaVariant.UN_NYNA,
						new ResourceLocation(Sennaton_Additions.MODID, "textures/entity/nyna/un_nyna.png"));
				p_114874_.put(NynaVariant.FIREY_NYNA,
						new ResourceLocation(Sennaton_Additions.MODID, "textures/entity/nyna/firey_nyna.png"));
				p_114874_.put(NynaVariant.HAUNTED_NYNA,
						new ResourceLocation(Sennaton_Additions.MODID, "textures/entity/nyna/haunted_nyna.png"));

			});


	protected ItemStack mainHandItem;
	protected ItemStack offhandItem;
	public NynaRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new NynaModel());

		//addRenderLayer(new AutoGlowingGeoLayer<>(this){});
		addRenderLayer(new GlowLayer(this ));
		// Add some armor rendering
		addRenderLayer(new ItemArmorGeoLayer<>(this) {
			@Nullable
			@Override
			protected ItemStack getArmorItemForBone(GeoBone bone, NynaEntity animatable) {
				// Return the items relevant to the bones being rendered for additional rendering
				return switch (bone.getName()) {
					case LEFT_BOOT, RIGHT_BOOT -> this.bootsStack;
					case LEFT_ARMOR_LEG, RIGHT_ARMOR_LEG, LEGGINGS_WAIST -> this.leggingsStack;
					case CHESTPLATE, RIGHT_SLEEVE, LEFT_SLEEVE -> this.chestplateStack;
					case HELMET -> this.helmetStack;
					default -> null;
				};
			}

			// Return the equipment slot relevant to the bone we're using
			@Nonnull
			@Override
			protected EquipmentSlot getEquipmentSlotForBone(GeoBone bone, ItemStack stack, NynaEntity animatable) {
				return switch (bone.getName()) {
					case LEFT_BOOT, RIGHT_BOOT -> EquipmentSlot.FEET;
					case LEFT_ARMOR_LEG, RIGHT_ARMOR_LEG, LEGGINGS_WAIST -> EquipmentSlot.LEGS;
					case RIGHT_SLEEVE -> !animatable.isLeftHanded() ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
					case LEFT_SLEEVE -> animatable.isLeftHanded() ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND;
					case CHESTPLATE -> EquipmentSlot.CHEST;
					case HELMET -> EquipmentSlot.HEAD;
					default -> super.getEquipmentSlotForBone(bone, stack, animatable);
				};
			}

			// Return the ModelPart responsible for the armor pieces we want to render
			@Nonnull
			@Override
			protected ModelPart getModelPartForBone(GeoBone bone, EquipmentSlot slot, ItemStack stack, NynaEntity animatable, HumanoidModel<?> baseModel) {
				return switch (bone.getName()) {
					case LEFT_BOOT, LEFT_ARMOR_LEG -> baseModel.leftLeg;
					case RIGHT_BOOT, RIGHT_ARMOR_LEG -> baseModel.rightLeg;
					case RIGHT_SLEEVE -> baseModel.rightArm;
					case LEFT_SLEEVE -> baseModel.leftArm;
					case CHESTPLATE, LEGGINGS_WAIST -> baseModel.body;
					case HELMET -> baseModel.head;
					default -> super.getModelPartForBone(bone, slot, stack, animatable, baseModel);
				};
			}
		});

		// Add some held item rendering
		addRenderLayer(new BlockAndItemGeoLayer<>(this) {
			@Nullable
			@Override
			protected ItemStack getStackForBone(GeoBone bone, NynaEntity animatable) {
				// Retrieve the items in the entity's hands for the relevant bone
				return switch (bone.getName()) {
					case LEFT_HAND -> animatable.isLeftHanded() ?
							NynaRenderer.this.mainHandItem : NynaRenderer.this.offhandItem;
					case RIGHT_HAND -> animatable.isLeftHanded() ?
							NynaRenderer.this.offhandItem : NynaRenderer.this.mainHandItem;
					default -> null;
				};
			}

			@Override
			protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack, NynaEntity animatable) {
				// Apply the camera transform for the given hand
				return switch (bone.getName()) {
					case LEFT_HAND, RIGHT_HAND -> ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
					default -> ItemDisplayContext.NONE;
				};
			}

			// Do some quick render modifications depending on what the item is
			@Override
			protected void renderStackForBone(PoseStack poseStack, GeoBone bone, ItemStack stack, NynaEntity animatable,
											  MultiBufferSource bufferSource, float partialTick, int packedLight, int packedOverlay) {
				if (stack == NynaRenderer.this.mainHandItem) {
					poseStack.mulPose(Axis.XP.rotationDegrees(-90f));

					if (stack.getItem() instanceof ShieldItem)
						poseStack.translate(0, 0.125, -0.25);
				}
				else if (stack == NynaRenderer.this.offhandItem) {
					poseStack.mulPose(Axis.XP.rotationDegrees(-90f));

					if (stack.getItem() instanceof ShieldItem) {
						poseStack.translate(0, 0.125, 0.25);
						poseStack.mulPose(Axis.YP.rotationDegrees(180));
					}
				}

				super.renderStackForBone(poseStack, bone, stack, animatable, bufferSource, partialTick, packedLight, packedOverlay);
			}
		});

	}



	@Override
	public ResourceLocation getTextureLocation(NynaEntity animatable) {
		String variant;
		return LOCATION_BY_VARIANT.get(animatable.getVariant());
	}

	@Override
	public void render(NynaEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
					   MultiBufferSource bufferSource, int packedLight) {
		if(entity.isBaby()) {
			poseStack.scale(0.4f, 0.4f, 0.4f);
		}

		super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
	}
	@Override
	public void preRender(PoseStack poseStack, NynaEntity animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);

		this.mainHandItem = animatable.getMainHandItem();
		this.offhandItem = animatable.getOffhandItem();
	}
}

