package de.scrupy.serverinfo.config;

import net.md_5.bungee.config.Configuration;

public class ServerInfo {
    private ServerInfoConfig serverInfoConfig;
    private Configuration configuration;
    private String firstLine;
    private String secondLine;

    public ServerInfo(ServerInfoConfig serverInfoConfig) {
        this.serverInfoConfig = serverInfoConfig;
        this.configuration = serverInfoConfig.getConfiguration();
        loadServerInfo();
    }

    private void loadServerInfo() {
        this.firstLine = this.configuration.getString("motd.firstLine");
        this.secondLine = this.configuration.getString("motd.secondLine");
    }

    public void reloadServerInfo() {
        this.serverInfoConfig.loadConfig();
        this.configuration = this.serverInfoConfig.getConfiguration();
        loadServerInfo();
    }

    public String getFirstLine() {
        return firstLine;
    }

    public String getSecondLine() {
        return secondLine;
    }
}
