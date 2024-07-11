package http

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.net.InetAddress
import java.net.UnknownHostException

@Component
class ServiceUtil {

    companion object {
        val log = LoggerFactory.getLogger(this::class.java)
    }

    @Value("${server.port}")
    lateinit var port: String

    var serviceAddress: String? = null

    fun getServiceAddress(): String? {
        if (serviceAddress == null) {
            serviceAddress = findMyHostName() + "/" + findMyIpAddress() + ":" + port
        }
        return serviceAddress
    }

    fun findMyHostName(): String {
        return try {
            InetAddress.getLocalHost().hostName
        } catch (err: UnknownHostException) {
            "unknown host name"
        }
    }

    fun findMyIpAddress(): String {
        return try {
            InetAddress.getLocalHost().hostAddress
        } catch (err: UnknownHostException) {
            "unknown IP address"
        }
    }
}