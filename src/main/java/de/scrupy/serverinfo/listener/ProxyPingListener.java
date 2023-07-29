package de.scrupy.serverinfo.listener;

import de.scrupy.serverinfo.ServerInfoPlugin;
import de.scrupy.serverinfo.config.ServerInfo;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyPingListener implements Listener {
    private ServerInfoPlugin plugin;

    public ProxyPingListener(ServerInfoPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPing(ProxyPingEvent event) {
        ServerPing serverPing = event.getResponse();
        ServerInfo serverInfo = plugin.getServerInfo();
        BaseComponent pingDescription = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', serverInfo.getFirstLine() + "\n" + serverInfo.getSecondLine())).getCurrentComponent();
        serverPing.setDescriptionComponent(pingDescription);
    }
}
