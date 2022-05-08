package product.data;

import product.Product;

public interface ProductRepository{
	Iterable<Product> findAll();
	Product findByCode(String code);
	Product save(Product product);
	Product delete(Product product);
}
