package executor.service.util;

import executor.service.model.ThreadPoolConfigDto;
import org.junit.Test;

import static org.junit.Assert.*;

public class PropertiesReaderTest {

    @Test
    public void readThreadPoolConfig() {
        var poolConfig = new ThreadPoolConfigDto(10, 600000L);
        assertEquals(PropertiesReader.readThreadPoolConfig(), poolConfig);
    }
}