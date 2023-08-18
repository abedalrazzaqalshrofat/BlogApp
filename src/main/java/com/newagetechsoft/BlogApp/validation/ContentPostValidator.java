package com.newagetechsoft.BlogApp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ContentPostValidator implements ConstraintValidator<TitleConstrain,String> {

    private Set<String> titles = new HashSet<>();
    public ContentPostValidator(){
        titles.add("Sport");
        titles.add("Foods");
        titles.add("Political");
        titles.add("Arts");
        titles.add("Fashion");
        titles.add("Technology");
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return titles.stream().anyMatch(s::equals);
    }
}
