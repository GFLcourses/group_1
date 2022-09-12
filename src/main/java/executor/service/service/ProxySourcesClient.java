package executor.service.service;

import executor.service.model.ProxyConfigHolderDto;

import java.io.IOException;
import java.net.URISyntaxException;

public interface ProxySourcesClient {
    ProxyConfigHolderDto getProxy() throws URISyntaxException, IOException;
}
