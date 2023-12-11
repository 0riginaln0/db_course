package com.example.db_course.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.db_course.exception.handlers.ForbiddenRequestException;
import com.example.db_course.model.Good;
import com.example.db_course.model.Sale;
import com.example.db_course.model.Warehouse1;
import com.example.db_course.model.Warehouse2;
import com.example.db_course.repository.GoodRepository;
import com.example.db_course.repository.SaleRepository;
import com.example.db_course.repository.Warehouse1Repository;
import com.example.db_course.repository.Warehouse2Repository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;
    private final GoodRepository goodRepository;
    private final Warehouse1Repository warehouse1Repository;
    private final Warehouse2Repository warehouse2Repository;

    public List<Sale> getAllSales() {
        return (List<Sale>) saleRepository.findAll();
    }

    public List<Sale> deleteSale(Sale toDeleteSale) {
        Sale returnSale = saleRepository.findById(toDeleteSale.getSaleId())
                .orElseThrow(() -> new EntityNotFoundException(
                    "Sale with id " + toDeleteSale.getSaleId() + " is not found"
                    ));
        saleRepository.deleteById(toDeleteSale.getSaleId());
        List<Sale> returnValue = new ArrayList<>();
        returnValue.add(returnSale);
        return returnValue;
    }

    public List<Sale> addNewSale(Sale newSale) {
        Good good = goodRepository.findById(newSale.getGood().getGoodId())
                .orElseThrow(() -> new EntityNotFoundException(
                    "Good with id " + newSale.getGood().getGoodId() + " is not found"
                    ));
        newSale.setGood(good);
        System.out.println(newSale.getCreateDate());
        System.out.println("Preparing adding new sale");
        // Со склада удаляем то что надо
        Warehouse1 wh1 = warehouse1Repository.getWarehouse1ByGood(good);
        Integer count = newSale.getGoodCount();
        if (wh1 != null) {
            Integer wh1Count = wh1.getGoodCount();
            if (wh1Count >= count) {
                wh1.setGoodCount(wh1Count - count);
                warehouse1Repository.save(wh1);
            } else {
                wh1.setGoodCount(0);
                warehouse1Repository.save(wh1);
                Warehouse2 wh2 = warehouse2Repository.getWarehouse2ByGood(good);
                Integer wh2Count = wh2.getGoodCount();
                wh2.setGoodCount(wh2Count - (count - wh1Count));
                warehouse2Repository.save(wh2);
            }
        } else {
            Warehouse2 wh2 = warehouse2Repository.getWarehouse2ByGood(good);
            if (wh2 == null) {
                throw new EntityNotFoundException(
                    "Good with id " + newSale.getGood().getGoodId() + " is not in stock");
            }
            Integer wh2Count = wh2.getGoodCount();
            wh2.setGoodCount(wh2Count - count);
            warehouse2Repository.save(wh2);
        }
        
        Sale addedSale = saleRepository.save(newSale);
        System.out.println("Added new sale");
        List<Sale> returnValue = new ArrayList<>();
        returnValue.add(addedSale);
        return returnValue;
    }

    public List<Sale> updateSale(Sale toUpdateSale) {
        Sale oldSale = saleRepository.findById(toUpdateSale.getSaleId())
                .orElseThrow(() -> new EntityNotFoundException(
                    "Sale with id " + toUpdateSale.getSaleId() + " is not found"
                    ));
        
        if (toUpdateSale.getCreateDate() == null) {
            toUpdateSale.setCreateDate(oldSale.getCreateDate());
        }
        if (toUpdateSale.getGood() == null || 
            toUpdateSale.getGood().getGoodId().equals(oldSale.getGood().getGoodId())) {
            
            if (toUpdateSale.getGoodCount() == null ||
                toUpdateSale.getGoodCount().equals(oldSale.getGoodCount())) {
                toUpdateSale.setGoodCount(oldSale.getGoodCount());
                saleRepository.save(toUpdateSale);
            } else {
                if (toUpdateSale.getGoodCount() < 1) {
                    throw new ForbiddenRequestException("Count can't be less than one");
                }
                Integer difference = toUpdateSale.getGoodCount() - oldSale.getGoodCount();
                Warehouse2 updatedWarehouse = warehouse2Repository.getWarehouse2ByGood(oldSale.getGood());
                
                if (difference < 0) {
                    updatedWarehouse.setGoodCount(updatedWarehouse.getGoodCount() - difference);
                    warehouse2Repository.save(updatedWarehouse);
                } else if (difference <= updatedWarehouse.getGoodCount()) {
                    updatedWarehouse.setGoodCount(updatedWarehouse.getGoodCount() - difference);
                    warehouse2Repository.save(updatedWarehouse);
                } else {
                    throw new ForbiddenRequestException("Not enough goods in warehouse");
                }
                saleRepository.save(toUpdateSale);
            }
        } else if (!toUpdateSale.getGood().getGoodId().equals(oldSale.getGood().getGoodId())) {
            Warehouse2 oldWh2 = warehouse2Repository.getWarehouse2ByGood(oldSale.getGood());
            Integer quantityToAdd = oldSale.getGoodCount();
            oldWh2.setGoodCount(oldWh2.getGoodCount() + quantityToAdd);


            addNewSale(toUpdateSale);
            warehouse2Repository.save(oldWh2);
        }
        List<Sale> returnValue = new ArrayList<>();
        returnValue.add(toUpdateSale);
        return returnValue;
    }
}
