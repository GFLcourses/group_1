package executor.service.flow.executable_flows;

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
        ProxyConfigHolder proxyConfigHolder = this.proxySourcesClient.getProxy();
        return CompletableFuture.completedFuture(proxyConfigHolder);
    }
}
