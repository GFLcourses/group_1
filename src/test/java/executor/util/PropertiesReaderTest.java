package executor.util;

import executor.model.ThreadPoolConfig;
import junit.framework.TestCase;

public class PropertiesReaderTest extends TestCase {

    public void testReadThreadPoolConfig() {
        var poolConfig = new ThreadPoolConfig(5, 600_000L);
        assertEquals(PropertiesReader.readThreadPoolConfig(), poolConfig);
    }
}
