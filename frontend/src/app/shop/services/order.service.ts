import { Injectable, signal } from '@angular/core';
import { AppConfigService } from 'src/app/core/services/app-config.service';
import { OrderDto } from '../models/order-dto';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private readonly orderServiceUrl = `${AppConfigService.settings.api.baseUrl}/${AppConfigService.settings.api.orderUrl}`;
  public order = signal<OrderDto>({});
  
  constructor() { }

}
