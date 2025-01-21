package org.sennaton.sennaton_additions.world.teleporter;

import java.util.function.Consumer;
import java.util.function.Function;
import net.kyrptonaught.customportalapi.CustomPortalApiRegistry;
import net.kyrptonaught.customportalapi.CustomPortalBlock;
import net.kyrptonaught.customportalapi.CustomPortalsMod;
import net.kyrptonaught.customportalapi.portal.PortalIgnitionSource;
import net.kyrptonaught.customportalapi.util.CPASoundEventData;
import net.kyrptonaught.customportalapi.util.ColorUtil;
//import net.kyrptonaught.customportalapi.util.FloatingLink;
import net.kyrptonaught.customportalapi.util.SHOULDTP;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FloatingPortalBuilder {
    private final FloatingLink floatingLink = new FloatingLink();

    private FloatingPortalBuilder() {
    }

    public static FloatingPortalBuilder beginPortal() {
        return new FloatingPortalBuilder();
    }

    public void registerPortal() {
        CustomPortalApiRegistry.addPortal(this.floatingLink.block, this.floatingLink);
    }

    public FloatingPortalBuilder frameBlock(ResourceLocation blockID) {
        this.floatingLink.block = ForgeRegistries.BLOCKS.getValue(blockID);
        return this;
    }

    public FloatingPortalBuilder frameBlock(Block block) {
        this.floatingLink.block = block;
        return this;
    }

    public FloatingPortalBuilder destDimID(ResourceLocation dimID) {
        this.floatingLink.dimID = dimID;
        return this;
    }

    public FloatingPortalBuilder tintColor(int color) {
        this.floatingLink.colorID = color;
        return this;
    }

    public FloatingPortalBuilder tintColor(int r, int g, int b) {
        this.floatingLink.colorID = ColorUtil.getColorFromRGB(r, g, b);
        return this;
    }

    public FloatingPortalBuilder lightWithWater() {
        this.floatingLink.portalIgnitionSource = PortalIgnitionSource.WATER;
        return this;
    }

    public FloatingPortalBuilder lightWithItem(Item item) {
        this.floatingLink.portalIgnitionSource = PortalIgnitionSource.ItemUseSource(item);
        return this;
    }

    public FloatingPortalBuilder lightWithFluid(Fluid fluid) {
        this.floatingLink.portalIgnitionSource = PortalIgnitionSource.FluidSource(fluid);
        return this;
    }

    public FloatingPortalBuilder customIgnitionSource(ResourceLocation customSourceID) {
        this.floatingLink.portalIgnitionSource = PortalIgnitionSource.CustomSource(customSourceID);
        return this;
    }

    public FloatingPortalBuilder customIgnitionSource(PortalIgnitionSource ignitionSource) {
        this.floatingLink.portalIgnitionSource = ignitionSource;
        return this;
    }

    public FloatingPortalBuilder forcedSize(int width, int height) {
        this.floatingLink.forcedWidth = width;
        this.floatingLink.forcedHeight = height;
        return this;
    }

    public FloatingPortalBuilder customPortalBlock(RegistryObject<CustomPortalBlock> portalBlock) {
        this.floatingLink.setPortalBlock(portalBlock);
        return this;
    }

    public FloatingPortalBuilder returnDim(ResourceLocation returnDimID, boolean onlyIgnitableInReturnDim) {
        this.floatingLink.returnDimID = returnDimID;
        this.floatingLink.onlyIgnitableInReturnDim = onlyIgnitableInReturnDim;
        return this;
    }

    public FloatingPortalBuilder onlyLightInOverworld() {
        this.floatingLink.onlyIgnitableInReturnDim = true;
        return this;
    }

    public FloatingPortalBuilder flatPortal() {
        this.floatingLink.portalFrameTester = CustomPortalsMod.FLATPORTAL_FRAMETESTER;
        return this;
    }

    public FloatingPortalBuilder customFrameTester(ResourceLocation frameTester) {
        this.floatingLink.portalFrameTester = frameTester;
        return this;
    }

    public FloatingPortalBuilder registerBeforeTPEvent(Function<Entity, SHOULDTP> event) {
        this.floatingLink.getBeforeTPEvent().register(event);
        return this;
    }

    public FloatingPortalBuilder registerInPortalAmbienceSound(Function<Player, CPASoundEventData> event) {
        this.floatingLink.getInPortalAmbienceEvent().register(event);
        return this;
    }

    public FloatingPortalBuilder registerPostTPPortalAmbience(Function<Player, CPASoundEventData> event) {
        this.floatingLink.getPostTpPortalAmbienceEvent().register(event);
        return this;
    }

    public FloatingPortalBuilder registerPostTPEvent(Consumer<Entity> event) {
        this.floatingLink.setPostTPEvent(event);
        return this;
    }
}

