package fr.keinz.surviePlugin.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.keinz.surviePlugin.rank.Rank;
import fr.keinz.surviePlugin.rank.RankList;

public final class PlayerListener implements Listener {
	private final Rank rank;
	
	public PlayerListener(Rank rank) {
		this.rank = rank;
	}

	@EventHandler
	private void onPlayerJoin(PlayerJoinEvent playerJoin) {
		rank.loadPlayer(playerJoin.getPlayer());
		playerJoin.getPlayer().setScoreboard(rank.getScoreboard());
	}
	
	@EventHandler
	private void onPlayerQuit(PlayerQuitEvent playerQuit) {
		rank.deletePlayer(playerQuit.getPlayer());
	}
	
	@EventHandler
	private void onPlayerSendChat(AsyncPlayerChatEvent playerSendChat) {
		RankList rankList = rank.getPlayerRank(playerSendChat.getPlayer());
		playerSendChat.setFormat(rankList.getPrefix() + playerSendChat.getPlayer().getName() + rankList.getSuffix() + rankList.getChatSeparator() + playerSendChat.getMessage());
	}
}
