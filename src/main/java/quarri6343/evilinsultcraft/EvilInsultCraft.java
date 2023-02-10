package quarri6343.evilinsultcraft;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import quarri6343.evilinsultcraft.japanizer.JapanizeType;
import quarri6343.evilinsultcraft.japanizer.Japanizer;

import java.util.HashMap;

public final class EvilInsultCraft extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
    }

    @EventHandler
    public void onPlayerChat(AsyncChatEvent e){
        String message = new PlainComponentSerializer().serialize(e.message());

        if (message.isEmpty() || message.startsWith("/") || message.startsWith("#")) {
            return;
        }

        String convertedMessage = Japanizer.japanize(message, JapanizeType.GOOGLE_IME, new HashMap<String, String>());

        if(!convertedMessage.equals("")){
            Component component = Component.text(convertedMessage).hoverEvent(e.message());
            e.message(component);
        }
    }
}
