package br.com.primeshoes.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.primeshoes.api.entities.Product;
import br.com.primeshoes.api.entities.ProductVariation;
import br.com.primeshoes.api.entities.User;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findByUser(User user);
	
    @NativeQuery("SELECT pv.* FROM product_variations pv WHERE pv.id = :idPV")
    public Optional<ProductVariation> findProductVariation(@Param("idPV") long idPV);
    
    @NativeQuery("SELECT pv.* FROM product_variations pv WHERE pv.product_id = :idP")
    public Optional<List<ProductVariation>> findAllProductVariation(@Param("idP") long idP);
    
    @NativeQuery("SELECT p.price FROM products p WHERE p.id =:idP")
    public float findSubtotal(@Param("idP") long idP);
    
    @NativeQuery("DELETE FROM product_variations WHERE id =:idV")
    public void deleteVariant(@Param("idV") long idV);
    
    @NativeQuery("INSERT INTO product_variation (color, size, stock, product) VALUES (pv.getColor, pv.getSize, pv.getStock, pv.getProduct)")
    public ProductVariation saveVariation(@Param("idPV") ProductVariation pv);
}
