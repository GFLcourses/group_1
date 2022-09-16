package executor;

import executor.service.factory.DIFactory;
import executor.service.factory.Factory;
import executor.service.parallel_stuff.FlowRunner;
import executor.service.parallel_stuff.FlowRunnerImpl;

public class Application {
    private static final FlowRunner TESTING_RUNNER;

    static {
        Factory factory = DIFactory.getInstance();
        TESTING_RUNNER = factory.getInstance(FlowRunnerImpl.class);
    }

    public static void main(String[] args) {
        new Application().run();
    }

    public void run() {
        try {
            TESTING_RUNNER.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
