package com.hqc.back.services.interfaces;


import java.util.List;

import com.hqc.back.dto.DetailsCartDTO;
import com.hqc.back.request.DetailsCartRequest;

public interface IDetailsCartsService {

    void create(DetailsCartRequest req) throws Exception;
    void update(DetailsCartRequest req) throws Exception;
    void delete(DetailsCartRequest req) throws Exception;


    List<DetailsCartDTO> list()throws Exception;
    List<DetailsCartDTO> listByCarts(Integer id) throws Exception;
    
    //per il checkout
    void deleteAllByCart(DetailsCartRequest req) throws Exception;

}
