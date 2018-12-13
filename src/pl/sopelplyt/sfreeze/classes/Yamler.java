package pl.sopelplyt.sfreeze.classes;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Yamler {

    File file;
    FileConfiguration fileConfiguration;

    public Yamler(File file)
    {
        this.file = file;
        this.fileConfiguration = YamlConfiguration.loadConfiguration(this.file);
    }

    public File getFile()
    {
        return this.file;
    }

    public FileConfiguration getFileConfiguration()
    {
        return this.fileConfiguration;
    }

    public void save()
    {
        try
        {
            fileConfiguration.save(file);
        } catch(IOException exception)
        {
            exception.printStackTrace();
        }
    }
}
