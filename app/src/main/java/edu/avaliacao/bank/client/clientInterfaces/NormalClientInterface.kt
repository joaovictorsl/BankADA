package edu.avaliacao.bank.client.clientInterfaces

import edu.avaliacao.bank.client.AbstractClient
import edu.avaliacao.bank.wallet.PhysicalWallet

interface NormalClientInterface {
    fun deposit(msg: String, v: Double): String
    fun withdraw(msg: String, v: Double): String
    fun payTicket(msg: String, v: Double): String
}