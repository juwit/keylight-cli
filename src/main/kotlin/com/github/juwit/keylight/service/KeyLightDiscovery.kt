package com.github.juwit.keylight.service

import java.net.InetAddress
import java.util.concurrent.CompletableFuture
import javax.jmdns.JmDNS
import javax.jmdns.ServiceEvent
import javax.jmdns.ServiceListener

/**
 * A mDNS service listener which sends the keylight URL in a completable future
 */
class CompletableServiceListener(private val completableFuture: CompletableFuture<String>) : ServiceListener {

    override fun serviceAdded(event: ServiceEvent?) = Unit

    override fun serviceRemoved(event: ServiceEvent?) = Unit

    override fun serviceResolved(event: ServiceEvent?) {
        when (event) {
            null -> completableFuture.completeExceptionally(RuntimeException("could not get keylight url"))
            else -> completableFuture.complete(event.info.urLs[0])
        }
    }

}

fun discovery(): CompletableFuture<String> {
    val future = CompletableFuture<String>()

    val jmdns = JmDNS.create(InetAddress.getLocalHost())
    jmdns.addServiceListener("_elg._tcp.local.", CompletableServiceListener(future))

    return future
}