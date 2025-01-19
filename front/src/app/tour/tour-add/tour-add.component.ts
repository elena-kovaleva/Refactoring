import {Component, OnInit} from '@angular/core';
import {NavigateDirective} from "../../navigate.directive";
import {AuthService} from "../../auth/auth.service";
import {GlobalService} from "../../global.service";
import {TourService} from "../tour.service";
import {Router} from "@angular/router";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {EnumService} from "../../enum.service";
import {KeyValuePipe, NgForOf} from "@angular/common";

@Component({
	selector: 'app-tour-add',
	standalone: true,
	imports: [
		NavigateDirective,
		ReactiveFormsModule,
		NgForOf,
		KeyValuePipe
	],
	templateUrl: './tour-add.component.html',
})

export class TourAddComponent implements OnInit {

	tourFormGroup = new FormGroup({
		name: new FormControl('', [Validators.required, Validators.minLength(1), Validators.maxLength(255)]),
		country: new FormControl('', [Validators.required, Validators.minLength(1), Validators.maxLength(255)]),
		description: new FormControl('', [Validators.required, Validators.minLength(1), Validators.maxLength(5000)]),
	});

	type: any = null;
	types: any[] = [];

	season: any = null;
	seasons: any[] = [];

	img: any = null;

	file: any = null;

	constructor(
		private authService: AuthService,
		private global: GlobalService,
		private router: Router,
		private tourService: TourService,
		private enumService: EnumService,
	) {
	}

	get role() {
		return this.global.role;
	}

	ngOnInit(): void {
		this.authService.getUser().add(() => {
			if (this.global.role !== 'MANAGER') this.router.navigate(['/login']);
		})

		this.enumService.enumSubject.subscribe(value => {
			this.types = value.tourTypes;
			this.seasons = value.tourSeasons;
		})
		this.enumService.getTourTypes();
		this.enumService.getTourSeasons();
	}

	changeType(event: any) {
		this.type = event.target.value;
	}

	changeSeason(event: any) {
		this.season = event.target.value;
	}

	changeImg(event: any) {
		this.img = event.target.files;
	}

	changeFile(event: any) {
		this.file = event.target.files;
	}

	checkSubmit() {
		if (this.type === null) return false;
		if (this.season === null) return false;
		if (this.img === null) return false;
		if (this.file === null) return false;

		return this.tourFormGroup.valid;
	}

	save() {
		this.tourService.save(this.tourFormGroup.value, this.type, this.season, this.img, this.file);
	}

}
