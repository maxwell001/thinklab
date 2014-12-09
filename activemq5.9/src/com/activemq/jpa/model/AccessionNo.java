package com.activemq.jpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "pis_accession_no")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AccessionNo extends BaseEntity {

    private static final long serialVersionUID = 1L;
    private Long id; 
    private String accessionNo;
    private String saved;
    private String delFlag;
    private String type = TYPE_AUTO;

    public static final String TYPE_AUTO = "0";
    public static final String TYPE_MANUAL = "1";

    public static final String SAVED = "saved";
    public static final String SAVED_INIT = "0";
    public static final String SAVED_COMPLETE = "1";

    public AccessionNo() {
        super();
        this.delFlag = DEL_FLAG_NORMAL;
        this.saved = SAVED_INIT;
    }

    public AccessionNo(Long id) {
        this();
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
    // "seq_pis_accessionType")
    // @SequenceGenerator(name = "seq_pis_accessionType", sequenceName =
    // "seq_pis_accessionType")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Length(min = 1, max = 200)
    public String getAccessionNo() {
        return accessionNo;
    }

    public void setAccessionNo(String accessionNo) {
        this.accessionNo = accessionNo;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getSaved() {
        return saved;
    }

    public void setSaved(String saved) {
        this.saved = saved;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
