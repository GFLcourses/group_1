package executor.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

public class ThreadPoolConfigTest {
    private ThreadPoolConfig threadPoolConfig;
    private ThreadPoolConfig secondThreadPoolConfig;
    private final Integer CORE_POOL_SIZE = 4;
    private final Long KEEP_ALIVE_TIME = 1337L;

    @Before
    public void init() {
        this.threadPoolConfig = new ThreadPoolConfig(CORE_POOL_SIZE, KEEP_ALIVE_TIME);
        this.secondThreadPoolConfig = new ThreadPoolConfig(CORE_POOL_SIZE, KEEP_ALIVE_TIME);
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
        assertNotEquals(threadPoolConfig, new ThreadPoolConfig());
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
        var emptyThreadPoolConfig = new ThreadPoolConfig();
        int hashCodeByUtil = Objects.hashCode(emptyThreadPoolConfig);
        assertEquals(emptyThreadPoolConfig.hashCode(), hashCodeByUtil);
    }
}
