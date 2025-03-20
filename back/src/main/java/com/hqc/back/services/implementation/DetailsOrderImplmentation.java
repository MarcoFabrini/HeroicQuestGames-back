package com.hqc.back.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqc.back.dto.DetailsOrderDTO;
import com.hqc.back.model.DetailsOrder;
import com.hqc.back.model.Orders;
import com.hqc.back.repository.IDetailsOrderRepository;
import com.hqc.back.repository.IOrdersRepository;
import com.hqc.back.services.interfaces.IDetailsOrderService;
import static com.hqc.back.utils.Utilities.buildDetailsOrderDTO;

@Service
public class DetailsOrderImplmentation implements IDetailsOrderService {
    @Autowired
    IDetailsOrderRepository detOrderRep;

    @Autowired
    IOrdersRepository orderRep;

    // stampa i dettagli dell'ordine selezionato
    @Override
    public List<DetailsOrderDTO> searchByOrder(Integer id) throws Exception {
        Optional<Orders> order = orderRep.findById(id);
        if (order.isEmpty()) {
            throw new Exception("order-noPresent");
        }

        List<DetailsOrder> listaDettagli = order.get().getListDetailsOrder();
        return buildDetailsOrderDTO(listaDettagli);
    }

}
