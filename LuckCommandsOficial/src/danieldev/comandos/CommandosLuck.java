package danieldev.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import danieldev.arquivosConfig.ArquivosConfig;
import danieldev.main.LuckMain;

public class CommandosLuck implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (args.length > 0) {

			if (args[0].equalsIgnoreCase("reload")) {

				if (sender instanceof Player) {
					if (sender.hasPermission("luckcommands.reload")) {
						Player p = (Player) sender;
						ArquivosConfig ac = new ArquivosConfig();
						ac.reloadComandoList();
						LuckMain.getLuckMain().setBlocos();
						LuckMain.getLuckMain().setEntidades();
						p.sendMessage("§3§l[LuckCommands]Arquivos Carregados");
					} else {
						sender.sendMessage("§fNão tem permissão para usar este comando.");
					}
					return false;
				} else {
					ArquivosConfig ac = new ArquivosConfig();
					ac.reloadComandoList();
					sender.sendMessage("§3§l[LuckCommands]Arquivos Carregados");
					return false;
				}
			}

			if (args[0].equalsIgnoreCase("iinfo")) {

				if (sender.hasPermission("luckcommands.iinfo")) {
					if (sender instanceof Player) {
						Player p = (Player) sender;

						if (p.getInventory().getItemInMainHand() != null) {
							sender.sendMessage("ID: " + p.getInventory().getItemInMainHand().getType());
						} else {
							p.sendMessage("Segure o item na mão principal para ver seu ID.");
						}

					}
				} else {
					sender.sendMessage("§fNão tem permissão para usar este comando.");
				}
			}

			return false;
		} else {

			if (sender instanceof Player) {
				if (sender.hasPermission("luckcommands.reload")) {
					Player p = (Player) sender;
					getHelp(p);
				} else {
					sender.sendMessage("§fNão tem permissão para usar este comando.");
				}
				return false;
			} else {
				getHelp(null);
			}

			return false;
		}

	}

	public void getHelp(Player sender) {

		if (sender != null) {
			if (sender.hasPermission("luckcommands.reload")) {
				sender.sendMessage("§3[LuckCommands] ----LuckCommands-----!");
				sender.sendMessage("§3[LuckCommands] use §2/luck reload  §3= recarrega os arquivos de configuração deste plugin.");
				sender.sendMessage("§3[LuckCommands] -------------------------!");
			} else {
				sender.sendMessage("§fNão tem permissão para usar este comando.");
			}
			return;
		} else {
			Bukkit.getConsoleSender().sendMessage("§3[LuckCommands] ----LuckCommands-----!");
			Bukkit.getConsoleSender().sendMessage("§3[LuckCommands] use §2/luck reload  §3= recarrega os arquivos de configuração deste plugin.");
			Bukkit.getConsoleSender().sendMessage("§3[LuckCommands] -------------------------!");
		}

	}
}
