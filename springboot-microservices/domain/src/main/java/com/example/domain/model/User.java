package com.example.domain.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message = "The field 'name' is mandatory")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "The field 'email' is mandatory")
    @Column(nullable = false)
    private String email;

    @NotNull(message = "The field 'login' is mandatory")
    @Column(nullable = false)
    private String login;

    @NotNull(message = "The field 'createdDate' is mandatory")
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date createdDate;

    @NotNull(message = "The field 'password' is mandatory")
    @Column(nullable = false)
    private String password;

    @NotNull(message = "The field 'admin' is mandatory")
    private Boolean admin;

}
