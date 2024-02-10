package controler

import entity.Product
import org.crud.kotlin.repository.ProductRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/products")
class ProductController(private val productRepository: ProductRepository) {

    @GetMapping
    fun getAllProducts(): Iterable<Product> {
        return productRepository.findAll()
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long): ResponseEntity<Product> {
        val product = productRepository.findById(id)
        return if (product.isPresent) {
            ResponseEntity.ok(product.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createProduct(@RequestBody product: Product): Product {
        return productRepository.save(product)
    }

    @PutMapping("/{id}")
    fun updateProduct(@PathVariable id: Long, @RequestBody updatedProduct: Product): ResponseEntity<Product> {
        return if (productRepository.existsById(id)) {
            updatedProduct.id = id
            ResponseEntity.ok(productRepository.save(updatedProduct))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<Void> {
        return if (productRepository.existsById(id)) {
            productRepository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}