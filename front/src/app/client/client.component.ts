import {Component, OnInit} from '@angular/core';
import {AuthService} from "../auth/auth.service";
import {Router} from "@angular/router";
import {GlobalService} from "../global.service";
import {KeyValuePipe, NgForOf, NgIf} from "@angular/common";
import {NavigateDirective} from "../navigate.directive";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ClientService} from "./client.service";

@Component({
	selector: 'app-client',
	standalone: true,
	imports: [
		KeyValuePipe,
		NavigateDirective,
		NgForOf,
		ReactiveFormsModule,
		NgIf,
		FormsModule
	],
	templateUrl: './client.component.html',
})

export class ClientComponent implements OnInit {

	clients: any[] = [];

	filterFio: string = '';
	filterTel: string = '';

	get clientsSorted() {
		let res = this.clients;

		if (this.filterFio !== '') res = res.filter((i: any) => i.fio.toLowerCase().includes(this.filterFio.toLowerCase()));
		if (this.filterTel !== '') res = res.filter((i: any) => i.tel.toLowerCase().includes(this.filterTel.toLowerCase()));

		return res;
	}

	constructor(
		private authService: AuthService,
		private router: Router,
		private global: GlobalService,
		private clientService: ClientService,
	) {
	}

	get role() {
		return this.global.role;
	}

	ngOnInit(): void {
		this.authService.getUser().add(() => {
			if (this.role === 'NOT') this.router.navigate(['/login']);
		})

		this.clientService.clientSubject.subscribe(value => {
			this.clients = value.clients;
		})
		this.clientService.findAll();
	}

}
