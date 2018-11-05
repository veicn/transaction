package com.sinochem.crude.trade.common.utils;

import com.aliyun.opensearch.DocumentClient;
import com.aliyun.opensearch.SearcherClient;
import com.aliyun.opensearch.sdk.generated.commons.OpenSearchClientException;
import com.aliyun.opensearch.sdk.generated.commons.OpenSearchException;
import com.aliyun.opensearch.sdk.generated.commons.OpenSearchResult;
import com.aliyun.opensearch.sdk.generated.search.SearchParams;
import com.aliyun.opensearch.sdk.generated.search.general.SearchResult;
import com.aliyun.opensearch.search.SearchParamsBuilder;
import com.sinochem.crude.trade.common.aliyun.opensearch.OpenSearchConfig.Factory;
import java.util.Map;

public final class OpenSearchUtils {

    private OpenSearchUtils() { }

    private static Factory<DocumentClient> documentClientFactory;
    private static Factory<SearcherClient> searcherClient;

    static void init(Factory<DocumentClient> documentClientFactory, Factory<SearcherClient> searcherClient) {
        OpenSearchUtils.documentClientFactory = documentClientFactory;
        OpenSearchUtils.searcherClient = searcherClient;
    }

    public static void add(Map<String, Object> fields) {
        documentClientFactory.getObject().add(fields);
    }

    public static void update(Map<String, Object> fields) {
        documentClientFactory.getObject().update(fields);
    }

    public static void remove(Map<String, Object> fields) {
        documentClientFactory.getObject().remove(fields);
    }

    public static OpenSearchResult commit(String appName, String tableName)
            throws OpenSearchException, OpenSearchClientException {
        return documentClientFactory.getObject().commit(appName, tableName);
    }

    public static OpenSearchResult push(String docsJson, String appName,
            String tableName) throws OpenSearchException, OpenSearchClientException {
        return documentClientFactory.getObject().push(docsJson, appName, tableName);
    }

    public static SearchResult search(
            SearchParamsBuilder searchParamsBuilder) throws OpenSearchException, OpenSearchClientException {
        return searcherClient.getObject().execute(searchParamsBuilder);
    }

    public static SearchResult search(
            SearchParams searchParams) throws OpenSearchException, OpenSearchClientException {
        return searcherClient.getObject().execute(searchParams);
    }

}
