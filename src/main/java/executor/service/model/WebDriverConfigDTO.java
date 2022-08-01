package executor.service.model;

import java.util.Objects;

public class WebDriverConfigDTO {
    private String webDriverExecutable;
    private String userAgent;
    private Long pageLoadTimeout;
    private Long implicitlyWait;

    public String getWebDriverExecutable() {
        return webDriverExecutable;
    }

    public void setWebDriverExecutable(String webDriverExecutable) {
        this.webDriverExecutable = webDriverExecutable;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Long getPageLoadTimeout() {
        return pageLoadTimeout;
    }

    public void setPageLoadTimeout(Long pageLoadTimeout) {
        this.pageLoadTimeout = pageLoadTimeout;
    }

    public Long getImplicitlyWait() {
        return implicitlyWait;
    }

    public void setImplicitlyWait(Long implicitlyWait) {
        this.implicitlyWait = implicitlyWait;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        WebDriverConfigDTO that = (WebDriverConfigDTO) o;

        return Objects.equals(webDriverExecutable, that.webDriverExecutable) &&
                Objects.equals(userAgent, that.userAgent) &&
                Objects.equals(pageLoadTimeout, that.pageLoadTimeout) &&
                Objects.equals(implicitlyWait, that.implicitlyWait);
    }

    @Override
    public int hashCode() {
        return Objects.hash(webDriverExecutable, userAgent, pageLoadTimeout, implicitlyWait);
    }
}
