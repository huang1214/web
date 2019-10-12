package com.aca.springboot.service;

import com.aca.springboot.entities.Bill;
import org.springframework.stereotype.Service;


@Service
public interface AwardService {

    //添加报销
    public Bill addBill(Bill bill);

}
