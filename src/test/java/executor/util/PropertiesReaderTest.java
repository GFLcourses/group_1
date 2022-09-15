package executor.util;

import executor.model.ThreadPoolConfigDto;
import junit.framework.TestCase;

public class PropertiesReaderTest extends TestCase {

    public void testReadThreadPoolConfig() {
        var poolConfig = new ThreadPoolConfigDto(2, 600_000L);
        assertEquals(PropertiesReader.readThreadPoolConfig(), poolConfig);
    }
}
