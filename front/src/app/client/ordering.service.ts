import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {GlobalService} from "../global.service";

@Injectable({
	providedIn: 'root'
})

export class OrderingService {

	constructor(
		private http: HttpClient,
		private global: GlobalService,
	) {
	}

	private get url() {
		return this.global.backendURL + '/orderings'
	}

	save(ordering: any, clientId: number, tourId: number) {
		return this.http.post(
			this.url,
			JSON.stringify(ordering),
			{
				headers: this.global.headersJsonToken,
				params: new HttpParams().appendAll({
					clientId: clientId,
					tourId: tourId,
				}),
			},
		);
	}

	delete(id: number) {
		return this.http.delete(
			this.url + `/${id}`,
			{headers: this.global.headersToken,},
		);
	}

}
