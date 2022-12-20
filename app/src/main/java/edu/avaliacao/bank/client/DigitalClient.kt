package edu.avaliacao.bank.client

import edu.avaliacao.bank.client.clientInterfaces.DigitalClientInterface
import edu.avaliacao.bank.wallet.DigitalWallet

class DigitalClient(name: String, lastName: String, cpf: String, password: String) : DigitalClientInterface,
AbstractClient(
    name, lastName, cpf, password, Plan.DIGITAL
)
{
    override fun pix(msg: String, v: Double, target: DigitalClientInterface): String {
        return (wallet as DigitalWallet).pix(msg, v, target)
    }

    override fun payTicket(msg: String, v: Double): String {
        return (wallet as DigitalWallet).payTicket(msg, v)
    }

    override fun invest(msg: String, v: Double): String {
        return (wallet as DigitalWallet).invest(msg, v)
    }

    override fun store(msg: String, v: Double): String {
        return (wallet as DigitalWallet).store(msg, v)
    }
}