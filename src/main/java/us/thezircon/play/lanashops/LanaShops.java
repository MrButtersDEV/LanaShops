package us.thezircon.play.lanashops;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import us.thezircon.play.lanashops.commands.reload;
import us.thezircon.play.lanashops.commands.shopignore;
import us.thezircon.play.lanashops.listener.*;
import us.thezircon.play.lanashops.utils.BuyMenu;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

public final class LanaShops extends JavaPlugin {

    private static final Logger log = Logger.getLogger("Minecraft");

    public HashMap<Player, BuyMenu> openShop = new HashMap<>();
    public HashMap<Location, Boolean> shopInUse = new HashMap<>();
    public HashMap<Location, Boolean> containerInUse = new HashMap<>();
    public ArrayList<Player> shopignore_list = new ArrayList<>();

    @Override
    public void onEnable() {
        // Setup - Config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Update Config:
        try {
            File configFile = new File(getDataFolder(), "config.yml");
            YamlConfiguration conf = YamlConfiguration.loadConfiguration(configFile);
            if (!conf.contains("Messages.shopIgnoreEnable")) {
                log.info("[LanaShops] Updating Config @ key: Messages.shopIgnoreEnable");
                conf.set("Messages.shopIgnoreEnable", "&7You are now ignoring shop access restrictions");
                conf.save(configFile);
            }

            if (!conf.contains("Messages.shopIgnoreDisable")) {
                log.info("[LanaShops] Updating Config @ key: Messages.shopIgnoreDisable");
                conf.set("Messages.shopIgnoreDisable", "&7You may no longer ignoring access restrictions to player shops");
                conf.save(configFile);
            }
        } catch (IOException ignored) {}

        // Setup - Commands
        getCommand("ls-reload").setExecutor(new reload());
        getCommand("shopignore").setExecutor(new shopignore());

        // Setup - Listeners
        getServer().getPluginManager().registerEvents(new SignChangeEventListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryOpenEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractEventListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickEventListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakEventListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryCloseEventListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryMoveItemEventListener(), this);

    }
}
