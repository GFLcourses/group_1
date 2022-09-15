package executor.service.proxy;

import executor.model.ProxyConfigHolderDto;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

public interface ProxySourcesClient {

    Optional<ProxyConfigHolderDto> getProxy() throws URISyntaxException, IOException;
}
