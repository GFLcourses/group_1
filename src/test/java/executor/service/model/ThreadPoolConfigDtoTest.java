package executor.service.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

public class ThreadPoolConfigDtoTest {
    private ThreadPoolConfigDto threadPoolConfig;
    private ThreadPoolConfigDto secondThreadPoolConfig;
    private final Integer CORE_POOL_SIZE = 4;
    private final Long KEEP_ALIVE_TIME = 1337L;

    @Before
    public void init() {
        this.threadPoolConfig = new ThreadPoolConfigDto(CORE_POOL_SIZE, KEEP_ALIVE_TIME);
        this.secondThreadPoolConfig = new ThreadPoolConfigDto(CORE_POOL_SIZE, KEEP_ALIVE_TIME);
    }

    @Test
    public void trueEqualsTest() {
        assertEquals(secondThreadPoolConfig, threadPoolConfig);
    }

    @Test
    public void falseEqualsTest() {
        secondThreadPoolConfig.setKeepAliveTime(1338L);
        assertNotEquals(secondThreadPoolConfig, threadPoolConfig);
    }

    @Test
    public void emptyObjectEqualsTest() {
        assertNotEquals(threadPoolConfig, new ThreadPoolConfigDto());
    }

    @Test
    public void sameHashCodeTest() {
        assertEquals(threadPoolConfig.hashCode(), secondThreadPoolConfig.hashCode());
    }

    @Test
    public void differentHashCodeTest() {
        secondThreadPoolConfig.setKeepAliveTime(1338L);
        assertNotEquals(threadPoolConfig.hashCode(), secondThreadPoolConfig.hashCode());
    }

    @Test
    public void emptyObjectHashCodeTest() {
        var emptyThreadPoolConfig = new ThreadPoolConfigDto();
        int hashCodeByUtil = Objects.hashCode(emptyThreadPoolConfig);
        assertEquals(emptyThreadPoolConfig.hashCode(), hashCodeByUtil);
    }
}
