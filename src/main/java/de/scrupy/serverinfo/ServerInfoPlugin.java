package de.scrupy.serverinfo;

import de.scrupy.serverinfo.command.ReloadServerInfoCommand;
import de.scrupy.serverinfo.config.ServerInfo;
import de.scrupy.serverinfo.config.ServerInfoConfig;
import de.scrupy.serverinfo.listener.ProxyPingListener;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.plugin.Plugin;

public class ServerInfoPlugin extends Plugin {
    private static final String PREFIX = "§8[§3§lServer-Info§8] §7• ";

    private ServerInfoConfig serverInfoConfig;
    private ServerInfo serverInfo;

    @Override
    public void onEnable() {
        this.serverInfoConfig = new ServerInfoConfig(this);
        this.serverInfo = new ServerInfo(serverInfoConfig);

        this.getProxy().getPluginManager().registerCommand(this, new ReloadServerInfoCommand(serverInfo));

        this.getProxy().getPluginManager().registerListener(this, new ProxyPingListener(this));

        ProxyServer.getInstance().getConsole().sendMessage(new ComponentBuilder(getPrefix() + "plugin successfully §a§lstarted.").create());
    }

    @Override
    public void onDisable() {
        serverInfoConfig.saveConfig();
        ProxyServer.getInstance().getConsole().sendMessage(new ComponentBuilder(getPrefix() + "plugin successfully §c§lstopped.").create());
    }

    public static String getPrefix() {
        return PREFIX;
    }

    public ServerInfo getServerInfo() {
        return serverInfo;
    }
}
