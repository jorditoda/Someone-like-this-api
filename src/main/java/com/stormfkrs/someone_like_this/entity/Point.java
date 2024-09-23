package com.stormfkrs.someone_like_this.entity;

import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Point implements Serializable {

    @Id
    private String id;

    private int points;

    private User user;

    private int positionInArray;
}
