package executor.service.service;

import executor.service.model.ProxyConfigHolder;

import java.io.IOException;
import java.net.URISyntaxException;

public interface ProxySourcesClient {
    ProxyConfigHolder getProxy() throws URISyntaxException, IOException;
}
