package fr.keinz.surviePlugin.rank;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.google.common.collect.Maps;

public final class Rank {
	private final Map<String, RankList> playerRanks = Maps.newHashMap();
	private Scoreboard scoreboard;
	private final Plugin plugin;
	private final String prefix = "§f[SERVEUR] : ";
	
	private FileConfiguration config;
	private File file;
	
	public Rank(Plugin plugin) {
		this.plugin = plugin;
		initConfig();
	}
	
	public final Plugin getPlugin() {
		return plugin;
	}
	
	public final String getPrefix() {
		return prefix;
	}
	
	public final Scoreboard getScoreboard() {
		return scoreboard;
	}
	
	public FileConfiguration getConfig() {
		return config;
	}
	
	private void initConfig() {
		File f = new File("plugins/SurviePlugin");
		if (!f.exists()) f.mkdirs();
		file = new File(f, "rank.yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		config = YamlConfiguration.loadConfiguration(file);
	}
	
	public void initScoreboard() {
		if (scoreboard != null) throw new UnsupportedOperationException("§f[SERVEUR] §4Le scoreboard est deja existant.");
		scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		
		for(RankList rankList : RankList.values()) {
			Team team = scoreboard.registerNewTeam(rankList.getName());
			team.setPrefix(rankList.getPrefix());
			team.setSuffix(rankList.getSuffix());
		}
	}
	
	public void loadPlayer(Player player) {
		String uuid = player.getUniqueId().toString();
		if (playerRanks.containsKey(uuid)) return;
		if (!config.contains(uuid)) {
			config.set(uuid, 12);
			saveConfig();
		}
		
		playerRanks.put(uuid, getRankById(config.getInt(uuid)));
		
		scoreboard.getTeam(playerRanks.get(uuid).getName()).addEntry(player.getName());
	}
	
	public void deletePlayer(Player player) {
		String uuid = player.getUniqueId().toString();
		if (!playerRanks.containsKey(uuid)) return;
		playerRanks.remove(uuid);
	}
	
	public RankList getPlayerRank(Player player) {
		String uuid = player.getUniqueId().toString();
		if(!playerRanks.containsKey(uuid)) loadPlayer(player);
		return playerRanks.get(uuid);
	}
	
	public RankList getRankById(int id) {
		for (RankList rankList : RankList.values()) {
			if (rankList.getId() == id) return rankList;
		}	
		return RankList.NOVICE;
	}
	
	public void saveConfig() {
		try {
			config.save(file);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public boolean hasPower(Player player, int power) {
		return (getPlayerRank(player).getPower() == power);
	}
	
	public boolean hasPowerSup(Player player, int power) {
		return (getPlayerRank(player).getPower() > power);
	}
	
	public boolean hasPowerInf(Player player, int power) {
		return (getPlayerRank(player).getPower() < power);
	}
	
	public void changeRank(Player player, RankList rankList) {
		config.set(player.getUniqueId().toString(), rankList.getId());
		saveConfig();
		deletePlayer(player);
		loadPlayer(player);
	}
	
	public void changeRankOffline(String uuid, RankList rankList) {
		config.set(uuid, rankList.getId());
		saveConfig();
	}
}
