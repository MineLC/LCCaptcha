package lc.captca;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public final class LCCaptcha extends JavaPlugin {

    private ConfigManager config;
    public static HashMap<Player, String> player_catpchat = new HashMap<Player, String>();
    public static ArrayList<Player> playerkick = new ArrayList<Player>();
    @Override
    public void onEnable() {
        config = new ConfigManager();
        config.registerConfig();

        Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getMessenger().registerOutgoingPluginChannel((Plugin)this, "captcha:channel");
        getCommand("captcha").setExecutor(new Command());
        System.out.println("[Captcha] Plugin Habilitado.");
    }




    public static void sendTitle(Player player, String mensaje, int fadein, int stay, int fadeout) {
        PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE,
                IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + mensaje + "\"}"), fadein, stay, fadeout);
        (((CraftPlayer)player).getHandle()).playerConnection.sendPacket(title);
    }

    public static void sendSubTitle(Player player, String mensaje, int fadein, int stay, int fadeout) {
        PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE,
                IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + mensaje + "\"}"), fadein, stay, fadeout);
        (((CraftPlayer)player).getHandle()).playerConnection.sendPacket(title);
    }

    public static void sendActionBar(String mensaje, Player player){
        PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + mensaje + "\"}"), (byte) 2);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
    }

    public static String generateRandomWord(int wordLength) {
        Random r = new Random(); // Intialize a Random Number Generator with SysTime as the seed
        StringBuilder sb = new StringBuilder(wordLength);
        for(int i = 0; i < wordLength; i++) { // For each letter in the word
            char tmp = (char) ('a' + r.nextInt('z' - 'a')); // Generate a letter between a and z
            sb.append(tmp); // Add it to the String
        }
        return sb.toString();
    }



}
