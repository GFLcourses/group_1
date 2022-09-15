package executor.service.proxy;

import executor.model.ProxyConfigHolderDto;

import java.io.IOException;
import java.net.URISyntaxException;

public interface ProxySourcesClient {
    ProxyConfigHolderDto getProxy() throws URISyntaxException, IOException;
}
