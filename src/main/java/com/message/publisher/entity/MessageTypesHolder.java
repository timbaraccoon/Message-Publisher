package com.message.publisher.entity;

import java.util.List;

public interface MessageTypesHolder {

    List<String> getAllTypes();

    String getTypeById(int id);

}
