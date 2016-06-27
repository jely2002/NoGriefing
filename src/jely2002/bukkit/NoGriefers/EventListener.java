import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import jely2002.bukkit.NoGriefers.Main;

public class EventListener implements org.bukkit.event.Listener {

	Main plugin;
	
	public EventListener(Main instance) {
		plugin = instance;
		}

	@EventHandler
	public void blockbreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (plugin.getConfig().getBoolean("blockbreak") == false){
			if (p.hasPermission("BreakBlocks")) {
				return;
			} else {
				e.setCancelled(true);	
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("deny-message-blockbreak")));
			}
		} else return;
	}


	@EventHandler
	public void blockplace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
				if (plugin.getConfig().getBoolean("blockplace") == false  && !p.hasPermission("PlaceBlocks")) {
					e.setCancelled(true);
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("deny-message-blockplace")));
				} else return;

			}

	@EventHandler
	public void dropitems(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if (plugin.getConfig().getBoolean("itemdrops") == false && !p.hasPermission("ItemDrops")) {
				e.setCancelled(true);
			    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("deny-message-itemdrops")));
       } else return;
	}
	}
}
