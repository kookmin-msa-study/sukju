package log.sukjuhong.api.composite.product

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

interface ProductCompositeService {

    /**
     * Sample usage: curl $HOST:$PORT/product-composite/1
     *
     * @param productId
     * @return the composite product info, if found, else null
     */
    @GetMapping(
        value = ["/product-composite/{productId}"],
        produces = ["application/json"]
    )
    fun getProduct(@PathVariable productId: Int): ProductAggregate
}