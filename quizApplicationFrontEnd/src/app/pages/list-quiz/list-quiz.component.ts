import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { QuizService } from '../../Service/QuizService';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-list-quiz',
  imports: [CommonModule],
  templateUrl: './list-quiz.component.html',
  styleUrl: './list-quiz.component.css'
})
export class ListQuizComponent {
   quizzes: any[] = [];

  constructor(private http: HttpClient, private router: Router,private quizeService: QuizService) {}

  ngOnInit() {
    this.quizeService.getAllQuiz().subscribe((data:any) => {
      this.quizzes = data;
    });
  }

  startQuiz(quizId: number) {
    this.router.navigate(['/do-quiz', quizId]);
  }
}
