package executor.service.flow.executable_flows;

import executor.exception.NoProxyFoundException;
import executor.model.ProxyConfigHolder;
import executor.service.proxy.ProxySourcesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;

@Service
public class ProxyFlow {
    private final ProxySourcesClient proxySourcesClient;

    @Autowired
    public ProxyFlow(ProxySourcesClient proxySourcesClient) {
        this.proxySourcesClient = proxySourcesClient;
    }

    @Async(value = "taskScheduler")
    public CompletableFuture<ProxyConfigHolder> getProxy() throws
            URISyntaxException,
            IOException {
        ProxyConfigHolder proxyConfigHolder = this.proxySourcesClient.getProxy().orElseThrow(
                () -> new NoProxyFoundException("proxy is not presented")
        );
        return CompletableFuture.completedFuture(proxyConfigHolder);
    }
}
