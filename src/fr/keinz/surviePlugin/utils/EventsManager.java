package fr.keinz.surviePlugin.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import fr.keinz.surviePlugin.SurviePlugin;
import fr.keinz.surviePlugin.listeners.InventoryClick;
 
public class EventsManager {
 
    public void registers(){
    	
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new InventoryClick(), SurviePlugin.getInstance());
    }
}