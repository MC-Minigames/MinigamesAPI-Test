package com.comze_instancelabs.minigamesapi_example;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.comze_instancelabs.minigamesapi.MinigamesAPI;

public class Main extends JavaPlugin {

	MinigamesAPI api = null;

	public void onEnable(){
		api = MinigamesAPI.getAPI().setupAPI(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		return api.getCommandHandler().handleArgs(this, "minigamesexample", "/" + cmd.getName(), sender, args);
	}
}
