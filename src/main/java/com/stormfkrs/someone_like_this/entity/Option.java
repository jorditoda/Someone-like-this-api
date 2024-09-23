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
public class Option implements Serializable {

    @Id
    private String id;

    private String text;

    private Boolean isDone;

    private User userCompleted;

    private int positionInArray;

}
