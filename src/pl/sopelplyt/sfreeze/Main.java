package pl.sopelplyt.sfreeze;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import pl.sopelplyt.sfreeze.classes.Yamler;
import pl.sopelplyt.sfreeze.cmds.FreezeCmd;
import pl.sopelplyt.sfreeze.events.OnJoin;
import pl.sopelplyt.sfreeze.events.OnMove;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main extends JavaPlugin {

    private static Main instance;

    public List<UUID> freezed = new ArrayList<UUID>();

    public Yamler dataFile;

    @Override
    public void onEnable() {
        instance = this;
        this.getServer().getPluginManager().registerEvents(new OnMove(), this);
        this.getServer().getPluginManager().registerEvents(new OnJoin(), this);
        this.getCommand("freeze").setExecutor(new FreezeCmd());
        dataFile = new Yamler(new File(this.getDataFolder() + "/data.yml"));

        if(this.getServer().getOnlinePlayers().size() > 0)
        {
            for (Player p : this.getServer().getOnlinePlayers())
            {
                if(dataFile.getFileConfiguration().getString("users." + p.getUniqueId()) != null && !freezed.contains(p.getUniqueId()))
                {
                    freezed.add(p.getUniqueId());
                }
                else
                {
                    continue;
                }
            }
        }

        this.getServer().getConsoleSender().sendMessage(ChatColor.GRAY + "______________________________");
        this.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "SFreeze");
        this.getServer().getConsoleSender().sendMessage(" ");
        this.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Autor: SopelPL");
        this.getServer().getConsoleSender().sendMessage(ChatColor.GRAY + "______________________________");
    }

    @Override
    public void onDisable()
    {
        dataFile.save();
    }

    public static Main getInstance()
    {
        return instance;
    }
}
