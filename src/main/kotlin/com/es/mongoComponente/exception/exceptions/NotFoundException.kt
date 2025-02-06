package com.es.mongoComponente.exception.exceptions

class NotFoundException (message: String)
    : Exception("Not found exception (404). $message") {

}