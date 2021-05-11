package com.renato.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller(value = "/autores")
class CadastraAutorController(val autorRepository: AutorRepository,
                              val enderecoClient: EnderecoClient) {

    @Post
    @Transactional
    fun cadastra(@Body @Valid request: NovoAutorRequest) : HttpResponse<Any> {

        val enderecoResponse = enderecoClient.consultaCep(request.cep)

        val novoAutor = request.paraAutor(enderecoResponse.body()!!)
        autorRepository.save(novoAutor)

        val uri = UriBuilder.of("/autores/{id}").expand(mutableMapOf(Pair("id", novoAutor.id)))

        return HttpResponse.created(uri)
    }
}