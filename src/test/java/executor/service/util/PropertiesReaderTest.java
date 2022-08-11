package executor.service.util;

import executor.service.model.ThreadPoolConfigDto;
import junit.framework.TestCase;

public class PropertiesReaderTest extends TestCase {

    public void testReadThreadPoolConfig() {
        var poolConfig = new ThreadPoolConfigDto(2, 600_000L);
        assertEquals(PropertiesReader.readThreadPoolConfig(), poolConfig);
    }
}
