package lc.captca;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }
        Player p = (Player) sender;
        if(args.length < 1){
            sender.sendMessage(ChatColor.RED + "Debes escribir el comando seguido de tu codigo.");
            return false;
        }
        if(args[0].equalsIgnoreCase(LCCaptcha.player_catpchat.get(p))){
            LCCaptcha.playerkick.remove(p);
            p.sendMessage(ChatColor.GREEN + "Captcha completado correctamente.");
            String barr = ChatColor.translateAlternateColorCodes('&', "&7&m-------------------------");
            String msg = ChatColor.translateAlternateColorCodes('&', "&fBien, ya completaste el captcha y podemos continuar");
            String msg2 = ChatColor.translateAlternateColorCodes('&', "&fsi eres nuevo, debes registrate usando el comando: &a/register <clave>");
            String msg3 = ChatColor.translateAlternateColorCodes('&', "&fsi ya estas registrado, usa el comando: &a/login <clave>");
            String msg4 = ChatColor.translateAlternateColorCodes('&', "&fsi tienes problemas contactanos en: &9discord.minelc.net");

            String barr2 = ChatColor.translateAlternateColorCodes('&', "&7&m-------------------------");
            p.sendMessage(barr);
            p.sendMessage(msg);
            p.sendMessage(msg2);
            p.sendMessage(msg3);
            p.sendMessage(msg4);
            p.sendMessage(barr2);
            p.playSound(p.getLocation(), Sound.NOTE_PLING, 5f,5f);
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            try {
                out.writeUTF("Message");
                out.writeUTF(p.getName());
                out.writeUTF("catpcha");
            } catch(Exception i) {
                i.printStackTrace();
            }
            p.sendPluginMessage(LCCaptcha.getPlugin(LCCaptcha.class), "captcha:channel", b.toByteArray());
        } else {
            p.sendMessage(ChatColor.RED + "El captcha no coincide, vuelve a intentarlo.");
        }
        return false;
    }
}
