package fr.keinz.surviePlugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Shop implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String shop, String[] args) {
		Player player = (Player) sender;
		
		if(sender instanceof Player) {
			
			if (player.isFlying()) {
				player.setFlying(false);
			}
			
			if (player.getWorld().getName().equalsIgnoreCase("world")) {
				Location spawnLocation = new Location(player.getWorld(), 4359.5, 66, 4441.5, -0.5f, -12.2f);
				player.teleport(spawnLocation);
				player.sendMessage("§f[SERVEUR] : §aVous venez d'être téléporté au §2shop.");
				return false;
			} else {
				Location shopLocation = new Location(Bukkit.getWorld("world"), 4359.5, 66, 4441.5, -0.5f, -12.2f);
				player.teleport(shopLocation);
				player.sendMessage("§f[SERVEUR] : §aVous venez d'être téléporté au §2shop.");
				return false;
			}
			
		} else {
			player.sendMessage("§f[SERVEUR] : §cSeul un joueur peut être téléporté au shop.");
			return false;
		}
	}

}
