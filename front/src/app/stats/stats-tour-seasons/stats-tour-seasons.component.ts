import {Component, OnInit} from '@angular/core';
import {NgApexchartsModule} from "ng-apexcharts";
import {StatsService} from "../stats.service";

@Component({
	selector: 'app-stats-tour-seasons',
	standalone: true,
	imports: [
		NgApexchartsModule
	],
	templateUrl: './stats-tour-seasons.component.html',
})

export class StatsTourSeasonsComponent implements OnInit{

	chartOptions: any;

	names: any[] = [];
	values: any[] = [];

	constructor(
		private statsService: StatsService,
	) {
	}

	ngOnInit(): void {
		this.statsService.tourSeasons().subscribe({
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
