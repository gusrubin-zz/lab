import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, of, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PublicInfoDto } from './public-info-dto';

@Injectable({
  providedIn: 'root'
})
export class PublicInfoService {

  private apiBaseUrl = environment.publicInfoUri;

  constructor(private http: HttpClient) { }

  getPublicInfo(): Observable<PublicInfoDto> {
    console.log("apiBaseUrl=", this.apiBaseUrl);
    return this.http.get<PublicInfoDto>(this.apiBaseUrl)
      .pipe(
        tap(_ => console.log('fetched public info')),
        catchError(this.handleError<PublicInfoDto>('getPublicInfo'))
      );
  }

  // handleError<T>(arg0: string, arg1: never[]): (err: any, caught: Observable<String>) => import("rxjs").ObservableInput<any> {
  //   console.log("Error on getting public info");
  //   throw new Error('Method not implemented.');
  // }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      console.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

}
