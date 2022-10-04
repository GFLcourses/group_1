package executor.service.flow.executable_flows;

import executor.exception.NoProxyFoundException;
import executor.model.ProxyConfigHolder;
import executor.service.proxy.ProxySourcesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ProxyFlow implements ExecutableFlow<ProxyConfigHolder> {
    private final ProxySourcesClient proxySourcesClient;

    @Autowired
    public ProxyFlow(ProxySourcesClient proxySourcesClient) {
        this.proxySourcesClient = proxySourcesClient;
    }

    @Async
    @Override
    public CompletableFuture<ProxyConfigHolder> execute() {
        ProxyConfigHolder proxyConfigHolder = this.proxySourcesClient.getProxy().orElseThrow(
                () -> new NoProxyFoundException("proxy is not presented")
        );
        return CompletableFuture.completedFuture(proxyConfigHolder);
    }
}
