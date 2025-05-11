import { Routes } from '@angular/router';
import { AddQuestionComponent } from './pages/add-question/add-question.component';
import { QuizComponent } from './pages/add-quiz/quiz.component';

export const routes: Routes = [{
    path : '',
    redirectTo : 'list-quiz',
    pathMatch : 'full'
},{
    path : 'add-question',
    component : AddQuestionComponent
},{
    path : 'add-quiz',
    component : QuizComponent
},{
    path : "list-quiz",
    loadComponent : () => import('./pages/list-quiz/list-quiz.component').then(m => m.ListQuizComponent)
},{
    path : "do-quiz/:id",
    loadComponent : ()=> import('./pages/take-quiz/do-quiz/do-quiz.component').then(m => m.DoQuizComponent)
},{
    path : "show-score",
    loadComponent : ()=>import('./pages/take-quiz/show-score/show-score.component').then(m => m.ShowScoreComponent)
}];
