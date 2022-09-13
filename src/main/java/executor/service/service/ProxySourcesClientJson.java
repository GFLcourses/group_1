package executor.service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.model.ProxyConfigHolderDto;
import executor.service.model.ProxyCredentials;
import executor.service.model.ProxyNetworkConfig;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ProxySourcesClientJson implements ProxySourcesClient {
    private static final ProxySourcesClientJson INSTANCE = new ProxySourcesClientJson();

    protected ProxySourcesClientJson() {  }

    public static ProxySourcesClientJson getInstance() {
        return INSTANCE;
    }

    @Override
    public ProxyConfigHolderDto getProxy() throws URISyntaxException, IOException {
        URI credentialsURI = this.getClass().getClassLoader().getResource("ProxyCredentials.json").toURI();
        URI networkURI = this.getClass().getClassLoader().getResource("ProxyNetwork.json").toURI();

        File credentialsFile = new File(credentialsURI);
        File networkFile = new File(networkURI);

        ObjectMapper objectMapper = new ObjectMapper();
        ProxyCredentials[] proxyCredentials = objectMapper.readValue(credentialsFile, ProxyCredentials[].class);
        ProxyNetworkConfig[] proxyNetworkConfigs = objectMapper.readValue(networkFile, ProxyNetworkConfig[].class);

        return new ProxyConfigHolderDto(proxyNetworkConfigs[proxyNetworkConfigs.length - 1],
                                        proxyCredentials[proxyCredentials.length - 1]);
    }
}
