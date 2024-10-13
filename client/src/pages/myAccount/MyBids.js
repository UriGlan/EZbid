import PermanentDrawerLeft from "../../components/PermanentDrawerLeft";
import ListItems from "../../components/items/ListItems";
import Box from "@mui/material/Box";
import * as React from "react";
import MyBidsDialog from "../../components/items/Dialog/MyBidsDialog";
import Typography from "@mui/material/Typography";
import makeApiCall, {ApiMethod} from "../../Utils/ApiUtils";
import {useEffect} from "react";



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
    }, []);
    return (
        <>
            <PermanentDrawerLeft />
            <Box
                component="main"
                sx={{ flexGrow: 1, paddingLeft: 35, paddingRight: 7 }}
            >
                <Typography
                    variant='h2'
                    align='center'
                    paddingTop={2}
                    fontWeight={700}
                   >
                    My Bids
                </Typography>
                <ListItems items={bids} DialogComponent={MyBidsDialog} />
            </Box>
        </>
            )
}

export default MyBids;