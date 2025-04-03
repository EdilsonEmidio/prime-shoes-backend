package br.com.primeshoes.api.entities;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_itens") 
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	@OneToMany
	@JoinColumn(name = "product_variation_id")
	private ProductVariation productVariation;
	private int quantity;
	private float subtotal;
	
	public OrderItem() {
	    
	}
	
	public OrderItem(long id, Order order, ProductVariation productVariation, int quantity, float subtotal) {
	    super();
	    this.id = id;
	    this.order = order;
	    this.productVariation = productVariation;
	    this.quantity = quantity;
	    this.subtotal = subtotal;
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
	public ProductVariation getProductVariation() {
	    return productVariation;
	}
	public void setProductVariation(ProductVariation productVariation) {
	    this.productVariation = productVariation;
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
