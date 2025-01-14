package org.lmh.common.service;

import java.util.List;
import java.util.Set;

public interface RedisService {
    Set<Long> getData(String key);
    void setData(String key, Long value);
    void setAllData(String key, List<Long> values);
    void deleteData(String key);
    void deleteValue(String key, Long value);
    void deleteValueByTargetId(String key, String targetId);
    boolean containsKey(String key);
    boolean containsValue(String key, Long value);
}
