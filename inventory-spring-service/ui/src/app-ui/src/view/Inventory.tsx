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
  getCars,
  putCar,
  deleteCar,
  getCarInventory,
  addCarInventory,
  deleteCarInventory,
} from "../api/service";
import "./adminUI.css";

//require('dotenv').config();

const serviceUrl = `http://localhost:${process.env.REACT_APP_API_PORT}`;

type AppProps = {
  name: string;
}; /* use `interface` if exporting so that consumers can extend */

interface AddCarProps {
  cars: Car[];
  onSaveCallbak: Function;
}

interface CarListProps {
  cars: CarInventory[];
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
export const AddCarInventoryUI = ({ name }: AppProps) => {
  const [cars, setCars] = React.useState<Car[]>([]);
  const [carinventory, setCarInventory] = React.useState<CarInventory[]>([]);
  const [addCarBox, setAddCarBox] = React.useState<boolean>(false);

  React.useEffect(() => {
    getCarsFromDb(serviceUrl);
  }, [addCarBox]);

  React.useEffect(() => {
    getCarsInventoryFromDB(serviceUrl);
  }, []);

  const onAddClick = (e) => {
    setAddCarBox(true);
  };

  const onCollapseClick = (e) => {
    setAddCarBox(false);
  };

  const onSaveClick = (car: Car) => {
    console.log("onSaveClick - ", car);
    getCarsInventoryFromDB(serviceUrl);
  };

  const getCarsFromDb = (serviceUrl: string) => {
    getCars(serviceUrl)
      .then((data) => {
        console.log("in cars response ", data);
        setCars(data);
      })
      .catch(function (error) {
        console.log("error while getting data from server" + error.toString());
      });
  };

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

  const onChangeCallBack = () => {
    getCarsInventoryFromDB(serviceUrl);
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
              Add
            </Button>
          )}
        </Item>
      </Stack>
      <Stack spacing={2} direction="column">
        <Item>
          {addCarBox && <AddCarView onSaveCallbak={onSaveClick} cars={cars} />}
        </Item>
      </Stack>

      <Stack spacing={2} alignItems="left">
        <Item>
          <InventoryView
            onChangeCallBack={onChangeCallBack}
            cars={carinventory}
          />
        </Item>
      </Stack>
    </div>
  );
};

export const AddCarView: React.FC<AddCarProps> = ({ cars, onSaveCallbak }) => {
  console.log("in CarView : cars", cars);

  const onAddClick = (car: Car) => {
    console.log("onDeleteClick - ", car);

    addCarInventory(serviceUrl, car)
      .then((data) => {
        console.log("response data ", data);
        onSaveCallbak();
      })
      .catch(function (error) {
        console.log("error while getting data from server" + error.toString());
      });
  };

  return (
    <>
      <Typography variant="h6" component="h6" id="car_make_lable">
        Add cars to inventory ...
      </Typography>
      <Card sx={{ minWidth: "sm" }}>
        <CardContent style={customStyle}>
          <Stack spacing={2} direction="row">
            <List style={{ width: "100%" }}>
              {cars.map(function (car, idx) {
                return (
                  <ListItem key={idx} style={{ width: "100%" }}>
                    <Paper style={{ width: "100%" }}>
                      <Stack spacing={2} direction="row">
                        <Item sx={{ minWidth: "sm" }} style={{ width: "80%" }}>
                          <Stack spacing={2}>
                            <Item>
                              <ListItemText
                                primary="Make"
                                secondary={car.make}
                                id={"car_make" + car.id}
                              />
                            </Item>
                            <Item>
                              <ListItemText
                                primary="Model"
                                secondary={car.model}
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
                                secondary={car.year}
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

export const InventoryView: React.FC<CarListProps> = ({
  cars,
  onChangeCallBack,
}) => {
  console.log("in CarView : cars", cars);

  const onDeleteClick = (car: CarInventory) => {
    console.log("onDeleteClick - ", car);

    deleteCarInventory(serviceUrl, car)
      .then((data) => {
        console.log("response data ", data);
        onChangeCallBack();
      })
      .catch(function (error) {
        console.log("error while getting data from server" + error.toString());
      });
  };

  return (
    <>
      <Typography variant="h6" component="h6" id="car_make_lable">
        Inventory
      </Typography>
      <Card sx={{ minWidth: "sm" }}>
        <CardContent style={customStyle}>
          <Stack spacing={2} direction="row">
            <List style={{ width: "100%" }}>
              {cars.map(function (car, idx) {
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
                                  <DeleteIcon
                                    onClick={(e) => {
                                      onDeleteClick(car);
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
