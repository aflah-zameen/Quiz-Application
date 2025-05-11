import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { QuestionService } from '../../Service/QuestionService';
import { Question } from '../../Model/Question';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-add-question',
  imports: [FormsModule,ReactiveFormsModule,CommonModule],
  templateUrl: './add-question.component.html',
  styleUrl: './add-question.component.css'
})
export class AddQuestionComponent implements OnInit{
  questionForm: FormGroup;
  successMessage: string = '';
  errorMessage: string = '';
  isSubmitting: boolean = false;

  constructor(
    private fb: FormBuilder,
    private questionService: QuestionService
  ) {
    this.questionForm = this.fb.group({
      questionText: ['', [Validators.required, Validators.minLength(5)]],
      category: ['', Validators.required],
      options: this.fb.array([
        this.fb.control('', Validators.required),
        this.fb.control('', Validators.required)
      ]),
      correctAnswer: ['', Validators.required]
    });
  }

  ngOnInit(): void {}

  get options(): FormArray {
    return this.questionForm.get('options') as FormArray;
  }

  addOption(): void {
    this.options.push(this.fb.control('', Validators.required));
  }

  removeOption(index: number): void {
    if (this.options.length > 2) {
      this.options.removeAt(index);
      
      // If the correct answer was the removed option, reset it
      const correctAnswer = this.questionForm.get('correctAnswer')?.value;
      const removedOption = this.options.at(index)?.value;
      
      if (correctAnswer === removedOption) {
        this.questionForm.get('correctAnswer')?.setValue('');
      }
    }
  }

  onSubmit(): void {
    if (this.questionForm.invalid) {
      this.questionForm.markAllAsTouched();
      return;
    }

    const formValues = this.questionForm.value;
    
    // Validate that correctAnswer is one of the options
    if (!formValues.options.includes(formValues.correctAnswer)) {
      this.errorMessage = 'The correct answer must match one of the options';
      return;
    }

    this.isSubmitting = true;
    this.successMessage = '';
    this.errorMessage = '';

    const question: Question = {
      question: formValues.questionText,
      category: formValues.category,
      options: formValues.options,
      answer: formValues.correctAnswer
    };

    this.questionService.addQuestion(question).subscribe({
      next: (response) => {
        this.isSubmitting = false;
        this.successMessage = 'Question added successfully!';
        this.resetForm();
      },
      error: (error) => {
        this.isSubmitting = false;
        this.errorMessage = 'Error adding question. Please try again.';
        console.error('Error adding question:', error);
      }
    });
  }

  resetForm(): void {
    this.questionForm.reset();
    
    // Reset options to have at least 2
    while (this.options.length) {
      this.options.removeAt(0);
    }
    
    this.options.push(this.fb.control('', Validators.required));
    this.options.push(this.fb.control('', Validators.required));
  }
}