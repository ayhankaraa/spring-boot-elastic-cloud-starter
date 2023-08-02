package es.startuphero.service;

import java.util.List;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * @author ayhankara
 */
@Service
public class SearchService implements Searchable {

  private static final String INDEX_NAME = "products";

  private final ElasticsearchOperations elasticsearchOperations;

  public SearchService(ElasticsearchOperations elasticsearchOperations) {
    this.elasticsearchOperations = elasticsearchOperations;
  }

  public List<Product> getAll() {
    Query query = elasticsearchOperations.matchAllQuery();
    SearchHits<Product> searchHits =
        elasticsearchOperations.search(query, Product.class, IndexCoordinates.of(INDEX_NAME));
    return searchHits.getSearchHits().stream().map(SearchHit::getContent).toList();
  }
}
