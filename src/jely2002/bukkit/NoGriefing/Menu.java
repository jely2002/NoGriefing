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

	public static Inventory settingsgui = Bukkit.createInventory(null, 54, ChatColor.RED.toString() + ChatColor.BOLD + "Anti-Grief settings");
	static {
		createItem(Material.GRASS, settingsgui, 1, "Block-place", ChatColor.GREEN, "Click to enable or disable block place!", ChatColor.DARK_GRAY);
		createItem(Material.DIAMOND_PICKAXE, settingsgui, 4, "Block-break", ChatColor.GREEN, "Click to enable or disable block break!", ChatColor.DARK_GRAY);
		createItem(Material.CHEST, settingsgui, 7, "Item-drops", ChatColor.GREEN, "Click to enable or disable item drops!", ChatColor.DARK_GRAY);
		createItem(Material.TNT, settingsgui, 22, "Anti-TNT", ChatColor.GREEN,"Click to enable or disable Anti-TNT!", ChatColor.DARK_GRAY);
		createItem(Material.ENDER_CHEST, settingsgui, 19, "Item-pickup", ChatColor.GREEN, "Click to enable or disable item pickup!", ChatColor.DARK_GRAY);
		createItem(Material.WORKBENCH, settingsgui, 25, "Inventory-use", ChatColor.GREEN, "Click to enable or disable inventory use!", ChatColor.DARK_GRAY);
		createItem(Material.DIAMOND_SWORD, settingsgui, 38, "PvP", ChatColor.GREEN, "Click to enable or disable PvP!", ChatColor.DARK_GRAY);
		createItem(Material.BARRIER, settingsgui, 42, "Close the menu", ChatColor.RED, "Click to close the settings menu!", ChatColor.DARK_GRAY);
	}

	public static void createItem(Material material, Inventory inv, int Slot, String name, ChatColor namecolor, String lore, ChatColor lorecolor) {
		ItemStack item = new ItemStack(material);
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
					p.sendMessage(ChatColor.GREEN + "Block-place has been set to false!");
				} else {
					e.setCancelled(true); 
					plugin.getConfig().set("blockplace", true);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Block-place has been set to true!");
				}
			}
			if (clicked.getItemMeta().getDisplayName().contains("Block-break")) {
				if (plugin.getConfig().getBoolean("blockbreak")) {
					e.setCancelled(true); 
					plugin.getConfig().set("blockbreak", false);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Block-break has been set to false!");
				} else {
					e.setCancelled(true); 
					plugin.getConfig().set("blockbreak", true);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Block-break has been set to true!");
				}
			}
			if (clicked.getItemMeta().getDisplayName().contains("Item-drops")) {
				if (plugin.getConfig().getBoolean("itemdrops")) {
					e.setCancelled(true); 
					plugin.getConfig().set("itemdrops", false);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Item-drops has been set to false!");
				} else {
					e.setCancelled(true); 
					plugin.getConfig().set("itemdrops", true);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Item-drops has been set to true!");
				}
			}
			if (clicked.getItemMeta().getDisplayName().contains("Item-pickup")) {
				if (plugin.getConfig().getBoolean("itempickup")) {
					e.setCancelled(true); 
					plugin.getConfig().set("itempickup", false);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Item-pickup has been set to false!");
				} else {
					e.setCancelled(true); 
					plugin.getConfig().set("itempickup", true);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Item-pickup has been set to true!");
				}
			}
			if (clicked.getItemMeta().getDisplayName().contains("Inventory-use")) {
				if (plugin.getConfig().getBoolean("inventory-use")) {
					e.setCancelled(true); 
					plugin.getConfig().set("inventory-use", false);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Inventory-use has been set to false!");
				} else {
					e.setCancelled(true); 
					plugin.getConfig().set("inventory-use", true);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Inventory-use has been set to true!");
				}
			}
			if (clicked.getItemMeta().getDisplayName().contains("Anti-TNT")) {
				if (plugin.getConfig().getBoolean("antitnt")) {
					e.setCancelled(true); 
					plugin.getConfig().set("antitnt", false);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Anti-TNT has been set to false!");
				} else {
					e.setCancelled(true); 
					plugin.getConfig().set("antitnt", true);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "Anti-TNT has been set to true!");
				}			
			}
			if (clicked.getItemMeta().getDisplayName().contains("PvP")) {
				if (plugin.getConfig().getBoolean("pvp")) {
					e.setCancelled(true); 
					plugin.getConfig().set("pvp", false);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "PvP has been set to false!");
				} else {
					e.setCancelled(true); 
					plugin.getConfig().set("pvp", true);
					plugin.saveConfig();
					p.sendMessage(ChatColor.GREEN + "PvP has been set to true!");
				}
			}
			if (clicked.getItemMeta().getDisplayName().contains("Close the menu")) {
					e.setCancelled(true); 
					e.getWhoClicked().closeInventory();
				} 
				}
			}
		}