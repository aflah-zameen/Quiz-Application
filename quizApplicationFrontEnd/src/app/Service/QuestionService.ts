import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Question } from '../Model/Question';
import { environment } from '../../enviornments/enivironment';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  addQuestion(question: Question): Observable<any> {
    console.log(question);
    
    return this.http.post(`${this.apiUrl}question-service/question/add`, {question : question.question, options : question.options, answer : question.answer, category : question.category, level : "EASY"});
  }
}