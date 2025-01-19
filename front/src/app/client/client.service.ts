import {Injectable} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {GlobalService} from "../global.service";
import {AlertService} from "../alert/alert.service";
import {Router} from "@angular/router";

@Injectable({
	providedIn: 'root'
})
export class ClientService {

	clientSubject = new BehaviorSubject<any>({
		clients: [],
	})

	constructor(
		private http: HttpClient,
		private global: GlobalService,
		private alert: AlertService,
		private router: Router,
	) {
	}

	private get url() {
		return this.global.backendURL + '/clients'
	}

	private error(e: any) {
		console.log(e.error)
		this.alert.showAlertMessage(e.error.message)
	}

	private page(id: number) {
		this.router.navigate(['client'], {queryParams: {id: id}})
	}

	findAll() {
		this.http.get(
			this.url,
			{headers: this.global.headersToken}
		).subscribe({
			next: (res: any) => this.clientSubject.next({
				...this.clientSubject.value,
				clients: res.data,
			}),
			error: (e: any) => this.error(e),
		});
	}

	find(id: number) {
		return this.http.get(
			this.url + `/${id}`,
			{headers: this.global.headersToken}
		);
	}

	save(client: any) {
		this.http.post(
			this.url,
			JSON.stringify(client),
			{headers: this.global.headersJsonToken,},
		).subscribe({
			next: (res: any) => this.page(res.data.id),
			error: (e: any) => this.error(e),
		});
	}

	update(id: number, client: any) {
		this.http.put(
			this.url + `/${id}`,
			JSON.stringify(client),
			{headers: this.global.headersJsonToken,},
		).subscribe({
			next: (res: any) => this.page(res.data.id),
			error: (e: any) => this.error(e),
		});
	}

	updatePassport(id: any, passport: string) {
		return this.http.patch(
			this.url + `/${id}/passport`,
			"",
			{
				headers: this.global.headersToken,
				params: new HttpParams().appendAll({passport: passport})
			},
		);
	}

	updateGender(id: any, gender: string) {
		return this.http.patch(
			this.url + `/${id}/gender`,
			"",
			{
				headers: this.global.headersToken,
				params: new HttpParams().appendAll({gender: gender})
			},
		);
	}

	updateCategory(id: any, category: string) {
		return this.http.patch(
			this.url + `/${id}/category`,
			"",
			{
				headers: this.global.headersToken,
				params: new HttpParams().appendAll({category: category})
			},
		);
	}

	updateTourType(id: any, tourType: string) {
		return this.http.patch(
			this.url + `/${id}/tourType`,
			"",
			{
				headers: this.global.headersToken,
				params: new HttpParams().appendAll({tourType: tourType})
			},
		);
	}

	delete(id: number) {
		this.http.delete(
			this.url + `/${id}`,
			{headers: this.global.headersToken,},
		).subscribe({
			next: (res: any) => this.router.navigate(['clients']),
			error: (e: any) => this.error(e),
		});
	}

}
