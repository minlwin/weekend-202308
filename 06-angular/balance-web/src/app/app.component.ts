import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { WidgetsModule } from './widgets/widgets.module';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, WidgetsModule],
  templateUrl: './app.component.html',
  styles: [],
})
export class AppComponent {
  show = signal<boolean>(true)

  toggleSideBar() {
    this.show.update(state => !state)
  }
}
