import {Component, OnInit} from '@angular/core';
import {NavigateDirective} from "../../navigate.directive";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {AuthService} from "../../auth/auth.service";
import {GlobalService} from "../../global.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ClientService} from "../client.service";

@Component({
	selector: 'app-client-update',
	standalone: true,
	imports: [
		NavigateDirective,
		ReactiveFormsModule
	],
	templateUrl: './client-update.component.html',
})

export class ClientUpdateComponent implements OnInit {

	id: any;

	clientFormGroup = new FormGroup({
		fio: new FormControl('', [Validators.required, Validators.minLength(1), Validators.maxLength(255)]),
		tel: new FormControl('', [Validators.required, Validators.minLength(1), Validators.maxLength(255)]),
		description: new FormControl('', [Validators.required, Validators.minLength(1), Validators.maxLength(5000)]),
	});

	constructor(
		private authService: AuthService,
		private global: GlobalService,
		private router: Router,
		private clientService: ClientService,
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
			this.clientService.find(params['id']).subscribe({
				next: (res: any) => {
					this.clientFormGroup.setValue({
						fio: res.data.fio,
						tel: res.data.tel,
						description: res.data.description,
					});
				},
				error: (e: any) => {
					console.log(e.error)
					if (e.error.code === 404) {
						this.router.navigate(['error'], {queryParams: {message: e.error.message}});
					}
				}
			})
		})

	}

	update() {
		this.clientService.update(this.id, this.clientFormGroup.value);
	}

}
