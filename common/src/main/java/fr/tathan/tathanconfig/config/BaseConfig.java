package fr.tathan.tathanconfig.config;

import com.electronwill.nightconfig.core.ConfigSpec;
import com.electronwill.nightconfig.core.file.FileConfig;

import java.io.File;

public abstract class BaseConfig {

    public final FileConfig config;
    public ConfigSpec spec = new ConfigSpec();
    private final String path;
    private final String name;

    public BaseConfig(String name, ConfigSide side, ConfigFormat format) {
        this.path = side.getPath() + name + format.getFormat();
        File file = new File(this.path);
        this.config = FileConfig.of(file);
        this.name = name;
    }

    public void addCommentedValue(String key, Object value, String description) {
        spec.define(key + ".value", value);
        spec.define(key + ".description", description);
    }


    public void addValue(String key, Object value) {
        spec.define(key, value);
    }

    protected abstract void addValues();

    public BaseConfig load() {
        config.load();
        addValues();
        spec.correct(config);
        config.save();

        return this;
    }

    public <T> T getCommentedValue(String key) {
        return getValue(key + ".value");
    }

    public <T> T getValue(String key) {
        this.config.load();
        return config.get(key);
    }


    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }
}
