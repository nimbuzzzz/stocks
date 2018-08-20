package com.assignment.stocks.repositories;

import com.assignment.stocks.domain.Stock;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StocksRepository extends PagingAndSortingRepository<Stock, Long> {
}
