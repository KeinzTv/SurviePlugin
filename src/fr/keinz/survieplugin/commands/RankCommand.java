package fr.keinz.surviePlugin.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import fr.keinz.surviePlugin.rank.Rank;
import fr.keinz.surviePlugin.rank.RankList;

public final class RankCommand implements CommandExecutor, TabCompleter {
	private final Rank rank;
	
	public RankCommand(Rank rank) {
		this.rank = rank;
	}
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if (s instanceof Player) {
			if (rank.hasPowerInf((Player)s, 95/*power requis pour effectuer la commande*/)) {
				return sendMessage(s, rank.getPrefix() + "§cVous n'avez pas la permission d'effectuer cette commande");
			}
		}
		
		if (args.length < 2) {
			return sendMessage(s, rank.getPrefix() + "§c/rank <Joueur> <Grade>");
		}
		
		Player target = Bukkit.getPlayer(args[0]);
		if (target == null) return sendMessage(s, rank.getPrefix() + "§cLe joueur n'a pas été trouvé");
		
		RankList rankList = null;
		
		try {
			rankList = rank.getRankById(Integer.parseInt(args[1]));
		} catch (NumberFormatException nfe) {
			try {
			rankList = RankList.valueOf(args[1].toUpperCase());
			} catch (Exception e) {
				return sendMessage(s, rank.getPrefix() + "§cLe rank n'a pas été trouvé");
			}
		}
		
		rank.changeRank(target, rankList);
		sendMessage(target, rank.getPrefix() + "§aVotre grade à été modifié");
		target.kickPlayer("§aVous avez été kick pour changement de grade,\nvous étes maintenant " + rankList.getPrefix() + "§a.");
		return sendMessage(s, rank.getPrefix() + "§aLe grade du joueur " + target.getName() + " à bien été modifié au grade de " + rankList.getName().toLowerCase());
	}
	
	public boolean sendMessage(CommandSender s, String msg) {
		s.sendMessage(msg);
		return true;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> tabComplete = Lists.newArrayList();
		
		if (args.length == 2) {
			for (RankList rankList : RankList.values()) {
				if (rankList.getName().toLowerCase().startsWith(args[1].toLowerCase())) tabComplete.add(rankList.getName());
			}
			return tabComplete;
		}
		
		return null;
	}

}
