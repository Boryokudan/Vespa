package com.manticore.Manticore.models;

import com.manticore.Manticore.models.user_models.Developer;
import com.manticore.Manticore.models.user_models.ProjectManager;
import com.manticore.Manticore.models.user_models.Submitter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "t_projects")
@NoArgsConstructor
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "status")
    private String status;

    @Column(name = "overview")
    private String overview;

    @Column(name = "initiation_date")
    private LocalDate initiationDate;

    @Column(name = "completion_date")
    private LocalDate completionDate;

    @ManyToOne
    private ProjectManager projectManager;

    @ManyToMany
    private List<Developer> team;

    @ManyToMany
    private List<Submitter> submitters;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Ticket> tickets;
}
