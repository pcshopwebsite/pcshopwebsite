import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { ComputerListComponent } from "./shop/components/computer-list/computer-list.component";

const routes: Routes = [
    {
        path: '',
        redirectTo: 'computers',
        pathMatch: 'full'
    },
    {
        path: 'computers',
        component: ComputerListComponent
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }