package com.example.avochadbackend.utility.exceptions;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PersonNotCreatedException extends RuntimeException implements GraphQLError {

    private String invalidField;

    public PersonNotCreatedException(String message, String invalidField) {
        super(message);
        this.invalidField = invalidField;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return Collections.singletonMap("invalidField", this.invalidField);
    }
}
