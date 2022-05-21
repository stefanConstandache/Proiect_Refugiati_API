package com.proiect_refugiati_api.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "Request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request_type")
    private String requestType;

    @Column(name = "user_notes")
    private String userNotes;

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "request_status")
    private String requestStatus;

    @Column(name = "refugee_email")
    private String refugeeEmail;

    @Column(name = "accepted_by")
    private String acceptedBy;

}
