package com.github.cubixcraft.regionhelper;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;

public class MainPlugin extends JavaPlugin {
	public static MainPlugin instance;

	private WorldGuardPlugin WorldGuard;
	
	public static Logger log = Bukkit.getLogger();
	private PluginManager pm;
	
	public void onEnable() {
		instance = this;
		pm = getServer().getPluginManager();
		
		// load WorldGuard
		getWorldGuard();
	}
	
	public void onDisable() {}

	public WorldGuardPlugin getWorldGuard() {
		if (WorldGuard == null || !(this.WorldGuard instanceof WorldGuardPlugin)) {
			Plugin plugin = pm.getPlugin("WorldGuard");
			
			if (plugin != null || plugin instanceof WorldGuardPlugin) WorldGuard = (WorldGuardPlugin) plugin;
		}
		
		return WorldGuard;
	}
	
	public RegionManager getRegionManager(World world) {
		return WorldGuard.getRegionManager(world);
	}
}
