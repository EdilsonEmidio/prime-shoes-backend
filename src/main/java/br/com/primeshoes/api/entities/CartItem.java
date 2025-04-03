package br.com.primeshoes.api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart_items")
public class CartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "product_variation_id")
	private ProductVariation procuctVariation;
	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;
	private int quantity;
	private float subtotal;
	
	public CartItem() {
		
	}
	public CartItem(long id, ProductVariation procuctVariation, Cart cart, int quantity, float subtotal) {
		super();
		this.id = id;
		this.procuctVariation = procuctVariation;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public ProductVariation getProcuctVariation() {
		return procuctVariation;
	}
	public void setProcuctVariation(ProductVariation procuctVariation) {
		this.procuctVariation = procuctVariation;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
	
	
}
