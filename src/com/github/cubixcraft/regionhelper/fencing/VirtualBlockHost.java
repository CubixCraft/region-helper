package com.github.cubixcraft.regionhelper.fencing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class VirtualBlockHost implements Listener {
	public static VirtualBlockHost instance;
	
	private Map<Player, Set<VirtualBlock>> blocks = new HashMap<Player, Set<VirtualBlock>>();
	private Set<Player> players = blocks.keySet();
	
	public VirtualBlockHost() {
		instance = this;
	}

	@EventHandler
	void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (players.contains(player)) {
			for (VirtualBlock block : blocks.get(player)) {
				block.onPlayerMove(event);
			}
		}
	}
	
	@EventHandler
	void onPlayerChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();

		Set<VirtualBlock> set = new HashSet<VirtualBlock>();
		set.add(new VirtualBlock(player, player.getTargetBlock(null, 100).getLocation(), Material.BRICK, (byte) 0x00));
		
		blocks.put(player, set);

		event.setCancelled(true);
	}
}
