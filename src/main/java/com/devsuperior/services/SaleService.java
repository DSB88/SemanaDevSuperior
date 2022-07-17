package com.devsuperior.services;

import com.devsuperior.entities.Sale;
import com.devsuperior.repositories.SaleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class SaleService {

    private final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public Page<Sale> findSales(String minDate, String maxDate, Pageable pageable){

        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        LocalDate min = minDate.equals("") ? today.minusDays(365) : LocalDate.parse(minDate);
        LocalDate max = minDate.equals("") ? today : LocalDate.parse(maxDate);
        return saleRepository.findSales(min,max,pageable);
    }

    public Optional<Sale> findById(Long id) {
        Optional<Sale> saleId = checkIfEntityIsValid(id);
        return saleId;
    }

    private Optional<Sale> checkIfEntityIsValid(Long id) {
        Optional<Sale> saleIdIsValid = saleRepository.findById(id);

        if (!saleIdIsValid.isPresent()) {
            throw new EntityNotFoundException("Id Not Found!");
        }

        return saleIdIsValid;
    }
}
