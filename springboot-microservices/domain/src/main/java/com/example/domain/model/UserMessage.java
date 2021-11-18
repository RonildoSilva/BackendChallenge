package com.example.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserMessage {

    @NotNull(message = "The field 'message' is mandatory")
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    Date createdDate;
}
