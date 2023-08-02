package es.startuphero.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ayhankara
 */

@Configuration
public class ClientConfig {

  private static final String HOSTNAME = "ELASTICSEARCH_ENDPOINT";

  private static final String USER = "elastic";

  private static final String PASSWORD = "PASSWORD";

  private static final int PORT = 9243;

  private static final String SCHEME = "https";

  private static final int CONNECT_TIMEOUT = 5000;

  private static final int SOCKET_TIMEOUT = 60000;

  @Bean
  public ElasticsearchClient client() {
    final CredentialsProvider credentialsProvider =
        new BasicCredentialsProvider();
    credentialsProvider.setCredentials(AuthScope.ANY,
                                       new UsernamePasswordCredentials(USER, PASSWORD));

    RestClientBuilder builder = RestClient.builder(new HttpHost(HOSTNAME, PORT, SCHEME))
                                          .setRequestConfigCallback(
                                              requestConfigBuilder -> requestConfigBuilder
                                                  .setConnectTimeout(CONNECT_TIMEOUT)
                                                  .setSocketTimeout(SOCKET_TIMEOUT))
                                          .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder
                                              .setDefaultCredentialsProvider(credentialsProvider));

    RestClient restClient = builder.build();
    ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
    return new ElasticsearchClient(transport);
  }
}
