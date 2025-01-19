import {Component, OnInit} from '@angular/core';
import {AuthService} from "../auth/auth.service";
import {GlobalService} from "../global.service";
import {TourService} from "./tour.service";
import {NavigateDirective} from "../navigate.directive";
import {EnumService} from "../enum.service";
import {FormsModule} from "@angular/forms";
import {KeyValuePipe, NgForOf, NgIf} from "@angular/common";

@Component({
	selector: 'app-tour',
	standalone: true,
	imports: [
		NavigateDirective,
		FormsModule,
		KeyValuePipe,
		NgForOf,
		NgIf
	],
	templateUrl: './tour.component.html',
})

export class TourComponent implements OnInit {

	tours: any[] = [];
	types: any[] = [];
	seasons: any[] = [];

	filterName: string = '';
	filterCountry: string = '';
	filterSeason: string = '';
	filterType: string = '';

	get toursSorted() {
		let res = this.tours;

		if (this.filterName !== '') res = res.filter((i: any) => i.name.toLowerCase().includes(this.filterName.toLowerCase()));
		if (this.filterCountry !== '') res = res.filter((i: any) => i.country.toLowerCase().includes(this.filterCountry.toLowerCase()));

		if (this.filterSeason !== '') res = res.filter((i: any) => i.season === this.filterSeason);
		if (this.filterType !== '') res = res.filter((i: any) => i.type === this.filterType);

		return res;
	}

	constructor(
		private authService: AuthService,
		private global: GlobalService,
		private tourService: TourService,
		private enumService: EnumService,
	) {
	}

	get role() {
		return this.global.role;
	}

	ngOnInit(): void {
		this.authService.getUser();

		this.tourService.tourSubject.subscribe(value => {
			this.tours = value.tours;
		})
		this.tourService.findAll();

		this.enumService.enumSubject.subscribe(value => {
			this.types = value.tourTypes;
			this.seasons = value.tourSeasons;
		})
		this.enumService.getTourTypes();
		this.enumService.getTourSeasons();
	}

}
