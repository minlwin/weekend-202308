import { Component, computed, effect, input, signal } from '@angular/core';
import { WidgetsModule } from '../../widgets/widgets.module';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { LedgerEntryService } from '../../model/services/ledger-entry.service';
import { Router } from '@angular/router';
import { CategoryService } from '../../model/services/category.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-balance-edit',
  standalone: true,
  imports: [WidgetsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './balance-edit.component.html',
  styles: ``
})
export class BalanceEditComponent {

  type = input<string>()
  id = input<number | undefined>()

  categories = signal<any[]>([])

  edit = computed<boolean>(() => this.id() != undefined)
  icon = computed<string>(() => this.edit() ? 'bi-pencil' : 'bi-plus-lg')
  title = computed<string>(() => `${this.edit() ? 'Edit' : 'Add New'} ${this.type()}`)

  form:FormGroup

  constructor(private builder:FormBuilder,
    private router:Router,
    categoryService:CategoryService,
    private service:LedgerEntryService) {

    this.form = builder.group({
      type: ['', Validators.required],
      issueAt: ['', Validators.required],
      category: ['', Validators.required],
      remark: '',
      items: builder.array([])
    })

    if(this.items.length == 0) {
      this.addItem()
    }

    effect(() => {
      if(this.type()) {
        this.form.patchValue({type: this.type()})
        categoryService.search({type: this.type()}).subscribe(result => {
          this.categories.set(result)
        })
      }

      if(this.id()) {
        service.findById(this.id()!).subscribe(result => {
          console.log(result)
        })
      }
    }, {allowSignalWrites: true})
  }

  save() {
    if(this.form.valid) {
      const request = this.id() ? this.service.update(this.id()!, this.form.value)
        : this.service.create(this.form.value)

      request.subscribe(result => {
        this.router.navigate(['/details'], {queryParams: {type: this.type(), id: result.id}})
      })
    }
  }

  get items() {
    return this.form.get('items') as FormArray
  }

  get totalQuantity() {
    return this.items.controls.map(form => form.get('quantity')?.value || 0).reduce((a, b) => a + b)
  }

  get totalAmount() {
    return this.items.controls.map(form => {
      const count = form.get('quantity')?.value || 0
      const unitPrice = form.get('unitPrice')?.value || 0
      return count * unitPrice
    }).reduce((a, b) => a + b)
  }

  addItem() {
    this.items.push(this.builder.group({
      item: ['', Validators.required],
      unitPrice: [0, [Validators.required, Validators.min(1000)]],
      quantity: [0, [Validators.required, Validators.min(1)]]
    }))
  }

  removeItem(index:number) {
    this.items.removeAt(index)
    if(this.items.controls.length == 0) {
      this.addItem()
    }
  }

  getItemTotal(index: number) {
    const item = this.items.controls[index] as FormGroup

    const quantity = item.get('quantity')?.value || 0
    const unitPrice = item.get('unitPrice')?.value || 0

    return quantity * unitPrice
  }
}
