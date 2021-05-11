package com.renato.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.micronaut.transaction.annotation.ReadOnly
import javax.transaction.Transactional

@Controller(value = "/autores")
class BuscaAutoresController(val autorRepository: AutorRepository) {

    @Get
    @Transactional
    fun pesquisaAutores(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {

        if (email.isBlank()) {
            val autores = autorRepository.findAll()

            val resposta = autores.map { autor -> DetalhesAutorResponse(autor) }

            return HttpResponse.ok(resposta)
        }

        val possivelAutor = autorRepository.buscarPorEmail(email)
        if (possivelAutor.isEmpty) {
            return HttpResponse.notFound()
        }

        val autor = possivelAutor.get()
        return HttpResponse.ok(DetalhesAutorResponse(autor))
    }
}
