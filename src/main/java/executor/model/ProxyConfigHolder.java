package executor.model;

import java.util.Objects;

public class ProxyConfigHolder implements Comparable<ProxyConfigHolder> {
    private boolean nullObject;
    private ProxyNetworkConfig proxyNetworkConfig;
    private ProxyCredentials proxyCredentials;

    public ProxyConfigHolder() {
        this.nullObject = true;
    }

    public ProxyConfigHolder(ProxyNetworkConfig proxyNetworkConfig, ProxyCredentials proxyCredentials) {
        this.proxyNetworkConfig = proxyNetworkConfig;
        this.proxyCredentials = proxyCredentials;
        this.nullObject = false;
    }

    public boolean isNull() {
        return this.nullObject;
    }

    public ProxyNetworkConfig getProxyNetworkConfig() {
        return proxyNetworkConfig;
    }

    public void setProxyNetworkConfig(ProxyNetworkConfig proxyNetworkConfig) {
        this.proxyNetworkConfig = proxyNetworkConfig;
        this.nullObject = false;
    }

    public ProxyCredentials getProxyCredentials() {
        return proxyCredentials;
    }

    public void setProxyCredentials(ProxyCredentials proxyCredentials) {
        this.proxyCredentials = proxyCredentials;
        this.nullObject = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProxyConfigHolder that = (ProxyConfigHolder) o;
        return Objects.equals(proxyNetworkConfig, that.proxyNetworkConfig) && Objects.equals(proxyCredentials, that.proxyCredentials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proxyNetworkConfig, proxyCredentials);
    }

    @Override
    public String toString() {
        return "ProxyConfigHolder{" +
                "proxyNetworkConfig=" + proxyNetworkConfig +
                ", proxyCredentials=" + proxyCredentials +
                '}';
    }

    @Override
    public int compareTo(ProxyConfigHolder o) {
        if (this.proxyNetworkConfig.equals(o.proxyNetworkConfig)){
            return 1;
        } else {
            return -1;
        }
    }
}
