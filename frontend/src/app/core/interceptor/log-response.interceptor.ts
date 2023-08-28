import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from "@angular/common/http";
import { Observable, tap } from "rxjs";

export class LogResponseInterceptor implements HttpInterceptor {
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        console.log(`LogResponseInterceptor - ${req.url}`);
        return next.handle(req).pipe(
            tap((event: HttpEvent<any>) => {
                console.log(`LogResponseInterceptor - ${req.url} - ${event.type}`);
                console.log(event);
            })
        );
    }

}