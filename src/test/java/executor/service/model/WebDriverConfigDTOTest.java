package executor.service.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class WebDriverConfigDTOTest {

    private static final Long LOAD_TIMEOUT = 1L;
    private static final String WEB_DRIVER_EXECUTABLE = "chrome";
    private static final String WEB_DRIVER_EXECUTABLE_OTHER = "chrome other";
    private static final String USER_AGENT = "user agent";
    private static final String USER_AGENT_OTHER = "user agent other";
    private WebDriverConfigDTO webDriverConfigDTO;
    private WebDriverConfigDTO otherWebDriverConfigDTO;

    @Before
    public void setUp() {
        webDriverConfigDTO = new WebDriverConfigDTO();
        otherWebDriverConfigDTO = new WebDriverConfigDTO();
    }

    @Test
    public void getWebDriverExecutableShouldReturnWebDriverExecutableFieldValue() {
        otherWebDriverConfigDTO.setWebDriverExecutable(WEB_DRIVER_EXECUTABLE);
        String actual = otherWebDriverConfigDTO.getWebDriverExecutable();

        assertEquals(WEB_DRIVER_EXECUTABLE, actual);
    }

    @Test
    public void getUserAgentShouldReturnUserAgentFieldValue() {
        otherWebDriverConfigDTO.setUserAgent(WEB_DRIVER_EXECUTABLE);
        String actual = otherWebDriverConfigDTO.getUserAgent();

        assertEquals(WEB_DRIVER_EXECUTABLE, actual);
    }

    @Test
    public void getPageLoadTimeoutShouldReturnPageLoadTimeoutFieldValue() {
        otherWebDriverConfigDTO.setPageLoadTimeout(LOAD_TIMEOUT);
        Long actual = otherWebDriverConfigDTO.getPageLoadTimeout();

        assertEquals(LOAD_TIMEOUT, actual);
    }

    @Test
    public void getImplicitlyWaitShouldReturnImplicitlyWaitFieldValue() {
        otherWebDriverConfigDTO.setImplicitlyWait(LOAD_TIMEOUT);
        Long actual = otherWebDriverConfigDTO.getImplicitlyWait();

        assertEquals(LOAD_TIMEOUT, actual);
    }

    @Test
    public void setWebDriverExecutableShouldSetWebDriverExecutableFieldValue() {
        otherWebDriverConfigDTO.setWebDriverExecutable(WEB_DRIVER_EXECUTABLE);
        String actual = otherWebDriverConfigDTO.getWebDriverExecutable();

        assertEquals(WEB_DRIVER_EXECUTABLE, actual);
    }

    @Test
    public void setUserAgentShouldSetUserAgentFieldValue() {
        otherWebDriverConfigDTO.setUserAgent(WEB_DRIVER_EXECUTABLE);
        String actual = otherWebDriverConfigDTO.getUserAgent();

        assertEquals(WEB_DRIVER_EXECUTABLE, actual);
    }

    @Test
    public void setPageLoadTimeoutShouldSetPageLoadTimeoutFieldValue() {
        otherWebDriverConfigDTO.setPageLoadTimeout(LOAD_TIMEOUT);
        Long actual = otherWebDriverConfigDTO.getPageLoadTimeout();

        assertEquals(LOAD_TIMEOUT, actual);
    }

    @Test
    public void setImplicitlyWaitShouldSetImplicitlyWaitFieldValue() {
        otherWebDriverConfigDTO.setImplicitlyWait(LOAD_TIMEOUT);
        Long actual = otherWebDriverConfigDTO.getImplicitlyWait();

        assertEquals(LOAD_TIMEOUT, actual);
    }

    @Test
    public void equalsShouldReturnTrueIfParameterHasSameReference(){
        boolean actual = webDriverConfigDTO.equals(webDriverConfigDTO);
        assertTrue(actual);
    }

    @Test
    public void equalsShouldReturnFalseIfParameterReferenceEqualsNull(){
        boolean actual = webDriverConfigDTO.equals(null);
        assertFalse(actual);
    }

    @Test
    public void equalsShouldReturnFalseIfParameterIsNotSameClass(){
        boolean actual = webDriverConfigDTO.equals("");
        assertFalse(actual);
    }

    @Test
    public void equalsShouldReturnTrueIfAllFieldsAreSame(){
        boolean actual = webDriverConfigDTO.equals(otherWebDriverConfigDTO);
        assertTrue(actual);
    }

    @Test
    public void equalsShouldReturnFalseIfWebDriverExecutableFieldsNotEquals(){
        otherWebDriverConfigDTO.setWebDriverExecutable(WEB_DRIVER_EXECUTABLE);
        boolean actual = webDriverConfigDTO.equals(otherWebDriverConfigDTO);
        assertFalse(actual);
    }

    @Test
    public void equalsShouldReturnFalseIfUserAgentFieldsNotEquals(){
        otherWebDriverConfigDTO.setUserAgent(WEB_DRIVER_EXECUTABLE);
        boolean actual = webDriverConfigDTO.equals(otherWebDriverConfigDTO);
        assertFalse(actual);
    }

    @Test
    public void equalsShouldReturnFalseIfPageLoadTimeoutFieldsNotEquals(){
        otherWebDriverConfigDTO.setPageLoadTimeout(LOAD_TIMEOUT);
        boolean actual = webDriverConfigDTO.equals(otherWebDriverConfigDTO);
        assertFalse(actual);
    }

    @Test
    public void equalsShouldReturnFalseIfImplicitlyWaitFieldsNotEquals(){
        otherWebDriverConfigDTO.setImplicitlyWait(LOAD_TIMEOUT);
        boolean actual = webDriverConfigDTO.equals(otherWebDriverConfigDTO);
        assertFalse(actual);
    }

    @Test
    public void hashCodeShouldReturnSameNumberIfBothObjectsAreEquals(){
        int expected = webDriverConfigDTO.hashCode();
        int actual = otherWebDriverConfigDTO.hashCode();
        assertEquals(expected, actual);
    }

    @Test
    public void hashCodeShouldReturnSameNumberIfAllFieldsAreEquals(){
        int expected = webDriverConfigDTO.hashCode();
        int actual = otherWebDriverConfigDTO.hashCode();
        assertEquals(expected, actual);
    }

    @Test
    public void hashCodeShouldReturnSameNumberIfHashCodeMethodWasCalledTwice(){
        int expected = webDriverConfigDTO.hashCode();
        int actual = webDriverConfigDTO.hashCode();
        assertEquals(expected, actual);
    }

    @Test
    public void equalsShouldReturnTrueIfBothNonEmptyObjectsEquals() {
        webDriverConfigDTO = new WebDriverConfigDTO(WEB_DRIVER_EXECUTABLE, USER_AGENT, LOAD_TIMEOUT, LOAD_TIMEOUT);
        otherWebDriverConfigDTO = new WebDriverConfigDTO(WEB_DRIVER_EXECUTABLE, USER_AGENT, LOAD_TIMEOUT, LOAD_TIMEOUT);

        boolean actual = webDriverConfigDTO.equals(otherWebDriverConfigDTO);
        assertTrue(actual);
    }

    @Test
    public void equalsShouldReturnFalseIfBothNonEmptyObjectsNotEquals() {
        webDriverConfigDTO = new WebDriverConfigDTO(WEB_DRIVER_EXECUTABLE, USER_AGENT, LOAD_TIMEOUT, LOAD_TIMEOUT);
        otherWebDriverConfigDTO = new WebDriverConfigDTO(WEB_DRIVER_EXECUTABLE_OTHER, USER_AGENT_OTHER, LOAD_TIMEOUT, LOAD_TIMEOUT);

        boolean actual = webDriverConfigDTO.equals(otherWebDriverConfigDTO);
        assertFalse(actual);
    }

    @Test
    public void hashCodeShouldReturnSameNumberIfBothNonEmptyObjectsEquals() {
        webDriverConfigDTO = new WebDriverConfigDTO(WEB_DRIVER_EXECUTABLE, USER_AGENT, LOAD_TIMEOUT, LOAD_TIMEOUT);
        otherWebDriverConfigDTO = new WebDriverConfigDTO(WEB_DRIVER_EXECUTABLE, USER_AGENT, LOAD_TIMEOUT, LOAD_TIMEOUT);

        int expected = webDriverConfigDTO.hashCode();
        int actual = otherWebDriverConfigDTO.hashCode();
        assertEquals(expected, actual);
    }

    @Test
    public void hashCodeShouldReturnDifferentNumberIfBothNonEmptyObjectsNotEquals() {
        webDriverConfigDTO = new WebDriverConfigDTO(WEB_DRIVER_EXECUTABLE, USER_AGENT, LOAD_TIMEOUT, LOAD_TIMEOUT);
        otherWebDriverConfigDTO = new WebDriverConfigDTO(WEB_DRIVER_EXECUTABLE_OTHER, USER_AGENT_OTHER, LOAD_TIMEOUT, LOAD_TIMEOUT);

        int expected = webDriverConfigDTO.hashCode();
        int actual = otherWebDriverConfigDTO.hashCode();
        assertNotEquals(expected, actual);
    }
}
