package com.es.mongoComponente.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

@Document(collection = "coll_noticias")
data class Noticia(
    @Id
    val _id:ObjectId?,
    @Field
    val titulo: String,
    @Field
    val cuerpo: String,
    @Field
    val fechaPub: Date,
    @Field
    val tag: List<String>?,
    @Field
    val user: String
)