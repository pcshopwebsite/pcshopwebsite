import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ComputerDto } from '../models/computer-dto';
import { Observable } from 'rxjs';
import { AppConfigService } from 'src/app/core/services/app-config.service';

@Injectable({
  providedIn: 'root'
})
export class ComputerService {
  private readonly computerUrl = `${AppConfigService.settings.api.baseUrl}/${AppConfigService.settings.api.computerUrl}`;
  constructor(private http: HttpClient) { }
  
  findAll() : Observable<ComputerDto[]> {
    return this.http.get<ComputerDto[]>(this.computerUrl);
  }
}
