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

		if(sender instanceof  Player){
			final Player player = (Player)sender;
			if(label.equals("tpa")) return tpa(player, args);
		}
		return false;
	}

	private boolean tpa(Player player, String[] args){
		if(args.length != 1){
			player.sendMessage("§cVeuillez saisir le pseudo d'un joueur !");
			return false;
		}
		if (player.isFlying()) {
			player.setFlying(false);
		}

		final Player target = Bukkit.getPlayer(args[0]);
		if(target != null){
			target.sendMessage("§2" + player.getDisplayName() + " §aveut se téléporter à votre position.\n" +
					"Pour accepter la demande : §2/tpaccept §aou §2/tpyes.\n" +
					"§aPour refuser la demande : §2/tpdeny §aou §2/tpno.");
			player.teleport(target.getLocation());
			player.sendMessage("§f[SERVEUR] : §aVous venez d'être téléporté à §a" + target.getDisplayName() + ".");
		} else {
			player.sendMessage("§cCe joueur n'existe pas ou n'est pas connecté !");
		}

		return false;
	}
	
}
