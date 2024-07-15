package log.sukjuhong.microservices.composite.product.services

import com.fasterxml.jackson.databind.ObjectMapper
import log.sukjuhong.api.core.product.Product
import log.sukjuhong.api.core.product.ProductService
import log.sukjuhong.api.core.recommendation.Recommendation
import log.sukjuhong.api.core.recommendation.RecommendationService
import log.sukjuhong.api.core.review.Review
import log.sukjuhong.api.core.review.ReviewService
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class ProductCompositeIntegration(
    private val restTemplate: RestTemplate,
    private val objectMapper: ObjectMapper,
    @Value("\${app.product-service.host}") productServiceHost: String,
    @Value("\${app.product-service.port}") productServicePort: String,
    @Value("\${app.recommendation-service.host}") recommendationServiceHost: String,
    @Value("\${app.recommendation-service.port}") recommendationServicePort: String,
    @Value("\${app.review-service.host}") reviewServiceHost: String,
    @Value("\${app.review-service.port}") reviewServicePort: String,
) : ProductService, RecommendationService, ReviewService {

    private final val productServiceUrl: String
    private final val recommendationServiceUrl: String
    private final val reviewServiceUrl: String
    final inline fun <reified T> typeReference() = object : ParameterizedTypeReference<T>() {}

    init {
        this.productServiceUrl = "http://$productServiceHost:$productServicePort/product"
        this.recommendationServiceUrl = "http://$recommendationServiceHost:$recommendationServicePort/recommendation?productId="
        this.reviewServiceUrl = "http://$reviewServiceHost:$reviewServicePort/review?productId="
    }


    override fun getProduct(productId: Int): Product? {
        val url = productServiceUrl + productId
        val product = restTemplate.getForObject(url, Product::class.java)
        return product
    }

    override fun getRecommendations(productId: Int): List<Recommendation> {
        val url = recommendationServiceUrl + productId
        val recommendations = restTemplate.exchange(url, HttpMethod.GET, null, typeReference<List<Recommendation>>()).body
        return recommendations
    }

    override fun getReviews(productId: Int): List<Review> {
        val url = reviewServiceUrl + productId
        val reviews = restTemplate.exchange(url, HttpMethod.GET, null, typeReference<List<Review>>()).body
        return reviews
    }

}