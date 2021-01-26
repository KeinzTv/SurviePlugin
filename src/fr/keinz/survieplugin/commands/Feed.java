package fr.keinz.surviePlugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.keinz.surviePlugin.rank.Rank;

public class Feed implements CommandExecutor {
private final Rank rank;
	
	public Feed(Rank rank) {
		this.rank = rank;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		
		if (sender instanceof Player) {
			if (rank.hasPowerInf(player, 4)) {
				player.sendMessage(rank.getPrefix() + "§cVous n'avez pas la permission d'effectuer cette commande");
				return false;
			}
			
			if (player.getFoodLevel() < 20) {
				player.setFoodLevel(20);
				player.sendMessage(rank.getPrefix() + "§aVous venez d'être nourri !");
				return false;
			} else {
				player.sendMessage(rank.getPrefix() + "§cVous ne pouvez pas être nourri.");
			}
		} else {
			player.sendMessage(rank.getPrefix() + "§cSeul un joueur peut être nourri.");
			return false;
		}
		return false;
	}

}
