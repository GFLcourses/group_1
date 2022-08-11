package executor.service.util;

import executor.service.model.ThreadPoolConfigDto;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

public final class PropertiesReader {
    private static final FileBasedConfigurationBuilder<FileBasedConfiguration> BUILDER;
    private static final Configuration CONFIGURATION;
    private static final String FILE_PATH = "application.properties";

    private PropertiesReader() {  }

    static {
        BUILDER = new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                .configure(new Parameters().properties().setFileName(FILE_PATH));
        try {
            CONFIGURATION = BUILDER.getConfiguration();
        } catch (ConfigurationException throwable) {
            throw new RuntimeException(throwable);
        }
    }

    public static ThreadPoolConfigDto readThreadPoolConfig() {
        Integer poolSize = CONFIGURATION.getInt("threadpool.size");
        Long aliveTime = CONFIGURATION.getLong("threadpool.aliveTime");
        return new ThreadPoolConfigDto(poolSize, aliveTime);
    }
}
