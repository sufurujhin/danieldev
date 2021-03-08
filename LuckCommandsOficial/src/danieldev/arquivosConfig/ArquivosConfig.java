package danieldev.arquivosConfig;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import danieldev.main.LuckMain;

public class ArquivosConfig {
	public static ArquivosConfig load;
	private File comandos;
	private FileConfiguration comandosFC;

	
	public ArquivosConfig() {
		// TODO Auto-generated constructor stub
		setLoad(this);
	}
	public void reloadComandoList() {
		String s = "comandos.yml";
		comandos = new File(LuckMain.getLuckMain().getDataFolder(), s);
		if (!comandos.exists()) {
			LuckMain.getLuckMain().saveResource(s, false);
		}
		comandosFC = YamlConfiguration.loadConfiguration(comandos);
	}

	public FileConfiguration getComandoList() {
		if (comandosFC == null) {
			reloadComandoList();
		}
		return comandosFC;
	}

	public void saveComandoList() {
		try {
			comandosFC.save(comandos);
		} catch (IOException e) {
			Bukkit.getServer().getLogger().severe(load().getComandoList().getString(comandos.getName() + " Não esta na pasta"));
		}
	}

	public static ArquivosConfig load() {
		return load;
	}
	
	@SuppressWarnings("static-access")
	public void setLoad(ArquivosConfig load){
		this.load= load;
	}
	
	
	
	
}
