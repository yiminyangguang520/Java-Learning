package guru.springframework.services;

import guru.springframework.commands.ProductForm;
import guru.springframework.domain.Product;
import java.util.List;

/**
 *
 * @author jt
 * @date 1/10/17
 */
public interface ProductService {

  /**
   * listAll
   * @return
   */
  List<Product> listAll();

  /**
   * getById
   * @param id
   * @return
   */
  Product getById(String id);

  /**
   * saveOrUpdate
   * @param product
   * @return
   */
  Product saveOrUpdate(Product product);

  /**
   * delete
   * @param id
   */
  void delete(String id);

  /**
   * saveOrUpdateProductForm
   * @param productForm
   * @return
   */
  Product saveOrUpdateProductForm(ProductForm productForm);
}
