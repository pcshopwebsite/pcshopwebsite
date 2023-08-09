import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ComputerService } from '../../services/computer.service';
import { ComputerDto } from 'src/app/shop/models/computer-dto';
import { Observable } from 'rxjs';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-computer-list',
  standalone: true,
  imports: [CommonModule, MatIconModule, MatButtonModule],
  templateUrl: './computer-list.component.html',
  styleUrls: ['./computer-list.component.scss']
})
export class ComputerListComponent implements OnInit {

  computers$: Observable<ComputerDto[]> | undefined;

  constructor(private computerService: ComputerService) { }

  ngOnInit(): void {
    this.computers$ = this.computerService.findAll();
  }
}
