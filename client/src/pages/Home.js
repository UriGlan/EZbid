import React, {useEffect} from "react";
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
import makeApiCall, {ApiMethod} from "../Utils/ApiUtils";

const Home = () => {
    const [auctions, setAuctions] = React.useState([]);
    const fetchAuctions = async () => {
        try {
            const data = await makeApiCall(ApiMethod.ALL_AUCTIONS)
            if (Array.isArray(data)){
                setAuctions(data);
            } else {
                console.error('Expected array, but got', data);
                setAuctions([]);
            }

        } catch (error) {
            console.error('Error fetching auctions:', error);
            setAuctions([]);
        }
    };

    useEffect(() => {
        fetchAuctions();
    }, []);

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
                            <ListItems items={auctions} DialogComponent={ItemDialog} />
                    </Container>
                </Box>
            </main>
            <Footer/>
        </>
    );
}

export default Home;
