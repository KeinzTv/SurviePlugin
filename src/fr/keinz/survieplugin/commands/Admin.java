package fr.keinz.survieplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import fr.keinz.survieplugin.utils.PlayerManager;

public class Admin implements CommandExecutor, Listener {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (!player.hasPermission("moderation.mod")) {
				player.sendMessage("�f[SERVEUR] : �cVous n'avez pas la permission d'effectuer cette commande");
				return false;
			}
			
			if(label.equalsIgnoreCase("admin")) {
	            if(PlayerManager.isInModerationMod(player)){
	                PlayerManager.getFromPlayer(player).destroy();
	            } else {
	                new PlayerManager(player).init();
	            }
			}
			
			if(label.equalsIgnoreCase("a")){ 
				if(PlayerManager.isInModerationMod(player)){
	                PlayerManager.getFromPlayer(player).destroy();
	            } else {
	                new PlayerManager(player).init();
	            }
	        }
			
			if (args.length >= 1) {
				player.sendMessage("�f[SERVEUR] : �c/admin or /a");
			}
		}
		return false;
	}

}
