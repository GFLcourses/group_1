package executor.service;

import executor.service.service.factory.DIFactory;
import executor.service.service.factory.Factory;
import executor.service.service.parallel_stuff.TestingRunner;
import executor.service.service.parallel_stuff.TestingRunnerImpl;

public class Application {
    private static final TestingRunner TESTING_RUNNER;

    static {
        Factory factory = DIFactory.getInstance();
        TESTING_RUNNER = factory.getInstance(TestingRunnerImpl.class);
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
