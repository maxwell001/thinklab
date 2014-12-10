package com.activemq.jpa.model;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ID = "id";

    public static final String SHOW = "1";
    public static final String HIDE = "0";

    public static final String YES = "1";
    public static final String NO = "0";

    public static final String DEL_FLAG = "delFlag";
    public static final String DEL_FLAG_NORMAL = "0";
    public static final String DEL_FLAG_DELETE = "1";
    public static final String DEL_FLAG_AUDIT = "2";

}
