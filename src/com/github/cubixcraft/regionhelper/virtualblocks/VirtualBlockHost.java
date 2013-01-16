package com.github.cubixcraft.regionhelper.virtualblocks;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class VirtualBlockHost implements Listener {
	public static VirtualBlockHost instance;
	
	private Map<Player, Set<VirtualBlock>> blocks = new HashMap<Player, Set<VirtualBlock>>();
	private Set<Player> players = blocks.keySet();
	
	public VirtualBlockHost() {
		instance = this;
	}
	
	public void updateVisibility(Player player) {
		if (players.contains(player)) {
			for (VirtualBlock block : blocks.get(player)) {
				block.updateVisibility();
			}
		}
	}
	
	public VirtualBlock addBlock(Player player, Location loc, Material material, byte data) {
		VirtualBlock block = new VirtualBlock(player, loc, material, data, this);
		
		if (!players.contains(player)) {
			blocks.put(player, new HashSet<VirtualBlock>());
		}
		blocks.get(player).add(block);
		
		return block;
	}
	
	public void removeBlock(VirtualBlock block) {
		block.showReal();
		if (players.contains(block.getPlayer())) blocks.get(players).remove(block);
	}
	
	public void removeBlock(Player player, Set<VirtualBlock> blocks) {
		for (VirtualBlock block : blocks)
			block.showReal();
		if (players.contains(player)) this.blocks.get(player).removeAll(blocks);
	}
	
	public void removeAllBlocks(Player player) {
		if (players.contains(player)) {
			for (VirtualBlock block : blocks.get(player))
				block.remove();
		}
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		updateVisibility(event.getPlayer());
	}
}
