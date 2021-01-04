package fr.keinz.survieplugin.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import fr.keinz.survieplugin.SurviePlugin;
import fr.keinz.survieplugin.listeners.InventoryClick;
 
public class EventsManager {
 
    public void registers(){
    	
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new InventoryClick(), SurviePlugin.getInstance());
    }
}