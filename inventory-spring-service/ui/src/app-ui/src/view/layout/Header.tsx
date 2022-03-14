import * as React from "react";
import { Typography, Paper } from "@mui/material";

const Header: React.FC = (props: any) => {
  return (
    <div>
      <Paper>
        <Typography variant="h6" component="h6" align="left">
          Cars Inventory Management
        </Typography>
      </Paper>
    </div>
  );
};

export default Header;
