package com.example.demo.Controller;

import java.util.List;

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
import com.example.demo.Models.Users;
import com.example.demo.Repository.UsersRepository;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {
	@Autowired
	private UsersRepository UsersRepository;
	
	@GetMapping("/getall")
	public ResponseEntity<ResponseObj> getAllUser() {
		try {
			List<Users> data = UsersRepository.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObj(true, "Tim thanh cong", data)
			);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new ResponseObj(true, "Tim that bai", "")
			);
		}
        
    }
	@GetMapping("/getbyname")
	public ResponseEntity<ResponseObj> getByName(@RequestParam String nameuser) {
		try {
			List<Users> data = UsersRepository.findByNameContaining(nameuser);
			return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObj(true, "Tim thanh cong", data)
			);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new ResponseObj(true, "Tim that bai", "")
			);
		}
        
    }
	@PostMapping("/login")
	public ResponseEntity<ResponseObj> login(@RequestBody Users data) {
		try {
			Users datauser = UsersRepository.findByUsername(data.getUsername());
			if (datauser==null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
						new ResponseObj(false, "Tài khoản không tồn tai", "")
					);
			}else if(!datauser.getPassword().equals(data.getPassword())) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
						new ResponseObj(false, "Mật khẩu không đúng", "")
					);
			}else {
				return ResponseEntity.status(HttpStatus.OK).body(
						new ResponseObj(true, "Đăng nhập thành công", datauser)
					);
			}
			
			
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new ResponseObj(true, "Tim that bai", "")
			);
		}
        
    }
	@PutMapping("/update")
	public ResponseEntity<ResponseObj> updateUser(@RequestBody Users data, @RequestParam Integer id ) {
		try {
			Users datauser = UsersRepository.findById(id).orElse(null);
			if (datauser==null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
						new ResponseObj(false, "Người dùng không tồn tai", "")
					);
			
			}else {
				datauser.setAddress(data.getAddress());
				datauser.setEmail(data.getEmail());
				datauser.setName(data.getName());
				datauser.setPassword(data.getPassword());
				datauser.setRole_id(data.getRole_id());
				datauser.setUsername(data.getUsername());
				UsersRepository.save(datauser);
				return ResponseEntity.status(HttpStatus.OK).body(
						new ResponseObj(true, "Sửa thông tin thành công", datauser)
					);
			}
			
			
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new ResponseObj(true, "Tim that bai", "")
			);
		}
        
    }
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseObj> deleteUser(@RequestParam Integer id ) {
		try {
			Users datauser = UsersRepository.findById(id).orElse(null);
			if (datauser==null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
						new ResponseObj(false, "Người dùng không tồn tai", "")
					);
			
			}else {
				UsersRepository.delete(datauser);
				return ResponseEntity.status(HttpStatus.OK).body(
						new ResponseObj(true, "Xóa thành công", datauser)
					);
			}
			
			
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new ResponseObj(true, "Xóa that bai", "")
			);
		}
        
    }
	
}
