package executor.service.parallel_stuff;

import java.io.IOException;
import java.net.URISyntaxException;

public interface FlowRunner {

    void run() throws InterruptedException, URISyntaxException, IOException;
}
