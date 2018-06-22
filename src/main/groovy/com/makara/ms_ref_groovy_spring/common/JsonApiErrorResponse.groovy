package com.makara.ms_ref_groovy_spring.common

/**
 * I wrap JsonApiErrors in a response top-level "errors" object.
 *
 * In general, we fail on first error so there is always one error element.
 * json:api standard allows multiple errors...
 */
class JsonApiErrorResponse {

    JsonApiError[] errors = null

    JsonApiErrorResponse(JsonApiError jsonApiError) {
        this.errors = new JsonApiError[1]
        this.errors[0] = jsonApiError
    }
}
