package com.sf.bl.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sf.core.Defaults;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "D_CUSTOMERS")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class TeCustomer implements BaseEntity<Long>, Serializable {

    @Serial
    private static final long serialVersionUID = -4159443683527882128L;

    @Id
    @SequenceGenerator(name = "SF_SEQUENCE", sequenceName = "SF_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SR_SEQUENCE")
    @Column(name = "ID", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
    private TePerson person;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "DATE_ADDED", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Defaults.DATE_FMT_ISO8601)
    private LocalDateTime dateAdded;

}
