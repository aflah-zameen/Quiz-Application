import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Question, QuizAnswer, QuizResult } from '../../Model/Question';
import { QuizService } from '../../Service/QuizService';
import { CommonModule, getLocaleMonthNames } from '@angular/common';
import { QuestionService } from '../../Service/QuestionService';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-quiz',
  imports: [ReactiveFormsModule,CommonModule,FormsModule],
  templateUrl: './quiz.component.html',
  styleUrl: './quiz.component.css'
})
export class QuizComponent {
  quizForm: FormGroup;
  constructor(private fb : FormBuilder,private http : HttpClient,private quizService : QuizService){
    this.quizForm  = fb.group({
      name: ['', Validators.required],
      category: ['', Validators.required],
      numberOfQuestions: [1, [Validators.required, Validators.min(1)]],
    });
  }

  onSubmit() {
    if (this.quizForm.valid) {
      const quizData = this.quizForm.value;
      console.log('Quiz created:', quizData);
      // Send POST request to backend
      this.quizService.generateQuiz(quizData.name,quizData.category,quizData.numberOfQuestions).subscribe({
        next: (response) => {
          alert('Quiz created successfully!');
          this.quizForm.reset();
        },
        error: (err) => {
          console.error(err);
          alert('Failed to create quiz');
        },
      });
    }
  }


}