package com.epam.task3.data.dataproviders;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestData {
	String usersFilePath() default "null";
	String lettersFilePath() default "null";
}
