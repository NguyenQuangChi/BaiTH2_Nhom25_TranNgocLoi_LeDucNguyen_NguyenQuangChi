package product.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import product.Product;

@Repository
public class JdbcRepository implements ProductRepository {
	private JdbcTemplate jdbc;

	@Autowired
	public JdbcRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Iterable<Product> findAll() {
		return jdbc.query("select code, description, price from Product", this::mapRowToProduct);
	}

	@Override
	public Product findByCode(String code) {
		return jdbc.queryForObject("select code, description, price from Product where code=?", this::mapRowToProduct, code);
	}

	private Product mapRowToProduct(ResultSet rs, int rowNum) throws SQLException {
		return new Product(rs.getString("code"), rs.getString("description"), rs.getDouble("price"));
	}

	@Override
	public Product save(Product product) {
		jdbc.update(
				"insert into Product (code, description, price) values (?, ?, ?)",
				product.getCode(),
				product.getDescription(),
				product.getPrice());
		return product;
	}
	
	@Override
	public Product delete(Product product) {
		jdbc.update(
				"delete from Product where code=?",
				product.getCode());
		return product;
	}
}
