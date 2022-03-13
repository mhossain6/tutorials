import React from "react";
import DeleteIcon from "@mui/icons-material/Delete";
import MinimizeIcon from "@mui/icons-material/Minimize";
import EventIcon from "@mui/icons-material/Event";
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
  onSaveCallbak: Function;
}

interface CarListProps {
  cars: Car[];
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
export const AddCarUI = ({ name }: AppProps) => {
  const [cars, setCars] = React.useState<Car[]>([]);
  const [addCarBox, setAddCarBox] = React.useState<boolean>(false);

  React.useEffect(() => {
    getCarsFromDb(serviceUrl);
  }, []);

  const onAddClick = (e) => {
    setAddCarBox(true);
  };

  const onCollapseClick = (e) => {
    setAddCarBox(false);
  };

  const onSaveClick = (car: Car) => {
    console.log("onSaveClick - ", car);

    putCar(serviceUrl, car)
      .then((data) => {
        console.log("response data ", data);
        addCarsToList(data);
      })
      .catch(function (error) {
        console.log("error while getting data from server" + error.toString());
      });
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

  const addCarsToList = (cars: Car[]) => {
    getCarsFromDb(serviceUrl);
  };

  const onChangeCallBack = () => {
    getCarsFromDb(serviceUrl);
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
        <Item>{addCarBox && <AddCarView onSaveCallbak={onSaveClick} />}</Item>
      </Stack>

      <Stack spacing={2} alignItems="left">
        <Item>
          <CarView cars={cars} onChangeCallBack={onChangeCallBack} />
        </Item>
      </Stack>
    </div>
  );
};

export const AddCarView: React.FC<AddCarProps> = ({ onSaveCallbak }) => {
  const [make, setMake] = React.useState<string>("");
  const [model, setModel] = React.useState<string>("");
  const [year, setYear] = React.useState<string>("");

  const onMakeChange = (event) => {
    setMake(event.target.value);
  };

  const onModelChange = (event) => {
    setModel(event.target.value);
  };

  const onYearChange = (event) => {
    setYear(event.target.value);
  };

  const onSaveClick = (event) => {
    const car: Car = new Car();
    car.make = make;
    car.model = model;
    car.year = year;
    onSaveCallbak(car);
  };

  return (
    <>
      <Card sx={{ minWidth: "sm" }}>
        <CardContent style={customStyle}>
          <Stack spacing={1}>
            <Item>
              <Typography variant="h6" component="h6" id="add_car_label">
                Add Car
              </Typography>
            </Item>
            <Item>
              <Typography variant="h6" component="h6" id="car_make_lable">
                Make
              </Typography>
            </Item>
            <Item>
              <TextField
                style={{ width: "100%" }}
                required
                id="make_field"
                value={make}
                onChange={onMakeChange}
              />
            </Item>
            <Item>
              <Typography variant="h6" component="h6" id="car_model_lable">
                Model
              </Typography>
            </Item>
            <Item>
              <TextField
                style={{ width: "100%" }}
                required
                id="model_field"
                value={model}
                onChange={onModelChange}
              />
            </Item>
            <Item>
              <Typography variant="h6" component="h6" id="car_year_lable">
                Year
              </Typography>
            </Item>
            <Item>
              <TextField
                style={{ width: "100%" }}
                required
                id="year_field"
                value={year}
                onChange={onYearChange}
              />
            </Item>
          </Stack>
        </CardContent>
        <CardActions sx={{ display: "grid" }}>
          <Stack spacing={2} direction="row-reverse" alignContent={"right"}>
            <Item>
              <Button
                variant="outlined"
                onClick={onSaveClick}
                id="car_save_button"
              >
                Save
              </Button>
            </Item>
          </Stack>
        </CardActions>
      </Card>
    </>
  );
};

export const CarView: React.FC<CarListProps> = ({ cars, onChangeCallBack }) => {
  console.log("in CarView : cars", cars);

  const onDeleteClick = (car: Car) => {
    console.log("onDeleteClick - ", car);

    deleteCar(serviceUrl, car)
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
