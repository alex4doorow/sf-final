package com.sf.bl.entity;

public interface BaseEntity<ID> {
    void setId(ID id);
    ID getId();
}

