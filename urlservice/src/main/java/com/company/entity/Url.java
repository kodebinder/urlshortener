package com.company.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author pushkar.chauhan@wissen.com
 */
@AllArgsConstructor
@Builder
@Data
@Document(collection = "url")
@NoArgsConstructor
public class Url implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private long id;
    private String urlId;
    private String urlName;
    private String email;
    private String userName;
    private String password;
    private String phoneNumber;
}
