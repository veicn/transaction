package com.sinochem.crude.trade.common.aliyun.opensearch;

import com.aliyun.opensearch.DocumentClient;
import com.aliyun.opensearch.OpenSearchClient;
import com.aliyun.opensearch.SearcherClient;
import com.aliyun.opensearch.sdk.generated.OpenSearch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenSearchConfig {

    private static final Log log = LogFactory.getLog(OpenSearchConfig.class);

    @Value("${aliyun.opensearch.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.opensearch.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.opensearch.host}")
    private String host;

    private OpenSearchClient openSearchClient() {
        OpenSearch openSearch = new OpenSearch(accessKeyId, accessKeySecret, host);
        log.info("Creating OpenSearch config object with host: " + host);
        return new OpenSearchClient(openSearch);
    }

    @Bean
    public Factory<DocumentClient> documentClient() {
        return new Factory<DocumentClient>() {
            @Override
            public DocumentClient getObject() {
                return new DocumentClient(openSearchClient());
            }
        };
    }

    @Bean
    public Factory<SearcherClient> searcherClient() {
        return new Factory<SearcherClient>() {
            @Override
            public SearcherClient getObject() {
                return new SearcherClient(openSearchClient());
            }
        };
    }

    public interface Factory<T> {
        T getObject();
    }

}
