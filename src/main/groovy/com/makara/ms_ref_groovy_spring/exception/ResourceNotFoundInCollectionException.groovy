package com.makara.ms_ref_groovy_spring.exception


/**
 * Created by steven.tarver@ctl.io on 12/22/17.
 */
class ResourceNotFoundInCollectionException extends RuntimeException {

    ResourceNotFoundInCollectionException(String collection, String id) {
        super(String.format("There is no item '%s' in the %s collection.", id, collection));
    }
}
