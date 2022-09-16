package executor.service.proxy;

import executor.model.ProxyConfigHolder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

public interface ProxySourcesClient {

    Optional<ProxyConfigHolder> getProxy() throws URISyntaxException, IOException;
}
