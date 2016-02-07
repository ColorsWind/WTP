package cc.isotopestudio.WTP.wtp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.avaje.ebeaninternal.server.cluster.Packet;

import cc.isotopestudio.WTP.wtp.WTP;
import cc.isotopestudio.WTP.wtp.files.WTPConfig;
import cc.isotopestudio.WTP.wtp.files.WTPData;
import cc.isotopestudio.WTP.wtp.files.WTPPlayers;

public class CommandW implements CommandExecutor {
	private final WTPPlayers wtpPlayers;

	public CommandW(WTP plugin) {
		wtpPlayers = new WTPPlayers(plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("w")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (!player.hasPermission("WTP.teleport")) {
					sender.sendMessage(new StringBuilder().append(ChatColor.RED).append("��û��Ȩ�޴���").toString());
					return true;
				}
				if (args != null && args.length == 1) {
					wtpPlayers.tpWarp(player, args[0]);
					return true;
				} else {
					sender.sendMessage(new StringBuilder().append(ChatColor.RED).append("/w <�ر�����>")
							.append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE)
							.append("���͵������ر꣬����/wlist�鿴�ر��б�").toString());
					return true;
				}
			} else {
				sender.sendMessage(
						(new StringBuilder(WTP.prefix)).append(ChatColor.RED).append("ֻ�������ִ��������").toString());
				return true;
			}
		}
		return false;
	}
}
