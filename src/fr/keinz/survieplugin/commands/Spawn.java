package fr.keinz.surviePlugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String spawn, String[] args) {
		Player player = (Player) sender;
		
		if(sender instanceof Player) {
			
			if (player.isFlying()) {
				player.setFlying(false);
			}
			
			if (player.getWorld().getName().equalsIgnoreCase("world")) {
				Location spawnLocation = new Location(player.getWorld(), 3000.5, 65, 3000.5, 0.1f, -3.5f);
				player.teleport(spawnLocation);
				player.sendMessage("§f[SERVEUR] : §aVous venez d'être téléporté au §2spawn.");
				return false;
			} else {
				Location spawnLocation = new Location(Bukkit.getWorld("world"), 3000.5, 65, 3000.5, 0.1f, -3.5f);
				player.teleport(spawnLocation);
				player.sendMessage("§f[SERVEUR] : §aVous venez d'être téléporté au §2spawn.");
				return false;
			}
			
		} else {
			player.sendMessage("§f[SERVEUR] : §cSeul un joueur peut être téléporté au spawn.");
		}
		return false;
	}

}
