package log.sukjuhong.reviewservice.services

import log.sukjuhong.api.core.review.Review
import log.sukjuhong.api.core.review.ReviewService
import log.sukjuhong.util.exceptions.InvalidInputException
import log.sukjuhong.util.http.ServiceUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RestController

@RestController
class ReviewServiceImpl(private val serviceUtil: ServiceUtil) : ReviewService {

    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(ReviewServiceImpl::class.java)
    }

    override fun getReviews(productId: Int): List<Review> {
        if (productId < 1) throw InvalidInputException("Invalid productId: $productId")

        if (productId == 213) {
            LOG.debug("No reviews found for productId: {}", productId)
            return emptyList()
        }

        val list = mutableListOf<Review>()
        list.add(Review(productId, 1, "Author 1", "Subject 1", "Content 1", serviceUtil.getServiceAddress()))
        list.add(Review(productId, 2, "Author 2", "Subject 2", "Content 2", serviceUtil.getServiceAddress()))
        list.add(Review(productId, 3, "Author 3", "Subject 3", "Content 3", serviceUtil.getServiceAddress()))

        LOG.debug("/reviews response size: {}", list.size)

        return list
    }
}

