package com.fastcampus.sns.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AlarmArgs {

    private Long fromUserId;
    private Long targetId;

}

