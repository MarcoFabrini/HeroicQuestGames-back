package com.hqc.beck.services.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hqc.beck.dto.PayCardsDTO;
import com.hqc.beck.model.PayCards;
import com.hqc.beck.model.Users;
import com.hqc.beck.repository.IPayCardsRepository;
import com.hqc.beck.repository.UsersRepository;
import com.hqc.beck.request.PayCardsRequest;
import com.hqc.beck.services.interfaces.IPayCardsService;
import static com.hqc.beck.utils.Utilities.buildPayCardsDTO;

@Service
public class PayCardsImplementation implements IPayCardsService {
    @Autowired
    IPayCardsRepository payCardsRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    Logger log;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<PayCardsDTO> listByUserId(Integer id) throws Exception {
        Optional<Users> user = usersRepository.findById(id);
        if (!user.isPresent())
            throw new Exception("user-noPresent");

        return buildPayCardsDTO(user.get().getListPayCards());
    }// listByUser

    @Override
    public void create(PayCardsRequest req) throws Exception {
        Optional<Users> user = usersRepository.findById(req.getUserId());
        if (!user.isPresent())
            throw new Exception("user-noPresent");

        PayCards pc = new PayCards();
        pc.setCardNumber(req.getCardNumber());
        pc.setCardHolderName(req.getCardHolderName());
        pc.setExpirationDate(req.getExpirationDate());
        pc.setUser(user.get());
        pc.setActive(true);

        payCardsRepository.save(pc);
    }// create

    @Override
    public void update(PayCardsRequest req) throws Exception {
        Optional<PayCards> pc = payCardsRepository.findById(req.getId());
        if (!pc.isPresent())
            throw new Exception("payCard-noPresent");

        pc.get().setCardNumber(req.getCardNumber());
        pc.get().setCardHolderName(req.getCardHolderName());
        pc.get().setExpirationDate(req.getExpirationDate());
        pc.get().setActive(true);

        payCardsRepository.save(pc.get());
    }// update

    @Override
    public void delete(Integer id) throws Exception {
        Optional<PayCards> pc = payCardsRepository.findById(id);
        if (!pc.isPresent())
            throw new Exception("payCard-noPresent");

        pc.get().setActive(false);
        payCardsRepository.save(pc.get());
    }// delete

}// class
