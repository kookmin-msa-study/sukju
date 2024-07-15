package log.sukjuhong.microservices.core.product.services

import log.sukjuhong.api.core.product.Product
import log.sukjuhong.api.core.product.ProductService
import log.sukjuhong.util.http.ServiceUtil
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductServiceImpl(
    val serviceUtil: ServiceUtil
): ProductService {

    override fun getProduct(productId: Int): Product? {
        return Product(productId, "name-$productId", 123, serviceUtil.getServiceAddress())
    }
}