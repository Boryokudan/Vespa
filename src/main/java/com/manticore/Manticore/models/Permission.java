package com.manticore.Manticore.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "t_permissions")
@NoArgsConstructor
@Data
public class Permission implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "permission_level")
    private String permissionLevel;

    public Permission(String permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    @Override
    public String getAuthority() {
        return this.permissionLevel;
    }
}
