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

  List<Product> listAll();

  Product getById(String id);

  Product saveOrUpdate(Product product);

  void delete(String id);

  Product saveOrUpdateProductForm(ProductForm productForm);
}
