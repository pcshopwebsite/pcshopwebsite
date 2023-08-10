import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from "@angular/common/http";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { AppConfigService } from './core/services/app-config.service';
import { CoreModule } from './core/core.module';
import { ShoppingCartComponent } from './shop/components/shopping-cart/shopping-cart.component';

export function appInitializer(appConfigService: AppConfigService) {
  return () => {
    return appConfigService.load();
  }
}

@NgModule({
  declarations: [
    AppComponent,
    ShoppingCartComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    CoreModule
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: appInitializer,
      deps: [AppConfigService],
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
