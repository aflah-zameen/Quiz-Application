import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Question, QuizAnswer, QuizResult } from "../Model/Question";
import { environment } from '../../enviornments/enivironment';

@Injectable({
  providedIn: 'root'
})
export class QuizService {


  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  generateQuiz(title : string,category: string, numQuestions: number) {
    const quizDTO = {
        "categoryName" : category,
        "numberOfQuestions" : numQuestions,
        "title" : title
    }
    return this.http.post(`${this.apiUrl}quiz-service/quiz/create`,quizDTO);
  }

  getQuiz(quiz_id : number):Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}quiz-service/quiz/getQuiz/${quiz_id}`)
  }

  submitQuizAnswers(answers: QuizAnswer[]): Observable<Number> {
    return this.http.post<Number>(`${this.apiUrl}quiz-service/quiz/getResult`, answers);
  }

  getAllQuiz(){
    return this.http.get(`${this.apiUrl}quiz-service/quiz/getAllQuiz`);
  }
}