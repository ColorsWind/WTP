package cc.isotopestudio.WTP.wtp;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import cc.isotopestudio.WTP.wtp.commands.*;
import cc.isotopestudio.WTP.wtp.files.WTPConfig;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

public final class WTP extends JavaPlugin {
	public final String version = "v1.0";

	public final String prefix = (new StringBuilder()).append(ChatColor.GREEN).append("[").append(ChatColor.ITALIC)
			.append(ChatColor.BOLD).append("公共地标").append(ChatColor.RESET).append(ChatColor.GREEN).append("]")
			.append(ChatColor.RESET).toString();
	
	private static final Logger log = Logger.getLogger("Minecraft");
	public static Economy econ = null;

	String FileVersion = "1";

	@Override
	public void onEnable() {
		getLogger().info("加载Vault API");
		if (!setupEconomy() ) {
            log.severe(String.format("[%s] - 公共地标无法载入，原因：Vault未安装", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

		getLogger().info("加载配置文件中");

		createFile("config");
		if (!getConfig().getString("FileVersion").equals(FileVersion)) {
			getLogger().info("公共地标 配置文件错误!");
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

		getLogger().info("公共地标 成功加载!");
		getLogger().info("公共地标 由ISOTOPE Studio制作!");
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
		getLogger().info("公共地标 成功卸载!");
	}

	// Vault API
	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}

	// Files
	public void createFile(String name) {

		File file;
		file = new File(getDataFolder(), name + ".yml");
		if (!file.exists()) {
			saveDefaultConfig();
		}
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
			getLogger().info("地标文件保存失败！");
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
			getLogger().info("地标文件保存失败！");
		}
	}

}
