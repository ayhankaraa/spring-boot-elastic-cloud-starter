package es.startuphero.controller;

import es.startuphero.service.Product;
import es.startuphero.service.SearchService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ayhankara
 */
@RestController
@RequestMapping("api/search")
public class SearchController {

  private final SearchService searchService;

  public SearchController(SearchService searchService) {
    this.searchService = searchService;
  }

  @GetMapping("/all")
  public List<Product> getAll() {
    return searchService.getAll();
  }
}
