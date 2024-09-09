import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { FileUploadComponent } from "./file-upload/file-upload.component";

const routes: Routes = [
    {
        path: '',
        redirectTo: 'shop',
        pathMatch: 'full'
    },
    {
        path: 'shop',
        loadChildren: () => import('./shop/shop.module').then(m => m.ShopModule)
    },
    {
        path: 'file-upload',
        component: FileUploadComponent
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }