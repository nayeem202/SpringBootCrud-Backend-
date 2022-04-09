package com.example.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Model.Product;
import com.example.Repository.ProductRepository;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class ProductController {
	
	@Autowired
	public ProductRepository productRepository;
	
	@PostMapping("/productSave")
	public ResponseEntity<Map> save(@RequestBody Product product){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
		 product = 	productRepository.save(product);
			map.put("Status", "success");
			map.put("data", product);
			map.put("message", "data successfully saved");
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			map.put("status", "failed");
			map.put("data", null);
			map.put("message", e.getLocalizedMessage());
			return ResponseEntity.status(500).body(map);
		}
		
		
	}
	
	@GetMapping("/showProduct")
	public ResponseEntity<Map> show(){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
		List<Product> model = (List<Product>) productRepository.findAll();
		map.put("data", model);
		map.put("message", "data get Successfully");
		return ResponseEntity.ok(map);
		} catch (Exception e) {
			map.put("message", "data get failed");
			return ResponseEntity.status(500).body(map);
			
		}
		
	}
	
	
	@GetMapping("/showPById/{id}")
	public ResponseEntity<Map> showByid(@PathVariable int id){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
		Optional<Product> model = 	productRepository.findById((int) id);
		map.put("model", model);
		map.put("message", "data successfully get");
		return ResponseEntity.ok(map);
		
		} catch (Exception e) {
			map.put("message", "data successfully get");
			return ResponseEntity.status(500).body(map);
		}
		
	}
	
	@PostMapping("/updateProduct/{productId}")
	public ResponseEntity<Map> updatePro(@RequestBody Product product, @PathVariable int productId){
		Map<String, Object> map = new HashMap<String, Object>();	
		try {
			//Product p = productRepository.findById(productId).get();
			product.setProductId(productId);
			product = 	productRepository.save(product);
			map.put("data", product);
			map.put("message", "successfully updated");
			return ResponseEntity.ok(map);
			
		} catch (Exception e) {
			map.put("message", " updated failed ");
			return ResponseEntity.status(500).body(map);
		}
	}
	
	
	@GetMapping("/deleteById/{productId}")
	public ResponseEntity<Map> map ( @PathVariable int productId){
	Map<String, Object> map = new HashMap<String, Object>();
	try {
		productRepository.deleteById((int) productId);
		map.put("message", "successfully Deleted");
		return ResponseEntity.ok(map);
	} catch (Exception e) {
		map.put("message" , e.getLocalizedMessage());
		return ResponseEntity.status(500).body(map);
	}
	}
	
	
	

}
