package br.com.primeshoes.api.entities;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;

import br.com.primeshoes.api.enuns.PaymentMethod;
import br.com.primeshoes.api.enuns.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	private float amount;
	@CreatedDate
	private Instant createdAt;
	
	public Payment() {
	    
	}
	public Payment(long id, Order order, PaymentStatus paymentStatus, PaymentMethod paymentMethod, float amount,
		Instant createdAt) {
	    super();
	    this.id = id;
	    this.order = order;
	    this.paymentStatus = paymentStatus;
	    this.paymentMethod = paymentMethod;
	    this.amount = amount;
	    this.createdAt = createdAt;
	}
	public long getId() {
	    return id;
	}
	public void setId(long id) {
	    this.id = id;
	}
	public Order getOrder() {
	    return order;
	}
	public void setOrder(Order order) {
	    this.order = order;
	}
	public PaymentStatus getPaymentStatus() {
	    return paymentStatus;
	}
	public void setPaymentStatus(PaymentStatus paymentStatus) {
	    this.paymentStatus = paymentStatus;
	}
	public PaymentMethod getPaymentMethod() {
	    return paymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
	    this.paymentMethod = paymentMethod;
	}
	public float getAmount() {
	    return amount;
	}
	public void setAmount(float amount) {
	    this.amount = amount;
	}
	public Instant getCreatedAt() {
	    return createdAt;
	}
	public void setCreatedAt(Instant createdAt) {
	    this.createdAt = createdAt;
	}
	
	
	
}
