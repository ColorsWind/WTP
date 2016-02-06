package cc.isotopestudio.WTP.wtp;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import cc.isotopestudio.WTP.wtp.commands.*;
import cc.isotopestudio.WTP.wtp.files.WTPConfig;

public final class WTP extends JavaPlugin {
	public final String version = "v1.0";

	public final String prefix = (new StringBuilder()).append(ChatColor.GREEN).append("[").append(ChatColor.ITALIC)
			.append(ChatColor.BOLD).append("�����ر�").append(ChatColor.RESET).append(ChatColor.GREEN).append("]")
			.append(ChatColor.RESET).toString();

	public void createFile(String name) {

		File file;
		file = new File(getDataFolder(), name + ".yml");
		if (!file.exists()) {
			saveDefaultConfig();
		}
	}

	String FileVersion = "1";

	@Override
	public void onEnable() {
		getLogger().info("���������ļ���");

		createFile("config");
		if (!getConfig().getString("FileVersion").equals(FileVersion)) {
			getLogger().info("�����ر� �����ļ�����!");
			onDisable();
			return;
		}

		try {
			getWarpsData().save(warpsFile);
			getPlayersData().save(playersFile);
		} catch (IOException e) {
		}

		// PluginManager pm = this.getServer().getPluginManager();
		WTPConfig.update(this);
		this.getCommand("w").setExecutor(new CommandW(this));
		this.getCommand("wlist").setExecutor(new CommandWlist(this));
		this.getCommand("wtp").setExecutor(new CommandWtp(this));
		this.getCommand("wtpadmin").setExecutor(new CommandWtpadmin(this));

		getLogger().info("�����ر� �ɹ�����!");
		getLogger().info("�����ر� ��ISOTOPE Studio����!");
		getLogger().info("http://isotopestudio.cc");
	}

	public void onReload() {
		reloadPlayersData();
		this.reloadConfig();
		WTPConfig.update(this);
	}

	@Override
	public void onDisable() {
		savePlayersData();
		getLogger().info("�����ر� �ɹ�ж��!");
	}

	private File playersFile = null;
	private FileConfiguration playersData = null;

	public void reloadPlayersData() {
		if (playersFile == null) {
			playersFile = new File(getDataFolder(), "players.yml");
		}
		playersData = YamlConfiguration.loadConfiguration(playersFile);
	}

	public FileConfiguration getPlayersData() {
		if (playersData == null) {
			reloadPlayersData();
		}
		return playersData;
	}

	public void savePlayersData() {
		if (playersData == null || playersFile == null) {
			return;
		}
		try {
			getPlayersData().save(playersFile);
		} catch (IOException ex) {
			getLogger().info("�ر��ļ�����ʧ�ܣ�");
		}
	}

	private File warpsFile = null;
	private FileConfiguration warpsData = null;

	public void reloadWarpsData() {
		if (warpsFile == null) {
			warpsFile = new File(getDataFolder(), "warps.yml");
		}
		warpsData = YamlConfiguration.loadConfiguration(warpsFile);
	}

	public FileConfiguration getWarpsData() {
		if (warpsData == null) {
			reloadWarpsData();
		}
		return warpsData;
	}

	public void saveWarpsData() {
		if (warpsData == null || warpsFile == null) {
			return;
		}
		try {
			getWarpsData().save(warpsFile);
		} catch (IOException ex) {
			getLogger().info("�ر��ļ�����ʧ�ܣ�");
		}
	}

}
