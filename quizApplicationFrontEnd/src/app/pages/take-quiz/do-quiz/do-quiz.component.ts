import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { QuizService } from '../../../Service/QuizService';

@Component({
  selector: 'app-do-quiz',
  imports: [ReactiveFormsModule,FormsModule,CommonModule],
  templateUrl: './do-quiz.component.html',
  styleUrl: './do-quiz.component.css'
})
export class DoQuizComponent implements OnInit{
  quizId!: number;
  questions: any[] = [];
  quizForm!: FormGroup;

   constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private fb: FormBuilder,
    private router: Router,
    private quizService: QuizService
  ) {}

  ngOnInit() {
  this.quizId = +this.route.snapshot.paramMap.get('id')!;
  this.quizService.getQuiz(this.quizId).subscribe((data) => {
    this.questions = data;
    const answersArray = this.fb.array(
      this.questions.map((q) =>
        this.fb.group({
          questionId: [q.id],
          selectedAnswer: ['', Validators.required],
        })
      )
    );

    this.quizForm = this.fb.group({
      answers: answersArray,
    });
  });
}

get answersFormArray() {
  return this.quizForm.get('answers') as FormArray;
}

  submitQuiz() {
    if (this.quizForm.valid) {
          const payload = this.answersFormArray.value.map((answer: any) => ({
            id: answer.questionId,
            response: answer.selectedAnswer
          }));
     this.http
      this.quizService.submitQuizAnswers(payload)
      .subscribe((score: any) => {
        this.router.navigate(['/show-score'], { state: { score: score } });
      });
    } else {
      alert('Please answer all questions.');
    }
  }
    
}
