package com.proiect_refugiati_api.models;

import lombok.*;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "Refugee")
public class Refugee {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Id
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "telephone")
    private String telephone;

}
