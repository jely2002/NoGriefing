package jely2002.bukkit.NoGriefing;


import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import jely2002.bukkit.NoGriefing.Main;

public class EventListener implements org.bukkit.event.Listener {

	private final ArrayList<Player> cooldown = new ArrayList<Player>();

	Main plugin;
	public EventListener(Main instance) {
		plugin = instance;
	}


	@EventHandler
	public void blockbreak(BlockBreakEvent e) {
		final Player p = e.getPlayer();
		if (plugin.getConfig().getBoolean("blockbreak") == false && !plugin.getConfig().getStringList("disabled-worlds").contains(e.getPlayer().getWorld().getName())){
			if (p.hasPermission("BreakBlocks")) {
				return;
			} else {
				if (!cooldown.contains(p)) {
					cooldown.add(p);
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("deny-message-blockbreak")));
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						public void run() {
							cooldown.remove(p);
						}
					}, 30);			  		
				} 
				e.setCancelled(true);	
			}
		} else return;
	}

	//@EventHandler
	//public void bucketfill(PlayerBucketFillEvent e) {
	//	final Player p = e.getPlayer();
	//	if (plugin.getConfig().getBoolean("blockbreak") == false  && !p.hasPermission("BreakBlock") && !plugin.getConfig().getStringList("disabled-worlds").contains(e.getPlayer().getWorld().getName())) {
	//		e.setCancelled(true);
	//		if (!cooldown.contains(p)) {
	//			cooldown.add(p);
	//			e.setCancelled(true);
	//			p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("deny-message-blockbreak")));
	//			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
	//				public void run() {
	//					cooldown.remove(p);
	//				}
	//			}, 30);			  		 
	//		}		
	//	} else return;
//	}


	@EventHandler
	public void blockplace(BlockPlaceEvent e) {
		final Player p = e.getPlayer();
		if (plugin.getConfig().getBoolean("blockplace") == false  && !p.hasPermission("PlaceBlocks") && !plugin.getConfig().getStringList("disabled-worlds").contains(e.getPlayer().getWorld().getName())) {
			if (!cooldown.contains(p)) {
				cooldown.add(p);
				e.setCancelled(true);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("deny-message-blockplace")));
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						cooldown.remove(p);
					}
				}, 30);			  		 
				e.setCancelled(true);	
			}
			e.setCancelled(true);		
		} else return;
	}


    @EventHandler
    public void BucketEmpty(PlayerBucketEmptyEvent e) {
        final Player p = e.getPlayer();
        if (plugin.getConfig().getBoolean("blockplace") == false && !p.hasPermission("PlaceBlocks") && !plugin.getConfig().getStringList("disabled-worlds").contains(p.getWorld().getName())) {
    		e.setCancelled(true);
			if (!cooldown.contains(p)) {
				cooldown.add(p);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("deny-message-blockplace")));
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						cooldown.remove(p);
					}
				}, 30);			  
			}
    		e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void BucketEmpty(PlayerBucketFillEvent e) {
        final Player p = e.getPlayer();
        if (plugin.getConfig().getBoolean("blockbreak") == false && !p.hasPermission("BreakBlocks") && !plugin.getConfig().getStringList("disabled-worlds").contains(p.getWorld().getName())) {
			if (!cooldown.contains(p)) {
				cooldown.add(p);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("deny-message-blockbreak")));
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						cooldown.remove(p);
					}
				}, 30);			  
			}
    		e.setCancelled(true);
        }
    }


	@EventHandler
	public void pickupitems(PlayerPickupItemEvent e) {
		final Player p = e.getPlayer();
		if (plugin.getConfig().getBoolean("itempickup") == false && !p.hasPermission("ItemPickup")  && !plugin.getConfig().getStringList("disabled-worlds").contains(e.getPlayer().getWorld().getName())) {
			if (!cooldown.contains(p)) {
				cooldown.add(p);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("deny-message-itempickup")));
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						cooldown.remove(p);
					}
				}, 30);			  
			}
			e.setCancelled(true);
		} else return;
	}

	@EventHandler
	public void dropitems(PlayerDropItemEvent e) {
		final Player p = e.getPlayer();
		if (plugin.getConfig().getBoolean("itemdrops") == false && !p.hasPermission("ItemDrops")  && !plugin.getConfig().getStringList("disabled-worlds").contains(e.getPlayer().getWorld().getName())) {
			if (!cooldown.contains(p)) {
				cooldown.add(p);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("deny-message-itemdrops")));
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						cooldown.remove(p);
					}
				}, 30);				
			}
			e.setCancelled(true);
		} else return;
	}

	@EventHandler
	public void antitntevent(EntityExplodeEvent e) {
		if (plugin.getConfig().getBoolean("antittnt") && e.getEntityType() == EntityType.PRIMED_TNT && !plugin.getConfig().getStringList("disabled-worlds").contains(e.getLocation().getWorld().getName())) {
			e.setCancelled(true);
		} else return;
	}

	@EventHandler
	public void antitntevent2(ExplosionPrimeEvent e) {
		if (plugin.getConfig().getBoolean("antitnt") && e.getEntityType() == EntityType.PRIMED_TNT && !plugin.getConfig().getStringList("disabled-worlds").contains(e.getEntity().getLocation().getWorld().getName())) {
			e.setCancelled(true);
		} else return;
	}


	@EventHandler
	public void use(InventoryOpenEvent e) {
		HumanEntity p = e.getPlayer();
		if (plugin.getConfig().getBoolean("inventory-use") == false && !plugin.getConfig().getStringList("disabled-worlds").contains(e.getPlayer().getWorld().getName())) {
			if (!p.hasPermission("use")) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("deny-message-use")));
				e.setCancelled(true);
			}
		} else return;
	}

	@EventHandler
	public void blocklogger(BlockPlaceEvent e) {	
		String blockplaced = e.getBlock().getType().toString();
		Player placer = e.getPlayer();
		if (plugin.getConfig().getBoolean("enable-blocklog"))  {	
			if (plugin.loggedblocks.contains(blockplaced)) {
				plugin.logger.put(placer, blockplaced);
			} 
		}	
	}
	
	@EventHandler
	public void nodamage(EntityDamageByEntityEvent e) {
		if (plugin.getConfig().getBoolean("antitnt") && !plugin.getConfig().getStringList("disabled-worlds").contains(e.getDamager().getLocation().getWorld().getName())) {
			if (e.getDamager() instanceof Player) {
				if (!e.getDamager().hasPermission("pvp")) {
					final Player p = (Player) e.getDamager();
					if (!cooldown.contains(p)) {
						cooldown.add(p);
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("deny-message-pvp").replace("%s", e.getEntity().getName())));
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							public void run() {
								cooldown.remove(p);
							}
						}, 30);				
					}
					e.setCancelled(true);
				}
			}
		} else return;
	}
	
	@EventHandler
	public void noarrowdamage(EntityShootBowEvent e) {
		if (plugin.getConfig().getBoolean("antitnt") && !plugin.getConfig().getStringList("disabled-worlds").contains(e.getEntity().getLocation().getWorld().getName())) {
			if (e.getEntity() instanceof Player) {
				if (!e.getEntity().hasPermission("pvp")) {
					final Player p = (Player) e.getEntity();
					if (!cooldown.contains(p)) {
						cooldown.add(p);
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("deny-message-bow")));
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							public void run() {
								cooldown.remove(p);
							}
						}, 30);				
					}
					e.setCancelled(true);
				}
			}
		} else return;
	}
	
	@EventHandler
	public void joinupdate(PlayerJoinEvent e) {	
		Player p = e.getPlayer();
		if (plugin.uptodate == false && plugin.getConfig().getBoolean("update-check") && p.isOp()) {
			p.sendMessage(ChatColor.GREEN + "NoGriefing " + ChatColor.GRAY + "A new version of the plugin was found!");
			p.sendMessage(ChatColor.GREEN + "NoGriefing " + ChatColor.GRAY + "Download it at:");
			p.sendMessage(ChatColor.GREEN + "NoGriefing " + ChatColor.GRAY + "http://dev.bukkit.org/bukkit-plugins/antigrief-nogriefers/");
		} else return;
	}
}



