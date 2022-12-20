package edu.avaliacao.bank.client

import edu.avaliacao.bank.client.clientInterfaces.NormalClientInterface
import edu.avaliacao.bank.wallet.PhysicalWallet

class NormalClient(name: String, lastName: String, cpf: String, password: String) : NormalClientInterface,
    AbstractClient(
    name,
    lastName, cpf, password, Plan.PHYSICAL
)
{
    override fun deposit(msg: String, v: Double): String {
        return (wallet as PhysicalWallet).deposit(msg, v)
    }

    override fun withdraw(msg: String, v: Double): String {
        return (wallet as PhysicalWallet).withdraw(msg, v)
    }

    override fun payTicket(msg: String, v: Double): String {
        return (wallet as PhysicalWallet).payTicket(msg, v)
    }
}