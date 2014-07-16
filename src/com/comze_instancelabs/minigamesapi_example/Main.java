package com.comze_instancelabs.minigamesapi_example;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.comze_instancelabs.minigamesapi.Arena;
import com.comze_instancelabs.minigamesapi.ArenaSetup;
import com.comze_instancelabs.minigamesapi.MinigamesAPI;
import com.comze_instancelabs.minigamesapi.PluginInstance;
import com.comze_instancelabs.minigamesapi.config.ArenasConfig;
import com.comze_instancelabs.minigamesapi.util.Util;
import com.comze_instancelabs.minigamesapi.util.Validator;

public class Main extends JavaPlugin {

	MinigamesAPI api = null;

	public void onEnable() {
		api = MinigamesAPI.getAPI().setupAPI(this, "example", IArena.class);
		PluginInstance pinstance = MinigamesAPI.getAPI().pinstances.get(this);
		pinstance.addLoadedArenas(loadArenas(this, pinstance.getArenasConfig()));
		// pinstance.pvp = false;
		System.out.println(MinigamesAPI.getAPI().pinstances.get(this).getArenas().get(0).toString());
	}

	public static ArrayList<Arena> loadArenas(JavaPlugin plugin, ArenasConfig cf) {
		ArrayList<Arena> ret = new ArrayList<Arena>();
		FileConfiguration config = cf.getConfig();
		if (!config.isSet("arenas")) {
			return ret;
		}
		for (String arena : config.getConfigurationSection("arenas.").getKeys(false)) {
			if (Validator.isArenaValid(plugin, arena, cf.getConfig())) {
				ret.add(initArena(plugin, arena));
			}
		}
		return ret;
	}

	public static IArena initArena(JavaPlugin m, String arena) {
		IArena a = new IArena(m, arena);
		ArenaSetup s = MinigamesAPI.getAPI().pinstances.get(m).arenaSetup;
		a.init(Util.getSignLocationFromArena(m, arena), Util.getAllSpawns(m, arena), Util.getMainLobby(m), Util.getComponentForArena(m, arena, "lobby"), s.getPlayerCount(m, arena, true), s.getPlayerCount(m, arena, false), s.getArenaVIP(m, arena));
		return a;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		return api.getCommandHandler().handleArgs(this, "minigamesexample", "/" + cmd.getName(), sender, args);
	}
}
