package com.fastcampus.sns.controller.response;


import com.fastcampus.sns.model.Post;
import com.fastcampus.sns.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
public class PostResponse {

    private Long id;

    private String title;

    private String body;

    private UserResponse user;

    private Timestamp registerAt;

    private Timestamp updatedAt;

    private Timestamp deletedAt;

    public static PostResponse fromPost(Post post) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getBody(),
                UserResponse.from(post.getUser()),
                post.getRegisterAt(),
                post.getUpdatedAt(),
                post.getDeletedAt()
        );
    }
}
