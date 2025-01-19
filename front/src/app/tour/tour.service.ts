import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {GlobalService} from "../global.service";
import {BehaviorSubject} from "rxjs";
import {AlertService} from "../alert/alert.service";
import {Router} from "@angular/router";

@Injectable({
	providedIn: 'root'
})

export class TourService {

	tourSubject = new BehaviorSubject<any>({
		tours: [],
	})

	constructor(
		private http: HttpClient,
		private global: GlobalService,
		private alert: AlertService,
		private router: Router,
	) {
	}

	private get url() {
		return this.global.backendURL + '/tours'
	}

	private error(e: any) {
		console.log(e.error)
		this.alert.showAlertMessage(e.error.message)
	}

	private page(id: number) {
		this.router.navigate(['tour'], {queryParams: {id: id}})
	}

	findAll() {
		this.http.get(
			this.url,
		).subscribe({
			next: (res: any) => this.tourSubject.next({
				...this.tourSubject.value,
				tours: res.data,
			}),
			error: (e: any) => this.error(e),
		});
	}

	find(id: number) {
		return this.http.get(
			this.url + `/${id}`
		);
	}

	save(tour: any, type: string, season: string, img: any, file: any) {
		this.http.post(
			this.url,
			JSON.stringify(tour),
			{
				headers: this.global.headersJsonToken,
				params: new HttpParams().appendAll({
					type: type,
					season: season,
				})
			},
		).subscribe({
			next: (res: any) => this.updateImg(res.data.id, img, file),
			error: (e: any) => this.error(e),
		});
	}

	update(id: number, tour: any, type: string, season: string, img: any, file: any) {
		this.http.put(
			this.url + `/${id}`,
			JSON.stringify(tour),
			{
				headers: this.global.headersJsonToken,
				params: new HttpParams().appendAll({
					type: type,
					season: season,
				})
			},
		).subscribe({
			next: (res: any) => {
				if (img != null) this.updateImg(res.data.id, img, file)
				else if (file != null) this.updateFile(res.data.id, file);
				else this.page(res.data.id);
			},
			error: (e: any) => this.error(e),
		});
	}

	private updateImg(id: any, img: any, file: any) {
		let formData = new FormData();
		for (let i = 0; i < img.length; i++) {
			formData.append('files', img[i]);
		}
		this.http.patch(
			this.url + `/${id}/img`,
			formData,
			{headers: this.global.headersMultipartToken,},
		).subscribe({
			next: (res: any) => {
				if (file != null) this.updateFile(res.data.id, file);
				else this.page(res.data.id);
			},
			error: (e: any) => this.error(e),
		});
	}

	private updateFile(id: any, file: any) {
		let formData = new FormData();
		for (let i = 0; i < file.length; i++) {
			formData.append('files', file[i]);
		}
		this.http.patch(
			this.url + `/${id}/file`,
			formData,
			{headers: this.global.headersMultipartToken,},
		).subscribe({
			next: (res: any) => this.page(res.data.id),
			error: (e: any) => this.error(e),
		});
	}

	delete(id: number) {
		this.http.delete(
			this.url + `/${id}`,
			{headers: this.global.headersToken,},
		).subscribe({
			next: (res: any) => this.router.navigate(['tours']),
			error: (e: any) => this.error(e),
		});
	}

}
