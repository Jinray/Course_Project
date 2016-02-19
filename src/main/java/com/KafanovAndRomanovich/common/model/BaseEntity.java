package com.KafanovAndRomanovich.common.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * @author Petri Kainulainen
 */
@MappedSuperclass
public abstract class BaseEntity<ID> {

//    @Column(name = "creation_time", nullable = false)
//    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
//    @JsonDeserialize(using= LocalDateDeserializer.class)
//    private DateTime creationTime;
//
//    @Column(name = "modification_time", nullable = false)
//    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
//    @JsonDeserialize(using=LocalDateDeserializer.class)
//    private DateTime modificationTime;

    @Version
    private long version;

    public abstract ID getId();

//    public DateTime getCreationTime() {
//        return creationTime;
//    }
//
//    public DateTime getModificationTime() {
//        return modificationTime;
//    }

    public long getVersion() {
        return version;
    }

    @PrePersist
    public void prePersist() {
        //DateTime now = DateTime.now();
//        this.creationTime = now;
//        this.modificationTime = now;
    }

    @PreUpdate
    public void preUpdate() {
    }
}


