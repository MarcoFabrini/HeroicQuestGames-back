package com.hqc.beck.services.implementation;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hqc.beck.model.BoardGame;
import com.hqc.beck.model.Product;
import com.hqc.beck.repository.IAuthorsRepository;
import com.hqc.beck.repository.IBoardGameRepository;
import com.hqc.beck.repository.ICategoriesRepository;
import com.hqc.beck.repository.IEditorsRepository;
import com.hqc.beck.repository.IProductRepository;
import com.hqc.beck.request.BoardGameRequest;
import com.hqc.beck.services.interfaces.IBoardGameService;

@Service
public class BoardGameImplementation implements IBoardGameService {

    private final Logger log;
    private final IProductRepository productRepository;
    private final IBoardGameRepository boardGameRepository;
    private final IEditorsRepository editorsRepository;
    private final IAuthorsRepository authorsRepository;
    private final ICategoriesRepository categoriesRepository;

    public BoardGameImplementation(Logger log, IProductRepository productRepository,
            IBoardGameRepository boardGameRepository, IEditorsRepository editorsRepository,
            IAuthorsRepository authorsRepository, ICategoriesRepository categoriesRepository) {
        this.log = log;
        this.productRepository = productRepository;
        this.boardGameRepository = boardGameRepository;
        this.editorsRepository = editorsRepository;
        this.authorsRepository = authorsRepository;
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(BoardGameRequest req) throws Exception {
        if (req == null)
            throw new Exception("Request cannot be null");

        if (req.getName() == null || req.getName().trim().isEmpty())
            throw new Exception("Product name cannot be null or empty");

        if (req.getPrice() == null || req.getPrice() <= 0)
            throw new Exception("Product price must be greater than 0");

        if (req.getStockQuantity() == null || req.getStockQuantity() < 0)
            throw new Exception("Stock quantity cannot be negative");

        BoardGame boardGame = new BoardGame();
        boardGame.setMinGameTime(req.getMinGameTime());
        boardGame.setMaxGameTime(req.getMaxGameTime());
        boardGame.setMinPlayerNumber(req.getMinPlayerNumber());
        boardGame.setMaxPlayerNumber(req.getMaxPlayerNumber());
        boardGame.setMinAge(req.getMinAge());

        boardGame = boardGameRepository.save(boardGame);

        Product product = new Product();
        product.setName(req.getName());
        product.setDate(req.getDate());
        product.setDescription(req.getDescription());
        product.setStockQuantity(req.getStockQuantity());
        product.setPrice(req.getPrice());
        product.setActive(true);

        product.setBoardGame(boardGame);

        if (req.getEditorsId() != null)
            product.setEditor(editorsRepository.findById(req.getEditorsId()).orElse(null));

        if (req.getAuthorsId() != null && !req.getAuthorsId().isEmpty())
            product.setListAuthors(authorsRepository.findAllById(req.getAuthorsId()));

        if (req.getCategoryId() != null && !req.getCategoryId().isEmpty())
            product.setListCategory(categoriesRepository.findAllById(req.getCategoryId()));

        productRepository.save(product);
        log.debug("Product and Board Game successfully created!");
    }// create

    @Override
    public void update(BoardGameRequest req) throws Exception {
        if (req == null)
            throw new Exception("Request cannot be null");

        if (req.getBoardGameId() == null)
            throw new Exception("Accessory ID cannot be null");

        if (req.getProductId() == null)
            throw new Exception("Product ID cannot be null");

        BoardGame boardGame = boardGameRepository.findById(req.getBoardGameId())
                .orElseThrow(() -> new Exception("Accessory not found with ID: " + req.getBoardGameId()));

        Product product = productRepository.findById(req.getProductId())
                .orElseThrow(() -> new Exception("Product not found with ID: " + req.getProductId()));

        boardGame.setMinGameTime(req.getMinGameTime());
        boardGame.setMaxGameTime(req.getMaxGameTime());
        boardGame.setMinPlayerNumber(req.getMinPlayerNumber());
        boardGame.setMaxPlayerNumber(req.getMaxPlayerNumber());
        boardGame.setMinAge(req.getMinAge());

        boardGame = boardGameRepository.save(boardGame);

        product.setName(req.getName());
        product.setDate(req.getDate());
        product.setDescription(req.getDescription());
        product.setStockQuantity(req.getStockQuantity());
        product.setPrice(req.getPrice());
        product.setActive(req.getActive());

        if (req.getEditorsId() != null)
            product.setEditor(editorsRepository.findById(req.getEditorsId()).orElse(null));

        if (req.getAuthorsId() != null && !req.getAuthorsId().isEmpty())
            product.setListAuthors(authorsRepository.findAllById(req.getAuthorsId()));

        if (req.getCategoryId() != null && !req.getCategoryId().isEmpty())
            product.setListCategory(categoriesRepository.findAllById(req.getCategoryId()));

        product.setBoardGame(boardGame);

        productRepository.save(product);

        log.debug("Board Game and Product successfully updated!");
    }// update

}// class
