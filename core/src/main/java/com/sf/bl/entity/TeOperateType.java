package com.sf.bl.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "D_OPERATES")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class TeOperateType implements BaseEntity<Long>, Serializable {

    @Serial
    private static final long serialVersionUID = 5696665954603200163L;

    @Id
    @SequenceGenerator(name = "SF_SEQUENCE", sequenceName = "SF_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SF_SEQUENCE")
    @Column(name = "ID", updatable = false)
    private Long id;

    @Column(name = "CODE", nullable = false)
    private String code;

    @Column(name = "NAME", length = 64)
    private String name;

}
