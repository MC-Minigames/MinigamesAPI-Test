package com.comze_instancelabs.minigamesapi_example;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.comze_instancelabs.minigamesapi.Arena;

public class IArena extends Arena {

	public IArena(JavaPlugin plugin, String name) {
		super(plugin, name);
	}
	
	@Override
	public void joinPlayerLobby(String playername){
		Bukkit.getLogger().info("Test joinPlayerLobby()");
		return;
	}
	
	@Override
	public void start(){
		Bukkit.getLogger().info("Test start()");
		return;
	}

}
