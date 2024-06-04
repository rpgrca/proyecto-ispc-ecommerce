import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemConfiguracionComponent } from './item-configuracion.component';

describe('ItemConfiguracionComponent', () => {
  let component: ItemConfiguracionComponent;
  let fixture: ComponentFixture<ItemConfiguracionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ItemConfiguracionComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ItemConfiguracionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
