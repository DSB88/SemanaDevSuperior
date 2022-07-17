package com.devsuperior.controllers;

import com.devsuperior.entities.Sale;
import com.devsuperior.services.SaleService;
import com.devsuperior.services.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

//    private final SaleService saleService;

    @Autowired
    private SaleService saleService;
    @Autowired
    private SmsService smsService;

//    public SaleController(SaleService saleService){
//        this.saleService = saleService;
//    }

    @GetMapping
    public Page<Sale> findSales(@RequestParam(value="minDate", defaultValue = "") String minDate,
                                @RequestParam(value="maxDate", defaultValue = "") String maxDate,
                                Pageable pageable){
        return saleService.findSales(minDate, maxDate, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Sale>> obterPorId(@PathVariable Long id){
        Optional<Sale> sale = saleService.findById(id);
        return ResponseEntity.ok(sale);
    }

    @GetMapping("/{id}/notification")
    public void notifySms(@PathVariable Long id){
         smsService.sendSms(id);
    }

}
