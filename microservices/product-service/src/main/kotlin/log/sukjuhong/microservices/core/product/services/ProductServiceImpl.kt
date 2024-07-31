package log.sukjuhong.microservices.core.product.services

import log.sukjuhong.api.core.product.Product
import log.sukjuhong.api.core.product.ProductService
import log.sukjuhong.util.exceptions.InvalidInputException
import log.sukjuhong.util.exceptions.NotFoundException
import log.sukjuhong.util.http.ServiceUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductServiceImpl(
    val serviceUtil: ServiceUtil
) : ProductService {

    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(ProductServiceImpl::class.java)
    }

    override fun getProduct(productId: Int): Product? {
        LOG.debug("/product return the found product for productId={}", productId)

        if (productId < 1)
            throw InvalidInputException("Invalid productId: $productId")

        if (productId == 13)
            throw NotFoundException("No product found for productId: $productId")

        return Product(productId, "name-$productId", 123, serviceUtil.getServiceAddress())
    }
}