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
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "SF_OPERATES")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class TeOperate implements BaseEntity<Long>, Serializable {

    @Serial
    private static final long serialVersionUID = 2997329689607676302L;

    @Id
    @SequenceGenerator(name = "SF_SEQUENCE", sequenceName = "SF_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SF_SEQUENCE")
    @Column(name = "ID", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEBT_ACC_ID", referencedColumnName = "ID")
    private TeBalance debitAcc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CRED_ACC_ID", referencedColumnName = "ID")
    private TeBalance creditAcc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OPERATE_ID", referencedColumnName = "ID")
    private TeOperateType type;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUR_ID", referencedColumnName = "ID")
    private TeCurrency currency;

    @Column(name = "DATE_ADDED", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Defaults.DATE_FMT_ISO8601)
    private LocalDateTime dateAdded;

    @Column(name = "DATE_MODIFIED", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Defaults.DATE_FMT_ISO8601)
    private LocalDateTime dateModified;
}
