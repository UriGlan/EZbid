import React from "react";
import {
    Typography,
    CssBaseline,
    Container,
} from "@mui/material";
import VariantAppBar from "../components/menu/VariantAppBar";
import SearchItem from "../components/items/SearchItem";
import Box from "@mui/material/Box";
import Footer from "../components/Footer";
import ListItems from "../components/items/ListItems";
import ItemDialog from "../components/items/Dialog/ItemDialog";

const cardList = [
    { id: 1, name: "A cute Dog" },
    { id: 2, name: "A very cute Dog" },
    { id: 3, name: "Snoop Dog" },
    { id: 4, name: "The Smartest Dog" },
];
const Home = () => {
    return (
        <>
            <CssBaseline />
            <VariantAppBar />
            <main>
                <Box sx={{ backgroundColor: '#d7d7d7', height: 'auto', paddingTop: '80px', paddingBottom:'15%'}}>
                    <div>
                        <SearchItem />
                    </div>
                    <div>
                        <Container maxWidth="sm">
                            <Typography variant="h2" align="center" color="textPrimary" gutterBottom sx={{fontWeight:"bold"}}>
                                Listed Items
                            </Typography>
                        </Container>
                    </div>
                    <Container>
                            <ListItems items={cardList} DialogComponent={ItemDialog} />
                    </Container>
                </Box>
            </main>
            <Footer/>
        </>
    );
}

export default Home;
