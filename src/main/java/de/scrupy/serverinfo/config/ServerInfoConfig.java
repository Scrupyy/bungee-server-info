package de.scrupy.serverinfo.config;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.*;

public class ServerInfoConfig {
    private final Plugin plugin;
    private File configFile;
    private Configuration configuration;

    public ServerInfoConfig(Plugin plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    private void createConfig() throws IOException {
        if (!plugin.getDataFolder().exists()) {
            plugin.getLogger().info("Created config folder: " + plugin.getDataFolder().mkdir());
        }

        this.configFile = new File(plugin.getDataFolder(), "serverinfo.yml");

        if (!configFile.exists()) {
            FileOutputStream outputStream = null;
            outputStream = new FileOutputStream(configFile);
            InputStream in = plugin.getResourceAsStream("serverinfo.yml");
            in.transferTo(outputStream);
        }
        this.configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
    }

    public void saveConfig() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(this.configuration, this.configFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadConfig() {
        try {
            this.createConfig();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }
}
