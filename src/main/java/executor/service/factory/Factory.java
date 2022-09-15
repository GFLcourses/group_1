package executor.service.factory;

public interface Factory {
    <T> T  getInstance(Class<T> clazz);
}
