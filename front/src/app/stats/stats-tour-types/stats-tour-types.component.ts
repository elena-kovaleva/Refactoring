import {Component, OnInit} from '@angular/core';
import {StatsService} from "../stats.service";
import {NgApexchartsModule} from "ng-apexcharts";

@Component({
	selector: 'app-stats-tour-types',
	standalone: true,
	imports: [
		NgApexchartsModule
	],
	templateUrl: './stats-tour-types.component.html',
})

export class StatsTourTypesComponent implements OnInit {

	chartOptions: any;

	names: any[] = [];
	values: any[] = [];

	constructor(
		private statsService: StatsService,
	) {
	}

	ngOnInit(): void {
		this.statsService.tourTypes().subscribe({
			next: (res: any) => {
				this.names = res.data.names;
				this.values = res.data.values;
				this.draw()
			}
		})
	}

	draw() {
		this.chartOptions = {
			series: [
				{
					name: "Количество",
					data: this.values
				}
			],
			chart: {
				height: 400,
				type: "bar"
			},
			plotOptions: {
				bar: {
					dataLabels: {
						position: "top" // top, center, bottom
					}
				}
			},
			dataLabels: {
				enabled: true,
				formatter: function (val:any) {
					return val;
				},
				offsetY: -20,
				style: {
					fontSize: "12px",
					colors: ["#304758"]
				}
			},
			xaxis: {
				categories: this.names,
				position: "top",
				labels: {
					offsetY: -18
				},
				axisBorder: {
					show: false
				},
				axisTicks: {
					show: false
				},
				crosshairs: {
					fill: {
						type: "gradient",
						gradient: {
							colorFrom: "#D8E3F0",
							colorTo: "#BED1E6",
							stops: [0, 100],
							opacityFrom: 0.4,
							opacityTo: 0.5
						}
					}
				},
				tooltip: {
					enabled: true,
					offsetY: -35
				}
			},
			yaxis: {
				axisBorder: {
					show: false
				},
				axisTicks: {
					show: false
				},
				labels: {
					show: false,
					formatter: function (val: any) {
						return val;
					}
				}
			},
		};
	}

}
