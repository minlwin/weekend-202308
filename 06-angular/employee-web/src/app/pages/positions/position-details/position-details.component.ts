import { Component, signal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PositionService } from '../../../service/position.service';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { PositionInfoComponent } from './position-info/position-info.component';

@Component({
  selector: 'app-position-details',
  standalone: true,
  imports: [
    WidgetsModule,
    PositionInfoComponent],
  templateUrl: './position-details.component.html',
  styles: ``
})
export class PositionDetailsComponent {

  data = signal<any>(undefined)

  constructor(route:ActivatedRoute, service:PositionService) {

    route.queryParamMap.subscribe(map => {
      const code = map.get('code')

      if(code) {
        service.findById(code).subscribe(result => {
          if(result.success) {
            this.data.set(result.payload)
          }
        })
      }
    })
  }
}
