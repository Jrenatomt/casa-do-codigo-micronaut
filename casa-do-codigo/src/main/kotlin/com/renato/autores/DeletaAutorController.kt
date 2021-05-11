package com.renato.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put
import javax.transaction.Transactional

@Controller(value = "/autores")
class DeletaAutorController(val autorRepository: AutorRepository) {

    @Delete(value = "/{id}")
    @Transactional
    fun deleta (@PathVariable id: Long) : HttpResponse<Any> {
        val possivalAutor = autorRepository.findById(id)

        if (possivalAutor.isEmpty){
            return HttpResponse.notFound()
        }
        val autor = possivalAutor.get()
        autorRepository.delete(autor)

        return HttpResponse.ok()
    }
}