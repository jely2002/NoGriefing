package jely2002.bukkit.NoGriefing;


import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;

import jely2002.bukkit.NoGriefing.Main;

public class EventListener implements org.bukkit.event.Listener {

	private final ArrayList<Player> cooldown = new ArrayList<Player>();

	Main plugin;
	public EventListener(Main instance) {
		plugin = instance;
	}


	//-------------------------------------- No block place/break (blockplace / blockbreak) ----------------------------------//
	
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
	
	@EventHandler
	public void noUproot(PlayerInteractEvent e)	{
		if (plugin.getConfig().getBoolean("blockbreak") == false && !plugin.getConfig().getStringList("disabled-worlds").contains(e.getPlayer().getWorld().getName())){
			if (!e.getPlayer().hasPermission("BreakBlocks"))
				if(e.getAction() == Action.PHYSICAL && e.getClickedBlock().getType() == Material.SOIL)
					e.setCancelled(true);
		}
	}

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

    //disables lava or water grief.
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
    
    //disables lava or water grief.
    @EventHandler
    public void BucketFill(PlayerBucketFillEvent e) {
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


	//------------------------------------- No item pickup (itempickup) ----------------------------------//
    
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
	
	//-------------------------------------- No item drop (itemdrops) ----------------------------------//
	
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

	//-------------------------------------- No tnt (antitnt) ----------------------------------//
	
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

	//-------------------------------------- No inventory use (inventory-use) ----------------------------------//
	
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
	
	//-------------------------------------- Block logger (blocklog) ----------------------------------//

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
	
	//--------------------------------------- No arrow damage & no pvp & no entity effects/attacks (pvp) ----------------------------------//
	
	@EventHandler
	public void nodamage(EntityDamageByEntityEvent e) {
		if (plugin.getConfig().getBoolean("pvp") && !plugin.getConfig().getStringList("disabled-worlds").contains(e.getDamager().getLocation().getWorld().getName())) {
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
	public void noentityeffects(EntityDamageEvent e) {
		if (plugin.getConfig().getBoolean("pvp") && !plugin.getConfig().getStringList("disabled-worlds").contains(e.getEntity().getLocation().getWorld().getName())) {
			if (e.getEntity() instanceof Player) {
				if (!e.getEntity().hasPermission("pvp")) {
					if (e.getCause() == DamageCause.WITHER || e.getCause() == DamageCause.DRAGON_BREATH || e.getCause() == DamageCause.ENTITY_ATTACK || e.getCause() == DamageCause.ENTITY_EXPLOSION || e.getCause() == DamageCause.MAGIC) {
					e.setCancelled(true);
					}
				}
			}
		} else return;
	}
	
	
	@EventHandler
	public void noarrowdamage(EntityShootBowEvent e) {
		if (plugin.getConfig().getBoolean("pvp") && !plugin.getConfig().getStringList("disabled-worlds").contains(e.getEntity().getLocation().getWorld().getName())) {
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
	
	//--------------------------------------Fall damage negater (falldamage) ----------------------------------//
	
	@EventHandler
	public void nofalldamage(EntityDamageEvent e) {
		if (plugin.getConfig().getBoolean("falldamage") == false && !plugin.getConfig().getStringList("disabled-worlds").contains(e.getEntity().getLocation().getWorld().getName())) {
			if (e.getEntity() instanceof Player) {
				if (!e.getEntity().hasPermission("FallDamage")) {
					if (e.getCause() == DamageCause.FALL || e.getCause() == DamageCause.CONTACT) {
					e.setCancelled(true);
					}
				}
			}
		} else return;
	}
	
	//--------------------------------------- Drowning (drowning) ----------------------------------//
	
	@EventHandler
	public void nodrowndamage(EntityDamageEvent e) {
		if (plugin.getConfig().getBoolean("drowning") == false && !plugin.getConfig().getStringList("disabled-worlds").contains(e.getEntity().getLocation().getWorld().getName())) {
			if (e.getEntity() instanceof Player) {
				if (!e.getEntity().hasPermission("Drowning")) {
					if (e.getCause() == DamageCause.DROWNING) {
					e.setCancelled(true);
					}
				}
			}
		} else return;
	}
	
	//--------------------------------------- Fire & related (fire) ----------------------------------//
	
	@EventHandler
	public void nofiredamage(EntityDamageEvent e) {
		if (plugin.getConfig().getBoolean("fire") == false && !plugin.getConfig().getStringList("disabled-worlds").contains(e.getEntity().getLocation().getWorld().getName())) {
			if (e.getEntity() instanceof Player) {
				if (!e.getEntity().hasPermission("Fire")) {
					if (e.getCause() == DamageCause.FIRE || e.getCause() == DamageCause.FIRE_TICK || e.getCause() == DamageCause.LIGHTNING || e.getCause() == DamageCause.HOT_FLOOR || e.getCause() == DamageCause.LAVA) {
					e.setCancelled(true);
					}
				}
			}
		} else return;
	}
	
	//--------------------------------------- hunger (hunger) ----------------------------------//
	
	@EventHandler
	public void nohunger(FoodLevelChangeEvent e) {
		if (plugin.getConfig().getBoolean("hunger") == false && !plugin.getConfig().getStringList("disabled-worlds").contains(e.getEntity().getLocation().getWorld().getName())) {
			if (e.getEntity() instanceof Player) {
				if (!e.getEntity().hasPermission("Hunger")) {
					e.setCancelled(true);
				}
			}
		} else return;
	}
	
	//--------------------------------------- tool-use (tool-use) ----------------------------------//
	
	@EventHandler
	public void noshears(PlayerShearEntityEvent e) {
		if (plugin.getConfig().getBoolean("tool-use") == false && !plugin.getConfig().getStringList("disabled-worlds").contains(e.getPlayer().getLocation().getWorld().getName())) {
			if (!e.getPlayer().hasPermission("tool-use")) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void nofishing(PlayerFishEvent e) {
		if (plugin.getConfig().getBoolean("tool-use") == false && !plugin.getConfig().getStringList("disabled-worlds").contains(e.getPlayer().getLocation().getWorld().getName())) {
			if (!e.getPlayer().hasPermission("tool-use")) {
				e.setCancelled(true);
			}
		}
	}
	
	
	@EventHandler
	public void notrampling(PlayerInteractEvent e)	{
		if (plugin.getConfig().getBoolean("tool-use") == false && !plugin.getConfig().getStringList("disabled-worlds").contains(e.getPlayer().getWorld().getName())){
			if (!e.getPlayer().hasPermission("tool-use"))
				if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.GRASS)
					e.setCancelled(true);
		}
	}

	

	
	//------------------------------------------- Updater -----------------------------------------------//
	
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
