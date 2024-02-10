// product-list.component.ts

import { Component, OnInit } from '@angular/core';
import { ProductService } from './../../services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit {
  products: any[] = [];
  newProduct: any = {};
  updatedProduct: any = {};

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.productService.getAllProducts().subscribe((data: any) => {
      this.products = data.map((product: any) => ({ ...product, isUpdating: false }));
    });
  }

  toggleUpdateForm(product: any): void {
    product.isUpdating = !product.isUpdating; // Alterna o valor da propriedade isUpdating
    if (!product.isUpdating) {
      // Limpa os campos de atualização se o formulário for ocultado
      product.updatedName = '';
      product.updatedPrice = '';
      product.updatedModel = '';
      product.updatedCor = '';
    }
  }

  addProduct(): void {
    this.productService.createProduct(this.newProduct).subscribe(() => {
      this.loadProducts();
      this.newProduct = {};
    });
  }

  deleteProduct(productId: number): void {
    this.productService.deleteProduct(productId).subscribe(() => {
      this.loadProducts();
    });
  }

  updateProduct(productId:number, product: any): void {
    this.productService.updateProduct(productId, product).subscribe(() => {
      this.loadProducts();
    });
  }
}
