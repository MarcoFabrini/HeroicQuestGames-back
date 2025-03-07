package com.hqc.beck.services.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hqc.beck.dto.ReviewsDTO;
import com.hqc.beck.model.Orders;
import com.hqc.beck.model.Product;
import com.hqc.beck.model.Reviews;
import com.hqc.beck.repository.IOrdersRepository;
import com.hqc.beck.repository.IProductRepository;
import com.hqc.beck.repository.IReviewsRepository;
import com.hqc.beck.request.ReviewsRequest;
import com.hqc.beck.services.interfaces.IReviewsService;
import static com.hqc.beck.utils.Utilities.buildReviewsDTO;

@Service
public class ReviewsImplementation implements IReviewsService {

    private final Logger log;
    private final IReviewsRepository reviewsRepository;
    private final IProductRepository productRepository;
    private final IOrdersRepository ordersRepository;
    // private final IDetailsOrderRepository detailsOrderRepository;

    public ReviewsImplementation(Logger log, IReviewsRepository reviewsRepository, IProductRepository productRepository,
            IOrdersRepository usersRepository) {
        this.log = log;
        this.reviewsRepository = reviewsRepository;
        this.productRepository = productRepository;
        this.ordersRepository = usersRepository;
    }

    @Override
    public List<ReviewsDTO> listByProductId(Integer idProduct) {
        List<Reviews> reviews = reviewsRepository.findByProductId(idProduct);
        return buildReviewsDTO(reviews);
    }// listByGameId

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(ReviewsRequest req) throws Exception {
        Optional<Product> product = productRepository.findById(req.getProductId());
        if (!product.isPresent())
            throw new Exception("game-noPresent");

        Optional<Orders> order = ordersRepository.findById(req.getOrderId());
        if (!order.isPresent())
            throw new Exception("user-noPresent");

        // Long detailsOrder =
        // detailsOrderRepository.findOrderForReview(user.get().getId(),
        // game.get().getId());
        // if (detailsOrder == 0)
        // throw new Exception(serviceMessagesService.getMessage("reviews-notBuy"));

        Optional<Reviews> existingReview = reviewsRepository.findByOrderIdAndProductId(order.get().getId(), product.get().getId());
        if (existingReview.isPresent())
            throw new Exception("reviews_AlreadyPresent");

        Reviews review = new Reviews();
        review.setProduct(product.get());
        review.setOrder(order.get());
        review.setScore(req.getScore());
        review.setDescription(req.getDescription());
        review.setActive(true);

        reviewsRepository.save(review);
    }// create

    @Override
    public void update(ReviewsRequest req) throws Exception {
        Optional<Reviews> review = reviewsRepository.findById(req.getId());
        if (!review.isPresent())
            throw new Exception("reviews-noPresent");

        Optional<Product> product = productRepository.findById(req.getProductId());
        if (!product.isPresent())
            throw new Exception("product-noPresent");

        Optional<Orders> order = ordersRepository.findById(req.getOrderId());
        if (!order.isPresent())
            throw new Exception("user-noPresent");

        // Long detailsOrder =
        // detailsOrderRepository.findOrderForReview(user.get().getId(),
        // game.get().getId());
        // if (detailsOrder == 0)
        // throw new Exception(serviceMessagesService.getMessage("reviews-notBuy"));

        review.get().setScore(req.getScore());
        review.get().setDescription(req.getDescription());
        review.get().setActive(true);

        reviewsRepository.save(review.get());
    }// update

    /*
     * solo per admin
     */
    @Override
    public void delete(Integer id) throws Exception {
        Optional<Reviews> review = reviewsRepository.findById(id);
        if (!review.isPresent())
            throw new Exception("reviews-noPresent");

        review.get().setActive(false);

        reviewsRepository.save(review.get());
    }// delete

}// class
