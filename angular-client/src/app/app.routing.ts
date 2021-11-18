import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {AddUserComponent} from "./components/user/add-user/add-user.component";
import {ListUserComponent} from "./components/user/list-user/list-user.component";
import {EditUserComponent} from "./components/user/edit-user/edit-user.component";

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'add-user', component: AddUserComponent },
  { path: 'list-user', component: ListUserComponent },
  { path: 'edit-user', component: EditUserComponent },
  { path: '', component: ListUserComponent },
];

export const routing = RouterModule.forRoot(routes);
