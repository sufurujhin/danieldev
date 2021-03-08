package danieldev.eventos;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import danieldev.main.LuckMain;

public class MatadorDeInseto implements Listener {

	@EventHandler
	public void mataBurra(EntityDeathEvent evento) {
		try {
			if (evento.getEntity().getKiller() != null) {
				if (evento.getEntity().getKiller().getType() == EntityType.PLAYER) {
					if (evento.getEntity() != null) {

						if (LuckMain.getLuckMain().entidadesComandos.containsKey(evento.getEntity().getType().toString())) {

							Random r = new Random();
							String[] path;
							for (String cmd : LuckMain.getLuckMain().entidadesComandos.get(evento.getEntity().getType().toString())) {
								path = cmd.split("=");
								if (path.length == 5) {
									if (evento.getEntity().getKiller().hasPermission(path[2].trim())) {

										if (r.nextDouble() <= Double.parseDouble(path[1])) {
											String comando = path[0].replaceAll("@player", evento.getEntity().getKiller().getName());
											Server s = LuckMain.getLuckMain().getServer();
											if (!(path[4].trim()).equalsIgnoreCase("nullo")) {
												Bukkit.broadcastMessage(path[4].replaceAll("&", "§").replaceAll("@player", evento.getEntity().getKiller().getName()));
											}

											if (!(path[3].trim()).equalsIgnoreCase("nullo")) {
												evento.getEntity().getKiller().sendMessage(path[3].replaceAll("&", "§"));
											}
											s.dispatchCommand(s.getConsoleSender(), comando);

										}
									}
								}
							}

						}

					}
				}
			}

		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("§3[LuckCommands]Erro - " + e.getMessage());
		}
	}

}
