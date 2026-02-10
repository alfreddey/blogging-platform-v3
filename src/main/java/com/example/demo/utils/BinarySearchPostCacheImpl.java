package com.example.demo.utils;

import com.example.demo.cache.Cache;
import com.example.demo.model.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class BinarySearchPostCacheImpl implements PostSearch {
    private final Cache<Post> postCache;

    public BinarySearchPostCacheImpl(Cache<Post> postCache) {
        this.postCache = postCache;
    }

    @Override
    public Post findByTitle(String title) {
        var posts = postCache.getAll();

        PostSorts.sort(posts);

        var left = 0;
        var right = posts.size() - 1;

        while (left <= right) {
            var mid = left + (right - left) / 2;
            var midPost = posts.get(mid);
            var comparison = midPost.getTitle().compareTo(title);

            if (comparison == 0) {
                return midPost;
            }

            if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return null;
    }
}
