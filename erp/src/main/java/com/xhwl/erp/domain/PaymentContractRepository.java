package com.xhwl.erp.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xhwl.erp.entity.PaymentContract;

public interface PaymentContractRepository extends JpaRepository<PaymentContract, Long>, JpaSpecificationExecutor<PaymentContract> {
	
	List<PaymentContract> findByOrderCode(String orderCode);
	
	@Query(" from PaymentContract pc where pc.contractInfo.business.region.id=?1")
	Page<PaymentContract> findAllByRegion(Long region_id,Pageable pageable);
	
	@Query("select p.id ,p.code from PaymentContract p where p.code like %:code%")
	List<Object[]> getCodeArrary(@Param("code") String code);
	
	@Query("select p.id ,p.code from PaymentContract p where p.code like %:code% and p.contractInfo.business.region.code = :region_code")
	List<Object[]> getCodeArrary(@Param("code") String code,@Param("region_code") String region_code);
	
	@Query("select p.id ,p.orderCode from PaymentContract p where p.orderCode like %:orderCode%")
	List<Object[]> getOrderCodeArrary(@Param("orderCode") String orderCode);
	
	@Query("select p.id ,p.orderCode from PaymentContract p where p.orderCode like %:orderCode% and p.contractInfo.business.region.code = :region_code")
	List<Object[]> getOrderCodeArrary(@Param("orderCode") String orderCode,@Param("region_code") String region_code);
	
	@Query(value = "SELECT SUM(ad_Amount) totalAdAmount,SUM(ac_Amount) totalAcAmount,SUM(billing_Amount) totalBillingAmount,SUM(payment_Amount) totalPaymentAmount, SUM(payable_Amount) totalPayableAmount FROM cost_payment_contract", nativeQuery = true)
	Object[] totalAmountArrary();
	
	@Query(value = "SELECT SUM(adAmount) totalAdAmount,SUM(acAmount) totalAcAmount,SUM(billingAmount) totalBillingAmount,SUM(paymentAmount) totalPaymentAmount, SUM(payableAmount) totalPayableAmount FROM PaymentContract p where p.contractInfo.business.region.code = :region_code", nativeQuery = true)
	Object[] totalAmountArrary(String region_code);
	
}