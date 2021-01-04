package fr.keinz.surviePlugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Teleportation implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		
		if(sender instanceof Player) {
            if(args.length != 1){
                player.sendMessage("§cVeuillez saisir le pseudo d'un joueur !");
                return false;
            }
			
			if (player.isFlying()) {
				player.setFlying(false);
			}
			
			String targetName = args[0];
			Player target = Bukkit.getPlayer(targetName);
			
			Location tl = target.getLocation();
			player.teleport(tl);
			player.sendMessage("§f[SERVEUR] : §aVous venez d'être téléporté au §2shop.");
			return false;	
		} else {
			player.sendMessage("§f[SERVEUR] : §cSeul un joueur peut être téléporté au shop.");
			return false;
		}
	}
	
}
