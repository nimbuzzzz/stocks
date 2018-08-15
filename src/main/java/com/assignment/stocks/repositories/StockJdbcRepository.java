package com.assignment.stocks.repositories;

import com.assignment.stocks.domain.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface StockJdbcRepository extends JpaRepository<Stock, Long> {
}
