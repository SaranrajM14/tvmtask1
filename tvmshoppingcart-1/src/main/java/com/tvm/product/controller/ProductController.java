package com.tvm.product.controller;

import java.io.IOException;
import java.util.List;

import com.tvm.product.excelreport.*;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.tvm.product.model.Product;
import com.tvm.product.service.ProductServicr;

@Controller
public class ProductController {
	@Autowired
	private ProductServicr productService;
	@GetMapping("/")
    public String viewAddtocart(Model model) {
        model.addAttribute("product", productService.getAllProduct());
        return "index";
	}
	@GetMapping("/searchbyproductid/{productid}")
	public String searchByProductId(@PathVariable(value="productid") long productid,Model model) {
		Product product =productService.getProductId(productid);
		model.addAttribute("product", product);
		return "index";
	}
	@GetMapping("/searchbyproductname/{productname}")
	public String searchByProductName(@PathVariable(value="productname") String productname,Model model) {
		Product product=productService.getProductByproductname(productname);
		model.addAttribute("product", product);
		return "index";
	}
@GetMapping("/export/excel")
	
	public void exportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headervalue = "attachment; filename=Product_info.xlsx";

		response.setHeader(headerKey, headervalue);
		List<Product> listProduct = productService.getAllProduct();
		ProductexcelExport exp = new ProductexcelExport(listProduct);
		exp.export(response);
}

}
