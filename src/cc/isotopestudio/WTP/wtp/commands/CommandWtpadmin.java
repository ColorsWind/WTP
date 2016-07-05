package cc.isotopestudio.WTP.wtp.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cc.isotopestudio.WTP.wtp.WTP;
import cc.isotopestudio.WTP.wtp.files.WTPConfig;
import cc.isotopestudio.WTP.wtp.files.WTPData;
import cc.isotopestudio.WTP.wtp.files.WTPPlayers;

public class CommandWtpadmin implements CommandExecutor {
	private final WTP plugin;
	private final WTPData wtpData;
	private final WTPPlayers wtpPlayers;

	public CommandWtpadmin(WTP plugin) {
		this.plugin = plugin;
		wtpData = new WTPData(plugin);
		wtpPlayers = new WTPPlayers(plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("wtpadmin")) {
			if (!sender.hasPermission("WTP.admin")) {
				sender.sendMessage(new StringBuilder().append(ChatColor.RED).append("��û��Ȩ��").toString());
				return true;
			}
			if (args.length > 0 && !args[0].equals("help")) {
				if (args[0].equals("check") && args.length == 2) {
					sender.sendMessage(args[1] + ": " + WTPConfig.getLimit(args[1]));
				}

				if (args[0].equalsIgnoreCase("player")) {
					if (args.length == 2) {
						Player player = Bukkit.getServer().getPlayer(args[1]);
						String playerName = args[1];
						// Check player online
						if (player != null) {
							playerName = player.getName();
						}
						sender.sendMessage((new StringBuilder(plugin.prefix)).append(ChatColor.AQUA).append("���")
								.append(args[1]).append("�Ĺ����ر�").toString());
						if (player != null)
							sender.sendMessage((new StringBuilder("    ")).append(ChatColor.YELLOW)
									.append(ChatColor.ITALIC).append("������ӵ��" + wtpPlayers.getPlayerSpareString(player)
											+ "������ӵ��" + wtpPlayers.getPlayerWarpNum(player) + "�������ر�")
									.toString());

						List<String> warpsList = wtpPlayers.getPlayerWarpsList(playerName);
						for (int i = 0; i < warpsList.size(); i++) {
							sender.sendMessage(wtpData.getWarpDes(warpsList.get(i)));
						}
						return true;
					} else {
						sender.sendMessage((new StringBuilder()).append(ChatColor.RED).append("/wtpadmin player <�������>")
								.append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE).append("�鿴��ҵĵر���Ϣ")
								.toString());
						return true;
					}
				}
				if (args[0].equalsIgnoreCase("warp")) {
					if (args.length == 2) {
						if (!wtpData.ifWarpExist(args[1])) {
							sender.sendMessage(new StringBuilder().append(ChatColor.RED).append("�ر겻����").toString());
							return true;
						}
						sender.sendMessage((new StringBuilder(plugin.prefix)).append(ChatColor.AQUA).append("�ر�: ")
								.append(args[1]).toString());
						sender.sendMessage(wtpData.getWarpDes(args[1]));
						sender.sendMessage((new StringBuilder("    ")).append(ChatColor.AQUA).append("ӵ����: ")
								.append(wtpData.getOwner(args[1])).toString());
						return true;
					} else {
						sender.sendMessage(new StringBuilder().append(ChatColor.RED).append("/wtpadmin warp <�ر�����>")
								.append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE).append("�鿴�����ر���Ϣ")
								.toString());

						return true;
					}
				}
				if (args[0].equalsIgnoreCase("delete")) {
					if (args.length == 2) {
						if (!wtpData.ifWarpExist(args[1])) {
							sender.sendMessage(new StringBuilder().append(ChatColor.RED).append("�ر겻����").toString());
							return true;
						}
						wtpData.deleteWarp(args[1]);
						sender.sendMessage(
								new StringBuilder().append(ChatColor.AQUA).append("�ɹ�ɾ��" + args[1]).toString());
						return true;
					} else {
						sender.sendMessage(new StringBuilder().append(ChatColor.RED).append("/wtpadmin delete <�ر�����>")
								.append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE).append("ɾ��һ�������ر�")
								.toString());
						return true;
					}
				}
				if (args[0].equalsIgnoreCase("about")) {
					CommandsUti.about(sender);
					return true;
				}
				if (args[0].equalsIgnoreCase("reload")) {
					plugin.onReload();
					return true;
				} else {
					sender.sendMessage(
							new StringBuilder().append(ChatColor.RED).append("δ֪�������/wtpadmin�鿴����").toString());
					return true;
				}
			} else { // Help Menu
				sender.sendMessage(
						(new StringBuilder(WTP.prefix)).append(ChatColor.AQUA).append("== ����Ա�˵� ==").toString());
				sender.sendMessage((new StringBuilder()).append(ChatColor.GOLD).append("/wtpadmin player <�������>")
						.append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE).append("�鿴��ҵĵر���Ϣ")
						.toString());
				sender.sendMessage(new StringBuilder().append(ChatColor.GOLD).append("/wtpadmin warp <�ر�����>")
						.append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE).append("�鿴�����ر���Ϣ")
						.toString());
				sender.sendMessage(new StringBuilder().append(ChatColor.GOLD).append("/wtpadmin delete <�ر�����>")
						.append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE).append("ɾ��һ�������ر�")
						.toString());
				sender.sendMessage(new StringBuilder().append(ChatColor.GOLD).append("/wtpadmin reload")
						.append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE).append("���ز��").toString());
				sender.sendMessage(
						new StringBuilder().append(ChatColor.GOLD).append("/wtpadmin about").append(ChatColor.GRAY)
								.append(" - ").append(ChatColor.LIGHT_PURPLE).append("�鿴�����Ϣ").toString());

				return true;
			}
		}
		return false;
	}
}
