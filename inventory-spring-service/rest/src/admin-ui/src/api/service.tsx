import axios from "axios";
import { Car, CarInventory } from "../model/models";

const ADMIN_URL="/api/v1/adminActions";
const GET_INVENTORY="/listInventory/";
const GET_CARS="/listCars/";
const ADD_CAR="/addCar/";
const DEL_CAR="/delCar/";
const ADD_INVENTORY="/addCarToInventory/";
const DEL_INVENTORY="/delCarFromInventory/";

export const getCars = (serviceUrl: string): Promise<any[]> => {
  console.log("in get cars api call");

  return axios
    .get<any[]>(`${serviceUrl}${ADMIN_URL}${GET_CARS}`, {
      headers: {
        "Accept": "application/json",
      },
    })
    .then((response) => {
      console.log("returned response ");
      return response.data;
    })
    .catch(function (error) {
      // handle error
      console.log(error);
      return [];
    });
};

export const putCar = (serviceUrl: string, car: Car): Promise<any[]> => {
  console.log("in add car api call", car);

  return axios
    .post<any[]>(`${serviceUrl}${ADMIN_URL}${ADD_CAR}`, car, {
      headers: {
        "Content-Type": "application/json",
      },
    })
    .then((response) => {
      console.log("returned response ");
      return response.data;
    })
    .catch(function (error) {
      // handle error
      console.log(error);
      return [];
    });
};

export const deleteCar = (serviceUrl: string, car: Car): Promise<any[]> => {
  console.log("in delete car api call", car);

  return axios
    .delete<any[]>(`${serviceUrl}${ADMIN_URL}${DEL_CAR}` + car, {
      headers: {
      },
    })
    .then((response) => {
      console.log("returned response ", response.data);
      return response.data;
    })
    .catch(function (error) {
      // handle error
      console.log(error);
      return [];
    });
};


export const getCarInventory = (serviceUrl: string): Promise<any[]> => {
  console.log("in getCarInventory api call");

  return axios
    .get<any[]>(`${serviceUrl}${ADMIN_URL}${GET_INVENTORY}`, {
      headers: {
        "Accept": "application/json",
      },
    })
    .then((response) => {
      console.log("returned response ");
      return response.data;
    })
    .catch(function (error) {
      // handle error
      console.log(error);
      return [];
    });
};

export const addCarInventory = (serviceUrl: string, carinventory: CarInventory): Promise<any[]> => {
  console.log("in add inventory api call", carinventory);

  return axios
    .post<any[]>(`${serviceUrl}${ADMIN_URL}${ADD_INVENTORY}`, carinventory, {
      headers: {
        "Content-Type": "application/json",
      },
    })
    .then((response) => {
      console.log("returned response ");
      return response.data;
    })
    .catch(function (error) {
      // handle error
      console.log(error);
      return [];
    });
};

export const deleteCarInventory = (serviceUrl: string, inventory: CarInventory): Promise<any[]> => {
  console.log("in delete inventory api call", inventory);

  return axios
    .post<any[]>(`${serviceUrl}${ADMIN_URL}${DEL_INVENTORY}` ,inventory, {
      headers: {
      },
    })
    .then((response) => {
      console.log("returned response ", response.data);
      return response.data;
    })
    .catch(function (error) {
      // handle error
      console.log(error);
      return [];
    });
};