package cc.isotopestudio.WTP.wtp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cc.isotopestudio.WTP.wtp.WTP;
import cc.isotopestudio.WTP.wtp.files.WTPConfig;

public class CommandWtpadmin implements CommandExecutor {
	private final WTP plugin;

	public CommandWtpadmin(WTP plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("wtpadmin")) {
			if (!sender.hasPermission("WTP.admin")) {
				sender.sendMessage(new StringBuilder().append(ChatColor.RED).append("��û��Ȩ��").toString());
				return true;
			}
			if (args.length > 0 && !args[0].equals("help")) {
				if (args[0].equalsIgnoreCase("player")) {
					if (args.length == 2) {

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
				sender.sendMessage(
						new StringBuilder().append(ChatColor.GOLD).append("/wtpadmin about").append(ChatColor.GRAY)
								.append(" - ").append(ChatColor.LIGHT_PURPLE).append("�鿴�����Ϣ").toString());

				return true;
			}
		}
		return false;
	}
}
