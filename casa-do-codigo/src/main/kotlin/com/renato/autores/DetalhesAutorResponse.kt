package com.renato.autores

class DetalhesAutorResponse(
    val nome: String,
    val email: String,
    val descricao: String,
) {
    constructor(autor: Autor) : this(autor.nome, autor.email, autor.descricao)
  }

