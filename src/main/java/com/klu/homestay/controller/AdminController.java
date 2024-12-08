		package com.klu.homestay.controller;
		
		import java.util.Map;
		
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.http.HttpStatus;
		import org.springframework.http.ResponseEntity;
		import org.springframework.stereotype.Controller;
		import org.springframework.web.bind.annotation.CrossOrigin;
		import org.springframework.web.bind.annotation.PostMapping;
		import org.springframework.web.bind.annotation.RequestBody;
		import org.springframework.web.bind.annotation.GetMapping;
		
		import com.klu.homestay.model.Admin;
		import com.klu.homestay.service.AdminService;
		
		import jakarta.servlet.http.HttpSession;
		
		@Controller
		@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
		public class AdminController {
		
		    @Autowired
		    private AdminService adminService;
		
		    @PostMapping("/adminLogin")
		    public ResponseEntity<Admin> adminLogin(@RequestBody Map<String, String> loginData, HttpSession session) {
		        String email = loginData.get("email");
		        String password = loginData.get("password");
		
		        Admin admin = adminService.adminLogin(email, password);
		        if (admin != null) {
		            session.setAttribute("admin", admin);
		            session.setMaxInactiveInterval(30 * 60); // Session timeout of 30 minutes
		            return ResponseEntity.ok(admin);
		        }
		        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		    }
		
		    @GetMapping("/getAdminDetails")
		    public ResponseEntity<Admin> getAdminDetails(HttpSession session) {
		        Admin admin = (Admin) session.getAttribute("admin");
		        if (admin == null) {
		            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		        }
		        return ResponseEntity.ok(admin);
		    }
		
		    @PostMapping("/adminLogout")
		    public ResponseEntity<String> logout(HttpSession session) {
		        session.invalidate();
		        return ResponseEntity.ok("Logged out successfully.");
		    }
		}
