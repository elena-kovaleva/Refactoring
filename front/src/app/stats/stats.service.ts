import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {GlobalService} from "../global.service";

@Injectable({
	providedIn: 'root'
})
export class StatsService {

	constructor(
		private http: HttpClient,
		private global: GlobalService
	) {
	}

	private get url() {
		return this.global.backendURL + '/stats'
	}

	tourSeasons() {
		return this.http.get(
			this.url + '/tourSeasons',
			{headers: this.global.headersToken}
		);
	}

	tourSeasonsOrdering() {
		return this.http.get(
			this.url + '/tourSeasonsOrdering',
			{headers: this.global.headersToken}
		);
	}

	tourTypes() {
		return this.http.get(
			this.url + '/tourTypes',
			{headers: this.global.headersToken}
		);
	}

	clientGenders() {
		return this.http.get(
			this.url + '/clientGenders',
			{headers: this.global.headersToken}
		);
	}

	clientStatuses() {
		return this.http.get(
			this.url + '/clientStatuses',
			{headers: this.global.headersToken}
		);
	}

	clientCategories() {
		return this.http.get(
			this.url + '/clientCategories',
			{headers: this.global.headersToken}
		);
	}

}
