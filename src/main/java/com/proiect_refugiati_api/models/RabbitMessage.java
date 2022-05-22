package com.proiect_refugiati_api.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RabbitMessage {
    private String type;
    private String message;
    private String refugeeEmail;
    private String userNotes;
    private String acceptedBy;
    private String requestType;
}
