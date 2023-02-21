package com.manticore.Manticore.models.user_models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_project_managers")
@NoArgsConstructor
@Getter
@Setter
public class ProjectManager extends Employee {

}
