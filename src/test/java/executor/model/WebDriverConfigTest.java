package executor.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class WebDriverConfigTest {

    private static final Long LOAD_TIMEOUT = 1L;
    private static final String WEB_DRIVER_EXECUTABLE = "chrome";
    private static final String WEB_DRIVER_EXECUTABLE_OTHER = "chrome other";
    private static final String USER_AGENT = "user agent";
    private static final String USER_AGENT_OTHER = "user agent other";
    private WebDriverConfig webDriverConfig;
    private WebDriverConfig otherWebDriverConfig;

    @Before
    public void setUp() {
        webDriverConfig = new WebDriverConfig();
        otherWebDriverConfig = new WebDriverConfig();
    }

    @Test
    public void getWebDriverExecutableShouldReturnWebDriverExecutableFieldValue() {
        otherWebDriverConfig.setWebDriverExecutable(WEB_DRIVER_EXECUTABLE);
        String actual = otherWebDriverConfig.getWebDriverExecutable();

        assertEquals(WEB_DRIVER_EXECUTABLE, actual);
    }

    @Test
    public void getUserAgentShouldReturnUserAgentFieldValue() {
        otherWebDriverConfig.setUserAgent(WEB_DRIVER_EXECUTABLE);
        String actual = otherWebDriverConfig.getUserAgent();

        assertEquals(WEB_DRIVER_EXECUTABLE, actual);
    }

    @Test
    public void getPageLoadTimeoutShouldReturnPageLoadTimeoutFieldValue() {
        otherWebDriverConfig.setPageLoadTimeout(LOAD_TIMEOUT);
        Long actual = otherWebDriverConfig.getPageLoadTimeout();

        assertEquals(LOAD_TIMEOUT, actual);
    }

    @Test
    public void getImplicitlyWaitShouldReturnImplicitlyWaitFieldValue() {
        otherWebDriverConfig.setImplicitlyWait(LOAD_TIMEOUT);
        Long actual = otherWebDriverConfig.getImplicitlyWait();

        assertEquals(LOAD_TIMEOUT, actual);
    }

    @Test
    public void setWebDriverExecutableShouldSetWebDriverExecutableFieldValue() {
        otherWebDriverConfig.setWebDriverExecutable(WEB_DRIVER_EXECUTABLE);
        String actual = otherWebDriverConfig.getWebDriverExecutable();

        assertEquals(WEB_DRIVER_EXECUTABLE, actual);
    }

    @Test
    public void setUserAgentShouldSetUserAgentFieldValue() {
        otherWebDriverConfig.setUserAgent(WEB_DRIVER_EXECUTABLE);
        String actual = otherWebDriverConfig.getUserAgent();

        assertEquals(WEB_DRIVER_EXECUTABLE, actual);
    }

    @Test
    public void setPageLoadTimeoutShouldSetPageLoadTimeoutFieldValue() {
        otherWebDriverConfig.setPageLoadTimeout(LOAD_TIMEOUT);
        Long actual = otherWebDriverConfig.getPageLoadTimeout();

        assertEquals(LOAD_TIMEOUT, actual);
    }

    @Test
    public void setImplicitlyWaitShouldSetImplicitlyWaitFieldValue() {
        otherWebDriverConfig.setImplicitlyWait(LOAD_TIMEOUT);
        Long actual = otherWebDriverConfig.getImplicitlyWait();

        assertEquals(LOAD_TIMEOUT, actual);
    }

    @Test
    public void equalsShouldReturnTrueIfParameterHasSameReference(){
        boolean actual = webDriverConfig.equals(webDriverConfig);
        assertTrue(actual);
    }

    @Test
    public void equalsShouldReturnFalseIfParameterReferenceEqualsNull(){
        boolean actual = webDriverConfig.equals(null);
        assertFalse(actual);
    }

    @Test
    public void equalsShouldReturnFalseIfParameterIsNotSameClass(){
        boolean actual = webDriverConfig.equals("");
        assertFalse(actual);
    }

    @Test
    public void equalsShouldReturnTrueIfAllFieldsAreSame(){
        boolean actual = webDriverConfig.equals(otherWebDriverConfig);
        assertTrue(actual);
    }

    @Test
    public void equalsShouldReturnFalseIfWebDriverExecutableFieldsNotEquals(){
        otherWebDriverConfig.setWebDriverExecutable(WEB_DRIVER_EXECUTABLE);
        boolean actual = webDriverConfig.equals(otherWebDriverConfig);
        assertFalse(actual);
    }

    @Test
    public void equalsShouldReturnFalseIfUserAgentFieldsNotEquals(){
        otherWebDriverConfig.setUserAgent(WEB_DRIVER_EXECUTABLE);
        boolean actual = webDriverConfig.equals(otherWebDriverConfig);
        assertFalse(actual);
    }

    @Test
    public void equalsShouldReturnFalseIfPageLoadTimeoutFieldsNotEquals(){
        otherWebDriverConfig.setPageLoadTimeout(LOAD_TIMEOUT);
        boolean actual = webDriverConfig.equals(otherWebDriverConfig);
        assertFalse(actual);
    }

    @Test
    public void equalsShouldReturnFalseIfImplicitlyWaitFieldsNotEquals(){
        otherWebDriverConfig.setImplicitlyWait(LOAD_TIMEOUT);
        boolean actual = webDriverConfig.equals(otherWebDriverConfig);
        assertFalse(actual);
    }

    @Test
    public void hashCodeShouldReturnSameNumberIfBothObjectsAreEquals(){
        int expected = webDriverConfig.hashCode();
        int actual = otherWebDriverConfig.hashCode();
        assertEquals(expected, actual);
    }

    @Test
    public void hashCodeShouldReturnSameNumberIfAllFieldsAreEquals(){
        int expected = webDriverConfig.hashCode();
        int actual = otherWebDriverConfig.hashCode();
        assertEquals(expected, actual);
    }

    @Test
    public void hashCodeShouldReturnSameNumberIfHashCodeMethodWasCalledTwice(){
        int expected = webDriverConfig.hashCode();
        int actual = webDriverConfig.hashCode();
        assertEquals(expected, actual);
    }

    @Test
    public void equalsShouldReturnTrueIfBothNonEmptyObjectsEquals() {
        webDriverConfig = new WebDriverConfig(WEB_DRIVER_EXECUTABLE, USER_AGENT, LOAD_TIMEOUT, LOAD_TIMEOUT);
        otherWebDriverConfig = new WebDriverConfig(WEB_DRIVER_EXECUTABLE, USER_AGENT, LOAD_TIMEOUT, LOAD_TIMEOUT);

        boolean actual = webDriverConfig.equals(otherWebDriverConfig);
        assertTrue(actual);
    }

    @Test
    public void equalsShouldReturnFalseIfBothNonEmptyObjectsNotEquals() {
        webDriverConfig = new WebDriverConfig(WEB_DRIVER_EXECUTABLE, USER_AGENT, LOAD_TIMEOUT, LOAD_TIMEOUT);
        otherWebDriverConfig = new WebDriverConfig(WEB_DRIVER_EXECUTABLE_OTHER, USER_AGENT_OTHER, LOAD_TIMEOUT, LOAD_TIMEOUT);

        boolean actual = webDriverConfig.equals(otherWebDriverConfig);
        assertFalse(actual);
    }

    @Test
    public void hashCodeShouldReturnSameNumberIfBothNonEmptyObjectsEquals() {
        webDriverConfig = new WebDriverConfig(WEB_DRIVER_EXECUTABLE, USER_AGENT, LOAD_TIMEOUT, LOAD_TIMEOUT);
        otherWebDriverConfig = new WebDriverConfig(WEB_DRIVER_EXECUTABLE, USER_AGENT, LOAD_TIMEOUT, LOAD_TIMEOUT);

        int expected = webDriverConfig.hashCode();
        int actual = otherWebDriverConfig.hashCode();
        assertEquals(expected, actual);
    }

    @Test
    public void hashCodeShouldReturnDifferentNumberIfBothNonEmptyObjectsNotEquals() {
        webDriverConfig = new WebDriverConfig(WEB_DRIVER_EXECUTABLE, USER_AGENT, LOAD_TIMEOUT, LOAD_TIMEOUT);
        otherWebDriverConfig = new WebDriverConfig(WEB_DRIVER_EXECUTABLE_OTHER, USER_AGENT_OTHER, LOAD_TIMEOUT, LOAD_TIMEOUT);

        int expected = webDriverConfig.hashCode();
        int actual = otherWebDriverConfig.hashCode();
        assertNotEquals(expected, actual);
    }
}
