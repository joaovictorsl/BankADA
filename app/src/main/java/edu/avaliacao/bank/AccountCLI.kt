package edu.avaliacao.bank

import edu.avaliacao.bank.client.*
import edu.avaliacao.bank.client.clientInterfaces.DigitalClientInterface
import edu.avaliacao.bank.util.*

class AccountCLI(
    private val client: AbstractClient,
    private val clientList: List<AbstractClient>
    ) {

    fun run() {
        showAccountMenu(client)
    }

    private fun showAccountMenu(user: AbstractClient) {
        val accMenu = when (user.plan) {
            Plan.PHYSICAL -> normalAccountMenu
            Plan.DIGITAL -> digitalAccountMenu
            Plan.PREMIUM -> premiumAccountMenu
        }

        do {
            printSection(accMenu.getMenu())

            val choice = getUserInput(
                prompt = { println("Digite a opção desejada:") },
                isInputValid = { input -> input.toIntOrNull() in accMenu.validOptions }
            ).toInt()

            val result = execAccountAction(choice, user.plan)

            if (result != null)
                printSection(result)
        } while (choice != 0)
    }

    private fun execAccountAction(action: Int, plan: Plan): String? {
        val result = when (plan) {
            Plan.PHYSICAL -> normalAction(action)
            Plan.DIGITAL -> digitalAction(action)
            Plan.PREMIUM -> premiumAction(action)
        }

        return result
    }

    private fun normalAction(action: Int): String? {
        var result: String? = null

        if (action != 0) {


            result = when (action) {
                in 1..3 -> {
                    val (msg, v) = getMsgAndValue()
                    when (action) {
                        1 -> (client as NormalClient).deposit(msg, v.toDouble())
                        2 -> (client as NormalClient).withdraw(msg, v.toDouble())
                        else -> (client as NormalClient).payTicket(msg, v.toDouble())
                    }
                }
                4 -> (client as NormalClient).checkBalance().toString()
                else -> (client as NormalClient).checkBankStatement()
            }
        }

        return result
    }

    private fun getMsgAndValue(): Array<String> {
        val msg =
            getUserInput({ println("Digite uma nota para poder relembrar dessa transação:") })
        val v = getUserInput({ println("Digite um valor:") }, { it.isNumberOnly() })

        return arrayOf<String>(msg, v)
    }

    private fun digitalAction(action: Int): String? {
        var result: String? = null

        if (action != 0) {

            result = when (action) {
                in 1..4 -> {
                    val (msg, v) = getMsgAndValue()
                    when(action) {
                        1 -> (client as DigitalClient).store(msg, v.toDouble())
                        2 -> (client as DigitalClient).invest(msg, v.toDouble())
                        3 -> (client as DigitalClient).payTicket(msg, v.toDouble())
                        else -> doPix(msg, v.toDouble())
                    }
                }
                5 -> (client as DigitalClient).checkBalance().toString()
                else -> (client as DigitalClient).checkBankStatement()
            }
        }

        return result
    }

    private fun premiumAction(action: Int): String? {
        var result: String? = null

        if (action != 0) {

            result = when (action) {
                in 1..6 -> {
                    val (msg, v) = getMsgAndValue()
                    when(action) {
                        1 -> (client as PremiumClient).store(msg, v.toDouble())
                        2 -> (client as PremiumClient).invest(msg, v.toDouble())
                        3 -> (client as PremiumClient).payTicket(msg, v.toDouble())
                        4 -> doPix(msg, v.toDouble())
                        5 -> (client as PremiumClient).deposit(msg, v.toDouble())
                        else -> (client as PremiumClient).withdraw(msg, v.toDouble())
                    }
                }
                7 -> (client as PremiumClient).checkBalance().toString()
                else -> (client as PremiumClient).checkBankStatement()
            }

        }

        return result
    }

    private fun doPix(msg: String, v: Double): String {
        val selectedClient = selectClient()
        val result = if (selectedClient == null)
            "Não conseguimos encontrar o destinatário."
        else {
            (client as PremiumClient).pix(msg, v, selectedClient)
        }

        return result
    }

    /**
     * Selects a Client and returns it. If clientList is empty it returns null. Returns null if client is not able to use pix.
     */
    private fun selectClient(): DigitalClientInterface? {
        var result: DigitalClientInterface? = null

        if (clientList.isNotEmpty()) {
            val targetCPF = getUserCPF()
            val idx = clientList.indexOf(NormalClient("", "", targetCPF, ""))
            val target = clientList[idx]

            result = if (target.plan == Plan.DIGITAL || target.plan == Plan.PREMIUM)
                target as DigitalClientInterface
            else
                null
        }

        return result
    }
}