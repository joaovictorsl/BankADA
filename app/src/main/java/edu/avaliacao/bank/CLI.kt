package edu.avaliacao.bank

import edu.avaliacao.bank.client.*
import edu.avaliacao.bank.util.*

class CLI {
    private val clientList = mutableListOf<AbstractClient>()
    private var currentUser: AbstractClient? = null

    /**
     * Executes the application.
     */
    fun run() {
        println("Bem-vindo ao sistema de clientes do banco ADA")

        do {
            val input = getUserInput({ printMenu() },
                {
                    it.toIntOrNull() in 0..2
                }).toInt()

            execCommand(input)

            currentUser?.let {
                AccountCLI(it, clientList).run()
                currentUser = null
            }

        } while (input != 0)
    }

    /**
     * Executes a command desired by the user.
     */
    private fun execCommand(command: Int) {
        when (command) {
            1 -> createAccount()
            2 -> logInAccount()
        }
    }

    /**
     * Creates a client and adds it to the clientList property.
     */
    private fun createAccount() {
        val name = getUserName()
        val lastName = getUserLastName()
        val cpf = getUserCPF()
        val password = getUserPassword()
        val plan = getUserPlan()

        val newClient = when (plan) {
            1 -> NormalClient(name, lastName, cpf, password)
            2 -> DigitalClient(name, lastName, cpf, password)
            else -> PremiumClient(name, lastName, cpf, password)
        }

        val result = if (!clientList.contains(newClient)) {
            clientList.add(newClient)
            "Cliente criado"
        } else
            "O cliente já existe."

        printSection(result)
    }

    /**
     * Logs a client in account
     */
    private fun logInAccount() {
        val cpf = getUserCPF()
        val idx = clientList.indexOf(NormalClient("", "", cpf, ""))

        if (idx == -1) {
            printSection("Não existe conta atrelada a esse CPF.")
        } else {
            val password = getUserPassword()
            val isCorrectPassword = clientList[idx].isCorrectPassword(password)

            if (isCorrectPassword)
                currentUser = clientList[idx]
        }

    }
}