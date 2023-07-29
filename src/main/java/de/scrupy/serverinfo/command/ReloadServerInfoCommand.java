package de.scrupy.serverinfo.command;

import de.scrupy.serverinfo.ServerInfoPlugin;
import de.scrupy.serverinfo.config.ServerInfo;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ReloadServerInfoCommand extends Command {
    private ServerInfo serverInfo;

    public ReloadServerInfoCommand(ServerInfo serverInfo) {
        super("reloadInfo");
        this.serverInfo = serverInfo;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            sender.sendMessage(new ComponentBuilder("§cYou are not allowed to execute this command.").create());
            return;
        }

        this.serverInfo.reloadServerInfo();
        sender.sendMessage(new ComponentBuilder(ServerInfoPlugin.getPrefix() + "successfully updated server-info.").create());
    }
}
