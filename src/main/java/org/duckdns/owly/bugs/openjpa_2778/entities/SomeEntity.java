package org.duckdns.owly.bugs.openjpa_2778.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class SomeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SequenceTableGen")
    @TableGenerator(name = "SequenceTableGen", table = "OPENJPA_SEQUENCE_TABLE", pkColumnName = "id", valueColumnName = "sequence_value")
    private Long id;

    @Column(length = 4000)
    private String nonLobValue;

    @Lob
    private String lobValue;
}
