package com.stormfkrs.someone_like_this.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Container(containerName = "Party")
public class Party implements Serializable {

    @Id
    @GeneratedValue
    private String id;

    private String name;

    private String joinCode; //notUsed

    private String partitionKey;

    private Date creationDate;

    private User creatorUser;

    private List<User> joinedUsers;

    private List<Option> options;

    private List<Point> points;
}
