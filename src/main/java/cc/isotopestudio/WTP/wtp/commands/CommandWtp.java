package cc.isotopestudio.WTP.wtp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cc.isotopestudio.WTP.wtp.WTP;
import cc.isotopestudio.WTP.wtp.files.WTPConfig;
import cc.isotopestudio.WTP.wtp.files.WTPData;
import cc.isotopestudio.WTP.wtp.files.WTPPlayers;

public class CommandWtp implements CommandExecutor {
	private final WTP plugin;
	private final WTPData wtpData;
	private final WTPPlayers wtpPlayers;

	public CommandWtp(WTP plugin) {
		this.plugin = plugin;
		wtpData = new WTPData(plugin);
		wtpPlayers = new WTPPlayers(plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length > 0 && !args[0].equals("help") && sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("wtp")) {
				if (args[0].equals("create")) {
					if (args.length == 2) {
						if (wtpPlayers.getPlayerSpare(player) <= 0 && wtpPlayers.getPlayerWarpLim(player) != -1) {
							sender.sendMessage(
									new StringBuilder().append(ChatColor.RED).append("�㲻���ٴ�������ĵر���").toString());
							return true;
						}
						if (!args[1].matches("^[a-zA-Z]*")) {
							sender.sendMessage(
									new StringBuilder().append(ChatColor.RED).append("����ֻ��ΪӢ����ĸ").toString());
							return true;
						}
						if (args[1].length() >= 10) {
							sender.sendMessage(
									new StringBuilder().append(ChatColor.RED).append("���ֲ��ܳ���ʮ����ĸ").toString());
							return true;
						}
						if (wtpData.ifWarpExist(args[1])) {
							sender.sendMessage(new StringBuilder().append(ChatColor.RED)
									.append("�ر�" + args[1] + "�Ѿ����ڣ��������ְ�").toString());
							return true;
						}

						if (WTP.econ.getBalance(player.getName()) < WTPConfig.createFee) {
							sender.sendMessage(new StringBuilder().append(ChatColor.RED).append("��Ľ�Ǯ����").toString());
							return true;
						}
						WTP.econ.withdrawPlayer(player.getName(), WTPConfig.createFee);
						wtpData.addWarp(player, args[1]);
						sender.sendMessage(new StringBuilder().append(ChatColor.AQUA).append("�ɹ�������").toString());
						return true;
					} else {
						sender.sendMessage((new StringBuilder()).append(ChatColor.RED).append("/wtp create <�ر�����>")
								.append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE)
								.append("����" + WTPConfig.createFee + "����һ�������ر�").toString());
						return true;
					}
				}

				if (args[0].equals("alias")) {
					if (args.length >= 3) {
						if (!wtpPlayers.isOwner(player, args[1])) {
							sender.sendMessage(new StringBuilder().append(ChatColor.RED).append("�ⲻ����ĵر�").toString());
							return true;
						}
						String alias = CommandsUti.getArgsString(args, 2);
						if (alias.length() >= 15) {
							sender.sendMessage(
									new StringBuilder().append(ChatColor.RED).append("�������ܳ���15����").toString());
							return true;
						}
						if (WTP.econ.getBalance(player.getName()) < WTPConfig.aliasFee) {
							sender.sendMessage(new StringBuilder().append(ChatColor.RED).append("��Ľ�Ǯ����").toString());
							return true;
						}
						WTP.econ.withdrawPlayer(player.getName(), WTPConfig.aliasFee);
						wtpData.addAlias(args[1], alias);
						sender.sendMessage(new StringBuilder().append(ChatColor.AQUA).append("�ɹ���ӱ�����").toString());
						return true;
					} else {
						sender.sendMessage(new StringBuilder().append(ChatColor.RED).append("/wtp alias <�ر�����> <�ر����>")
								.append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE)
								.append("����" + WTPConfig.aliasFee + "�������ر���ӱ���").toString());
						return true;
					}
				}

				if (args[0].equals("msg")) {
					if (args.length >= 3) {
						if (!wtpPlayers.isOwner(player, args[1])) {
							sender.sendMessage(new StringBuilder().append(ChatColor.RED).append("�ⲻ����ĵر�").toString());
							return true;
						}
						String msg = CommandsUti.getArgsString(args, 2);
						if (msg.length() >= 30) {
							sender.sendMessage(
									new StringBuilder().append(ChatColor.RED).append("��ӭ��Ϣ���ܳ���30����").toString());
							return true;
						}
						if (WTP.econ.getBalance(player.getName()) < WTPConfig.welcomeFee) {
							sender.sendMessage(new StringBuilder().append(ChatColor.RED).append("��Ľ�Ǯ����").toString());
							return true;
						}
						WTP.econ.withdrawPlayer(player.getName(), WTPConfig.welcomeFee);
						wtpData.addMsg(args[1], msg);
						sender.sendMessage(new StringBuilder().append(ChatColor.AQUA).append("�ɹ���ӻ�ӭ��Ϣ��").toString());
						return true;
					} else {
						sender.sendMessage(new StringBuilder().append(ChatColor.RED).append("/wtp msg <�ر�����> <��ӭ��Ϣ>")
								.append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE)
								.append("����" + WTPConfig.welcomeFee + "�������ر���ӻ�ӭ��Ϣ").toString());
						return true;
					}
				}

				if (args[0].equals("relocate")) {
					if (args.length == 2) {
						if (!wtpPlayers.isOwner(player, args[1])) {
							sender.sendMessage(new StringBuilder().append(ChatColor.RED).append("�ⲻ����ĵر�").toString());
							return true;
						}
						if (WTP.econ.getBalance(player.getName()) < WTPConfig.relocationFee) {
							sender.sendMessage(new StringBuilder().append(ChatColor.RED).append("��Ľ�Ǯ����").toString());
							return true;
						}
						WTP.econ.withdrawPlayer(player.getName(), WTPConfig.relocationFee);
						wtpData.relocate(args[1], player);
						sender.sendMessage(new StringBuilder().append(ChatColor.AQUA).append("�ɹ��ı䴫�͵㣡").toString());
						return true;
					} else {
						sender.sendMessage(new StringBuilder().append(ChatColor.RED).append("/wtp relocate <�ر�����>")
								.append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE)
								.append("����" + WTPConfig.relocationFee + "���Ĺ����ر�Ϊ��ǰλ��").toString());
						return true;
					}
				}

				if (args[0].equals("about")) {
					CommandsUti.about(sender);
					return true;
				}
			}
		} else { // Help Menu
			sender.sendMessage((new StringBuilder(WTP.prefix)).append(ChatColor.AQUA).append("== ���� ==").toString());
			sender.sendMessage((new StringBuilder()).append(ChatColor.YELLOW).append("����һ�����Դ��������ر�Ĳ����������ҿ����������")
					.append(ChatColor.RED).append(ChatColor.ITALIC).append("�ر�����").append(ChatColor.RESET)
					.append(ChatColor.YELLOW).append("���͹���").toString());
			sender.sendMessage((new StringBuilder()).append(ChatColor.YELLOW).append("�鿴�ر��б����ʱ��ҽ��ῴ�������õĵر�")
					.append(ChatColor.RED).append(ChatColor.ITALIC).append("��ӭ��Ϣ").toString());
			sender.sendMessage((new StringBuilder()).append(ChatColor.RED).append(ChatColor.ITALIC).append("�ر����")
					.append(ChatColor.RESET).append(ChatColor.YELLOW).append("��������ҿ����ر�ļ�����Ľ���").toString());
			if (sender instanceof Player)
				sender.sendMessage((new StringBuilder()).append(ChatColor.GREEN)
						.append("�㻹���Դ���" + wtpPlayers.getPlayerSpareString((Player) sender) + "���ر�").toString());
			sender.sendMessage((new StringBuilder(WTP.prefix)).append(ChatColor.AQUA).append("== ����˵� ==").toString());
			sender.sendMessage(new StringBuilder().append(ChatColor.GOLD).append("/w <�ر�����>").append(ChatColor.GRAY)
					.append(" - ").append(ChatColor.LIGHT_PURPLE).append("���͵������ر�").toString());
			sender.sendMessage(new StringBuilder().append(ChatColor.GOLD).append("/wlist [ҳ��]").append(ChatColor.GRAY)
					.append(" - ").append(ChatColor.LIGHT_PURPLE).append("�鿴�����ر��б�").toString());

			sender.sendMessage((new StringBuilder()).append(ChatColor.GOLD).append("/wtp create <�ر�����>")
					.append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE)
					.append("����" + WTPConfig.createFee + "����һ�������ر�").toString());
			sender.sendMessage(new StringBuilder().append(ChatColor.GOLD).append("/wtp alias <�ر�����> <�ر����>")
					.append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE)
					.append("����" + WTPConfig.aliasFee + "�������ر���ӱ���").toString());
			sender.sendMessage(new StringBuilder().append(ChatColor.GOLD).append("/wtp msg <�ر�����> <��ӭ��Ϣ>")
					.append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE)
					.append("����" + WTPConfig.welcomeFee + "�������ر���ӻ�ӭ��Ϣ").toString());
			sender.sendMessage(new StringBuilder().append(ChatColor.GOLD).append("/wtp relocate <�ر�����>")
					.append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE)
					.append("����" + WTPConfig.relocationFee + "���Ĺ����ر�Ϊ��ǰλ��").toString());
			sender.sendMessage(new StringBuilder().append(ChatColor.GOLD).append("/wtp about").append(ChatColor.GRAY)
					.append(" - ").append(ChatColor.LIGHT_PURPLE).append("�鿴�����Ϣ").toString());

			if (!(sender instanceof Player)) {
				sender.sendMessage(
						(new StringBuilder(WTP.prefix)).append(ChatColor.RED).append("ֻ�������ִ����Щ���").toString());
			}
			return true;
		}
		return false;
	}
}
