package xyz.invisraidinq.pwhitelist;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class pWhitelist extends JavaPlugin implements Listener {

    public void onEnable() {
        if(!(new File(getDataFolder(), "config.yml").exists())) {
            saveDefaultConfig();
        }

        if(getConfig().getBoolean("Whitelist.enabled")) {
            Bukkit.getPluginManager().registerEvents(this, this);
        }
    }

    public static String translate (String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(!(p.hasPermission(getConfig().getString("Whitelist.permission")))) {
            p.kickPlayer(translate(getConfig().getString("kick-message")));
            return;
        } else {
                System.out.println(p.getName() + " has joined the server via the whitelist");
                return;
            }
        }
    }
