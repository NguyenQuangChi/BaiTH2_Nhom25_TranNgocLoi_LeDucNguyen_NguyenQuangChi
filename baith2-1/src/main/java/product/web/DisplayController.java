package product.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import product.Product;
import product.data.ProductRepository;

@Controller
@RequestMapping("/display")
public class DisplayController {
	private final ProductRepository productRepo;
	
	@Autowired
	public DisplayController(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}
	
	@ModelAttribute
	public void displayProducts(Model model) {
		List<Product> products = (List<Product>) productRepo.findAll();
		model.addAttribute("products", products);
	}
	
	@GetMapping
	public String display() {
		return "display";
	}
}
