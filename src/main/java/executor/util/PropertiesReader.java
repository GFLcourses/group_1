package executor.util;

import executor.model.*;
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

    public static WebDriverConfigDTO readWebDriverConfig() {
        String webDriverExecutable = CONFIGURATION.getString("webdriver.executable");
        String userAgent = CONFIGURATION.getString("webdriver.userAgent");
        Long pageLoadTimeout = CONFIGURATION.getLong("webdriver.pageLoadTimeout");
        Long implicitlyWait = CONFIGURATION.getLong("webdriver.implicitlyWait");

        return new WebDriverConfigDTO(webDriverExecutable,userAgent,pageLoadTimeout,implicitlyWait);
    }

    public static ProxyConfigHolderDto readProxyConfig() {
        String hostname = CONFIGURATION.getString("proxy.hostname");
        Integer port = CONFIGURATION.getInt("proxy.port");
        String username = CONFIGURATION.getString("proxy.username");
        String password = CONFIGURATION.getString("proxy.password");

        return new ProxyConfigHolderDto(new ProxyNetworkConfig(hostname,port), new ProxyCredentials(username,password));
    }
}
