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
@RequestMapping("/deleteProduct")
public class DeleteProduct {
	private final ProductRepository productRepo;
	
	@Autowired
	public DeleteProduct(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}
	
	@ModelAttribute
	@ResponseBody
	public void modelDelete(@RequestParam String productCode ,Model model) {
		Product product = productRepo.findByCode(productCode);
		model.addAttribute("product", product);
	}

	@PostMapping()
	public String delete(Model model) {
		String code = (String) model.getAttribute("product");
		System.out.println(code);
		Product product = productRepo.findByCode(code);
		productRepo.delete(product);
		return "redirect:/display";
	}

	@GetMapping
	public String deleteProduct() {
		return "deleteProduct";
	}
}
