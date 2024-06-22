package org.sennaton.sennaton_additions.SennatonBlocks;

import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.sennaton.sennaton_additions.Sennaton_Additions;

public class SennatonBlocks {

    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, Sennaton_Additions.MODID);
    public static final RegistryObject<Block> FLOATING_PORTAL = REGISTRY.register("floating_portal", () -> new FloatingPortalBlock());

    public static void register(IEventBus modEventBus) {REGISTRY.register(modEventBus);}
}
