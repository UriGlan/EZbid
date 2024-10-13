import PermanentDrawerLeft from "../../components/PermanentDrawerLeft";
import ListItems from "../../components/items/ListItems";
import Box from "@mui/material/Box";
import * as React from "react";
import Typography from "@mui/material/Typography";
import MyListedItemsDialog from "../../components/items/Dialog/MyListedItemsDialog";
import Button from "@mui/material/Button";
import Grid from "@mui/material/Grid";
import {useEffect, useState} from "react";
import Footer from "../../components/Footer";
import MyBidsDialog from "../../components/items/Dialog/MyBidsDialog";
import makeApiCall, {ApiMethod} from "../../Utils/ApiUtils";
import {func} from "prop-types";


const MyItems = () => {
    const [auctions, setAuctions] = React.useState([]);
    const fetchAuctions = async () => {
        try {
            const data = await makeApiCall(ApiMethod.ALL_AUCTIONS)
            if (Array.isArray(data)){
               const userdata =  await makeApiCall(ApiMethod.PROFILE)
                console.log(userdata)
                setAuctions(data.filter(auction => auction.username === userdata.username));
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
        fetchAuctions()
        const intervalId = setInterval(fetchAuctions, 2000);
        return () => clearInterval(intervalId);
    }, []);

    function handleNewItem() {

    }

    function handleDeleteItem(id) {}

    return (
        <>
            <PermanentDrawerLeft />
            <main>
                <Box
                    sx={{
                        backgroundColor: '#d7d7d7',
                        height: 'auto',
                        paddingLeft:35,
                        paddingRight:7,
                        paddingTop: '80px',
                        paddingBottom: '15%'
                }}>
                    <Typography
                        variant='h2'
                        align='center'
                        paddingTop={2}
                        fontWeight={700}
                    >
                        My Listed Items
                    </Typography>
                    <ListItems items={auctions} DialogComponent={MyListedItemsDialog}
                               handleDeleteItem={handleDeleteItem}/>
                    <Grid
                        container
                        justifyContent="center"
                        sx={{mt: 4, paddingBottom: 10, paddingTop: 2}}
                    >
                        <Button onClick={handleNewItem} variant="contained" color="primary">
                            Add new item +
                        </Button>
                    </Grid>
                    <Footer/>
                </Box>
                </main>
            </>
            )
            }

            export default MyItems;