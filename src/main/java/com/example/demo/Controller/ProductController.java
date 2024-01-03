package com.example.demo.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.Products;
import com.example.demo.Models.ResponseObj;
import com.example.demo.Repository.ProductRepository;

@RestController
@RequestMapping(path = "/api/v1/product")
public class ProductController {
	
	@Autowired
	private ProductRepository ProductRepository;
	
	
	
	@GetMapping("/getall")
	public ResponseEntity<ResponseObj> getAllProducts() {
		try {
			List<Products> data = ProductRepository.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObj(true, "Tim thanh cong", data)
			);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new ResponseObj(true, "Tim that bai", "")
			);
		}
        
    }
	
	@GetMapping("/getbyid")
	public ResponseEntity<ResponseObj> getProductsById(@RequestParam Long id) {
		try {
			Products data = ProductRepository.findById(id).orElse(null);
			if (data != null) {
				return ResponseEntity.status(HttpStatus.OK).body(
						new ResponseObj(true, "Tim thanh cong", data)
					);
	        } else {
	        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
	    				new ResponseObj(false, "Tim that bai", "")
	    		);
	        }
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new ResponseObj(false, "Tim that bai", "")
			);
		}
        
    }
	@GetMapping("/getbyname")
	public ResponseEntity<ResponseObj> getProducts(@RequestParam String nameprd) {
		try {
			List<Products> data = ProductRepository.findByNameContaining(nameprd);
			
			if (!data.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(
						new ResponseObj(true, "Tim thanh cong", data)
					);
	        } else {
	        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
	    				new ResponseObj(false, "Tim that bai", "")
	    		);
	        }
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new ResponseObj(false, "Tim that bai", "")
			);
		}
        
    }
	@PostMapping("/addprd")
	public ResponseEntity<ResponseObj> addProducts(@RequestBody Products newprd) {
		try {
			
			
			
			if (newprd.getName().isEmpty() || newprd.getImage().isEmpty() || newprd.getCategoryId()==0 || newprd.getDateOfManufacture()==null  ) {
				return ResponseEntity.status(HttpStatus.OK).body(
						new ResponseObj(false, "Vui long nhap day du thong tin", "")
					);
			}else {
				Products data = ProductRepository.save(newprd);
				return ResponseEntity.status(HttpStatus.OK).body(
						new ResponseObj(true, "Thêm thanh cong", data)
					);
			}
			
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new ResponseObj(false, "Thêm that bai", e)
			);
		}
        
    }
	@PutMapping("/updateprd")
	public ResponseEntity<ResponseObj> putProducts(@RequestBody Products dataprd, @RequestParam Long id) {
		try {
				Optional<Products> data = ProductRepository.findById(id);
				if (data.isPresent()) {
				    Products product = data.get();
				    product.setName(dataprd.getName());
				    product.setImage(dataprd.getImage());
				    product.setQuantity(dataprd.getQuantity());
				    product.setCategoryId(dataprd.getCategoryId());
				    product.setQuantity(dataprd.getQuantity());
				    product.setDateOfManufacture(dataprd.getDateOfManufacture());
				    product.setExpirationDate(dataprd.getExpirationDate());
				    // Save the updated product
				    ProductRepository.save(product);
				} else {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
							new ResponseObj(false, "Sản phẩm không tồn tại", "")
						);
				}
				return ResponseEntity.status(HttpStatus.OK).body(
						new ResponseObj(true, "Sửa thanh cong", data)
					);
			
			
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new ResponseObj(false, "Sửa that bai", e)
			);
		}
        
    }
	@DeleteMapping("/deleteprd")
	public ResponseEntity<ResponseObj> deleteProductsById(@RequestParam Long id) {
		try {
			Products data = ProductRepository.findById(id).orElse(null);
			if (data != null) {
				ProductRepository.delete(data);
				return ResponseEntity.status(HttpStatus.OK).body(
						new ResponseObj(true, "Xoa thanh cong", "")
					);
	        } else {
	        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
	    				new ResponseObj(false, "San pham khong ton tai", "")
	    		);
	        }
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new ResponseObj(false, "Xoa that bai", "")
			);
		}
        
    }
}
