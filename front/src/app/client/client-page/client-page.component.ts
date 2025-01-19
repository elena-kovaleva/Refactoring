import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../auth/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import {GlobalService} from "../../global.service";
import {ClientService} from "../client.service";
import {NavigateDirective} from "../../navigate.directive";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {KeyValuePipe, NgForOf, NgIf} from "@angular/common";
import {AlertService} from "../../alert/alert.service";
import {EnumService} from "../../enum.service";
import {OrderingService} from "../ordering.service";
import {TourService} from "../../tour/tour.service";

@Component({
	selector: 'app-client-page',
	standalone: true,
	imports: [
		NavigateDirective,
		ReactiveFormsModule,
		NgIf,
		FormsModule,
		NgForOf,
		KeyValuePipe
	],
	templateUrl: './client-page.component.html',
})

export class ClientPageComponent implements OnInit {

	client: any = {
		fio: '',
	};

	tourTypes: any[] = [];
	clientCategories: any [] = [];
	clientGenders: any[] = [];

	constructor(
		private authService: AuthService,
		private router: Router,
		private global: GlobalService,
		private clientService: ClientService,
		private activatedRoute: ActivatedRoute,
		private alert: AlertService,
		private enumService: EnumService,
		private orderingService: OrderingService,
		private tourService: TourService,
	) {
	}

	get role() {
		return this.global.role;
	}

	private error(e: any) {
		console.log(e.error)
		this.alert.showAlertMessage(e.error.message)
	}

	ngOnInit(): void {
		this.authService.getUser().add(() => {
			if (this.role === 'NOT') this.router.navigate(['/login']);
		})

		this.activatedRoute.queryParams.subscribe(params => {
			this.clientService.find(params['id']).subscribe({
				next: (res: any) => {
					this.client = res.data;
				},
				error: (e: any) => {
					console.log(e.error)
					if (e.error.code === 404) {
						this.router.navigate(['error'], {queryParams: {message: e.error.message}});
					}
				}
			})
		})

		this.enumService.enumSubject.subscribe(value => {
			this.tourTypes = value.tourTypes;
			this.clientCategories = value.clientCategories;
			this.clientGenders = value.clientGenders;
		})
		this.enumService.getTourTypes();
		this.enumService.getClientCategories();
		this.enumService.getClientGenders();

		this.tourService.tourSubject.subscribe(value => {
			this.tours = value.tours;
		})
		this.tourService.findAll();
	}

	delete() {
		this.clientService.delete(this.client.id);
	}

	updatePassport() {
		this.clientService.updatePassport(this.client.id, this.client.passport).subscribe({
			next: (res: any) => this.client = res.data,
			error: (e: any) => this.error(e)
		})
	}

	updateCategory() {
		this.clientService.updateCategory(this.client.id, this.client.category).subscribe({
			next: (res: any) => this.client = res.data,
			error: (e: any) => this.error(e)
		})
	}

	updateTourType() {
		this.clientService.updateTourType(this.client.id, this.client.tourType).subscribe({
			next: (res: any) => this.client = res.data,
			error: (e: any) => this.error(e)
		})
	}

	updateGender() {
		this.clientService.updateGender(this.client.id, this.client.gender).subscribe({
			next: (res: any) => this.client = res.data,
			error: (e: any) => this.error(e)
		})
	}

	// Ordering

	orderingFormGroup = new FormGroup({
		date: new FormControl('', [Validators.required, Validators.minLength(1), Validators.maxLength(255)]),
		price: new FormControl(null, [Validators.required, Validators.min(0.01), Validators.max(1000000)]),
	});

	tours: any[] = [];
	tourId: any = null;

	changeTourId(event: any) {
		this.tourId = event.target.value;
	}

	checkSubmitOrdering(): boolean {
		if (this.tourId === null) return false;
		return this.orderingFormGroup.valid;
	}

	ordering() {
		this.orderingService.save(this.orderingFormGroup.value, this.client.id, this.tourId).subscribe({
			next: (res: any) => this.client.orderings.unshift(res.data),
			error: (e: any) => this.error(e)
		})
	}

	orderingDelete(id: number): void {
		this.orderingService.delete(id).subscribe({
			next: () => {
				let orderings = this.client.orderings;
				orderings = orderings.filter((i: any) => i.id !== id);
				this.client.orderings = orderings;
			},
			error: (e: any) => this.error(e)
		})
	}

}
