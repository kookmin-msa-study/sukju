package log.sukjuhong.util.http

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.net.InetAddress
import java.net.UnknownHostException

@Component
class ServiceUtil {

    companion object {
        val log: Logger = LoggerFactory.getLogger(ServiceUtil::class.java)
    }

    @Value("\${server.port}")
    private lateinit var port: String

    private var serviceAddress: String? = null

    fun getServiceAddress(): String {
        if (serviceAddress == null) {
            serviceAddress = findMyHostName() + "/" + findMyIpAddress() + ":" + port
        }
        return serviceAddress ?: "unknown service address"
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