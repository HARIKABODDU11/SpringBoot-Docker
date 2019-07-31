package com.stackroute.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.annotation.Documented;


@Data

@NoArgsConstructor
@AllArgsConstructor
@Document(collection="user")
public class User {

    @Id
    private int id;
    private String firstName;
    private String lastName;
    private int age;


}

