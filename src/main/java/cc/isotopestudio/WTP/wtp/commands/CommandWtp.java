package cc.isotopestudio.WTP.wtp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cc.isotopestudio.WTP.wtp.WTP;
import cc.isotopestudio.WTP.wtp.files.WTPConfig;
import cc.isotopestudio.WTP.wtp.files.WTPPlayers;

public class CommandWtp implements CommandExecutor {
	private final WTP plugin;

	public CommandWtp(WTP plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length > 0 && !args[0].equals("help") && sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("wtp")) {
				if (args[0].equals("create")) {
					if (args.length == 2) {
						WTPPlayers.returnPlayerSpare(player, plugin);

						return true;
					} else {
						sender.sendMessage((new StringBuilder()).append(ChatColor.RED).append("/wtp create <�ر�����>")
								.append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE)
								.append("����" + WTPConfig.createFee + "����һ�������ر�(����ǿ�ҽ��鲻Ҫ��������)").toString());
						return true;
					}
				}
				if (args[0].equals("name")) {
					if (args.length == 3) {
						return true;
					} else {
						sender.sendMessage(new StringBuilder().append(ChatColor.RED).append("/wtp name <�ر�����> <�ر����>")
								.append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE)
								.append("����" + WTPConfig.aliasFee + "�������ر���ӱ���").toString());
						return true;
					}
				}
				if (args[0].equals("msg")) {
					if (args.length == 3) {
						return true;
					} else {
						sender.sendMessage(new StringBuilder().append(ChatColor.RED).append("/wtp msg <�ر�����> <��ӭ��Ϣ>")
								.append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE)
								.append("����" + WTPConfig.welcomeFee + "�������ر���ӻ�ӭ��Ϣ").toString());
						return true;
					}
				}
				if (args[0].equals("about")) {
					about(sender);
					return true;
				}
			}
		} else { // Help Menu
			sender.sendMessage((new StringBuilder(plugin.prefix)).append(ChatColor.AQUA).append("== ���� ==").toString());
			sender.sendMessage((new StringBuilder()).append(ChatColor.YELLOW).append("����һ�����Դ��������ر�Ĳ����������ҿ����������")
					.append(ChatColor.RED).append(ChatColor.ITALIC).append("�ر�����").append(ChatColor.RESET)
					.append(ChatColor.YELLOW).append("���͹���").toString());
			sender.sendMessage((new StringBuilder()).append(ChatColor.YELLOW).append("����ʱ��ҽ��ῴ�������õĵر�")
					.append(ChatColor.RED).append(ChatColor.ITALIC).append("��ӭ��Ϣ").toString());
			sender.sendMessage((new StringBuilder()).append(ChatColor.RED).append(ChatColor.ITALIC).append("�ر����")
					.append(ChatColor.RESET).append(ChatColor.YELLOW).append("��������ҿ����ر�ļ�����Ľ���").toString());
			if (sender instanceof Player)
				sender.sendMessage((new StringBuilder()).append(ChatColor.GREEN)
						.append("�㻹���Դ���" + WTPPlayers.returnPlayerSpareString((Player) sender, plugin) + "���ر�")
						.toString());
			sender.sendMessage(
					(new StringBuilder(plugin.prefix)).append(ChatColor.AQUA).append("== ����˵� ==").toString());
			sender.sendMessage((new StringBuilder()).append(ChatColor.GOLD).append("/wtp create <�ر�����>")
					.append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE)
					.append("����" + WTPConfig.createFee + "����һ�������ر�(����ǿ�ҽ��鲻Ҫ��������)").toString());
			sender.sendMessage(new StringBuilder().append(ChatColor.GOLD).append("/wtp name <�ر�����> <�ر����>")
					.append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE)
					.append("����" + WTPConfig.aliasFee + "�������ر���ӱ���").toString());
			sender.sendMessage(new StringBuilder().append(ChatColor.GOLD).append("/wtp msg <�ر�����> <��ӭ��Ϣ>")
					.append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE)
					.append("����" + WTPConfig.welcomeFee + "�������ر���ӻ�ӭ��Ϣ").toString());
			sender.sendMessage(new StringBuilder().append(ChatColor.GOLD).append("/wtp about").append(ChatColor.GRAY)
					.append(" - ").append(ChatColor.LIGHT_PURPLE).append("�鿴�����Ϣ").toString());

			if (!(sender instanceof Player)) {
				sender.sendMessage(
						(new StringBuilder(plugin.prefix)).append(ChatColor.RED).append("ֻ�������ִ����Щ���").toString());
			}
			return true;
		}
		return false;
	}

	public void about(CommandSender sender) {
		sender.sendMessage((new StringBuilder()).append(ChatColor.GRAY).append("---- " + plugin.prefix)
				.append(ChatColor.RESET).append(ChatColor.DARK_GRAY).append(" " + plugin.version).append(ChatColor.GRAY)
				.append(" ----").toString());
		sender.sendMessage((new StringBuilder()).append(ChatColor.BLUE).append(ChatColor.ITALIC).append("Ϊ�����������Ĺ����ر���")
				.toString());
		sender.sendMessage((new StringBuilder()).append(ChatColor.GOLD).append(ChatColor.BOLD).append("������ ")
				.append(ChatColor.RESET).append(ChatColor.AQUA).append("Mars (ISOTOPE Studio)").toString());
		sender.sendMessage((new StringBuilder()).append(ChatColor.GOLD).append(ChatColor.BOLD).append("��ַ�� ")
				.append(ChatColor.RESET).append(ChatColor.AQUA).append("http://isotopestudio.cc/minecraft.html")
				.toString());
	}
}
