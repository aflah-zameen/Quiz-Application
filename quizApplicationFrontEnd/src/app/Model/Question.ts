export interface Question {
    id?: string;
    question: string;
    category: string;
    options: string[];
    answer: string;
  }
  
  export interface QuizParams {
    categoryName: string;
    numberOfQuestions: number;
  }
  
  export interface QuizAnswer {
    id: string;
    response: string;
  }
  
  export interface QuizResult {
    score: number;
  }