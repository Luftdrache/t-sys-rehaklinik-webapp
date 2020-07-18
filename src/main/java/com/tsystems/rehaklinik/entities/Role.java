package com.tsystems.rehaklinik.entities;

import com.tsystems.rehaklinik.types.Roles;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "roles", schema = "rehaklinik",
        uniqueConstraints = @UniqueConstraint(columnNames = "role", name = "UNQ_ROLE"))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false, length = 11)
    private int roleId;

    @NotNull(message = "Role must be set")
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 25)
    private Roles role;

    @OneToMany(mappedBy = "role")
    private List<Employee> employees;

}
