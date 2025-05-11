import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-show-score',
  imports: [],
  templateUrl: './show-score.component.html',
  styleUrl: './show-score.component.css'
})
export class ShowScoreComponent {
    score = history.state.score ?? 0;

  constructor(private router: Router) {}
}
