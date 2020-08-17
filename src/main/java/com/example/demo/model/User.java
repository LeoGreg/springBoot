package com.example.demo.model;


import com.example.demo.model.ISP.UserStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @Size(min = 5, max = 5)
    private String code;

    @NotBlank
    @Column(name = "username", nullable = false, unique = true)
    @Pattern(regexp = "[A-Z]{2,}[a-z]{2,}[0-9]{2,}[~!@#$%^&*()_|<>?]{2,}", message = "password has to have upper,lower case letters at least 2 of each of them numbers and punctuations again 2 of them at least")
    private String username;

    @Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,5}", message = "plz insert a real g-mail")
    private String g_mail;

    @Enumerated
    @Column(name = "status", nullable = false)
    private UserStatus status;


    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Pattern(regexp = "[A-Z]{2,}[a-z]{2,}[0-9]{3,}[~!@#$%^&*()_+|:'><?.,/]{2,}")
    private String password;


    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
    /*
    1 if we consumes json but we get xml for example it'll cause 415 not supported
    2 @Valid uses to secure model restrictions if sth goes wrong it'll cause 40 bad request
     */

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

 /*   @Transient
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateOfBirth;*/

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
    joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "authority_id",referencedColumnName = "id"))
    private List<Authority> authorities;
}
