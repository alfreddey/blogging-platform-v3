package com.example.demo.mapper;

import com.example.demo.dto.PostResponse;
import com.example.demo.model.entity.Post;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MongoPostMapperTest {

    private final MongoPostMapper mapper = new MongoPostMapper();

    @Test
    void toResponse_ShouldMapAllFields() {
        Post post = new Post();
        post.setId("507f1f77bcf86cd799439011");
        post.setTitle("Java");
        post.setTags(List.of("coding"));

        PostResponse response = mapper.toResponse(post);

        assertEquals(post.getId(), response.getId());
        assertEquals(post.getTitle(), response.getTitle());
        assertEquals(1, response.getTags().size());
    }

    @Test
    void toPost_FromDocument_ShouldHandleNestedComments() {
        ObjectId id = new ObjectId();
        Document doc = new Document("_id", id)
                .append("title", "Doc Title")
                .append("comments", List.of(
                        new Document("_id", new ObjectId()).append("text", "nice")
                ));

        Post post = MongoPostMapper.toPost(doc);

        assertEquals(id.toHexString(), post.getId());
        assertEquals(1, post.getComments().size());
        assertEquals("nice", post.getComments().get(0).getText());
    }
}