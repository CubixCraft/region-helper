package com.github.cubixcraft.regionhelper.virtualblocks;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class VirtualBlock {
	private Player player;
	private Location loc;
	private Material material;
	private byte data;
	private VirtualBlockHost host;
	private boolean visible;

	public VirtualBlock(Player player, Location loc, Material material, byte data, VirtualBlockHost host) {
		this.player = player;
		this.loc = loc;
		this.material = material;
		this.data = data;
		this.host = host;
		
		showVirtual();
	}
	
	public void remove() {
		showReal();
		host.removeBlock(this);
	}
	
	public void finalize() {
		showReal();
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
	
	public Player getPlayer() {
		return player;
	}

	public void updateVisibility() {
		List<Block> LineOfSight = player.getLineOfSight(null, 5);
		boolean inLine = false;

		for (Block block : LineOfSight)
			inLine = inLine || block.getLocation().equals(this.loc);
		
		if (inLine)
			showReal();
		else
			showVirtual();
	}
}
