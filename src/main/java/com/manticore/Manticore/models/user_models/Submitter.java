package com.manticore.Manticore.models.user_models;

import com.manticore.Manticore.models.Ticket;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "t_submitters")
@NoArgsConstructor
@Getter
@Setter
public class Submitter extends Employee {

    @Column(name = "position")
    private String position;

    @Column(name = "grade")
    private String grade;

    @OneToMany
    private List<Ticket> tickets;
}
