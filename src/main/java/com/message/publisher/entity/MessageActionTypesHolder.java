package com.message.publisher.entity;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@EqualsAndHashCode
@ToString
public class MessageActionTypesHolder implements MessageTypesHolder {

    private final List<String> actionTypes;

    public MessageActionTypesHolder() {
        this.actionTypes = new ArrayList<>();

        actionTypes.add("PURCHASE");
        actionTypes.add("SUBSCRIPTION");

    }

    @Override
    public List<String> getAllTypes() {
        return actionTypes;
    }

    @Override
    public String getTypeById(int id) {
        return actionTypes.get(id);
    }
}
