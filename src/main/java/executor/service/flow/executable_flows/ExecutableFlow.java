package executor.service.flow.executable_flows;

import java.util.concurrent.CompletableFuture;

public interface ExecutableFlow<T> {

    CompletableFuture<T> execute();
}
