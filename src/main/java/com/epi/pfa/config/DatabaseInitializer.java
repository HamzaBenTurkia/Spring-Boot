package com.epi.pfa.config;

import com.epi.pfa.model.*;
import com.epi.pfa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TagRepository tagRepository;

    @PostConstruct
    public void initializeDatabase() {
        // Delete existing data
        deleteAllData();

        // Insert mock data
        insertMockData();
    }

    private void deleteAllData() {
        userRepository.deleteAll();
        postRepository.deleteAll();
        tagRepository.deleteAll();
    }

    private void insertMockData() {

        PasswordEncoder passwordEncoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();

        User superUser = new User();
        superUser.setUsername("admin");
        superUser.setPassword(passwordEncoder.encode("admin"));
        userRepository.save(superUser);

        String imageUrl = "https://imgs.capitalxtra.com/images/335192?crop=16_9&width=660&relax=1&format=webp&signature=khnPgEmqqoGtzCdannbozQyvnWE=";
        byte[] profileImageBytes = downloadImage(imageUrl);
        User user = new User();
        user.setUsername("Drake");
        user.setProfileImage(profileImageBytes);
        userRepository.save(user);

        String imageUrl1 = "https://variety.com/wp-content/uploads/2024/02/Eminem.jpg?w=1000&h=667&crop=1&resize=1000%2C667";
        byte[] profileImageBytes1 = downloadImage(imageUrl1);
        User user1 = new User();
        user1.setUsername("eminem");
        user1.setProfileImage(profileImageBytes1);
        userRepository.save(user1);

        String imageUrl2 = "https://thefader-res.cloudinary.com/private_images/w_2400,c_limit,f_auto,q_auto:best/F103_feature_metro_Stahl_01_WEB_ezlq0j/metro-boomin-cover-story-interview.jpg";
        byte[] profileImageBytes2 = downloadImage(imageUrl2);
        User user2 = new User();
        user2.setUsername("metro");
        user2.setProfileImage(profileImageBytes2);
        userRepository.save(user2);

        String imageUrl3 = "https://imageio.forbes.com/specials-images/imageserve/65a55f3cae92659ccdacd90f/Rolling-Loud-Miami-2022/960x0.jpg?format=jpg&width=1440";
        byte[] profileImageBytes3 = downloadImage(imageUrl3);
        User user3 = new User();
        user3.setUsername("kendrik");
        user3.setProfileImage(profileImageBytes3);
        userRepository.save(user3);

        String imageUrl4 = "https://upload.wikimedia.org/wikipedia/commons/a/a3/50_Cent_AKA_Curtis_Jackson_%28cropped%29.jpg";
        byte[] profileImageBytes4 = downloadImage(imageUrl4);
        User user4 = new User();
        user4.setUsername("50 cent");
        user4.setProfileImage(profileImageBytes4);
        userRepository.save(user4);

        String imageUrl5 = "https://www.thefactorystl.com/wp-content/uploads/Lil-Wayne_750x450.jpg";
        byte[] profileImageBytes5 = downloadImage(imageUrl5);
        User user5 = new User();
        user5.setUsername("lil wayne");
        user5.setProfileImage(profileImageBytes5);
        userRepository.save(user5);

        String imageUrl6 = "https://smartcdn.gprod.postmedia.digital/ottawacitizen/wp-content/uploads/2024/04/kanye-west-apology.jpg?quality=90&strip=all&w=564&h=423&type=webp&sig=4_jwatLH72V_W_MQQz5qkQ";
        byte[] profileImageBytes6 = downloadImage(imageUrl6);
        User user6 = new User();
        user6.setUsername("kanye");
        user6.setProfileImage(profileImageBytes6);
        userRepository.save(user6);

        String imageUrl7 = "https://www.billboard.com/wp-content/uploads/2023/06/Lil-Uzi-Vert-cr-Kenneth-Cappello-press-2023-billboard-1548-1.jpg?w=942&h=623&crop=1";
        byte[] profileImageBytes7 = downloadImage(imageUrl7);
        User user7 = new User();
        user7.setUsername("lil uzi vert");
        user7.setProfileImage(profileImageBytes7);
        userRepository.save(user7);

        Post post = new Post();
        post.setContent("mock data");
        post.setUser(user);
        postRepository.save(post);

        Post post1 = new Post();
        post1.setContent("mock data");
        post1.setUser(user);
        postRepository.save(post1);

        Post post2 = new Post();
        post2.setContent("mock data");
        post2.setUser(user);
        postRepository.save(post2);

        Post post3 = new Post();
        post3.setContent("mock data");
        post3.setUser(user5);
        postRepository.save(post3);

        Post post4 = new Post();
        post4.setContent("mock data");
        post4.setUser(user1);
        postRepository.save(post4);

        List<Post> posts = new ArrayList<>();
        posts.add(post);
        posts.add(post1);
        posts.add(post2);
        Tag tag = new Tag();
        tag.setName("mock data");
        tag.setPosts(posts);
        tagRepository.save(tag);

        // Assign tags to posts, etc.
    }
    private byte[] downloadImage(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
