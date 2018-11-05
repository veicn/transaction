package com.sinochem.crude.trade.datafeed;

import java.util.Map;
import java.util.Set;

public interface Handler extends Callback<String> {
    Set<String> listId();
    Map<String, String> getItem(String id);
}
