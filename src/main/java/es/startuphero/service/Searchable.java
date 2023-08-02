package es.startuphero.service;

import java.util.List;

/**
 * @author ayhankara
 */
public interface Searchable {

  default Product searchById() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  default Product searchByName(String name) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  List<Product> getAll();
}
