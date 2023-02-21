package com.manticore.Manticore.models.user_models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_developers")
@NoArgsConstructor
@Getter
@Setter
public class Developer extends Employee {

    @Column(name = "grade")
    private String grade;

    @Column(name = "specialization")
    private String specialization;
}
