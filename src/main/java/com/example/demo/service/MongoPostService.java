package com.example.demo.service;

import com.example.demo.cache.Cache;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.entity.Post;
import com.example.demo.repository.interfaces.PostRepository;
import com.example.demo.service.interfaces.PostService;
import com.example.demo.utils.PostSearch;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@CacheConfig(cacheNames = "posts")
public class MongoPostService implements PostService {
    private final PostRepository postRepository;
    private final Cache<Post> postCache;
    private final PostSearch postSearch;

    public MongoPostService(PostRepository postRepository, Cache<Post> postCache, PostSearch postSearch) {
        this.postCache = postCache;
        this.postRepository = postRepository;
        this.postSearch = postSearch;
    }


    @Override
    public List<Post> getAll(int page, int size, String sortBy) {
        var cacheResult = postCache.getAll();

        if (cacheResult != null && !cacheResult.isEmpty() && cacheResult.size() >= size) {
            return cacheResult;
        }

        var pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        var posts = postRepository.findAll(pageable).getContent();

        posts.forEach(postCache::put);

        return posts;
    }

    @Cacheable
    @Override
    public Post getById(String postId) {
        var cacheResult = postCache.getById(postId);

        if (cacheResult != null) {
            return cacheResult;
        }

        var post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));

        postCache.put(post);

        return post;
    }

    @CachePut(key = "#result.id")
    @Transactional
    @Override
    public Post create(Post post) {
        post = postRepository.save(post);
        postCache.put(post);

        return post;
    }

    @CacheEvict(key = "#postId")
    @Transactional
    @Override
    public void delete(String postId) {
        postCache.remove(postId);
        postRepository.deleteById(postId);
    }

    @CachePut(key = "#postId")
    @Transactional
    @Override
    public Post updatePostContent(String postId, String content) {
        var post = postRepository.updatePostContent(postId, content);
        postCache.put(postId, post);

        return post;
    }

    @Cacheable(key = "#title")
    @Override
    public Post findByTitle(String title) {
        var post = postSearch.findByTitle(title);

        if (post != null) {
            return post;
        }

        post = postRepository.findByTitle(title);

        if (post != null) {
            return post;
        }

        throw new ResourceNotFoundException("Post not found with title: " + title);
    }
}
