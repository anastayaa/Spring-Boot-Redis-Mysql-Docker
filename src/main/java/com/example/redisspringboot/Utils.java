package com.example.redisspringboot;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Utils {

  private static Map<String, AtomicInteger> DATABASE_ACCESS_COUNTER = new HashMap<>();

  public static void incrementDbAccessFor(String category) {
    if (!DATABASE_ACCESS_COUNTER.containsKey(category)) {
      DATABASE_ACCESS_COUNTER.put(category, new AtomicInteger(0));
    }
    DATABASE_ACCESS_COUNTER.get(category).incrementAndGet();
  }

  public static Integer getDbAccessCount(String category) {
    return DATABASE_ACCESS_COUNTER.get(category).get();
  }
}
