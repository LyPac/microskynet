package com.lpcheng.microskynet.domain;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 */
@Slf4j
@Component
@ConditionalOnClass(com.google.gson.Gson.class)
public class BasicEntity implements Serializable {

    @Autowired
    private Gson gson;

    @Override
    public String toString() {
        log.debug("{}", gson);
        return gson.toJson(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasicEntity)) return false;
        BasicEntity that = (BasicEntity) o;
        return Objects.equals(toString(), that.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(toString());
    }
}
