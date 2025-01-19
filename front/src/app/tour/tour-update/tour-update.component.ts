import {Component, OnInit} from '@angular/core';
import {KeyValuePipe, NgForOf} from "@angular/common";
import {NavigateDirective} from "../../navigate.directive";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {AuthService} from "../../auth/auth.service";
import {GlobalService} from "../../global.service";
import {ActivatedRoute, Router} from "@angular/router";
import {TourService} from "../tour.service";
import {EnumService} from "../../enum.service";

@Component({
	selector: 'app-tour-update',
	standalone: true,
	imports: [
		KeyValuePipe,
		NavigateDirective,
		NgForOf,
		ReactiveFormsModule
	],
	templateUrl: './tour-update.component.html',
})

export class TourUpdateComponent implements OnInit {

	id: any;

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
		private activatedRoute: ActivatedRoute,
	) {
	}

	get role() {
		return this.global.role;
	}

	ngOnInit(): void {
		this.authService.getUser().add(() => {
			if (this.global.role !== 'MANAGER') this.router.navigate(['/login']);
		})

		this.activatedRoute.queryParams.subscribe(params => {
			this.id = params['id'];
			this.tourService.find(params['id']).subscribe({
				next: (res: any) => {
					this.type = res.data.type;
					this.season = res.data.season;

					this.tourFormGroup.setValue({
						name: res.data.name,
						country: res.data.country,
						description: res.data.description,
					})
				},
				error: (e: any) => {
					console.log(e.error);
					this.router.navigate(['error'], {queryParams: {message: e.error.message}});
				}
			})
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

		return this.tourFormGroup.valid;
	}

	update() {
		this.tourService.update(this.id, this.tourFormGroup.value, this.type,this.season, this.img, this.file);
	}

}
