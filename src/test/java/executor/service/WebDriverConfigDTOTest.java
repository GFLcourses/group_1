package executor.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import executor.service.model.WebDriverConfigDTO;
import org.junit.Test;

public class WebDriverConfigDTOTest {

    private final WebDriverConfigDTO webDriverConfigDTO = new WebDriverConfigDTO();

    @Test
    public void getWebDriverExecutableShouldReturnWebDriverExecutableFieldValue() {
        String expected = "chrome";

        WebDriverConfigDTO otherWebDriverConfigDTO = new WebDriverConfigDTO();
        otherWebDriverConfigDTO.setWebDriverExecutable(expected);

        String actual = otherWebDriverConfigDTO.getWebDriverExecutable();

        assertEquals(expected, actual);
    }

    @Test
    public void getUserAgentShouldReturnUserAgentFieldValue() {
        String expected = "chrome";

        WebDriverConfigDTO otherWebDriverConfigDTO = new WebDriverConfigDTO();
        otherWebDriverConfigDTO.setUserAgent(expected);

        String actual = otherWebDriverConfigDTO.getUserAgent();

        assertEquals(expected, actual);
    }

    @Test
    public void getPageLoadTimeoutShouldReturnPageLoadTimeoutFieldValue() {
        Long expected = 1l;

        WebDriverConfigDTO otherWebDriverConfigDTO = new WebDriverConfigDTO();
        otherWebDriverConfigDTO.setPageLoadTimeout(1l);

        Long actual = otherWebDriverConfigDTO.getPageLoadTimeout();

        assertEquals(expected, actual);
    }

    @Test
    public void getImplicitlyWaitShouldReturnImplicitlyWaitFieldValue() {
        Long expected = 1l;

        WebDriverConfigDTO otherWebDriverConfigDTO = new WebDriverConfigDTO();
        otherWebDriverConfigDTO.setImplicitlyWait(expected);

        Long actual = otherWebDriverConfigDTO.getImplicitlyWait();

        assertEquals(expected, actual);
    }

    @Test
    public void setWebDriverExecutableShouldSetWebDriverExecutableFieldValue() {
        String expected = "chrome";

        WebDriverConfigDTO otherWebDriverConfigDTO = new WebDriverConfigDTO();
        otherWebDriverConfigDTO.setWebDriverExecutable(expected);

        String actual = otherWebDriverConfigDTO.getWebDriverExecutable();

        assertEquals(expected, actual);
    }

    @Test
    public void setUserAgentShouldSetUserAgentFieldValue() {
        String expected = "chrome";

        WebDriverConfigDTO otherWebDriverConfigDTO = new WebDriverConfigDTO();
        otherWebDriverConfigDTO.setUserAgent(expected);

        String actual = otherWebDriverConfigDTO.getUserAgent();

        assertEquals(expected, actual);
    }

    @Test
    public void setPageLoadTimeoutShouldSetPageLoadTimeoutFieldValue() {
        Long expected = 1l;

        WebDriverConfigDTO otherWebDriverConfigDTO = new WebDriverConfigDTO();
        otherWebDriverConfigDTO.setPageLoadTimeout(1l);

        Long actual = otherWebDriverConfigDTO.getPageLoadTimeout();

        assertEquals(expected, actual);
    }

    @Test
    public void setImplicitlyWaitShouldSetImplicitlyWaitFieldValue() {
        Long expected = 1l;

        WebDriverConfigDTO otherWebDriverConfigDTO = new WebDriverConfigDTO();
        otherWebDriverConfigDTO.setImplicitlyWait(expected);

        Long actual = otherWebDriverConfigDTO.getImplicitlyWait();

        assertEquals(expected, actual);
    }

    @Test
    public void equalsShouldReturnTrueIfParameterHasSameReference(){
        boolean expected = true;
        boolean actual = webDriverConfigDTO.equals(webDriverConfigDTO);

        assertEquals(expected, actual);
    }

    @Test
    public void equalsShouldReturnFalseIfParameterReferenceEqualsNull(){
        boolean expected = false;
        boolean actual = webDriverConfigDTO.equals(null);

        assertEquals(expected, actual);
    }

    @Test
    public void equalsShouldReturnFalseIfParameterIsNotSameClass(){
        boolean expected = false;
        boolean actual = webDriverConfigDTO.equals(new String());

        assertEquals(expected, actual);
    }

    @Test
    public void equalsShouldReturnTrueIfAllFieldsAreSame(){
        boolean expected = true;

        WebDriverConfigDTO otherWebDriverConfigDTO = new WebDriverConfigDTO();
        boolean actual = webDriverConfigDTO.equals(otherWebDriverConfigDTO);

        assertEquals(expected, actual);
    }

    @Test
    public void equalsShouldReturnFalseIfWebDriverExecutableFieldsNotEquals(){
        boolean expected = false;

        WebDriverConfigDTO otherWebDriverConfigDTO = new WebDriverConfigDTO();
        otherWebDriverConfigDTO.setWebDriverExecutable("chrome");

        boolean actual = webDriverConfigDTO.equals(otherWebDriverConfigDTO);

        assertEquals(expected, actual);
    }

    @Test
    public void equalsShouldReturnFalseIfUserAgentFieldsNotEquals(){
        boolean expected = false;

        WebDriverConfigDTO otherWebDriverConfigDTO = new WebDriverConfigDTO();
        otherWebDriverConfigDTO.setUserAgent("chrome");

        boolean actual = webDriverConfigDTO.equals(otherWebDriverConfigDTO);

        assertEquals(expected, actual);
    }

    @Test
    public void equalsShouldReturnFalseIfPageLoadTimeoutFieldsNotEquals(){
        boolean expected = false;

        WebDriverConfigDTO otherWebDriverConfigDTO = new WebDriverConfigDTO();
        otherWebDriverConfigDTO.setPageLoadTimeout(1l);

        boolean actual = webDriverConfigDTO.equals(otherWebDriverConfigDTO);

        assertEquals(expected, actual);
    }

    @Test
    public void equalsShouldReturnFalseIfImplicitlyWaitFieldsNotEquals(){
        boolean expected = false;

        WebDriverConfigDTO otherWebDriverConfigDTO = new WebDriverConfigDTO();
        otherWebDriverConfigDTO.setImplicitlyWait(1l);

        boolean actual = webDriverConfigDTO.equals(otherWebDriverConfigDTO);

        assertEquals(expected, actual);
    }

    @Test
    public void hashCodeShouldReturnSameNumberIfBothObjectsAreEquals(){
        int expected = webDriverConfigDTO.hashCode();

        WebDriverConfigDTO otherWebDriverConfigDTO = new WebDriverConfigDTO();

        int actual = otherWebDriverConfigDTO.hashCode();

        assertEquals(expected, actual);
    }

    @Test
    public void hashCodeShouldReturnSameNumberIfAllFieldsAreEquals(){
        int expected = webDriverConfigDTO.hashCode();

        WebDriverConfigDTO otherWebDriverConfigDTO = new WebDriverConfigDTO();

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
        WebDriverConfigDTO webDriverConfigDTO = new WebDriverConfigDTO("driver", "agent", 1l, 1l);
        WebDriverConfigDTO otherWebDriverConfigDTO = new WebDriverConfigDTO("driver", "agent", 1l, 1l);

        boolean expected = true;
        boolean actual = webDriverConfigDTO.equals(otherWebDriverConfigDTO);

        assertEquals(expected, actual);
    }

    @Test
    public void equalsShouldReturnFalseIfBothNonEmptyObjectsNotEquals() {
        WebDriverConfigDTO webDriverConfigDTO = new WebDriverConfigDTO("driver", "agent", 1l, 1l);
        WebDriverConfigDTO otherWebDriverConfigDTO = new WebDriverConfigDTO("driver2", "agent2", 1l, 1l);

        boolean expected = false;
        boolean actual = webDriverConfigDTO.equals(otherWebDriverConfigDTO);

        assertEquals(expected, actual);
    }

    @Test
    public void hashCodeShouldReturnSameNumberIfBothNonEmptyObjectsEquals() {
        WebDriverConfigDTO webDriverConfigDTO = new WebDriverConfigDTO("driver", "agent", 1l, 1l);
        WebDriverConfigDTO otherWebDriverConfigDTO = new WebDriverConfigDTO("driver", "agent", 1l, 1l);

        int expected = webDriverConfigDTO.hashCode();
        int actual = otherWebDriverConfigDTO.hashCode();

        assertEquals(expected, actual);
    }

    @Test
    public void hashCodeShouldReturnDifferentNumberIfBothNonEmptyObjectsNotEquals() {
        WebDriverConfigDTO webDriverConfigDTO = new WebDriverConfigDTO("driver", "agent", 1l, 1l);
        WebDriverConfigDTO otherWebDriverConfigDTO = new WebDriverConfigDTO("driver2", "agent2", 1l, 1l);

        int expected = webDriverConfigDTO.hashCode();
        int actual = otherWebDriverConfigDTO.hashCode();

        assertNotEquals(expected, actual);
    }
}
