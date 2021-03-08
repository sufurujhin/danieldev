package danieldev.eventos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import danieldev.main.LuckMain;

public class QuebradorBlocos implements Listener {

	@EventHandler
	public void breakBlock(BlockBreakEvent evento) {

		if (!(evento instanceof Player)) {

			if (evento.getBlock() != null) {
				try {

					if (LuckMain.getLuckMain().blocosComandos.containsKey(evento.getBlock().getType().toString())) {

						Random r = new Random();
						String[] path;

						if (LuckMain.getLuckMain().blocoLocal.containsKey(evento.getPlayer())) {

							if (LuckMain.getLuckMain().blocoLocal.get(evento.getPlayer()).size() == 150) {
								List<Location> listLocal = new ArrayList<>();
								listLocal.add(evento.getBlock().getLocation());
							} else {
								if (LuckMain.getLuckMain().blocoLocal.get(evento.getPlayer()).contains(evento.getBlock().getLocation())) {
									return;
								}
								LuckMain.getLuckMain().blocoLocal.get(evento.getPlayer()).add(evento.getBlock().getLocation());
							}
						} else {
							List<Location> listLocal = new ArrayList<>();
							listLocal.add(evento.getBlock().getLocation());
							LuckMain.getLuckMain().blocoLocal.put(evento.getPlayer(), listLocal);
						}

						for (String cmd : LuckMain.getLuckMain().blocosComandos.get(evento.getBlock().getType().toString())) {
							path = cmd.split("=");
							if (path.length == 5) {
								if (evento.getPlayer().hasPermission(path[2].trim())) {

									
									if (r.nextDouble() <= Double.parseDouble(path[1].trim())) {
										String comando = path[0].replaceAll("@player", evento.getPlayer().getName());
										Server s = LuckMain.getLuckMain().getServer();

										if (!(path[4].trim()).equalsIgnoreCase("nullo")) {
											Bukkit.broadcastMessage(path[4].replaceAll("&", "§").replaceAll("@player", evento.getPlayer().getName()));
										}

										if (!(path[3].trim()).equalsIgnoreCase("nullo")) {
											evento.getPlayer().sendMessage(path[3].replaceAll("&", "§"));
										}

										s.dispatchCommand(s.getConsoleSender(), comando);

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
	}
}
