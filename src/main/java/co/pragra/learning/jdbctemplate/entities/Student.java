package co.pragra.learning.jdbctemplate.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String course;
    private Instant createDate;
    private Instant updateDate;
}
