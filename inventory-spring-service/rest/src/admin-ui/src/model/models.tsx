export class Car {
  id: string;
  make: string;
  model: string;
  year: string;

  constructor() {}
}

export class CarInventory {
  id: string;
  car: Car;
  constructor() {}
}
