package edu.avaliacao.bank.util

import edu.avaliacao.bank.isNumberOnly

/**
 * Gets valid user input. While user input is not valid, prompt will be executed, errorMsg will be printed and input is going to be read again.
 *
 * @param prompt Executable to run before reading line.
 * @param isInputValid Executable which receives a String argument and returns a boolean. By default it returns true if not specified.
 * @param errorMsg Message to be printed when invalid input is given.
 */
fun getUserInput(prompt: () -> Unit, isInputValid: (String) -> Boolean = {true}, errorMsg: String = "Entrada inválida, tente novamente."): String {
    prompt()
    var input = readln()

    while(!isInputValid(input)) {
        prompt()
        println(errorMsg)
        input = readln()
    }

    return input
}

fun getUserName(): String {
    return getUserInput(
        prompt = { printSection("Digite seu nome:")},
        isInputValid = {it.isNotEmpty() && it.isNotBlank() && it.length >= 3},
        "O nome deve ter 3 ou mais caracteres e não pode ser vazio ou branco."
    )
}

fun getUserLastName(): String {
    return getUserInput(
        prompt = { printSection("Digite seu último nome:")},
        isInputValid = {it.isNotEmpty() && it.isNotBlank() && it.length >= 3},
        "O último nome deve ter 3 ou mais caracteres e não pode ser vazio ou branco."
    )
}

fun getUserCPF(): String {
    return getUserInput(
        prompt = { printSection("Digite o CPF (sem pontuação):")},
        isInputValid = {it.isNotEmpty() && it.isNotBlank() && it.length == 11 && it.isNumberOnly()},
        "CPF inválido, tente novamente."
    )
}

fun getUserPassword(): String {
    return getUserInput(
        prompt = { printSection("Digite sua senha:")},
        isInputValid = {it.isNotEmpty() && it.isNotBlank() && it.length >= 3},
        "A senha deve ter mais de 3 dígitos, não pode ser vazia nem branca. Tente novamente."
    )
}

fun getUserPlan(): Int {
    return getUserInput(
        prompt = { printSection("Selecione um plano:\n1 - Normal\n2 - Digital\n3 - Premium")},
        isInputValid = {it.toIntOrNull() in 1..3},
    ).toInt()
}
