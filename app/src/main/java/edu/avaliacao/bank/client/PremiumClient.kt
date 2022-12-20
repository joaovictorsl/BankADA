package edu.avaliacao.bank.client

import edu.avaliacao.bank.client.clientInterfaces.DigitalClientInterface
import edu.avaliacao.bank.client.clientInterfaces.NormalClientInterface
import edu.avaliacao.bank.wallet.PremiumWallet

class PremiumClient(name: String, lastName: String, cpf: String, password: String) : DigitalClientInterface, NormalClientInterface,
    AbstractClient(
        name, lastName, cpf, password, Plan.PREMIUM
    )
{
    override fun pix(msg: String, v: Double, target: DigitalClientInterface): String {
        return (wallet as PremiumWallet).pix(msg, v, target)
    }

    override fun deposit(msg: String, v: Double): String {
        return (wallet as PremiumWallet).deposit(msg, v)
    }

    override fun withdraw(msg: String, v: Double): String {
        return (wallet as PremiumWallet).withdraw(msg, v)
    }

    override fun payTicket(msg: String, v: Double): String {
        return (wallet as PremiumWallet).payTicket(msg, v)
    }

    override fun invest(msg: String, v: Double): String {
        return (wallet as PremiumWallet).invest(msg, v)
    }

    override fun store(msg: String, v: Double): String {
        return (wallet as PremiumWallet).store(msg, v)
    }
}