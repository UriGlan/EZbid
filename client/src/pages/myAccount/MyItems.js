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
import MyBidsDialog from "../../components/items/Dialog/ItemDialog";
import makeApiCall, {ApiMethod} from "../../Utils/ApiUtils";
import {func} from "prop-types";
import NewAuctionDialog from "../../components/items/Dialog/NewAuctionDialog";


// This is the MyItems page that displays the items that the user has listed for auction
const MyItems = () => {
    const [auctions, setAuctions] = React.useState([]);
    const [isDialogOpen, setIsDialogOpen] = useState(false);
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
        setIsDialogOpen(true);
    }

    function handleDeleteItem(id) {}

    return (
        <>
            <PermanentDrawerLeft />
            <main>
                <Box
                    sx={{
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
                    <ListItems items={auctions.sort((a, b) => {
                        const dateA = new Date(a.endTime);
                        const dateB = new Date(b.endTime);
                        if (dateA < new Date() && dateB < new Date()) {
                            return dateA - dateB; // Both are expired, sort by end time
                        } else if (dateA < new Date()) {
                            return 1; // a is expired, move it to the end
                        } else if (dateB < new Date()) {
                            return -1; // b is expired, move it to the end
                        } else {
                            return dateA - dateB; // Both are active, sort by end time
                        }
                    })}
                               DialogComponent={MyListedItemsDialog}
                               handleDeleteItem={handleDeleteItem}/>
                    <Grid
                        container
                        justifyContent="center"
                        sx={{mt: 4, paddingBottom: 10, paddingTop: 2}}
                    >
                        <Button onClick={handleNewItem} variant="contained" color="primary" sx={{
                            backgroundColor: '#748eeb',
                            marginTop:'4em',
                            color: 'white',
                            borderRadius: '9px',
                            '&:hover': {
                                backgroundColor: '#5a7ddb', // Change this to your desired hover color
                            }
                        }}>
                            Add new item +
                        </Button>
                        <NewAuctionDialog open={isDialogOpen} onClose={() => setIsDialogOpen(false)} onCreate={(newAuction) => {
                            // Handle the creation of a new auction
                            setIsDialogOpen(false);
                        }} />
                    </Grid>
                    <Footer/>
                </Box>
                </main>
            </>
            )
            }

            export default MyItems;