package me.gallowsdove.foxymachines.commands;

import io.github.mooy1.infinitylib.commands.SubCommand;
import me.gallowsdove.foxymachines.abstracts.CustomMob;
import me.gallowsdove.foxymachines.utils.CompatUtils;
import me.gallowsdove.foxymachines.utils.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.List;

public class ListallCommand extends SubCommand {
    public ListallCommand() {
        super("listall", "Lists all Custom Mobs from FoxyMachines", "foxymachines.admin");
    }

    @Override
    protected void execute(@Nonnull CommandSender sender, @Nonnull String[] args) {
        if (args.length != 0) {
            Messages.send(sender, ChatColor.LIGHT_PURPLE, "commands.usage", "Usage: /%usage%",
                    line -> line.replace("%usage%", "foxy listall"));
            return;
        }

        if (!CompatUtils.customMobsSupported()) {
            Messages.send(sender, ChatColor.LIGHT_PURPLE, "commands.custom-mobs-unsupported", "Custom mobs are not supported on this Minecraft version.");
            return;
        }

        CustomMob.debug(sender);
    }

    @Override
    protected void complete(@Nonnull CommandSender commandSender, @Nonnull String[] strings, @Nonnull List<String> list) { }
}