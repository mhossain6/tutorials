import { AddCarUI } from "../Car";
import { AddCarInventoryUI } from "../Inventory";
import Footer from "./Footer";
import Header from "./Header";
import { Container } from "@mui/material";
import Tabs from "@mui/material/Tabs";
import Tab from "@mui/material/Tab";
import Box from "@mui/material/Box";
import React from "react";
import { CartInventoryUI } from "../cart";
import { getUserRole } from "../../api/service";

interface TabPanelProps {
  children?: React.ReactNode;
  index: number;
  value: number;
}

function TabPanel(props: TabPanelProps) {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`simple-tabpanel-${index}`}
      aria-labelledby={`simple-tab-${index}`}
      {...other}
    >
      {value === index && (
        <Box sx={{ p: 3 }}>
          <div>{children}</div>
        </Box>
      )}
    </div>
  );
}

const HomePageLayout = (props?: any) => {
  const [value, setValue] = React.useState(0);
  const [adminrole, setrole] = React.useState<boolean>(false);

  const serviceUrl = `http://localhost:${process.env.REACT_APP_API_PORT}`;

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  React.useEffect(() => {
    getRoleFromDb(serviceUrl);
  }, []);

 
  const getRoleFromDb = (serviceUrl: string) => {
    getUserRole(serviceUrl)
      .then((data) => {
        console.log("in cars response ", data);

        if (data.length > 0 && data === "ADMIN") setrole(true);
      })
      .catch(function (error) {
        console.log("error while getting data from server" + error.toString());
      });
  };

  return (
    <div className="homepage">
      <Container sx={{ minWidth: "sm" }} maxWidth="md" fixed={true}>
        <div className="header">
          <Header />
        </div>
        <div className="main">
          <Box sx={{ borderBottom: 1, borderColor: "divider" }}>
            <Tabs
              value={value}
              onChange={handleChange}
              aria-label="basic tabs example"
            >
              {adminrole && <Tab label="Car" />}
              {adminrole && <Tab label="Inventory" />}
              {!adminrole && <Tab label="Cart" />}
            </Tabs>
          </Box>
          {adminrole && (
            <TabPanel value={value} index={0}>
              <AddCarUI name="Add Car" />
            </TabPanel>
          )}
          {adminrole && (
            <TabPanel value={value} index={1}>
              <AddCarInventoryUI name="Inventory" />
            </TabPanel>
          )}
          {!adminrole && (
            <TabPanel value={value} index={0}>
              <CartInventoryUI name="Cart" />
            </TabPanel>
          )}
        </div>
        <div className="footer">
          <Footer />
        </div>
      </Container>
    </div>
  );
};

export default HomePageLayout;
