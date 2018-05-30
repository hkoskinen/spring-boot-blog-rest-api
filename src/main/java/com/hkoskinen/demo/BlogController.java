package com.hkoskinen.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class BlogController {

    @Autowired
    BlogRepository blogRepository;

    @RequestMapping(path = "/blogs", method = RequestMethod.GET)
    public List<Blog> index() {
        return blogRepository.findAll();
    }

    @GetMapping("/blogs/{id}")
    public Blog show(@PathVariable String id) {
        int blogId = Integer.parseInt(id);

        Optional<Blog> blog = blogRepository.findById(blogId);
        if (blog.isPresent()) {
            return blog.get();
        } else {
            return null;
        }
    }

    @PostMapping("/blogs")
    public Blog create(@RequestBody Map<String, String> body) {
        String title = body.get("title");
        String content = body.get("content");
        return blogRepository.save(new Blog(title, content));
    }

    @PutMapping("/blogs/{id}")
    public Blog update(@PathVariable String id, @RequestBody Map<String, String> body) {
        int blogId = Integer.parseInt(id);

        Optional<Blog> blog = blogRepository.findById(blogId);
        if (blog.isPresent()) {
            blog.get().setTitle(body.get("title"));
            blog.get().setContent(body.get("content"));
            return blogRepository.save(blog.get());
        }
        return null;
    }

    @DeleteMapping("/blogs/{id}")
    public boolean delete(@PathVariable String id) {
        blogRepository.deleteById(Integer.parseInt(id));
        return true;
    }
}
