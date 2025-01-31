package com.spring.boilerplate.spring_boilerplate.auth.models.entity;

import com.spring.boilerplate.spring_boilerplate.auth.enums.EnumUserRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    private EnumUserRole name;
}
