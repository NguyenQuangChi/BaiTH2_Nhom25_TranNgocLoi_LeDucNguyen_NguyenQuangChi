package product.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import product.Product;
import product.data.ProductRepository;

@Controller
@RequestMapping("/updateProduct")
public class EditProduct {
	private final ProductRepository productRepo;
	
	@Autowired
	public EditProduct(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}
	
	@ModelAttribute
	@ResponseBody
	public void modelUPdate(@RequestParam String productCode, Model model) {
		Product prd = productRepo.findByCode(productCode);
		productRepo.save(prd);
		model.addAttribute("product", prd);
	}
	
	@PostMapping("/updateProduct")
	public String UpdateProduct() {
		return "redirect:/display";
	}
	
	@GetMapping
	public String updateProduct() {
		return "updateProduct";
	}
}
