package org.restaurant.repository;

import org.restaurant.entity.DailyMoney;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface DailyMoneyRepository extends JpaRepository<DailyMoney, Long> {
}
