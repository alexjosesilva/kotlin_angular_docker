package org.example.Controller

import org.example.Entity.Product
import org.example.Repository.ProductRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping
@CrossOrigin("http://localhost:4200")
class ProductController(private val productRepository: ProductRepository) {

    @GetMapping("/api/products/all")
    fun getAllProducts(): Iterable<Product> {
        return productRepository.findAll()
    }

    @GetMapping("/api/products/{id}")
    fun getProductById(@PathVariable id: Long): ResponseEntity<Product> {
        val product = productRepository.findById(id)
        return if (product.isPresent) {
            ResponseEntity.ok(product.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/api/products")
    fun createProduct(@RequestBody product: Product): Product {
        return productRepository.save(product)
    }

    @PutMapping("/api/products/{id}")
    fun updateProduct(@PathVariable id: Long, @RequestBody updatedProduct: Product): ResponseEntity<Product> {
        return if (productRepository.existsById(id)) {
            updatedProduct.id = id
            ResponseEntity.ok(productRepository.save(updatedProduct))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/api/products/{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<Void> {
        return if (productRepository.existsById(id)) {
            productRepository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}