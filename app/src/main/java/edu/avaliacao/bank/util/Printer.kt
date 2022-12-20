package edu.avaliacao.bank.util

private const val divisor = "💸. ° .• .\uD83D\uDCB8 .• ° . 💸 . ° .• .\uD83D\uDCB8"

private val menu = """
        1 - Criar conta
        2 - Entrar
        0 - Encerrar programa
    """.trimIndent()

data class AccountMenu(private val menu: String, val validOptions: IntRange) {
    fun getMenu(): String = menu
}

private val normalMenu = """
    1 - Depósito
    2 - Saque
    3 - Pagar boleto
    4 - Verificar saldo
    5 - Verificar extrato
    0 - Sair
""".trimIndent()
val normalAccountMenu = AccountMenu(normalMenu, 0..5)


private val digitalMenu = """
    1 - Guardar
    2 - Investir
    3 - Pagar boleto
    4 - Realizar pix
    5 - Verificar saldo
    6 - Verificar extrato
    0 - Sair
""".trimIndent()
val digitalAccountMenu = AccountMenu(digitalMenu, 0..6)

private val premiumMenu = """
    Carteira Digital
    1 - Guardar
    2 - Investir
    3 - Realizar pix
    Carteira Física
    4 - Pagar boleto
    5 - Depósito
    6 - Saque
    7 - Verificar saldo
    8 - Verificar extrato
    0 - Sair
""".trimIndent()
val premiumAccountMenu = AccountMenu(premiumMenu, 0..8)

/**
 * Prints the divisor and the menu right after it.
 */
fun printMenu() {
    printSection(menu)
}

/**
 * Prints an string after the divisor.
 *
 * @param section String to be printed after the divisor.
 */
fun printSection(section: String) {
    println(divisor)
    println(section)
}
