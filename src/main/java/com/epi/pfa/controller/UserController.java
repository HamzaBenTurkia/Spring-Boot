package com.epi.pfa.controller;

import com.epi.pfa.model.Post;
import com.epi.pfa.model.User;
import com.epi.pfa.repository.PostRepository;
import com.epi.pfa.repository.TagRepository;
import com.epi.pfa.repository.UserRepository;
import com.epi.pfa.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping("/addUser")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @Transactional
    @PostMapping("/save")
    public String uploadProfileImage(@RequestParam("file") MultipartFile file, @RequestParam("username") String username,@RequestParam("password") String password) throws IOException {
            User user = new User();
            user.setUsername(username);
            user.setProfileImage(file.getBytes());
//            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
            return "redirect:/users/showAllUsers";
    }

    @GetMapping("/showAllUsers")
    public String showAllUsers(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userRepository.findAll(pageable);

        // Iterate over each user and fetch their posts
        userPage.getContent().forEach(user -> {
            List<Post> userPosts = postRepository.findByUser(user);
            user.setPosts(userPosts);
        });

        model.addAttribute("users", userPage);
        return "show-all-users";
    }

    @GetMapping("/profileImage/{userId}")
    public ResponseEntity<byte[]> getUserProfileImage(@PathVariable Long userId, HttpServletResponse response) throws SQLException, IOException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            byte[] imageData = user.getProfileImage();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            headers.setContentLength(user.getProfileImage().length);
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.valueOf("image/png"))
                    .body(imageData);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") Long userId) {
        userRepository.deleteById(userId);
        return "redirect:/users/showAllUsers";
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
