package pl.sopelplyt.sfreeze.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import pl.sopelplyt.sfreeze.Main;

public class OnMove implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event)
    {
        if(event.getTo().getBlockX() == event.getFrom().getBlockX() && event.getTo().getBlockY() == event.getFrom().getBlockY() && event.getTo().getBlockZ() == event.getFrom().getBlockZ()) return;
        if(Main.getInstance().freezed.contains(event.getPlayer().getUniqueId()))
        {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.DARK_RED + "Jestes zamrozony!");
        }
    }
}
