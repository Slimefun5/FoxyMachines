package me.gallowsdove.foxymachines.utils;

import java.util.List;
import java.util.function.UnaryOperator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;

import me.gallowsdove.foxymachines.FoxyMachines;

/**
 * Resolves this addon's chat text for one viewer, from {@code languages/<lang>/messages.yml}.
 *
 * @implNote The {@link ChatColor} deliberately stays at the call site rather than moving into the
 *           translated value: a translator never edits a colour code they cannot see the effect of.
 *           A console recipient is handled by the service, which applies the same substitutions to
 *           the English literal and strips the colour.
 */
public final class Messages {

    private Messages() {}

    @Nonnull
    public static String get(@Nullable Player player, @Nonnull String key, @Nonnull String english) {
        return Slimefun.getAddonMessageService().getMessage(player, FoxyMachines.getInstance(), key, english);
    }

    @Nonnull
    public static List<String> getList(@Nullable Player player, @Nonnull String key, @Nonnull List<String> english) {
        return Slimefun.getAddonMessageService().getMessages(player, FoxyMachines.getInstance(), key, english);
    }

    public static void send(@Nonnull CommandSender recipient, @Nonnull ChatColor colour, @Nonnull String key, @Nonnull String english) {
        send(recipient, colour, key, english, UnaryOperator.identity());
    }

    public static void send(@Nonnull CommandSender recipient, @Nonnull String key, @Nonnull String english, @Nonnull UnaryOperator<String> placeholders) {
        Slimefun.getAddonMessageService().sendMessage(recipient, FoxyMachines.getInstance(), key, english, placeholders);
    }

    public static void send(@Nonnull CommandSender recipient, @Nonnull ChatColor colour, @Nonnull String key, @Nonnull String english, @Nonnull UnaryOperator<String> placeholders) {
        Slimefun.getAddonMessageService().sendMessage(recipient, FoxyMachines.getInstance(), key, english,
                line -> colour + placeholders.apply(line));
    }

    /**
     * The viewer's own name for one of this addon's items, so a message naming an item reads exactly
     * like the item does in their guide.
     *
     * @implNote Colour-stripped on purpose: the name carries its own colour from {@code items.yml},
     *           which would end the surrounding sentence's colour mid-line.
     */
    @Nonnull
    public static String itemName(@Nullable Player player, @Nonnull SlimefunItemStack item, @Nonnull String english) {
        SlimefunItem resolved = player == null ? null : SlimefunItem.getById(item.getItemId());

        if (resolved == null) {
            return english;
        }

        return ChatColor.stripColor(Slimefun.getItemTranslationService().getName(player, resolved));
    }
}
