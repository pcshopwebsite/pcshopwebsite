import { Injectable } from '@angular/core';
import { AppConfig } from '../models/app-config.model';
import { Subject } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AppConfigService {

  static settings: AppConfig;
  private readonly _configurationChanged$ = new Subject<AppConfig>();
  readonly configurationChanged$ = this._configurationChanged$.asObservable();
  constructor() {
  }

  async load() {
    const jsonFile = `${environment.configurationUrl}?v=${Date.now()}`;
    const response = await fetch(jsonFile);
    AppConfigService.settings = await response.json();
    console.log('AppConfigService.settings', AppConfigService.settings);
    this._configurationChanged$.next(AppConfigService.settings);
  }
}
