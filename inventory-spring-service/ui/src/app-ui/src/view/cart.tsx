import React from "react";
import DeleteIcon from "@mui/icons-material/Delete";
import MinimizeIcon from "@mui/icons-material/Minimize";
import EventIcon from "@mui/icons-material/Event";
import AddIcon from "@mui/icons-material/Add";
import {
  Button,
  Stack,
  TextField,
  Typography,
  Card,
  CardContent,
  Paper,
  ListItemAvatar,
  Avatar,
  CardActions,
  InputAdornment,
  styled,
  List,
  ListItem,
  ListItemText,
} from "@mui/material";

import { Car, CarInventory } from "../model/models";
import {
  getCarInventory,
  getCartItems,
  addCartoCart,
  deleteCartItem,
  checkoutCart,
} from "../api/userservice";
import "./adminUI.css";

//require('dotenv').config();

const serviceUrl = `http://localhost:${process.env.REACT_APP_API_PORT}`;

type AppProps = {
  name: string;
}; /* use `interface` if exporting so that consumers can extend */

interface AddToCartProps {
  onSaveCallbak: Function;
}

interface CartListProps {
  onChangeCallBack: Function;
}

const Item = styled(Paper)(({ theme }) => ({
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: "left",
  boxShadow: "none",
  color: theme.palette.text.secondary,
}));

const customStyle = {
  alignContent: "left",
};

// Easiest way to declare a Function Component; return type is inferred.
export const CartInventoryUI = ({ name }: AppProps) => {
  const [addCarBox, setAddCarBox] = React.useState<boolean>(false);

  const onAddClick = (e) => {
    setAddCarBox(true);
  };

  const onCollapseClick = (e) => {
    setAddCarBox(false);
  };

  const onSaveClick = (car: Car) => {
    console.log("onSaveClick - ", car);
    //setAddCarBox(true);
  };

  const onChangeCallBack = () => {
    //setAddCarBox(true);
  };

  return (
    <div>
      <Stack spacing={2} direction="row-reverse" alignItems="right" padding={2}>
        <Item>
          {addCarBox ? (
            <Button
              variant="outlined"
              startIcon={<MinimizeIcon fontSize="large" />}
              onClick={onCollapseClick}
            />
          ) : (
            <Button variant="outlined" onClick={onAddClick}>
              Inventory
            </Button>
          )}
        </Item>
      </Stack>
      <Stack spacing={2} direction="column">
        <Item>
         {addCarBox &&  <AddToCartView onSaveCallbak={onSaveClick} /> }
        </Item>
      </Stack>

      <Stack spacing={2} alignItems="left">
        <Item>
          <CartView onChangeCallBack={onChangeCallBack} />
        </Item>
      </Stack>
    </div>
  );
};

export const AddToCartView: React.FC<AddToCartProps> = ({ onSaveCallbak }) => {
  const [carinventory, setCarInventory] = React.useState<CarInventory[]>([]);

  React.useEffect(() => {
    getCarsInventoryFromDB(serviceUrl);
  }, []);

  const getCarsInventoryFromDB = (serviceUrl: string) => {
    getCarInventory(serviceUrl)
      .then((data) => {
        console.log("in cars response ", data);
        setCarInventory(data);
      })
      .catch(function (error) {
        console.log("error while getting data from server" + error.toString());
      });
  };

  const onAddClick = (car: CarInventory) => {
    console.log("onDeleteClick - ", car);

    addCartoCart(serviceUrl, car)
      .then((data) => {
        console.log("response data ", data);
        setTimeout ( ()=> getCarsInventoryFromDB(serviceUrl), 200);
      })
      .catch(function (error) {
        console.log("error while getting data from server" + error.toString());
      });
  };

  return (
    <>
      <Typography variant="h6" component="h6" id="car_make_lable">
        Cars Inventory
      </Typography>
      <Card sx={{ minWidth: "sm" }}>
        <CardContent style={customStyle}>
          <Stack spacing={2} direction="row">
            <List style={{ width: "100%" }}>
              {carinventory.map(function (car, idx) {
                return (
                  <ListItem key={idx} style={{ width: "100%" }}>
                    <Paper style={{ width: "100%" }}>
                      <Stack spacing={2} direction="row">
                        <Item sx={{ minWidth: "sm" }} style={{ width: "80%" }}>
                          <Stack spacing={2}>
                            <Item>
                              <ListItemText
                                primary="Make"
                                secondary={car.inventoryObject.make}
                                id={"car_make" + car.id}
                              />
                            </Item>
                            <Item>
                              <ListItemText
                                primary="Model"
                                secondary={car.inventoryObject.model}
                                id={"car_model" + car.id}
                              />
                            </Item>
                          </Stack>
                        </Item>
                        <Item style={{ alignItems: "right" }}>
                          <Stack spacing={2} direction="row">
                            <Item>
                              <ListItemText
                                primary="Year"
                                secondary={car.inventoryObject.year}
                                id={"car_year" + car.id}
                              />
                            </Item>
                            <Item>
                              <ListItemAvatar>
                                <Avatar>
                                  <AddIcon
                                    onClick={(e) => {
                                      onAddClick(car);
                                    }}
                                  />
                                </Avatar>
                              </ListItemAvatar>
                            </Item>
                          </Stack>
                        </Item>
                      </Stack>
                    </Paper>
                  </ListItem>
                );
              })}
            </List>
          </Stack>
        </CardContent>
      </Card>
    </>
  );
};

export const CartView: React.FC<CartListProps> = ({ onChangeCallBack }) => {
  
  const [cartitems, setCartItems] = React.useState<CarInventory[]>([]);

  React.useEffect(() => {
    getCartItemsFromDb(serviceUrl);
  }, []);

  const getCartItemsFromDb = (serviceUrl: string) => {
    getCartItems(serviceUrl)
      .then((data) => {
        console.log("in cars response ", data);
        setCartItems(data);
      })
      .catch(function (error) {
        console.log("error while getting data from server" + error.toString());
      });
  };

  const onCheckOut = () => {
    checkoutCart(serviceUrl)
      .then((data) => {
        console.log("response data ", data);
        //getCartItemsFromDb(serviceUrl);
        setTimeout ( ()=> getCartItemsFromDb(serviceUrl), 200);
      })
      .catch(function (error) {
        console.log("error while getting data from server" + error.toString());
      });
  };

  return (
    <>
      <Typography variant="h6" component="h6" id="car_make_lable">
        Shopping Cart
      </Typography>
      <Card sx={{ minWidth: "sm" }}>
        <CardContent style={customStyle}>
          <Stack spacing={2} direction="row">
            <List style={{ width: "100%" }}>
              {cartitems.map(function (car, idx) {
                return (
                  <ListItem key={idx} style={{ width: "100%" }}>
                    <Paper style={{ width: "100%" }}>
                      <Stack spacing={2} direction="row">
                        <Item sx={{ minWidth: "sm" }} style={{ width: "80%" }}>
                          <Stack spacing={2}>
                            <Item>
                              <ListItemText
                                primary="Make"
                                secondary={car.inventoryObject.make}
                                id={"car_make" + car.id}
                              />
                            </Item>
                            <Item>
                              <ListItemText
                                primary="Model"
                                secondary={car.inventoryObject.model}
                                id={"car_model" + car.id}
                              />
                            </Item>
                          </Stack>
                        </Item>
                        <Item style={{ alignItems: "right" }}>
                          <Stack spacing={2} direction="row">
                            <Item>
                              <ListItemText
                                primary="Year"
                                secondary={car.inventoryObject.year}
                                id={"car_year" + car.id}
                              />
                            </Item>
                          </Stack>
                        </Item>
                      </Stack>
                    </Paper>
                  </ListItem>
                );
              })}
            </List>
          </Stack>
        </CardContent>
        <CardActions>
          <Button variant="outlined" onClick={onCheckOut}>
            CheckOut
          </Button>
        </CardActions>
      </Card>
    </>
  );
};
