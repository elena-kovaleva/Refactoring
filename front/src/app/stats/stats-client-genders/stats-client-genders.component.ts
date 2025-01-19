import {Component, OnInit} from '@angular/core';
import {NgApexchartsModule} from "ng-apexcharts";
import {StatsService} from "../stats.service";

@Component({
	selector: 'app-stats-client-genders',
	standalone: true,
	imports: [
		NgApexchartsModule
	],
	templateUrl: './stats-client-genders.component.html',
})

export class StatsClientGendersComponent implements OnInit {

	chartOptions: any;

	names: any[] = [];
	values: any[] = [];

	constructor(
		private statsService: StatsService,
	) {
	}

	ngOnInit(): void {
		this.statsService.clientGenders().subscribe({
			next: (res: any) => {
				this.names = res.data.names;
				this.values = res.data.values;
				this.draw()
			}
		})
	}

	draw() {
		this.chartOptions = {
			labels: this.names,
			series: this.values,
			chart: {
				height: 400,
				type: "pie"
			},
			responsive: [
				{
					breakpoint: 480,
					options: {
						chart: {
							width: 200
						},
						legend: {
							position: "bottom"
						}
					}
				}
			]
		};
	}

}
