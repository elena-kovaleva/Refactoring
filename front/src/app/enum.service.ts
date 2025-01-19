import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject} from "rxjs";
import {GlobalService} from "./global.service";

@Injectable({
	providedIn: 'root'
})

export class EnumService {

	enumSubject = new BehaviorSubject<any>({
		roles: [],
		tourTypes: [],
		tourSeasons: [],
		clientCategories: [],
		clientGenders: [],
	})

	constructor(
		private http: HttpClient,
		private global: GlobalService,
	) {
	}

	private get url() {
		return this.global.backendURL + '/enums';
	}

	getRoles() {
		this.http.get(
			this.url + '/roles',
		).subscribe({
			next: (res: any) => this.enumSubject.next({
				...this.enumSubject.value,
				roles: res.data,
			}),
			error: (e: any) => console.log(e.error),
		})
	}

	getTourTypes() {
		this.http.get(
			this.url + '/tourTypes',
		).subscribe({
			next: (res: any) => this.enumSubject.next({
				...this.enumSubject.value,
				tourTypes: res.data,
			}),
			error: (e: any) => console.log(e.error),
		})
	}

	getTourSeasons() {
		this.http.get(
			this.url + '/tourSeasons',
		).subscribe({
			next: (res: any) => this.enumSubject.next({
				...this.enumSubject.value,
				tourSeasons: res.data,
			}),
			error: (e: any) => console.log(e.error),
		})
	}

	getClientCategories() {
		this.http.get(
			this.url + '/clientCategories',
		).subscribe({
			next: (res: any) => this.enumSubject.next({
				...this.enumSubject.value,
				clientCategories: res.data,
			}),
			error: (e: any) => console.log(e.error),
		})
	}

	getClientGenders() {
		this.http.get(
			this.url + '/clientGenders',
		).subscribe({
			next: (res: any) => this.enumSubject.next({
				...this.enumSubject.value,
				clientGenders: res.data,
			}),
			error: (e: any) => console.log(e.error),
		})
	}


}
