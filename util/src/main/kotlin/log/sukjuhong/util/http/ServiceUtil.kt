package log.sukjuhong.util.http

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.net.InetAddress
import java.net.UnknownHostException

@Component
class ServiceUtil {

    @Value("\${server.port}")
    private lateinit var port: String

    private var _serviceAddress: String? = null

    fun getServiceAddress(): String {
        if (_serviceAddress == null) {
            _serviceAddress = findMyHostName() + "/" + findMyIpAddress() + ":" + port
        }
        return _serviceAddress ?: "unknown service address"
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