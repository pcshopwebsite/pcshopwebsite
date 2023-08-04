import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ComputerDto } from '../../models/computer-dto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ComputerService {
  private readonly computerUrl = `${environment.api.baseUrl}/${environment.api.computerUrl}`;
  constructor(private http: HttpClient) { }

  findAll() : Observable<ComputerDto[]> {
      return this.http.get<ComputerDto[]>(this.computerUrl);
  }
}
