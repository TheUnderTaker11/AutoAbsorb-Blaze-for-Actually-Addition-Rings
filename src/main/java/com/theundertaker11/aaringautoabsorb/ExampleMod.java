package com.theundertaker11.aaringautoabsorb;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = ExampleMod.MODID, name = ExampleMod.NAME, version = ExampleMod.VERSION, serverSideOnly = true, acceptableRemoteVersions = "*")
public class ExampleMod
{
    public static final String MODID = "aaringautoabsorb";
    public static final String NAME = "AA Ring Auto Absorb";
    public static final String VERSION = "1.0";

   // private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        
    }
}
