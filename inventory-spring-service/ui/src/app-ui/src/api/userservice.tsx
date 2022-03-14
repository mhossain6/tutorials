import axios from "axios";
import { Car, CarInventory } from "../model/models";

const USER_URL = "/api/v1/buyerActions";
const GET_INVENTORY = "/listInventory/";
const GET_CART = "/listCartItems/";
const ADD_CAR = "/addItemToCart";
const DEL_CAR = "/removeCartItem";
const CHECK_OUT = "/checkout/";

export const getCarInventory = (serviceUrl: string): Promise<any[]> => {
  console.log("in getCarInventory api call");

  return axios
    .get<any[]>(`${serviceUrl}${USER_URL}${GET_INVENTORY}`, {
      headers: {
        Accept: "application/json",
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

export const getCartItems = (serviceUrl: string): Promise<any[]> => {
  console.log("in get cart items api call");

  return axios
    .get<any[]>(`${serviceUrl}${USER_URL}${GET_CART}`, {
      headers: {
        Accept: "application/json",
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

export const addCartoCart = (
  serviceUrl: string,
  car: CarInventory
): Promise<any[]> => {
  console.log("in add cart item api call", car);

  return axios
    .get<any[]>(`${serviceUrl}${USER_URL}${ADD_CAR}`, {
      params: {
        inventoryId: car.id,
      },
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

export const deleteCartItem = (
  serviceUrl: string,
  car: CarInventory
): Promise<any[]> => {
  console.log("in delete cart item api call", car);

  return axios
    .get<any[]>(`${serviceUrl}${USER_URL}${DEL_CAR}`, {
      params: {
        inventoryId: car.id,
      },
      headers: {},
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

export const checkoutCart = (serviceUrl: string): Promise<any[]> => {
  console.log("in Checkout api call");

  return axios
    .get<any[]>(`${serviceUrl}${USER_URL}${CHECK_OUT}`, {
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
