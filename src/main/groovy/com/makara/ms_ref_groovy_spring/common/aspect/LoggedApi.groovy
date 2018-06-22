package com.makara.ms_ref_groovy_spring.common.aspect

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * Add this annotation to:
 * - @RestController classes to generate telemetry logging for public methods.
 * - @RestController methods to generate telemetry logging for only the attached method.
 */
@Target([ElementType.TYPE, ElementType.METHOD])
@Retention(RetentionPolicy.RUNTIME)
@interface LoggedApi {
}
