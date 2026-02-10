package com.example.demo.repository;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.MongoPostMapper;
import com.example.demo.model.entity.Post;
import com.example.demo.repository.interfaces.PostRepository;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MongoPostRepository {
    private final MongoCollection<Document> postCollection;

    public MongoPostRepository(MongoDatabase postDatabase) {
        this.postCollection = postDatabase.getCollection("posts");
    }
//
//    @Override
//    public Post updatePostContent(String id, String content) {
//        var filter = Filters.eq("_id", new ObjectId(id));
//        var update = Updates.set("content", content);
//        var updateOpts = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
//        var postDocument = postCollection.findOneAndUpdate(filter, update, updateOpts);
//
//        if (postDocument == null) {
//            throw new ResourceNotFoundException("Post with id: " + id + " not found. Update was unsuccessful");
//        }
//
//        return MongoPostMapper.toPost(postDocument);
//    }
//
//    @Override
//    public boolean delete(String postId) {
//        var deleteResult = postCollection.deleteOne(Filters.eq("_id", new ObjectId(postId)));
//
//        return deleteResult.getDeletedCount() > 0;
//    }
//
//    @Override
//    public List<Post> getAll(int page, int size) {
//        var postDocuments = new ArrayList<Document>();
//
//        postCollection.find().skip(page * size).limit(size).into(postDocuments);
//
//        return new ArrayList<>(postDocuments.stream().map(MongoPostMapper::toPost).toList());
//    }
//
//    @Override
//    public Post getById(String id) {
//        var idFilter = Filters.eq("_id", new ObjectId(id));
//        var postDocument = postCollection.find(idFilter).first();
//
//        if (postDocument == null) {
//            throw new ResourceNotFoundException("Post not found with id: " + id);
//        }
//
//        return MongoPostMapper.toPost(postDocument);
//    }
//
//    @Override
//    public Post insert(Post post) {
//        var postDocument = MongoPostMapper.toDocument(post);
//
//        postCollection.insertOne(postDocument);
//
//        return MongoPostMapper.toPost(postDocument);
//    }
}
