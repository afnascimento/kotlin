package br.com.anascimento.spotify

import org.junit.Assert
import org.junit.Test

/**
 * Created by andre.nascimento on 19/09/2017.
 */
class KotlinClassesTest {

    open class Pessoa {

        constructor()

        constructor(nome: String?, idade: Int?) {
            this.nome = nome
            this.idade = idade
        }

        constructor(nome: String?, idade: Int?, endereco: Endereco?) {
            this.nome = nome
            this.idade = idade
            this.endereco = endereco
        }

        var nome : String? = null
        var idade: Int? = null
        var endereco: Endereco? = null

        override fun toString(): String {
            return "Pessoa(nome=$nome, idade=$idade, endereco=$endereco)"
        }
    }

    class Endereco {
        var logradouro : String? = null
        var numero: Int? = null
        var bairro: String? = null

        constructor(logradouro: String?, numero: Int?, bairro: String?) {
            this.logradouro = logradouro
            this.numero = numero
            this.bairro = bairro
        }

        override fun toString(): String {
            return "Endereco(logradouro=$logradouro, numero=$numero, bairro=$bairro)"
        }
    }

    class PessoaFisica : Pessoa {

        constructor() : super() {
            this.cpf = cpf
        }

        constructor(nome: String?, idade: Int?, cpf: String?) : super(nome, idade) {
            this.cpf = cpf
        }

        constructor(nome: String?, idade: Int?, cpf: String?, endereco: Endereco?) : super(nome, idade, endereco) {
            this.cpf = cpf
        }

        var cpf : String? = null
    }

    class PessoaJuridica : Pessoa {

        constructor(cnpj: String?) : super() {
            this.cnpj = cnpj
        }

        constructor(nome: String?, idade: Int?, cnpj: String?) : super(nome, idade) {
            this.cnpj = cnpj
        }

        constructor(nome: String?, idade: Int?, cnpj: String?, endereco: Endereco?) : super(nome, idade, endereco) {
            this.cnpj = cnpj
        }

        var cnpj : String? = null
    }

    fun getLogradouroPessoa(pessoa: Pessoa?) : String? {
        return pessoa?.endereco?.logradouro
    }

    @Test
    fun test() {
        val fisica = PessoaFisica(nome = "Andre Freitas", idade = 28, cpf = "998.736.873-66",
                endereco = Endereco("Rua Costa e Silva", 345, "Chapada"))

        val logradouroNull = getLogradouroPessoa(null)
        Assert.assertNull(logradouroNull)

        val logradouroNotNull = getLogradouroPessoa(fisica)
        Assert.assertNotNull(logradouroNotNull)

        println(fisica)
    }
}