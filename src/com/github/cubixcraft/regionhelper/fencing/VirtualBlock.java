package com.github.cubixcraft.regionhelper.fencing;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

public class VirtualBlock {
	private Player player;
	private Location loc;
	private Material material;
	private byte data;
	private boolean visible;

	public VirtualBlock(Player player, Location loc, Material material, byte data) {
		this.player = player;
		this.loc = loc;
		this.material = material;
		this.data = data;
		
		showVirtual();
	}
	
	public void showVirtual() {
		if(!visible) {
			visible = true;
			player.sendBlockChange(loc, material, data);
		}
	}
	
	public void showReal() {
		if (visible) {
			visible = false;
			Block real = loc.getBlock();
			player.sendBlockChange(loc, real.getType(), real.getData());
		}
	}
	
	public void onPlayerMove(PlayerMoveEvent event) {
		List<Block> LineOfSight = player.getLineOfSight(null, 5);
		boolean inLine = false;

		for (Block block : LineOfSight) {
			inLine = inLine || block.getLocation().equals(this.loc);
		}
		
		if (inLine)
			showReal();
		else
			showVirtual();
	}
}
