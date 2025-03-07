package com.hqc.beck.services.interfaces;


import java.util.List;

import com.hqc.beck.dto.DetailsCartDTO;
import com.hqc.beck.request.DetailsCartRequest;

public interface IDetailsCartsService {

    void create(DetailsCartRequest req) throws Exception;
    void update(DetailsCartRequest req) throws Exception;
    void delete(DetailsCartRequest req) throws Exception;


    List<DetailsCartDTO> list()throws Exception;
    List<DetailsCartDTO> listByCarts(Integer id) throws Exception;
    
    //per il checkout
    void deleteAllByCart(DetailsCartRequest req) throws Exception;

}
