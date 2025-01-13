package org.lmh.common.service;

import java.util.List;
import java.util.Set;

public interface RedisService<T> {
    Set<T> getData(String key, Class<T> clazz);
    void setData(String key, T value);
    void setAllData(String key, List<T> values);
    void deleteData(String key);
    void deleteValue(String key, T value);
    void deleteValueByTargetId(String key, String targetId);
    boolean containsKey(String key);
    boolean containsValue(String key, T value);
}
