import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CdkStepper, CdkStepperModule } from '@angular/cdk/stepper';

@Component({
  selector: 'app-layout',
  standalone: true,
  imports: [CommonModule, CdkStepperModule, ],
  providers: [
    {provide: CdkStepper, useExisting: LayoutComponent}
  ],
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent extends CdkStepper {
  selectStepByIndex(index: number) {
    this.selectedIndex = index;
  }
}
