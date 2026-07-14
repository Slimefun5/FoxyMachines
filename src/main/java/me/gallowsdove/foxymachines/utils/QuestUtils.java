package me.gallowsdove.foxymachines.utils;

import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.libraries.keys.NamespacedKey;
import io.github.thebusybiscuit.slimefun5.utils.compatibility.PdcCompat;
import io.github.thebusybiscuit.slimefun5.utils.ChatUtils;
import me.gallowsdove.foxymachines.FoxyMachines;
import me.gallowsdove.foxymachines.Items;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;

public class QuestUtils {
    private QuestUtils() {}

    public static final NamespacedKey KEY = new NamespacedKey(FoxyMachines.getInstance(), "quest");

    private static final List<EntityType> QUEST_MOBS = new ArrayList<>();
    private static final List<String> CURSED_LINES = Collections.unmodifiableList(Arrays.asList(
            "I would love to kill a {entity}, so tasty!",
            "Give me a {entity}, now!",
            "Surely you can help me slay a {entity}.",
            "I want blood....  {entity} blood.",
            "I need a {entity} liver.",
            "I've heard that {entity} blood is tasty...",
            "{entity} heart, hmmm...",
            "I would slay God himself for some {entity} flesh.",
            "I could be devouring a {entity} whole day.",
            "I've been waiting for too long. Too long or a day to kill a {entity}.",
            "{entity}'s blood shall be spilled",
            "My curse shall devour {entity}'s soul"));
    private static final List<String> CELESTIAL_LINES = Collections.unmodifiableList(Arrays.asList(
            "I love all beings... except {entity}, I hate those.",
            "All life must be in balance, what's why I need to kill a {entity}.",
            "I am celestial, but I am also a sword. Now get me a {entity}.",
            "I'm sorry, but please get me some {entity}. No questions.",
            "Celestial sword requires a celestial sacrifice. A {entity}.",
            "My next victim should be {entity}, just as God intended.",
            "And the next in line is ... {entity}!",
            "The God wants a {entity} dead.",
            "For God and honour, go slay a {entity}.",
            "Go, get that {entity}! For justice!",
            "The stars have aligned. I can clearly see the {entity} that shall die by my blade"));


    public static void init() {
        if (!QUEST_MOBS.isEmpty()) {
            FoxyMachines.log(Level.WARNING, "Attempted to initialize QuestUtils after already initialized!");
            return;
        }

        for (String questMob : FoxyMachines.getInstance().getConfig().getStringList("quest-mobs")) {
            try {
                QuestUtils.QUEST_MOBS.add(EntityType.valueOf(questMob));
            } catch (IllegalArgumentException ignored) {
                FoxyMachines.log(Level.WARNING, "Invalid Entity Type in \"quest-mobs\": " + questMob);
            }
        }
    }

    @ParametersAreNonnullByDefault
    public static boolean hasActiveQuest(Player p) {
        return PdcCompat.has(p, KEY, "INTEGER");
    }

    @ParametersAreNonnullByDefault
    public static boolean isQuestEntity(Player p, LivingEntity e) {
        return hasActiveQuest(p) && toEntityType(p, getQuestLine(p)) == e.getType();
    }

    @ParametersAreNonnullByDefault
    public static int getQuestLine(Player p) {
        int id;

        if (PdcCompat.has(p, KEY, "INTEGER")) {
            id = PdcCompat.getInt(p, KEY);
        } else {
            id = nextQuestLine(p);
        }

        return id;
    }

    @ParametersAreNonnullByDefault
    public static int nextQuestLine(Player p) {
        int id = ThreadLocalRandom.current().nextInt(QUEST_MOBS.size());
        PdcCompat.setInt(p, KEY, id);
        return id;
    }

    @ParametersAreNonnullByDefault
    public static void sendQuestLine(Player p, SlimefunItemStack item) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        String entity = toString(p, getQuestLine(p));

        if (item == Items.CURSED_SWORD) {
            int i = random.nextInt(CURSED_LINES.size());
            String line = CURSED_LINES.get(i).replace("{entity}", entity);
            p.sendMessage(ChatColor.RED + line);
        } else if (item == Items.CELESTIAL_SWORD) {
            int i = random.nextInt(CELESTIAL_LINES.size());
            String line = CELESTIAL_LINES.get(i).replace("{entity}", entity);
            p.sendMessage(ChatColor.YELLOW + line);
        }
    }

    @ParametersAreNonnullByDefault
    public static void resetQuestLine(Player p) {
        if (PdcCompat.has(p, KEY, "INTEGER")) {
            PdcCompat.remove(p, KEY);
        }
    }

    public static EntityType toEntityType(Player p, int id) {
        if (id >= QUEST_MOBS.size()) {
            id = nextQuestLine(p);
        }

        return QUEST_MOBS.get(id);
    }

    public static String toString(Player p, int id) {
        if (id >= QUEST_MOBS.size()) {
            id = nextQuestLine(p);
        }

        return ChatUtils.humanize(QUEST_MOBS.get(id).name().toLowerCase());
    }
}