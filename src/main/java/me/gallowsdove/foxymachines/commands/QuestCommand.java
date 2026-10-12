package me.gallowsdove.foxymachines.commands;

import io.github.mooy1.infinitylib.commands.SubCommand;
import io.github.thebusybiscuit.slimefun5.utils.SlimefunUtils;
import io.github.thebusybiscuit.slimefun5.utils.compatibility.HandCompat;
import me.gallowsdove.foxymachines.Items;
import me.gallowsdove.foxymachines.utils.QuestUtils;
import me.gallowsdove.foxymachines.utils.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.List;

public class QuestCommand extends SubCommand {
    public QuestCommand() {
        super("quest", "Prints your current quest.", "foxymachines.info");
    }

    @Override
    protected void execute(@Nonnull CommandSender commandSender, @Nonnull String[] args) {
        if (!(commandSender instanceof Player)) {
            return;
        }
        Player p = (Player) commandSender;

        if (args.length != 0) {
            Messages.send(commandSender, ChatColor.LIGHT_PURPLE, "commands.usage", "Usage: /%usage%",
                    line -> line.replace("%usage%", "foxy quest"));
            return;
        }

        if (SlimefunUtils.isItemSimilar(HandCompat.getMainHand(p.getInventory()), Items.CURSED_SWORD.item(), false, false)) {
            QuestUtils.sendQuestLine(p, Items.CURSED_SWORD);
        } else if (SlimefunUtils.isItemSimilar(HandCompat.getMainHand(p.getInventory()), Items.CELESTIAL_SWORD.item(), false, false)) {
            QuestUtils.sendQuestLine(p, Items.CELESTIAL_SWORD);
        } else {
            Messages.send(p, ChatColor.LIGHT_PURPLE, "commands.hold-a-sword",
                    "You need to be holding the %cursed% or the %celestial% to view your quest.",
                    line -> line
                            .replace("%cursed%", ChatColor.RED + Messages.itemName(p, Items.CURSED_SWORD, "Cursed Sword") + ChatColor.LIGHT_PURPLE)
                            .replace("%celestial%", ChatColor.YELLOW + Messages.itemName(p, Items.CELESTIAL_SWORD, "Celestial Sword") + ChatColor.LIGHT_PURPLE));
        }
    }

    @Override
    protected void complete(@Nonnull CommandSender commandSender, @Nonnull String[] strings, @Nonnull List<String> list) { }
}