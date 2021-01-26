package fr.keinz.surviePlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.keinz.surviePlugin.commands.Admin;
import fr.keinz.surviePlugin.commands.Feed;
import fr.keinz.surviePlugin.commands.GameMode;
import fr.keinz.surviePlugin.commands.Kits;
import fr.keinz.surviePlugin.commands.Ping;
import fr.keinz.surviePlugin.commands.RankCommand;
import fr.keinz.surviePlugin.commands.Spawn;
import fr.keinz.surviePlugin.commands.Teleportation;
import fr.keinz.surviePlugin.listeners.ModItemsInteract;
import fr.keinz.surviePlugin.listeners.PlayerChat;
import fr.keinz.surviePlugin.listeners.PlayerListener;
import fr.keinz.surviePlugin.listeners.PlayerQuit;
import fr.keinz.surviePlugin.rank.Rank;
import fr.keinz.surviePlugin.utils.PlayerManager;

public final class SurviePlugin extends JavaPlugin {
	private static SurviePlugin instance;
	public HashMap<UUID, PlayerManager> players = new HashMap<>();
	public HashMap<Player, ArrayList<Player>> teleportationRequestMap = new HashMap<>();
	public ArrayList<UUID> moderateurs = new ArrayList<>();
	private Rank rank;
	
	
	@Override
	public void onLoad() {
		rank = new Rank(this);
		super.onLoad();
	}
	
	@Override
	public void onEnable() {
		instance = this;
		rank.initScoreboard();
		Bukkit.getPluginManager().registerEvents(new PlayerQuit(), this);
		Bukkit.getPluginManager().registerEvents(new ModItemsInteract(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerChat(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerListener(rank), this);
		getCommand("rank").setExecutor(new RankCommand(rank));
		getCommand("kit").setExecutor(new Kits(rank));
		getCommand("spawn").setExecutor(new Spawn());
		getCommand("admin").setExecutor(new Admin(rank));
		getCommand("a").setExecutor(new Admin(rank));
		getCommand("feed").setExecutor(new Feed(rank));
		getCommand("gm").setExecutor(new GameMode(rank));
		getCommand("ping").setExecutor(new Ping());
		getCommand("tpa").setExecutor(new Teleportation());
		getCommand("tpaccept").setExecutor(new Teleportation());
		getCommand("tpyes").setExecutor(new Teleportation());
		getCommand("tpdeny").setExecutor(new Teleportation());
		getCommand("tpno").setExecutor(new Teleportation());
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
	}
	
	public static SurviePlugin getInstance() {
		return instance;
	}
	
	public List<UUID> getModerateurs() {
        return moderateurs;
    }
 
    public Map<UUID, PlayerManager> getPlayers() {
        return players;
    }
}
