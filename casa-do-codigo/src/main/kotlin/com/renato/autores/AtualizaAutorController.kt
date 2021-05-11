package com.renato.autores


import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put
import javax.transaction.Transactional


@Controller(value = "/autores")
class AtualizaAutorController(val autorRepository: AutorRepository) {

    @Put(value = "/{id}")
    @Transactional
    fun atualiza (@PathVariable id: Long, descricao: String) : HttpResponse<Any> {
        val possivalAutor = autorRepository.findById(id)

        if (possivalAutor.isEmpty){
            return HttpResponse.notFound()
        }

        val autor = possivalAutor.get()

        autor.descricao = descricao

        return HttpResponse.ok(DetalhesAutorResponse(autor))
    }
}