package pl.sopelplyt.sfreeze.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.sopelplyt.sfreeze.Main;

public class OnJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        if(Main.getInstance().dataFile.getFileConfiguration().getString("users." + event.getPlayer().getUniqueId()) != null)
        {
            if(!Main.getInstance().freezed.contains(event.getPlayer().getUniqueId()))
            {
                Main.getInstance().freezed.add(event.getPlayer().getUniqueId());
            }
        }
    }
}
