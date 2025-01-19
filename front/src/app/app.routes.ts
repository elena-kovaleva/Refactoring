import {Routes} from '@angular/router';
import {MainComponent} from "./main/main.component";
import {LoginComponent} from "./auth/login/login.component";
import {RegComponent} from "./auth/reg/reg.component";
import {UserComponent} from "./user/user.component";
import {ErrorComponent} from "./error/error.component";
import {StatsComponent} from "./stats/stats.component";
import {TourPageComponent} from "./tour/tour-page/tour-page.component";
import {TourComponent} from "./tour/tour.component";
import {TourAddComponent} from "./tour/tour-add/tour-add.component";
import {TourUpdateComponent} from "./tour/tour-update/tour-update.component";
import {ClientPageComponent} from "./client/client-page/client-page.component";
import {ClientComponent} from "./client/client.component";
import {ClientAddComponent} from "./client/client-add/client-add.component";
import {ClientUpdateComponent} from "./client/client-update/client-update.component";

export const routes: Routes = [
	{path: "", component: MainComponent},

	{path: "reg", component: RegComponent},
	{path: "login", component: LoginComponent},

	{path: "users", component: UserComponent},

	{path: "tour", component: TourPageComponent},
	{path: "tours", component: TourComponent},
	{path: "tour_add", component: TourAddComponent},
	{path: "tour_update", component: TourUpdateComponent},

	{path: "client", component: ClientPageComponent},
	{path: "clients", component: ClientComponent},
	{path: "client_add", component: ClientAddComponent},
	{path: "client_update", component: ClientUpdateComponent},

	{path: "stats", component: StatsComponent},

	{path: "error", component: ErrorComponent},
	{path: "**", component: ErrorComponent},
];
