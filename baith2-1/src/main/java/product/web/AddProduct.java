package product.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import product.Product;
import product.data.ProductRepository;

@Controller
@RequestMapping("/addProduct")
public class AddProduct {
	private final ProductRepository productRepo;
	
	@Autowired
	public AddProduct(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}
	
	@PostMapping("/addProduct")
	public String UpdateProduct(@Validated Product product) {
		productRepo.save(product);		
		return "redirect:/display";
	}

	@GetMapping
	public String updateProduct() {
		return "addProduct";
	}
}
