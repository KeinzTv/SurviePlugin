package fr.keinz.survieplugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.keinz.survieplugin.commands.Admin;
import fr.keinz.survieplugin.commands.Report;
import fr.keinz.survieplugin.commands.Shop;
import fr.keinz.survieplugin.commands.Spawn;
import fr.keinz.survieplugin.commands.Teleportation;
import fr.keinz.survieplugin.listeners.InventoryClick;
import fr.keinz.survieplugin.listeners.ModItemsInteract;
import fr.keinz.survieplugin.listeners.PlayerChat;
import fr.keinz.survieplugin.listeners.PlayerQuit;
import fr.keinz.survieplugin.utils.PlayerManager;

public final class SurviePlugin extends JavaPlugin {
	private static SurviePlugin instance;
	public HashMap<UUID, PlayerManager> players = new HashMap<>();
	public ArrayList<UUID> moderateurs = new ArrayList<>();
	
	@Override
	public void onLoad() {
		super.onLoad();
	}
	
	@Override
	public void onEnable() {
		instance = this;
		Bukkit.getPluginManager().registerEvents(new PlayerQuit(), this);
		Bukkit.getPluginManager().registerEvents(new ModItemsInteract(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerChat(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryClick(), this);
		getCommand("shop").setExecutor(new Shop());
		getCommand("spawn").setExecutor(new Spawn());
		getCommand("admin").setExecutor(new Admin());
		getCommand("report").setExecutor(new Report());
		getCommand("a").setExecutor(new Admin());
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
