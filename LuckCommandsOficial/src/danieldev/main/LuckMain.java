package danieldev.main;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import danieldev.arquivosConfig.ArquivosConfig;
import danieldev.comandos.CommandosLuck;
import danieldev.eventos.MatadorDeInseto;
import danieldev.eventos.QuebradorBlocos;

public class LuckMain extends JavaPlugin {
	private static LuckMain luckMain;

	public Map<String, List<String>> blocosComandos;
	public Map<String, List<String>> entidadesComandos;
	public Map<Player, List<Location>> blocoLocal;
	public void setBlocos() {
		ArquivosConfig ac = new ArquivosConfig();
		ac.reloadComandoList();
		blocosComandos = new HashMap<>();
		
		for (String block : ac.getComandoList().getConfigurationSection("BlockBreak").getKeys(false)) {
			String s = block.toUpperCase();
			blocosComandos.put(s, ac.getComandoList().getStringList("BlockBreak." + block + ".comando"));
			
		}
	}

	public void setEntidades() {
		ArquivosConfig ac = new ArquivosConfig();
		ac.reloadComandoList();
		entidadesComandos = new HashMap<>();
		for (String entidade : ac.getComandoList().getConfigurationSection("KillEntity").getKeys(false)) {
			String s = entidade.toUpperCase();
			entidadesComandos.put(s, ac.getComandoList().getStringList("KillEntity." + entidade + ".comando"));
			
		}
	}

	@Override
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage("§5§l[LuckCommands] -------------------------");
		Bukkit.getConsoleSender().sendMessage("§5§l[LuckCommands]		HABILITADO		");
		Bukkit.getConsoleSender().sendMessage("§5§l[LuckCommands] -------------------------");
		Bukkit.getPluginCommand("luck").setExecutor(new CommandosLuck());
		blocoLocal = new HashMap<>();
		setLuckMain(this);
		files();
		if (!new File(getDataFolder(), "config.yml").exists()) {
			saveDefaultConfig();
		}

		if (entidadesComandos == null) {
			setEntidades();
		}
		if (blocosComandos == null) {
			setBlocos();
		}
		
		if(getConfig().getBoolean("AtivarComandosBlock")){
		Bukkit.getPluginManager().registerEvents(new QuebradorBlocos(), this);	
		}
		if(getConfig().getBoolean("AtivarComandosEntity")){
			Bukkit.getPluginManager().registerEvents(new MatadorDeInseto(), this);
		}
		
	}

	@Override
	public void onDisable() {
		HandlerList.unregisterAll();
		Bukkit.getConsoleSender().sendMessage("§5§l[LuckCommands] -------------------------");
		Bukkit.getConsoleSender().sendMessage("§5§l[LuckCommands] 	DESABILITADO		");
		Bukkit.getConsoleSender().sendMessage("§5§l[LuckCommands] -------------------------");

	}

	public static LuckMain getLuckMain() {
		return luckMain;
	}

	@SuppressWarnings("static-access")
	public void setLuckMain(LuckMain luckMain) {
		this.luckMain = luckMain;
	}

	public void files() {
		ArquivosConfig ac = new ArquivosConfig();
		ac.reloadComandoList();
	}


}
