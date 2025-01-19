import {Component, OnInit} from '@angular/core';
import {KeyValuePipe, NgForOf} from "@angular/common";
import {NavigateDirective} from "../../navigate.directive";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {AuthService} from "../../auth/auth.service";
import {GlobalService} from "../../global.service";
import {Router} from "@angular/router";
import {TourService} from "../../tour/tour.service";
import {EnumService} from "../../enum.service";
import {ClientService} from "../client.service";

@Component({
	selector: 'app-client-add',
	standalone: true,
	imports: [
		KeyValuePipe,
		NavigateDirective,
		NgForOf,
		ReactiveFormsModule
	],
	templateUrl: './client-add.component.html',
})

export class ClientAddComponent implements OnInit{

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
	) {
	}

	get role() {
		return this.global.role;
	}

	ngOnInit(): void {
		this.authService.getUser().add(() => {
			if (this.global.role !== 'MANAGER') this.router.navigate(['/login']);
		})
	}

	save() {
		this.clientService.save(this.clientFormGroup.value);
	}

}
