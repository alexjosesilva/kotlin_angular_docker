// product.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private baseUrl = 'http://localhost:8080/api/products';

  constructor(private http: HttpClient) {}

  getAllProducts(): Observable<any> {
    return this.http.get(this.baseUrl+"/all");
  }

  createProduct(product: any): Observable<any> {
    return this.http.post(this.baseUrl, product);
  }

  deleteProduct(productId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${productId}`);
  }

  updateProduct(productId: number, product: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/${productId}`, product);
  }
}
