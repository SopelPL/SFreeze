package pl.sopelplyt.sfreeze.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.sopelplyt.sfreeze.Main;

public class FreezeCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("freeze"))
        {
            if(sender instanceof Player) {
                if (args.length == 1) {
                    if (sender.hasPermission("sfreeze.use")) {
                        Player p = Bukkit.getPlayer(args[0]);

                        if (p != null) {
                            if (Main.getInstance().freezed.contains(p.getUniqueId())) {
                                Main.getInstance().dataFile.getFileConfiguration().set("users." + p.getUniqueId(), null);
                                Main.getInstance().freezed.remove(p.getUniqueId());

                                sender.sendMessage(ChatColor.GREEN + "Odmroziles tego gracza!");
                                p.sendMessage(ChatColor.GRAY + "Zostales odmrozony!");
                                return true;
                            }

                            Main.getInstance().dataFile.getFileConfiguration().set("users." + p.getUniqueId(), "freezed");
                            Main.getInstance().freezed.add(p.getUniqueId());
                            sender.sendMessage(ChatColor.GREEN + "Gracz " + ChatColor.YELLOW + p.getName() + ChatColor.GREEN + " zostal zamrozony!");
                            return true;
                        }

                        sender.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "Ten gracz jest offline!");
                        return false;
                    }

                    sender.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "Brak uprawnien " + ChatColor.GRAY + "(sfreeze.use)");
                    return false;
                }
                sender.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "Uzycie komendy " + ChatColor.WHITE + "/freeze <nick>");
                return false;
            }

            sender.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "Komenda dostepna tylko dla gracza!");
            return false;
        }
        return false;
    }
}
