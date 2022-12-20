package edu.avaliacao.bank.client.clientInterfaces

interface DigitalClientInterface {
    fun pix(msg: String, v: Double, target: DigitalClientInterface): String
    fun payTicket(msg: String, v: Double): String
    fun invest(msg: String, v: Double): String
    fun store(msg: String, v: Double): String
}
