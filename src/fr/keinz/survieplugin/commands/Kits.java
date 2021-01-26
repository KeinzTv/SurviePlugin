package fr.keinz.surviePlugin.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.keinz.surviePlugin.rank.Rank;

public class Kits implements CommandExecutor {
	private final Rank rank;
	
	public Kits(Rank rank) {
		this.rank = rank;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String shop, String[] args) {
		Player player = (Player) sender;
		if (sender instanceof Player) {
			if (rank.hasPower(player, 1)) {
				ItemStack pickaxe = new ItemStack(Material.WOODEN_PICKAXE, 1);
				ItemMeta pickaxeMeta = pickaxe.getItemMeta();
				pickaxeMeta.addEnchant(Enchantment.DURABILITY, 3, true);
				pickaxe.setItemMeta(pickaxeMeta);
				
				player.getInventory().addItem(pickaxe);
				player.updateInventory();
				
				player.sendMessage("§f[SERVEUR] : §aVous venez de reçevoir le kit débutant");
			}
			
			if (rank.hasPower(player, 4)) {
				ItemStack pickaxe = new ItemStack(Material.STONE_PICKAXE, 1);
				ItemMeta pickaxeMeta = pickaxe.getItemMeta();
				pickaxeMeta.addEnchant(Enchantment.DURABILITY, 3, true);
				pickaxeMeta.addEnchant(Enchantment.DIG_SPEED, 5, true);
				pickaxe.setItemMeta(pickaxeMeta);
				
				ItemStack sword = new ItemStack(Material.STONE_SWORD, 1);
				ItemMeta swordMeta = sword.getItemMeta();
				swordMeta.addEnchant(Enchantment.DURABILITY, 3, true);
				swordMeta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
				swordMeta.addEnchant(Enchantment.KNOCKBACK, 2, true);
				sword.setItemMeta(swordMeta);
				
				player.getInventory().addItem(sword, pickaxe);
				player.updateInventory();
				
				player.sendMessage("§f[SERVEUR] : §aVous venez de reçevoir le kit assassin");
			}
			
			
			if (rank.hasPower(player, 5)) {
				ItemStack pickaxe = new ItemStack(Material.STONE_PICKAXE, 1);
				ItemMeta pickaxeMeta = pickaxe.getItemMeta();
				pickaxeMeta.addEnchant(Enchantment.DURABILITY, 3, true);
				pickaxeMeta.addEnchant(Enchantment.DIG_SPEED, 5, true);
				pickaxe.setItemMeta(pickaxeMeta);
				
				ItemStack sword = new ItemStack(Material.STONE_SWORD, 1);
				ItemMeta swordMeta = sword.getItemMeta();
				swordMeta.addEnchant(Enchantment.DURABILITY, 3, true);
				swordMeta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
				sword.setItemMeta(swordMeta);
				
				ItemStack bow = new ItemStack(Material.BOW, 1);
				ItemMeta bowMeta = bow.getItemMeta();
				bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
				bow.setItemMeta(bowMeta);
				
				ItemStack arrow = new ItemStack(Material.ARROW, 1);
				
				player.getInventory().addItem(sword, pickaxe, bow, arrow);
				player.updateInventory();
				
				player.sendMessage("§f[SERVEUR] : §aVous venez de reçevoir le kit chancelier");
			}
			
			if (rank.hasPowerSup(player, 6)) {
				ItemStack pickaxe = new ItemStack(Material.STONE_PICKAXE, 1);
				ItemMeta pickaxeMeta = pickaxe.getItemMeta();
				pickaxeMeta.addEnchant(Enchantment.DURABILITY, 3, true);
				pickaxeMeta.addEnchant(Enchantment.DIG_SPEED, 5, true);
				pickaxe.setItemMeta(pickaxeMeta);
				
				ItemStack sword = new ItemStack(Material.STONE_SWORD, 1);
				ItemMeta swordMeta = sword.getItemMeta();
				swordMeta.addEnchant(Enchantment.DURABILITY, 1, true);
				swordMeta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
				swordMeta.addEnchant(Enchantment.KNOCKBACK, 2, true);
				swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
				sword.setItemMeta(swordMeta);
				
				ItemStack bow = new ItemStack(Material.BOW, 1);
				ItemMeta bowMeta = bow.getItemMeta();
				bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
				bow.setItemMeta(bowMeta);
				
				ItemStack arrow = new ItemStack(Material.ARROW, 1);
				
				player.getInventory().addItem(sword, pickaxe, bow, arrow);
				player.updateInventory();
				
				player.sendMessage("§f[SERVEUR] : §aVous venez de reçevoir le kit custom");
			}
		}
		return false;
	}

}
