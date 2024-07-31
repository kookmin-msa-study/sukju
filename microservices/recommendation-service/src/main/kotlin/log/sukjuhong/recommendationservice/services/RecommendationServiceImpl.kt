package log.sukjuhong.recommendationservice.services

import log.sukjuhong.api.core.recommendation.Recommendation
import log.sukjuhong.api.core.recommendation.RecommendationService
import log.sukjuhong.util.exceptions.InvalidInputException
import log.sukjuhong.util.http.ServiceUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RestController

@RestController
class RecommendationServiceImpl(val serviceUtil: ServiceUtil) : RecommendationService {

    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(RecommendationServiceImpl::class.java)
    }

    override fun getRecommendations(productId: Int): List<Recommendation> {
        if (productId < 1) throw InvalidInputException("Invalid productId: $productId")

        if (productId == 113) {
            LOG.debug("No recommendations found for productId: {}", productId)
            return emptyList()
        }

        val list = mutableListOf<Recommendation>()
        list.add(Recommendation(productId, 1, "Author 1", 1, "Content 1", serviceUtil.getServiceAddress()))
        list.add(Recommendation(productId, 2, "Author 2", 2, "Content 2", serviceUtil.getServiceAddress()))
        list.add(Recommendation(productId, 3, "Author 3", 3, "Content 3", serviceUtil.getServiceAddress()))

        LOG.debug("/recommendation response size: {}", list.size)

        return list
    }
}

