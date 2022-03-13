import {AddCarUI} from "../AdminUI";
import Footer from "./Footer";
import Header from "./Header";
import { Container } from "@mui/material";

const HomePageLayout = (props?: any) => {
  return (
    <div className="homepage">
      <Container sx={{ minWidth: "sm" }} maxWidth="md" fixed={true} >
        <div className="header">
          <Header />
        </div>
        <div>
          <AddCarUI name="Hello World" />
        </div>

        <div className="footer">
          <Footer />
        </div>
      </Container>
    </div>
  );
};

export default HomePageLayout;
