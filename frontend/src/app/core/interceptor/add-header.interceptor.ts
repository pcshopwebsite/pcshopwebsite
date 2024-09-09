import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable()
export class AddHeaderInterceptor implements HttpInterceptor {
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let modifiedReq = req.clone({
            headers: req.headers.set('Accept', 'application/json')
        });
        if (req.method === 'POST' || req.method === 'PUT') {
            modifiedReq = modifiedReq.clone({
                headers: modifiedReq.headers.set('Content-Type', 'application/json')
            })
        }
        return next.handle(modifiedReq);
    }

}