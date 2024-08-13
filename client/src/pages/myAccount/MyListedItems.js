import React from "react";
import {
    Typography,
    CssBaseline,
    Grid,
    Container,
} from "@mui/material";
import VariantAppBar from "../../components/menu/VariantAppBar";
import SearchItem from "../../components/items/SearchItem";
import Box from "@mui/material/Box";
import CardItem from "../../components/items/CardItem";
import Footer from "../../components/Footer";
import ListItem from "@mui/material/ListItem";
import ListItems from "../../components/items/ListItems";

const cardList = [
    { id: 1, name: "A cute Dog" },
    { id: 2, name: "A very cute Dog" },
    { id: 3, name: "Snoop Dog" },
    { id: 4, name: "The Smartest Dog" },
];
const MyListedItems = () => {
    return (
        <>
            <CssBaseline />
            <VariantAppBar />
            <main>
                <Box sx={{ backgroundColor: '#d7d7d7', height: 'auto', paddingTop: '100px', paddingBottom:'15%'}}>
                    <div>
                        <Container maxWidth="sm">
                            <Typography variant="h2" align="center" color="textPrimary" gutterBottom sx={{fontWeight:"bold"}}>
                                My Listed Items
                            </Typography>
                        </Container>
                    </div>
                    <Container>
                        <ListItems items={cardList}/>
                    </Container>
                </Box>
            </main>
            <Footer/>
        </>
    );
}

export default MyListedItems;
