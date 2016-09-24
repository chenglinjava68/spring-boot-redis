package com.zheng.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by zhenglian on 2016/9/24.
 */
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private Integer locked;

    public User() {
    }

    public User(String username, String password, Integer locked) {
        this.username = username;
        this.password = password;
        this.locked = locked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("username", this.username).append("password", this.password)
                .append("locked", this.locked).build();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof User)) {
            return false;
        }

        User other = (User) obj;
        return new EqualsBuilder().append(this.username, other.username).append(this.password, other.password)
                .append(this.locked, other.locked).build();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.username).append(this.password).append(this.locked).build();
    }
}
