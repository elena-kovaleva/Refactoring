import {Component, OnInit} from '@angular/core';
import {AuthService} from "../auth/auth.service";
import {Router} from "@angular/router";
import {NgClass, NgIf} from "@angular/common";
import {NgApexchartsModule} from "ng-apexcharts";
import {FormsModule} from "@angular/forms";
import {GlobalService} from "../global.service";
import {StatsTourTypesComponent} from "./stats-tour-types/stats-tour-types.component";
import {StatsClientGendersComponent} from "./stats-client-genders/stats-client-genders.component";
import {StatsClientStatusesComponent} from "./stats-client-statuses/stats-client-statuses.component";
import {StatsClientCategoriesComponent} from "./stats-client-categories/stats-client-categories.component";
import {StatsTourSeasonsComponent} from "./stats-tour-seasons/stats-tour-seasons.component";
import {StatsTourSeasonsOrderingComponent} from "./stats-tour-seasons-ordering/stats-tour-seasons-ordering.component";
import html2canvas from "html2canvas";
import {jsPDF} from "jspdf";

@Component({
	selector: 'app-stats',
	standalone: true,
	imports: [
		NgApexchartsModule,
		NgIf,
		NgClass,
		FormsModule,
		StatsTourTypesComponent,
		StatsClientGendersComponent,
		StatsClientStatusesComponent,
		StatsClientCategoriesComponent,
		StatsTourSeasonsComponent,
		StatsTourSeasonsOrderingComponent
	],
	templateUrl: './stats.component.html',
})

export class StatsComponent implements OnInit {

	constructor(
		private authService: AuthService,
		private router: Router,
		private global: GlobalService,
	) {
	}

	ngOnInit(): void {
		this.authService.getUser().add(() => {
			if (this.global.role !== 'ADMIN') this.router.navigate(['/login']);
		})
	}

	generatePDF() {
		let data: any = document.getElementById('generatePDF');
		html2canvas(data).then(canvas => {
			const contentDataURL = canvas.toDataURL('image/png')
			let pdf = new jsPDF(
				'p',
				'cm',
				'a4'
			);
			pdf.addImage(contentDataURL, 'PNG', 1, 1, 19, 0);
			pdf.save('pdf.pdf');
		});
	}

}
