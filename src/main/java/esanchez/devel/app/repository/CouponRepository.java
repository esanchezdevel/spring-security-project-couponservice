package esanchez.devel.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import esanchez.devel.app.model.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long>{

	Coupon findByCode(String code);
}
