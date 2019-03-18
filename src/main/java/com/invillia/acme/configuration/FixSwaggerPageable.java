package com.invillia.acme.configuration;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Declares Annotation that fixes issue https://github.com/springfox/springfox/issues/2623
 * between Spring Pageable and Swagger
 * @author José Victor | jvas.2000@gmail.com
 */
@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiImplicitParams({
		@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
				value = "Results page you want to retrieve (0..N)", defaultValue = "0"),
		@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
				value = "Number of records per page.", defaultValue = "5"),
		@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
				value = "Sorting criteria in the format: property(,asc|desc). " +
						"Default sort order is ascending. " +
						"Multiple sort criteria are supported.")})
public @interface FixSwaggerPageable {
}