package com.NIED.service;

import com.NIED.model.AdminUser;
import com.NIED.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Optional;

@Service
public class AdminUserService {

    @Autowired
    private AdminUserRepository adminUserRepository;

    // Correctly initialize BCryptPasswordEncoder as a class field
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AdminUser createAdminUser(AdminUser adminUser) {
        // Hash the plain text password before saving
        String hashedPassword = passwordEncoder.encode(adminUser.getPassword());
        adminUser.setPassword(hashedPassword);
        return adminUserRepository.save(adminUser);
    }

    public Optional<AdminUser> getAdminByUsername(String username) {
        return adminUserRepository.findByUsername(username);
    }

    // You can also add a method for password verification (e.g., login)
    public boolean checkPassword(String plainPassword, String hashedPassword) {
        return passwordEncoder.matches(plainPassword, hashedPassword);
    }
    public boolean authenticate(String username, String password) {
        Optional<AdminUser> adminUserOptional = adminUserRepository.findByUsername(username);
        if (adminUserOptional.isPresent()) {
            AdminUser adminUser = adminUserOptional.get();
            return passwordEncoder.matches(password, adminUser.getPassword());
        }
        return false;
    }
}