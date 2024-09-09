import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { AppConfigService } from './core/services/app-config.service';
import { CoreModule } from './core/core.module';
import { AddHeaderInterceptor } from './core/interceptor/add-header.interceptor';
import { LogResponseInterceptor } from './core/interceptor/log-response.interceptor';
// import filepond module
import { FilePondModule, registerPlugin } from 'ngx-filepond';
// import and register filepond file type validation plugin
import FilePondPluginFileValidateType from 'filepond-plugin-file-validate-type';
registerPlugin(FilePondPluginFileValidateType);

export function appInitializer(appConfigService: AppConfigService) {
  return () => {
    return appConfigService.load();
  }
}

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    CoreModule,
    FilePondModule,
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: appInitializer,
      deps: [AppConfigService],
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AddHeaderInterceptor,
      multi: true // Interceptor can be chained with other interceptors
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: LogResponseInterceptor,
      multi: true // Interceptor can be chained with other interceptors
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
