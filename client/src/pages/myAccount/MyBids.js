import PermanentDrawerLeft from "../../components/PermanentDrawerLeft";
import ListItems from "../../components/items/ListItems";
import Box from "@mui/material/Box";
import * as React from "react";
import MyBidsDialog from "../../components/items/Dialog/ItemDialog";
import Typography from "@mui/material/Typography";
import makeApiCall, {ApiMethod} from "../../Utils/ApiUtils";
import {useEffect} from "react";
import Footer from "../../components/Footer";


// MyBids is a page that displays all the bids that the user has placed on auctions.
const MyBids = () => {
    const [bids, setBids] = React.useState([]);
    const fetchBids = async () => {
        try {
            const data = await makeApiCall(ApiMethod.MY_BIDS)
            if (Array.isArray(data)){
                setBids(data);
            } else {
                console.error('Expected array, but got', data);
                setBids([]);
            }

        } catch (error) {
            console.error('Error fetching bids:', error);
            setBids([]);
        }
    };
    useEffect(() => {
        fetchBids();
        const intervalId = setInterval(fetchBids, 2000);
        return () => clearInterval(intervalId);
    }, []);
    return (
        <>
            <PermanentDrawerLeft />
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
                    My Bids
                </Typography>
                <ListItems
                    items={bids
                        .map(b => Object.assign(b.auction, { myBid: b.bidAmount }))
                        .sort((a, b) => {
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
                        })
                }
                    DialogComponent={MyBidsDialog}
                />
                <Footer sx={{marginTop:'10em'}}/>
            </Box>
        </>
            )
}

export default MyBids;