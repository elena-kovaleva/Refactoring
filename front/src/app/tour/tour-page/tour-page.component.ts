import {Component, OnInit} from '@angular/core';
import {KeyValuePipe, NgForOf, NgIf} from "@angular/common";
import {NavigateDirective} from "../../navigate.directive";
import {ReactiveFormsModule} from "@angular/forms";
import {AuthService} from "../../auth/auth.service";
import {GlobalService} from "../../global.service";
import {TourService} from "../tour.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
	selector: 'app-tour-page',
	standalone: true,
	imports: [
		KeyValuePipe,
		NavigateDirective,
		NgForOf,
		ReactiveFormsModule,
		NgIf
	],
	templateUrl: './tour-page.component.html',
})

export class TourPageComponent implements OnInit {

	tour: any = {
		name: '',
	};

	constructor(
		private authService: AuthService,
		private global: GlobalService,
		private tourService: TourService,
		private activatedRoute: ActivatedRoute,
		private router: Router,
	) {
	}

	get role() {
		return this.global.role;
	}

	ngOnInit(): void {
		this.authService.getUser();

		this.activatedRoute.queryParams.subscribe(params => {
			this.tourService.find(params['id']).subscribe({
				next: (res: any) => {
					this.tour = res.data;
				},
				error: (e: any) => {
					console.log(e.error);
					if (e.error.code === 404) {
						this.router.navigate(['error'], {queryParams: {message: e.error.message}});
					}
				}
			})
		})
	}

	delete() {
		this.tourService.delete(this.tour.id);
	}
}
