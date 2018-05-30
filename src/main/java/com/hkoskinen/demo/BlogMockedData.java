package com.hkoskinen.demo;

import java.util.ArrayList;
import java.util.List;

public class BlogMockedData {

    private List<Blog> blogs;

    private static BlogMockedData instance = null;
    public static BlogMockedData getInstance() {
        if (instance == null) {
            instance = new BlogMockedData();
        }
        return instance;
    }

    public BlogMockedData() {
        blogs = new ArrayList<>();

        blogs.add(new Blog(1, "Go up, and away with your Google Assistant", "With holiday travel coming up, and 2018 just around the corner, " +
            "you may be already thinking about getaways for next year. Consider the Google Assistant your new travel buddy, " +
            "ready at the drop of a hat-or a passport"));
        blogs.add(new Blog(2, "Get local help with your Google Assistant", "Long content description."));
        blogs.add(new Blog(3, "The new maker toolkit: IoT, AI, and Google Cloud Platform", "Long content description."));
        blogs.add(new Blog(4, "Learn more about the world around you with Google Lens and the Assistant", "Long content description."));
        blogs.add(new Blog(5, "7 ways the Assistant can help you get ready for Turkey Day", "Long content description."));
        blogs.add(new Blog(6, "The Artificial Intelligence in your daily life", "Long content description."));
        blogs.add(new Blog(7, "Speed running as a hobby or as a job", "Long content description."));
    }

    public List<Blog> fetchBlogs() {
        return blogs;
    }

    public Blog getBlogById(int id) {
        for (Blog b : blogs) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    public List<Blog> searchBlogs(String searchTerm) {
        List<Blog> searchedBlogs = new ArrayList<>();
        for (Blog b : blogs) {
            if (b.getTitle().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    b.getContent().toLowerCase().contains(searchTerm.toLowerCase())) {
                searchedBlogs.add(b);
            }
        }
        return searchedBlogs;
    }

    public Blog createBlog(int id, String title, String content) {
        Blog newBlog = new Blog(id, title, content);
        blogs.add(newBlog);
        return newBlog;
    }

    public Blog updateBlog(int id, String title, String content) {
        for (Blog b : blogs) {
            if (b.getId() == id) {
                int blogIndex = blogs.indexOf(b);
                b.setTitle(title);
                b.setContent(content);
                blogs.set(blogIndex, b);
                return b;
            }
        }
        return null;
    }

    public boolean delete(int id) {
        int blogIndex = -1;
        for (Blog b : blogs) {
            if (b.getId() == id) {
                blogIndex = blogs.indexOf(b);
                continue;
            }
        }
        // item is removed outside the for-loop to avoid the "java.util.ConcurrentModificationException"
        if (blogIndex > -1) {
            blogs.remove(blogIndex);
        }
        return true;
    }
}
