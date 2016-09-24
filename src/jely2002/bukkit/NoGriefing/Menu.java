package jely2002.bukkit.NoGriefing;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class Menu implements Listener {



	Main plugin;
	public Menu(Main instance) {
		plugin = instance;
	}

	public static Inventory settingsgui = Bukkit.createInventory(null, 36, ChatColor.RED.toString() + ChatColor.BOLD + "No-Griefing settings");
	static {
		createItem(Material.GRASS, (short) 0, settingsgui, 3, "Block-place", ChatColor.GREEN, "Click to enable or disable block place!", ChatColor.DARK_GRAY);
		createItem(Material.DIAMOND_PICKAXE, (short) 0, settingsgui, 4, "Block-break", ChatColor.GREEN, "Click to enable or disable block break!", ChatColor.DARK_GRAY);
		createItem(Material.ENDER_CHEST, (short) 0, settingsgui, 5, "Item-pickup", ChatColor.GREEN, "Click to enable or disable item pickup!", ChatColor.DARK_GRAY);
		createItem(Material.CHEST, (short) 0, settingsgui, 12, "Item-drops", ChatColor.GREEN, "Click to enable or disable item drops!", ChatColor.DARK_GRAY);
		createItem(Material.WORKBENCH, (short) 0, settingsgui, 13, "Inventory-use", ChatColor.GREEN, "Click to enable or disable inventory use!", ChatColor.DARK_GRAY);
		createItem(Material.DIAMOND_SWORD, (short) 0, settingsgui, 14, "PvP", ChatColor.GREEN, "Click to enable or disable PvP!", ChatColor.DARK_GRAY);
		createItem(Material.TNT, (short) 0, settingsgui, 21, "Anti-TNT", ChatColor.GREEN,"Click to enable or disable Anti-TNT!", ChatColor.DARK_GRAY);
		createItem(Material.LAVA_BUCKET, (short) 0, settingsgui, 22, "Fire", ChatColor.GREEN,"Click to enable or disable fire!", ChatColor.DARK_GRAY);
		createItem(Material.WATER_BUCKET, (short) 0, settingsgui, 23, "Drowning", ChatColor.GREEN,"Click to enable or disable Drowning!", ChatColor.DARK_GRAY);
		createItem(Material.CHAINMAIL_BOOTS, (short) 0, settingsgui, 30, "Falldamage", ChatColor.GREEN,"Click to enable or disable falldamage!", ChatColor.DARK_GRAY);
		createItem(Material.IRON_HOE, (short) 0, settingsgui, 31, "Tool-use", ChatColor.GREEN,"Click to enable or disable tool use!", ChatColor.DARK_GRAY);
		createItem(Material.COOKED_CHICKEN, (short) 0, settingsgui, 32, "Hunger", ChatColor.GREEN,"Click to enable or disable hunger!", ChatColor.DARK_GRAY);
		createItem(Material.STAINED_GLASS, (short) 14, settingsgui, 0, "", ChatColor.GREEN, "", ChatColor.DARK_GRAY);
		createItem(Material.STAINED_GLASS, (short) 14, settingsgui, 1, "", ChatColor.GREEN, "", ChatColor.DARK_GRAY);
		createItem(Material.STAINED_GLASS, (short) 14, settingsgui, 2, "", ChatColor.GREEN, "", ChatColor.DARK_GRAY);
		createItem(Material.STAINED_GLASS, (short) 14, settingsgui, 6, "", ChatColor.GREEN, "", ChatColor.DARK_GRAY);
		createItem(Material.STAINED_GLASS, (short) 14, settingsgui, 7, "", ChatColor.GREEN, "", ChatColor.DARK_GRAY);
		createItem(Material.STAINED_GLASS, (short) 14, settingsgui, 8, "", ChatColor.GREEN, "", ChatColor.DARK_GRAY);
		createItem(Material.STAINED_GLASS, (short) 14, settingsgui, 9, "", ChatColor.GREEN, "", ChatColor.DARK_GRAY);
		createItem(Material.STAINED_GLASS, (short) 14, settingsgui, 10, "", ChatColor.GREEN, "", ChatColor.DARK_GRAY);
		createItem(Material.STAINED_GLASS, (short) 14, settingsgui, 11, "", ChatColor.GREEN, "", ChatColor.DARK_GRAY);
		createItem(Material.STAINED_GLASS, (short) 14, settingsgui, 15, "", ChatColor.GREEN, "", ChatColor.DARK_GRAY);
		createItem(Material.STAINED_GLASS, (short) 14, settingsgui, 16, "", ChatColor.GREEN, "", ChatColor.DARK_GRAY);
		createItem(Material.STAINED_GLASS, (short) 14, settingsgui, 17, "", ChatColor.GREEN, "", ChatColor.DARK_GRAY);
		createItem(Material.STAINED_GLASS, (short) 14, settingsgui, 18, "", ChatColor.GREEN, "", ChatColor.DARK_GRAY);
		createItem(Material.STAINED_GLASS, (short) 14, settingsgui, 19, "", ChatColor.GREEN, "", ChatColor.DARK_GRAY);
		createItem(Material.STAINED_GLASS, (short) 14, settingsgui, 20, "", ChatColor.GREEN, "", ChatColor.DARK_GRAY);
		createItem(Material.STAINED_GLASS, (short) 14, settingsgui, 24, "", ChatColor.GREEN, "", ChatColor.DARK_GRAY);
		createItem(Material.STAINED_GLASS, (short) 14, settingsgui, 25, "", ChatColor.GREEN, "", ChatColor.DARK_GRAY);
		createItem(Material.STAINED_GLASS, (short) 14, settingsgui, 26, "", ChatColor.GREEN, "", ChatColor.DARK_GRAY);
		createItem(Material.STAINED_GLASS, (short) 14, settingsgui, 27, "", ChatColor.GREEN, "", ChatColor.DARK_GRAY);
		createItem(Material.STAINED_GLASS, (short) 14, settingsgui, 28, "", ChatColor.GREEN, "", ChatColor.DARK_GRAY);
		createItem(Material.STAINED_GLASS, (short) 14, settingsgui, 29, "", ChatColor.GREEN, "", ChatColor.DARK_GRAY);
		createItem(Material.STAINED_GLASS, (short) 14, settingsgui, 33, "", ChatColor.GREEN, "", ChatColor.DARK_GRAY);
		createItem(Material.STAINED_GLASS, (short) 14, settingsgui, 34, "", ChatColor.GREEN, "", ChatColor.DARK_GRAY);
		createItem(Material.STAINED_GLASS, (short) 14, settingsgui, 35, "", ChatColor.GREEN, "", ChatColor.DARK_GRAY);
		createItem(Material.STAINED_GLASS, (short) 14, settingsgui, 36, "", ChatColor.GREEN, "", ChatColor.DARK_GRAY);
		
	}

	public static void createItem(Material material, Short dataValue, Inventory inv, int Slot, String name, ChatColor namecolor, String lore, ChatColor lorecolor) {
		ItemStack item = new ItemStack(material, 1, (short) dataValue);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(namecolor + name);
		ArrayList<String> Lore = new ArrayList<String>();
		Lore.add(lorecolor + lore);
		meta.setLore(Lore);
		item.setItemMeta(meta);

		inv.setItem(Slot, item); 

	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked(); 
		ItemStack clicked = e.getCurrentItem(); 
		Inventory inventory = e.getInventory(); 
		if (!e.getInventory().getName().equalsIgnoreCase(inventory.getName())) return;
        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getItemMeta() == null) return;
		if (inventory.getName().equals(settingsgui.getName())) { 
			if (clicked.getItemMeta().getDisplayName().contains("Block-place")) { 
				if (plugin.getConfig().getBoolean("blockplace")) {
					e.setCancelled(true); 
					plugin.getConfig().set("blockplace", false);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Block-place has been disabled!");
				} else {
					e.setCancelled(true); 
					plugin.getConfig().set("blockplace", true);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Block-place has been enabled!");
				}
			}
			if (clicked.getItemMeta().getDisplayName().contains("Block-break")) {
				if (plugin.getConfig().getBoolean("blockbreak")) {
					e.setCancelled(true); 
					plugin.getConfig().set("blockbreak", false);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Block-break has been disabled!");
				} else {
					e.setCancelled(true); 
					plugin.getConfig().set("blockbreak", true);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Block-break has been enabled!");
				}
			}
			if (clicked.getItemMeta().getDisplayName().contains("Item-drops")) {
				if (plugin.getConfig().getBoolean("itemdrops")) {
					e.setCancelled(true); 
					plugin.getConfig().set("itemdrops", false);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Item-drops has been disabled!");
				} else {
					e.setCancelled(true); 
					plugin.getConfig().set("itemdrops", true);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Item-drops has been enabled!");
				}
			}
			if (clicked.getItemMeta().getDisplayName().contains("Item-pickup")) {
				if (plugin.getConfig().getBoolean("itempickup")) {
					e.setCancelled(true); 
					plugin.getConfig().set("itempickup", false);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Item-pickup has been disabled!");
				} else {
					e.setCancelled(true); 
					plugin.getConfig().set("itempickup", true);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Item-pickup has been enabled!");
				}
			}
			if (clicked.getItemMeta().getDisplayName().contains("Inventory-use")) {
				if (plugin.getConfig().getBoolean("inventory-use")) {
					e.setCancelled(true); 
					plugin.getConfig().set("inventory-use", false);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Inventory-use has been disabled!");
				} else {
					e.setCancelled(true); 
					plugin.getConfig().set("inventory-use", true);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Inventory-use has been enabled!");
				}
			}
			if (clicked.getItemMeta().getDisplayName().contains("Anti-TNT")) {
				if (plugin.getConfig().getBoolean("antitnt")) {
					e.setCancelled(true); 
					plugin.getConfig().set("antitnt", false);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Anti-TNT has been disabled!");
				} else {
					e.setCancelled(true); 
					plugin.getConfig().set("antitnt", true);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Anti-TNT has been enabled!");
				}			
			}
			if (clicked.getItemMeta().getDisplayName().contains("PvP")) {
				if (plugin.getConfig().getBoolean("pvp")) {
					e.setCancelled(true); 
					plugin.getConfig().set("pvp", false);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "PvP has been disabled!");
				} else {
					e.setCancelled(true); 
					plugin.getConfig().set("pvp", true);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "PvP has been enabled!");
				}
			}
			if (clicked.getItemMeta().getDisplayName().contains("Falldamage")) {
				if (plugin.getConfig().getBoolean("falldamage")) {
					e.setCancelled(true); 
					plugin.getConfig().set("falldamage", false);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Falldamage has been disabled!");
				} else {
					e.setCancelled(true); 
					plugin.getConfig().set("falldmage", true);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Falldamage has been enabled!");
				}
			}
			if (clicked.getItemMeta().getDisplayName().contains("Drowning")) {
				if (plugin.getConfig().getBoolean("drowning")) {
					e.setCancelled(true); 
					plugin.getConfig().set("drowning", false);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Drowning has been disabled!");
				} else {
					e.setCancelled(true); 
					plugin.getConfig().set("drowning", true);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Drowning has been enabled!");
				}
			}
			if (clicked.getItemMeta().getDisplayName().contains("Fire")) {
				if (plugin.getConfig().getBoolean("fire")) {
					e.setCancelled(true); 
					plugin.getConfig().set("fire", false);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Fire has been disabled!");
				} else {
					e.setCancelled(true); 
					plugin.getConfig().set("fire", true);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Fire has been enabled!");
				}
			}
			if (clicked.getItemMeta().getDisplayName().contains("Hunger")) {
				if (plugin.getConfig().getBoolean("hunger")) {
					e.setCancelled(true); 
					plugin.getConfig().set("hunger", false);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Hunger has been disabled!");
				} else {
					e.setCancelled(true); 
					plugin.getConfig().set("hunger", true);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Hunger has been enabled!");
				}
			}
			if (clicked.getItemMeta().getDisplayName().contains("Tool-use")) {
				if (plugin.getConfig().getBoolean("tool-use")) {
					e.setCancelled(true); 
					plugin.getConfig().set("tool-use", false);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Tool use has been disabled!");
				} else {
					e.setCancelled(true); 
					plugin.getConfig().set("tool-use", true);
					p.sendMessage(ChatColor.GREEN + "Tool use has been enabled!");
				}
			}
			if (clicked.getItemMeta().getDisplayName().contains("Close menu")) {
					e.setCancelled(true); 
					e.getWhoClicked().closeInventory();
				} 
				}
			}
		}
