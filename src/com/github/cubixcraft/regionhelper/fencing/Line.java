package com.github.cubixcraft.regionhelper.fencing;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.github.cubixcraft.regionhelper.virtualblocks.VirtualBlockHost;

public class Line {
	private VirtualBlockHost host;
	
	private Player player;
	private Location start;
	private Location end;
	
	public Line(Player player, Location start, Location end, Material material, byte data, VirtualBlockHost host) {
		this.player = player;
		this.start = start;
		this.end = end;
		this.host = host;
		
		host.addBlock(player, start, material, data);
	}
	
	public Line(Player player, Location start, Location end, Material material, byte data) {
		this(player, start, end, material, data, VirtualBlockHost.instance);
	}
}
